package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDateGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {
	TbItem getItemById(Long itemId);
	EasyUIDateGridResult getItemList(int page, int rows);
	E3Result addItem(TbItem item, String desc);
	TbItemDesc getItemDescById(long itemId);
	E3Result deleteItem(Long[] ids);
	E3Result instockItem(Long[] ids);
	E3Result reshelfItem(Long[] ids);
	E3Result queryItemDesc(Long itemId);
	//E3Result queryItemParamItem(Long itemId);
	E3Result queryItemParam(Long id);
	List<TbItem> getAdItemList();
	E3Result updateItem(TbItem item, String desc);
}
