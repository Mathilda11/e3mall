package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		return nodeList;
	}

	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		//设置pojo的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1(正常),2(删除)
		contentCategory.setStatus(1);
		//默认排序就是1
		contentCategory.setSortOrder(1);
		//新添加的节点一定是叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入到数据库
		contentCategoryMapper.insert(contentCategory);
		//判断父节点的isparent属性。如果不是true改为true
		//根据parentid查询父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			//更新到数数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果，返回E3Result，包含pojo
		return E3Result.ok(contentCategory);
	}

	@Override
	public E3Result updateContentCategory(Long id, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return E3Result.ok();
	}
	/**
	 * 1、根据id删除记录。
	   2、判断父节点下是否还有子节点，如果没有需要把父节点的isparent改为false
       3、如果删除的是父节点，子节点要级联删除。

	 */
	public E3Result deleteContentCategory(Long id) {
		//找到要删除的节点
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//要删除节点的父节点
		Long parentId = contentCategory.getParentId();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//要删除的子节点对应的父节点下面的所有子节点
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//如果要删除的节点是父节点 则不允许删除
		if(contentCategory.getIsParent()){
			return E3Result.build(400, "该节点不允许删除！");
		}
		//删除的节点的父节点只有当前子节点 删除当前子节点 且修改属性值
		if(list.size()==1 && list.get(0).getId()==id){
			//删除当前子节点
			contentCategoryMapper.deleteByPrimaryKey(id);
			//找到父节点 修改为不是父节点
			TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
			parent.setIsParent(false);
			//更新到数数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//删除的节点的父节点不仅有当前子节点 还有其他子节点 直接删除当前子节点
		if(list.size()>1){
			//删除当前子节点
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
		return E3Result.ok();
	}


}
