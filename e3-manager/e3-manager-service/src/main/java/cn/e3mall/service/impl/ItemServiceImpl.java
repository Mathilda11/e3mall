package cn.e3mall.service.impl;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemDescExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamItem;
import cn.e3mall.pojo.TbItemParamItemExample;
import cn.e3mall.service.ItemService;
import cn.e3mall.mapper.TbItemParamItemMapper;
import cn.e3mall.mapper.TbItemParamMapper;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource
	private Destination topicDestination;
	@Resource
	private Destination topicDestination2;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;
	
	public TbItem getItemById(Long itemId) {
		try{
			//查询缓存
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
			if(StringUtils.isNotBlank(json)){
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return tbItem;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//缓存中没有 查询数据库
		//根据主键查询
		//TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			//把结果添加到缓存
			try{
				jedisClient.set(REDIS_ITEM_PRE+":"+itemId+":BASE",JSONUtils.toJSONString(list.get(0)));
				//设置过期时间
				jedisClient.expire(REDIS_ITEM_PRE+":"+itemId+":BASE", ITEM_CACHE_EXPIRE);
			}catch(Exception e){
				e.printStackTrace();
			}
			return list.get(0);
		}
		return null;
	}
	
	public TbItemDesc getItemDescById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
			if(StringUtils.isNotBlank(json)) {
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//把结果添加到缓存
		try {
			jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}

	/**
	 * 获得商品列表
	 */
	@Override
	public EasyUIDateGridResult getItemList(int page, int rows) {
		// 设置分业信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDateGridResult result = new EasyUIDateGridResult();
		result.setRows(list);
		//去分页结果
		//用PageInfo对结果进行包装 PageInfo包含了非常全面的分页属性
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//总记录数
		result.setTotal(pageInfo.getTotal());
		//取分页结果
		return result;
	}
	
	/**
	 * 添加商品
	 */
	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成商品id
		final long itemId = IDUtils.genItemId();
		//补全item属性
		item.setId(itemId);
		//1-正常 2-下架 3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建商品描述表对应的pojo对象
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//发送商品添加消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId+"");
				return textMessage;
			}
		});
		//返回成功
		return E3Result.ok();
	}
	
	/**
	 * 更新商品信息
	 */
	public E3Result updateItem(TbItem item, String desc) {
		final String itemId = item.getId()+"";
		//更新时间
		item.setUpdated(new Date());
		//向商品表更新数据
		itemMapper.updateByPrimaryKeySelective(item);
		//创建商品描述pojo对象
		TbItemDescExample example = new TbItemDescExample();
		cn.e3mall.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(item.getId());
		//获得要更新的商品对应的商品描述对象
		List<TbItemDesc> itemDescList = itemDescMapper.selectByExampleWithBLOBs(example);
		TbItemDesc itemDesc = itemDescList.get(0);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		//向商品描述表更新数据
		itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
		//发送商品更新消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId);
				return textMessage;
			}
		});
		//返回成功
		return E3Result.ok();
	}
	
	/**
	 * 删除商品以及索引库中的商品
	 */
	public E3Result deleteItem(Long[] ids) {
		for (Long id : ids) {
			itemMapper.deleteByPrimaryKey(id);
			//发送商品删除消息
			final String itemId = id+"";
			jmsTemplate.send(topicDestination2, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage textMessage = session.createTextMessage(itemId);
					return textMessage;
				}
			});
		}
		return E3Result.ok();
	}
	
	/**
	 * 下架商品
	 */
	public E3Result instockItem(Long[] ids) {
		for (Long id : ids) {
			TbItemExample example = new TbItemExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(id);
			TbItem item = new TbItem();
			item.setStatus((byte)0);
			itemMapper.updateByExampleSelective(item, example);
		}
		return E3Result.ok();
	}
	/**
	 * 重新上架商品
	 */
	public E3Result reshelfItem(Long[] ids) {
		for (Long id : ids) {
			TbItemExample example = new TbItemExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(id);
			TbItem item = new TbItem();
			item.setStatus((byte)1);
			itemMapper.updateByExampleSelective(item, example);
		}
		return E3Result.ok();
	}

	public E3Result queryItemDesc(Long itemId) {
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return E3Result.ok(tbItemDesc);
	}

/*	public E3Result queryItemParamItem(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		cn.e3mall.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		TbItemParamItem item = (TbItemParamItem) itemParamItemMapper.selectByExample(example);
		return E3Result.ok(item);
	}*/

	public E3Result queryItemParam(Long id) {
		TbItemParamExample example = new TbItemParamExample();
		cn.e3mall.pojo.TbItemParamExample.Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(id);
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		return E3Result.ok(list);
	}

	/**
	 * 首页搜索提示词展示
	 */
	@Override
	public List<TbItem> getAdItemList() {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		//专门设置一些关键词 item里面下架的 这样 没有同步到solr
		criteria.andStatusEqualTo((byte) 0);
		List<TbItem> list = itemMapper.selectByExample(example);
		return list;
	}

}
