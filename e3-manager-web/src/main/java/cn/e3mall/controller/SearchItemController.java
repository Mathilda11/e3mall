package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

/**
 * 将商品数据一键导入索引库
 * @author 54060
 *
 */
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;
	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemList(){
		E3Result e3Result = searchItemService.importAllItems();
		return e3Result;
	}
}