package cn.e3mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 商品搜索Controller
 * @author 54060
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;
@Controller
public class SearchController {
	//每页显示的记录数
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String searchItemList(String keyword, @RequestParam(defaultValue="1")Integer page, Model model) throws Exception{
		keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		//查询商品列表
		SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		//把结果传递给页面 需要model相当于request
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages",searchResult.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("recourdCount", searchResult.getRecourdCount());
		model.addAttribute("itemList", searchResult.getItemList());
		//异常测试
		//int a = 1/0;
		return "search";
	}
}