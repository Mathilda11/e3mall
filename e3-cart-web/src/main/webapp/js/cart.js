var CART = {
	itemNumChange : function(){
		$(".increment").click(function(){//＋
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			var subTotal = (eval(_thisInput.attr("itemPrice")) * 10000 * eval(_thisInput.val())) / 10000;
			$(this).parent().parent().nextAll(".pSubtotal").children().html(new Number(subTotal/100).toFixed(2)).priceFormat({ //价格格式化插件
				 prefix: '¥',
				 thousandsSeparator: ',',
				 centsLimit: 2
			});
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				CART.refreshTotalPrice();
			});
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			var subTotal = (eval(_thisInput.attr("itemPrice")) * 10000 * eval(_thisInput.val())) / 10000;
			$(this).parent().parent().nextAll(".pSubtotal").children().html(new Number(subTotal/100).toFixed(2)).priceFormat({ //价格格式化插件
				 prefix: '¥',
				 thousandsSeparator: ',',
				 centsLimit: 2
			});
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				CART.refreshTotalPrice();
			});
		});
		$(".itemnum").change(function(){
			var _thisInput = $(this);
			var subTotal = (eval(_thisInput.attr("itemPrice")) * 10000 * eval(_thisInput.val())) / 10000;
			$(this).parent().parent().nextAll(".pSubtotal").children().html(new Number(subTotal/100).toFixed(2)).priceFormat({ //价格格式化插件
				 prefix: '¥',
				 thousandsSeparator: ',',
				 centsLimit: 2
			});
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				CART.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		$(".itemnum").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$("#allMoney2").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '¥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	CART.itemNumChange();
});