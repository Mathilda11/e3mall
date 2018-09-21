<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">
    </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		//初始化tree请求的url 异步加载
		url : '/content/category/list',
		animate: true,
		method : "GET",
		//在右键点击节点的时候触发
		onContextMenu: function(e,node){
            e.preventDefault();
            //查找节点
            $(this).tree('select',node.target);
            // 显示快捷菜单
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点 有parentId
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		// 更新节点
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
			/* 追加若干子节点到一个父节点，param参数有2个属性：
			parent：DOM对象，将要被追加子节点的父节点，如果未指定，子节点将被追加至根节点。
data：数组，节点数据。 */
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id
            }]
        }); 
		//查找指定节点并返回节点对象
		var _node = tree.tree('find',0);
		//开始编辑一个节点
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		//开始编辑一个节点
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{id:node.id},function(data){
	       			if(data.status == 200){
						tree.tree("remove",node.target);
        			}else{
        				$.messager.alert('提示','删除"'+node.text+'"分类失败!');
        			}
				});	
			}
		});
	}
}
</script>