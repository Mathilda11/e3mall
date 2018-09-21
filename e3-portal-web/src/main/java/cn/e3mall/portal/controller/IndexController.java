package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
/**
 * 商城首页
 * @author 54060
 *
 */
@Controller
public class IndexController {
	//轮播图的category_id
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		//首页轮播图展示
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		model.addAttribute("ad1List",ad1List);
		//搜索框下的推荐商品
		List<TbItem> itemlist = itemService.getAdItemList();
		model.addAttribute("adItemList",itemlist);
		return "index";
	}
}
