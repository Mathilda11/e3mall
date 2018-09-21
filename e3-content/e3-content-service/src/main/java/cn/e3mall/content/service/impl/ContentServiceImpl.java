package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.server.quorum.FollowerMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

@Service
public class ContentServiceImpl implements ContentService{

	
	@Autowired
	private TbContentMapper contentMapper;	
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	//添加内容
	@Override
	public E3Result addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		contentMapper.insert(content);
		//缓存同步 删除缓存中对应的数据 精确地删除
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}
	
	/**
	 * 查询内容列表
	 */
	@Override
	public EasyUIDateGridResult getContentList(long categoryId,Integer page, Integer rows) {
		// 设置分业信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDateGridResult result = new EasyUIDateGridResult();
		result.setRows(list);
		//去分页结果
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//总记录数
		result.setTotal(pageInfo.getTotal());
		//取分页结果
		return result;
	}
	/**
	 * 根据内容分类id查询内容列表
	 */
	public List<TbContent> getContentListByCid(long cid) {
		//查询缓存
		try {
			//如果缓存中有直接响应结果
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			if (StringUtils.isNotBlank(json)) {
				//list中每个元素的泛型.class
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有查询数据库
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//把结果添加到缓存
		try {
			//都是字符串
			jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	/**
	 * 更新内容
	 */
	@Override
	public E3Result updateContent(TbContent content) {
		//获得对应的数据库的内容
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(content.getId());
		TbContent selectByPrimaryKey = contentMapper.selectByPrimaryKey(content.getId());
		TbContent tbContent = new TbContent();
		//获取页面更新的内容数据
		tbContent.setTitle(content.getTitle());
		tbContent.setSubTitle(content.getSubTitle());
		tbContent.setTitleDesc(content.getTitleDesc());
		tbContent.setUrl(content.getUrl());
		tbContent.setContent(content.getContent());
		tbContent.setCreated(selectByPrimaryKey.getCreated());
		tbContent.setUpdated(new Date());
		tbContent.setPic(content.getPic());
		tbContent.setPic(content.getPic2());
		//执行更新操作
		contentMapper.updateByExampleSelective(tbContent, example);
		//缓存同步 删除缓存中对应的数据 精确地删除
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContent(Long[] ids) {
		for (Long id : ids) {
			contentMapper.deleteByPrimaryKey(id);
			jedisClient.hdel(CONTENT_LIST, id+"");
		}

		//缓存同步 删除缓存中对应的数据 精确地删除
		return E3Result.ok();
	}

/*	@Override
	public E3Result deteleContentById(long id) {
		contentMapper.deleteByPrimaryKey(id);
		jedisClient.hdel(CONTENT_LIST, id+"");
		//缓存同步 删除缓存中对应的数据 精确地删除
		return E3Result.ok();
	}*/
}
