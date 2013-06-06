<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/styles/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<title>位置信息</title>
<script type="text/javascript">
	var key, lastValue = "", nodeList = [];
	var zTree, setting = {
		view : {
			selectedMulti : false
		},
		check : {
			enable : true,
			nocheckInherit : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "p",
				"N" : "s"
			}
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			fontCss : function(treeId, treeNode) {
				return (!!treeNode.highlight) ? {
					"font-weight" : "bold"
				} : {
					"font-weight" : "normal"
				};
			}
		},
		callback : {
			beforeClick : function(id, node) {
				if ("${checked}" == "true") {
					zTree.checkNode(node, !node.checked, true, true);
					return false;
				}
			}
		}
	};
	$(document).ready(
			function() {
				$.get("${ctx}/location/list?_=" + new Date().getTime(),
								function(zNodes) {
									// 初始化树结构
									zTree = $.fn.zTree.init($("#tree"), setting,
											zNodes);
									// 默认展开一级节点
									var nodes = zTree
											.getNodesByParam("level", 0);
									for ( var i = 0; i < nodes.length; i++) {
										zTree.expandNode(nodes[i], true, false,
												false);
									}
								});
				key = $("#key");
				key.bind("focus", focusKey).bind("blur", blurKey).bind(
						"change keydown cut input propertychange", searchNode);
				//new setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
//			setEdit();
//			$("#remove").bind("change", setEdit);
//			$("#rename").bind("change", setEdit);
//			$("#removeTitle").bind("propertychange", setEdit)
//			.bind("input", setEdit);
//			$("#renameTitle").bind("propertychange", setEdit)
//			.bind("input", setEdit);
			});
	function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	function blurKey(e) {
		if (key.get(0).value === "") {
			key.addClass("empty");
		}
		searchNode(e);
	}
	function searchNode(e) {
		// 取得输入的关键字的值
		var value = $.trim(key.get(0).value);
		// 按名字查询
		var keyType = "name";
		if (key.hasClass("empty")) {
			value = "";
		}
		// 如果和上次一次，就退出不查了。
		if (lastValue === value) {
			return;
		}
		// 保存最后一次
		lastValue = value;
		// 如果要查空字串，就退出不查了。
		if (value === "") {
			return;
		}
		updateNodes(false);
		nodeList = zTree.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	}
	function updateNodes(highlight) {
		for ( var i = 0, l = nodeList.length; i < l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
			zTree.expandNode(nodeList[i].getParentNode(), true, false, false);
		}
	}
	function search() {
		$("#search").slideToggle(200);
		$("#txt").toggle();
		$("#key").focus();
	}
	//新添加的
		var code;
		
			function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function zTreeBeforeRemove(treeId, treeNode) {
                var sNodes = treeNode.children;
                if (sNodes!=null) {
	                alert('请先删除子机构');
	                return false;
                }
                else
                {
                return true;
                }
	          
             }
        function zTreeOnRemove(event, treeId, treeNode) {
	          $.post("DeleteDept.ashx",{ids:treeNode.id},function(result){
                //alert(result);
             });
	         // alert(treeNode.tId + ", " + treeNode.name);
              }
		function zTreeOnRename(event, treeId, treeNode) {
	             alert(treeNode.tId + ", " + treeNode.name);
           }
       function zTreeBeforeRename(treeId, treeNode, newName) {
	       return newName.length > 5;
           }
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		function setEdit() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			remove = $("#remove").attr("checked"),
			rename = $("#rename").attr("checked");
			removeTitle = $.trim($("#removeTitle").value),
			renameTitle = $.trim($("#renameTitle").value);
			zTree.setting.edit.showRemoveBtn = remove;
			zTree.setting.edit.showRenameBtn = rename;
			zTree.setting.edit.removeTitle = removeTitle;
			zTree.setting.edit.renameTitle = renameTitle;
			showCode(['setting.edit.showRemoveBtn = ' + remove, 'setting.edit.showRenameBtn = ' + rename,
				'setting.edit.removeTitle = "' + removeTitle +'"', 'setting.edit.renameTitle = "' + renameTitle + '"']);
		}
</script>
</head>
<body>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/location/addUi" target="dialog"
					mask="true" title="添加角色信息"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="${ctx}/location/editUi/{sid_role}"
					target="ztreeEditSelectOne" mask="true" title="修改角色信息"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="${ctx}/location/delete"
					target="ztreeDelAjaxTodo" mask="true" title="删除角色信息"><span>删除</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<div style="margin-left: auto; margin-right: auto; width: 60%;">
			<div
				style="margin-top: 18px; cursor: pointer; float: right; width: 60%;"
				onclick="search();">
				<i class="icon-search"></i><label id="txt">搜索</label>
			</div>
			<div id="search" class="control-group hide"
				style="padding: 15px 0 0 10px; width: 80%;">
				<label for="key" class="control-label"
					style="float: left; padding: 5px 5px 3px;">关键字：</label> <input
					type="text" class="empty" id="key" name="key" maxlength="50"
					style="width: 150px;">
			</div>
			<div id="tree" class="ztree" style="padding: 15px 20px;"></div>
		</div>
	</div>
</body>
</html>