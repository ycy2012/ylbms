<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加菜单信息</title>
<link href="${ctx}/styles/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/ztree/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/styles/ztree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/styles/ztree/js/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var key, lastValue = "", nodeList = [];
	var tree, setting = {
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
					tree.checkNode(node, !node.checked, true, true);
					return false;
				}
			}
		}
	};
	$(document).ready(
			function() {
				$.get("${ctx}/menu/treeData?_=" + new Date().getTime(),
								function(zNodes) {
									// 初始化树结构
									tree = $.fn.zTree.init($("#tree1"), setting,
											zNodes);
									// 默认展开一级节点
									var nodes = tree.getNodesByParam("level", 0);
									for ( var i = 0; i < nodes.length; i++) {
										tree.expandNode(nodes[i], true, false,
												false);
									}
									// 默认选择节点
									var ids = "${selectIds}".split(",");
									for ( var i = 0; i < ids.length; i++) {
										var node = tree.getNodeByParam("id",ids[i]);
											try {
												tree.checkNode(node, true,true);
											} catch (e) {
											}
									}
								});
				key = $("#key");
				key.bind("focus", focusKey).bind("blur", blurKey).bind(
						"change keydown cut input propertychange", searchNode);

				//addPerm method
				$("#button").click(function(){
					var $data=getMenuIds();
					if($data!=null){
						$.ajax({
							type:"post",
							url:"${ctx}/role/addPerm",
							data:{ids:$data,roleID:${roleId}},
							dataType: "json",
							success:function(data){
								DWZ.ajaxDone(data);
								$.pdialog.closeCurrent();
							}
						});
					}//
				});
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
		nodeList = tree.getNodesByParamFuzzy(keyType, value);
		updateNodes(true);
	}
	function updateNodes(highlight) {
		for ( var i = 0, l = nodeList.length; i < l; i++) {
			nodeList[i].highlight = highlight;
			tree.updateNode(nodeList[i]);
			tree.expandNode(nodeList[i].getParentNode(), true, false, false);
		}
	}
	function search() {
		$("#search").slideToggle(200);
		$("#txt").toggle();
		$("#key").focus();
	}
	function getMenuIds() {
		var result = '', zTree = $.fn.zTree.getZTreeObj("tree1");
		var nodes = zTree.getCheckedNodes(true);
		for ( var i = 0, len = nodes.length; i < len; i++) {
			result += nodes[i].id + ',';
		}
		return result;
	}
</script>
</head>
<body class="page">
	<div class="pageContent" style="height: 230px; overflow: auto;">
		<div
			style="position: absolute; right: 8px; top: 5px; cursor: pointer;"
			onclick="search();">
			<i class="icon-search"></i><label id="txt">搜索</label>
		</div>
		<div id="search" class="control-group hide"
			style="padding: 10px 0 0 10px; margin-top: 20px;">
			<label for="key" class="control-label"
				style="float: left; padding: 5px 5px 3px;">关键字：</label> <input
				type="text" class="empty" id="key" name="key" maxlength="50"
				style="width: 150px;">
		</div>
		<div id="tree1" class="ztree" style="padding: 15px 20px;"></div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
							<button type="button" id="button">确定</button>
					</div>
				</div></li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</body>
</html>