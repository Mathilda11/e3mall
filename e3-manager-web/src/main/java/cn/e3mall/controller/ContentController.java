package cn.e3mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	//添加内容
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content){
		E3Result e3Result = contentService.addContent(content);
		return e3Result;
	}
	//查询内容列表
	@RequestMapping(value="/content/query/list")
	@ResponseBody
	public EasyUIDateGridResult queryContent(Long categoryId,Integer page,Integer rows){
		EasyUIDateGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	//更新内容
	@RequestMapping(value="/content/update")
	@ResponseBody
	public E3Result updateContent(TbContent content){
		E3Result result = contentService.updateContent(content);
		return result;
	}
	@RequestMapping(value="/content/delete")
	@ResponseBody
	public E3Result deleteContent(Long[] ids){
		E3Result result = contentService.deleteContent(ids);
		return result;
	}
/*	@RequestMapping(value="/content/delete")
	@ResponseBody
	public E3Result deleteContent(long id){
		E3Result result = contentService.deteleContentById(id);
		return result;
	}*/
	

}
