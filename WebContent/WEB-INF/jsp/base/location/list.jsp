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
			chkStyle: "checkbox",
			chkboxType: { "Y": "p", "N": "s" },
			radioType: "level",
			chkDisabledInherit: true
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
							zTree = $.fn.zTree
									.init($("#tree"), setting, zNodes);
							// 默认展开一级节点
							var nodes = zTree.getNodesByParam("level", 0);
							for ( var i = 0; i < nodes.length; i++) {
								zTree.expandNode(nodes[i], true, false, false);
							}
						});
				key = $("#key");
				key.bind("focus", focusKey).bind("blur", blurKey).bind(
						"change keydown cut input propertychange", searchNode);
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
</script>
</head>
<body>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${ctx}/location/addUi" target="ztreeAddAjaxTodo" rel="ids"
					mask="true" title="添加位置信息" warn="请选择要添加的位置节点"><span>添加信息</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="${ctx}/location/editUi" visible="false" name="editlink" id="editlink" rel="ids"
					target="ztreeEditSelectOne" mask="true" warn="请选择且只能选择一个位置节点"
					title="修改位置信息"><span>修改信息</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="${ctx}/location/delete" rel="ids"
					mask="true" target="ztreeDelAjaxTodo" mask="true" title="删除位置信息"><span>删除信息</span></a></li>
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
			<input type="hidden" name="id" id="id" />
			<input type="hidden" name="ids" id="ids" />
			<input type="hidden" name="allName" id="all" />
			<div id="tree" class="ztree" style="padding: 15px 20px;"></div>
		</div>
	</div>
</body>
</html>