package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {
	//添加内容
	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);

	EasyUIDateGridResult getContentList(long categoryId, Integer page, Integer rows);
	//更新内容
	E3Result updateContent(TbContent content);
	E3Result deleteContent(Long[] ids);
	//E3Result deteleContentById(long id);
}
