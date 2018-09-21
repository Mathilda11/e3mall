package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDateGridResult getItemList(Integer page,Integer rows){
		//调用服务 插叙商品列表
		EasyUIDateGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result result = itemService.addItem(item, desc);
		return result;
	}
	@RequestMapping(value="/item/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateItem(TbItem item,String desc){
		E3Result result = itemService.updateItem(item,desc);
		return result;
	}

	@RequestMapping(value="/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteItem(Long[] ids){
		E3Result result = itemService.deleteItem(ids);
		return result;
	}
	
	@RequestMapping(value="/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public E3Result instockItem(Long[] ids){
		E3Result result = itemService.instockItem(ids);
		return result;
	}
	
	@RequestMapping(value="/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public E3Result reshelfItem(Long[] ids){
		E3Result result = itemService.reshelfItem(ids);
		return result;
	}
	
	@RequestMapping(value="/item/query/item/desc/{itemId}")
	@ResponseBody
	public E3Result queryItemDesc(@PathVariable Long itemId){
		E3Result result = itemService.queryItemDesc(itemId);
		return result;
	}
/*	@RequestMapping(value="/item/param/item/query/{itemId}")
	@ResponseBody
	public E3Result queryItemParamItem(@PathVariable Long itemId){
		E3Result result = itemService.queryItemParamItem(itemId);
		return result;
	}*/
	@RequestMapping(value="/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public E3Result queryItemParam(@PathVariable Long id){
		E3Result result = itemService.queryItemParam(id);
		return result;
	}
	
}
