function initEnv() {
	$("body").append(DWZ.frag["dwzFrag"]);

	if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
		try {
			document.execCommand("BackgroundImageCache", false, true);
		}catch(e){}
	}
	// 清理浏览器内存,只对IE起效
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}

	$(window).resize(function(){
		initLayout();
		$(this).trigger(DWZ.eventType.resizeGrid);
	});

	var ajaxbg = $("#background,#progressBar");
	ajaxbg.hide();
	$(document).ajaxStart(function(){
		ajaxbg.show();
	}).ajaxStop(function(){
		ajaxbg.hide();
	});
	
	$("#leftside").jBar({minW:150, maxW:700});
	
	if ($.taskBar) $.taskBar.init();
	navTab.init();
	if ($.fn.switchEnv) $("#switchEnvBox").switchEnv();
	if ($.fn.navMenu) $("#navMenu").navMenu();
		
	setTimeout(function(){
		initLayout();
		initUI();
		
		// navTab styles
		var jTabsPH = $("div.tabsPageHeader");
		jTabsPH.find(".tabsLeft").hoverClass("tabsLeftHover");
		jTabsPH.find(".tabsRight").hoverClass("tabsRightHover");
		jTabsPH.find(".tabsMore").hoverClass("tabsMoreHover");
	
	}, 10);

}
function initLayout(){
	var iContentW = $(window).width() - (DWZ.ui.sbar ? $("#sidebar").width() + 10 : 34) - 5;
	var iContentH = $(window).height() - $("#header").height() - 34;

	$("#container").width(iContentW);
	$("#container .tabsPageContent").height(iContentH - 34).find("[layoutH]").layoutH();
	$("#sidebar, #sidebar_s .collapse, #splitBar, #splitBarProxy").height(iContentH - 5);
	$("#taskbar").css({top: iContentH + $("#header").height() + 5, width:$(window).width()});
}

function initUI(_box){
	var $p = $(_box || document);

	$("div.panel", $p).jPanel();

	// tables
	$("table.table", $p).jTable();
	
	// css tables
	$('table.list', $p).cssTable();

	// auto bind tabs
	$("div.tabs", $p).each(function(){
		var $this = $(this);
		var options = {};
		options.currentIndex = $this.attr("currentIndex") || 0;
		options.eventType = $this.attr("eventType") || "click";
		$this.tabs(options);
	});

	$("ul.tree", $p).jTree();
	$('div.accordion', $p).each(function(){
		var $this = $(this);
		$this.accordion({fillSpace:$this.attr("fillSpace"),alwaysOpen:true,active:0});
	});

	$(":button.checkboxCtrl, :checkbox.checkboxCtrl", $p).checkboxCtrl($p);
	
	if ($.fn.combox) $("select.combox",$p).combox();
	
	if ($.fn.xheditor) {
		$("textarea.editor", $p).each(function(){
			var $this = $(this);
			var op = {html5Upload:false, skin: 'vista',tools: $this.attr("tools") || 'full'};
			var upAttrs = [
				["upLinkUrl","upLinkExt","zip,rar,txt"],
				["upImgUrl","upImgExt","jpg,jpeg,gif,png"],
				["upFlashUrl","upFlashExt","swf"],
				["upMediaUrl","upMediaExt","avi"]
			];
			
			$(upAttrs).each(function(i){
				var urlAttr = upAttrs[i][0];
				var extAttr = upAttrs[i][1];
				
				if ($this.attr(urlAttr)) {
					op[urlAttr] = $this.attr(urlAttr);
					op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
				}
			});
			
			$this.xheditor(op);
		});
	}
	
	if ($.fn.uploadify) {
		$(":file[uploader]", $p).each(function(){
			var $this = $(this);
			var options = {
				uploader: $this.attr("uploader"),
				script: $this.attr("script"),
				buttonImg: $this.attr("buttonImg"),
				cancelImg: $this.attr("cancelImg"),
				queueID: $this.attr("fileQueue") || "fileQueue",
				fileDesc: $this.attr("fileDesc"),
				fileExt : $this.attr("fileExt"),
				folder	: $this.attr("folder"),
				fileDataName: $this.attr("name") || "file",
				auto: $this.attr("auto") || false,
				multi: true,
				onError:uploadifyError,
				onComplete: uploadifyComplete,
				onAllComplete: uploadifyAllComplete
			};
			if ($this.attr("onComplete")) {
				options.onComplete = DWZ.jsonEval($this.attr("onComplete"));
			}
			if ($this.attr("onAllComplete")) {
				options.onAllComplete = DWZ.jsonEval($this.attr("onAllComplete"));
			}
			if ($this.attr("scriptData")) {
				options.scriptData = DWZ.jsonEval($this.attr("scriptData"));
			}
			$this.uploadify(options);
		});
	}
	
	// init styles
	$("input[type=text], input[type=password], textarea", $p).addClass("textInput").focusClass("focus");

	$("input[readonly], textarea[readonly]", $p).addClass("readonly");
	$("input[disabled=true], textarea[disabled=true]", $p).addClass("disabled");

	$("input[type=text]", $p).not("div.tabs input[type=text]", $p).filter("[alt]").inputAlert();

	// Grid ToolBar
	$("div.panelBar li, div.panelBar", $p).hoverClass("hover");

	// Button
	$("div.button", $p).hoverClass("buttonHover");
	$("div.buttonActive", $p).hoverClass("buttonActiveHover");
	
	// tabsPageHeader
	$("div.tabsHeader li, div.tabsPageHeader li, div.accordionHeader, div.accordion", $p).hoverClass("hover");

	// validate form
	$("form.required-validate", $p).each(function(){
		var $form = $(this);
		$form.validate({
			onsubmit: false,
			focusInvalid: false,
			focusCleanup: true,
			errorElement: "span",
			ignore:".ignore",
			invalidHandler: function(form, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					var message = DWZ.msg("validateFormError",[errors]);
					alertMsg.error(message);
				} 
			}
		});
		
		$form.find('input[customvalid]').each(function(){
			var $input = $(this);
			$input.rules("add", {
				customvalid: $input.attr("customvalid")
			})
		});
	});

	if ($.fn.datepicker){
		$('input.date', $p).each(function(){
			var $this = $(this);
			var opts = {};
			if ($this.attr("dateFmt")) opts.pattern = $this.attr("dateFmt");
			if ($this.attr("minDate")) opts.minDate = $this.attr("minDate");
			if ($this.attr("maxDate")) opts.maxDate = $this.attr("maxDate");
			if ($this.attr("mmStep")) opts.mmStep = $this.attr("mmStep");
			if ($this.attr("ssStep")) opts.ssStep = $this.attr("ssStep");
			$this.datepicker(opts);
		});
	}

	// navTab
	$("a[target=navTab]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			navTab.openTab(tabid, url,{title:title, fresh:fresh, external:external});

			event.preventDefault();
		});
	});
	
	// dialogs
	$("a[target=dialog]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var rel = $this.attr("rel") || "_blank";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w) options.width = w;
			if (h) options.height = h;
			options.max = eval($this.attr("max") || "false");
			options.mask = eval($this.attr("mask") || "false");
			options.maxable = eval($this.attr("maxable") || "true");
			options.minable = eval($this.attr("minable") || "true");
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");
			options.drawable = eval($this.attr("drawable") || "true");
			options.close = eval($this.attr("close") || "");
			options.param = $this.attr("param") || "";

			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			$.pdialog.open(url, rel, title, options);
			
			return false;
		});
	});
	$("a[target=ajax]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var rel = $this.attr("rel");
			if (rel) {
				var $rel = $("#"+rel);
				$rel.loadUrl($this.attr("href"), {}, function(){
					$rel.find("[layoutH]").layoutH();
				});
			}

			event.preventDefault();
		});
	});
	
	$("div.pagination", $p).each(function(){
		var $this = $(this);
		$this.pagination({
			targetType:$this.attr("targetType"),
			rel:$this.attr("rel"),
			totalCount:$this.attr("totalCount"),
			numPerPage:$this.attr("numPerPage"),
			pageNumShown:$this.attr("pageNumShown"),
			currentPage:$this.attr("currentPage")
		});
	});

	if ($.fn.sortDrag) $("div.sortDrag", $p).sortDrag();

	// dwz.ajax.js
	if ($.fn.ajaxTodo) $("a[target=ajaxTodo]", $p).ajaxTodo();
	if ($.fn.dwzExport) $("a[target=dwzExport]", $p).dwzExport();

	if ($.fn.lookup) $("a[lookupGroup]", $p).lookup();
	if ($.fn.multLookup) $("[multLookup]:button", $p).multLookup();
	if ($.fn.suggest) $("input[suggestFields]", $p).suggest();
	if ($.fn.itemDetail) $("table.itemDetail", $p).itemDetail();
	if ($.fn.selectedTodo) $("a[target=selectedTodo]", $p).selectedTodo();
	if ($.fn.pagerForm) $("form[rel=pagerForm]", $p).pagerForm({parentBox:$p});
	

	 // selectOne ztree编辑时只能选择一项
   $("a[target=ztreeEditSelectOne]", $p).each(function() {
       $(this).click(function(event) {
           var $this = $(this);
           var title = $this.attr("title") || $this.text();
           var rel = $this.attr("rel") || "ids";
           var targetType = $this.attr("targetType");
           var ids = "",id="";
           var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
           // 这里要根据你使用ztree的id查找ztree对象
           var zTree = $.fn.zTree.getZTreeObj("tree"), nodes = zTree.getCheckedNodes(true), v = "";
           var dept = ""; // 位置对象
           for (var i = 0, len = nodes.length; i < len; i++) {
               v += nodes[i].name + ",";
               dept += nodes[i].id + ",";
           }
           if (v.length > 0) {
               v = v.substring(0, v.length - 1);
               dept = dept.substring(0, dept.length - 1);
           }
           ids=dept;
           if (!ids) {
               alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
               return false;
           }
          /**
			 * if (ids.indexOf(',') != -1) { alertMsg.error($this.attr("warn") ||
			 * DWZ.msg("alertSelectMsg")); return false; }
			 */
           var options = {};
           var w = $this.attr("width");
           var h = $this.attr("height");
           if (w) options.width = w;
           if (h) options.height = h;
           // options.max = eval_r($this.attr("max") || "false");
           options.mask = eval($this.attr("mask") || "false");
           options.maxable = eval($this.attr("maxable") || "true");
           options.minable = eval($this.attr("minable") || "true");
           options.fresh = eval($this.attr("fresh") || "true");
           options.resizable = eval($this.attr("resizable") || "true");
           options.drawable = eval($this.attr("drawable") || "true");
           options.close = eval($this.attr("close") || "");
           options.param = $this.attr("param") || "";

           var url = ($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
           url = encodeURI(encodeURI(url + "/" + ids+"/"+v));
           DWZ.debug(url);
           if (!url.isFinishedTm()) {
               alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
               return false;
           }
           $.pdialog.open(url, rel, title, options);

           return false;
       });
   });
   
   // 通过ajax添加 ztree
   $("a[target=ztreeAddAjaxTodo]", $p).each(function() {
	   $(this).click(function(event){
		   var $this=$(this);
		   var title=$this.attr("title")|| $this.text();
		   var rel = $this.attr("rel") || "ids";
		   var targetType = $this.attr("targetType");
		   var ids="",v="",location_id="",id="";
		   var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
		 // 这里要根据你使用ztree的id查找ztree对象
		   var zTree = $.fn.zTree.getZTreeObj("tree"), nodes = zTree.getCheckedNodes(true);
		   for(var i=0,len=nodes.length;i<len;i++){
			   if(nodes[i].pId!=null){
				   v+=nodes[i].name+",";
			   }
			   location_id+=nodes[i].id+",";
		   }
		   if (v.length > 0) {
               v = v.substring(0, v.length - 1);
               location_id = location_id.substring(0, location_id.length - 1);
           }
		   ids=location_id;
		   v=(v==""?"0":v);
		   if (!ids||!v) {
               alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
               return false;
           }
		   
           var options = {};
           var w = $this.attr("width");
           var h = $this.attr("height");
           if (w) options.width = w;
           if (h) options.height = h;
           // options.max = eval_r($this.attr("max") || "false");
           options.mask = eval($this.attr("mask") || "false");
           options.maxable = eval($this.attr("maxable") || "true");
           options.minable = eval($this.attr("minable") || "true");
           options.fresh = eval($this.attr("fresh") || "true");
           options.resizable = eval($this.attr("resizable") || "true");
           options.drawable = eval($this.attr("drawable") || "true");
           options.close = eval($this.attr("close") || "");
           options.param = $this.attr("param") || "";

           var url = ($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
           url =encodeURI( encodeURI(url + "/" + ids+"/"+v));
           DWZ.debug(url);
           if (!url.isFinishedTm()) {
               alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
               return false;
           }
           $.pdialog.open(url, rel, title, options);

           return false;
		   
	   });
   });

   $("a[target=ztreeDelAjaxTodo]", $p).each(function() {

       $(this).click(function(event) {
           var $this = $(this);
           var title = $this.attr("title") || $this.text();
           var rel = $this.attr("rel") || "ids";
           var targetType = $this.attr("targetType");
           var ids = "",v = "";
           var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
           // 这里要根据你使用ztree的id查找ztree对象
           var zTree = $.fn.zTree.getZTreeObj("tree"),nodes = zTree.getCheckedNodes(true);
           var dept = "";
           for (var i = 0, l = nodes.length; i < l; i++) {
               v += nodes[i].name + ",";
               dept += nodes[i].id + ",";
           }
           if (v.length > 0) {
               v = v.substring(0, v.length - 1);
               dept = dept.substring(0, dept.length - 1);
           }
           ids = dept;
           if (!ids) {
               alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
                    return false;
            }
           /**
			 * if (ids.indexOf(',') >3) { alertMsg.error($this.attr("warn") ||
			 * DWZ.msg("alertSelectMsg")); return false; }
			 */
           var options = {};
           var w = $this.attr("width");
           var h = $this.attr("height");
           if (w) options.width = w;
           if (h) options.height = h;
           // options.max = eval_r($this.attr("max") || "false");
           options.mask = eval($this.attr("mask") || "false");
           options.maxable = eval($this.attr("maxable") || "true");
           options.minable = eval($this.attr("minable") || "true");
           options.fresh = eval($this.attr("fresh") || "true");
           options.resizable = eval($this.attr("resizable") || "true");
           options.drawable = eval($this.attr("drawable") || "true");
           options.close = eval($this.attr("close") || "");
           options.param = $this.attr("param") || "";

           var url = ($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
           url = url + "/" + ids;
           var title = $this.attr("title");
           if (title) {
               alertMsg.confirm(title, {
                   okCall: function() {
                       ajaxTodo(url, $this.attr("callback"));
                   }
               });
           } else {
               ajaxTodo(url, $this.attr("callback"));
           }
           event.preventDefault();
           // DWZ.debug(url);
           // if (!url.isFinishedTm()) {
           // alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
           // return false;
           // }
           // //(url, rel, title, options);
           // ajaxTodo(url, $this.attr("callback"));
           // return false;
       });
   });
   // 添加明细 制作单据的时候
   $("a[target=addMxAjaxTodo]", $p).each(function() {
	   $(this).click(function(event) {
           var $this = $(this);
           var title = $this.attr("title") || $this.text();
           var rel = $this.attr("rel") || "ids",flag=0;
           var targetType = $this.attr("targetType");
           var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
           
           var options = {};
           var w = $this.attr("width");
           var h = $this.attr("height");
           if (w) options.width = w;
           if (h) options.height = h;
           // options.max = eval_r($this.attr("max") || "false");
           options.mask = eval($this.attr("mask") || "false");
           options.maxable = eval($this.attr("maxable") || "true");
           options.minable = eval($this.attr("minable") || "true");
           options.fresh = eval($this.attr("fresh") || "true");
           options.resizable = eval($this.attr("resizable") || "true");
           options.drawable = eval($this.attr("drawable") || "true");
           options.close = eval($this.attr("close") || "");
           options.param = $this.attr("param") || "";
           var flwz=$("input[name='sendLocation.id']").val();
           var url = ($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
           if(rel!=""&&rel=="addJd"){
        	   url=encodeURI(encodeURI(url+"?mids="+getMids())); //这个是页面一个方法
           }else{
        	   url=encodeURI(encodeURI(url+"?mids="+getMids()+"&wz="+flwz)); //这个是页面一个方法
           }
           // 添加一些处理方法
           $("div[class='pageHeader']").find(".required").each(function(){
				var v=$(this).val();
				if(typeof(v)=='undefined'||v==null||v==""){
					alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
					flag+=1;  //用来判断有几个是必须填写的选项是空的
				}
           });
           if(flag > 0){
				return false;
			}
	         DWZ.debug(url);
	            if (!url.isFinishedTm()) {
	            alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
	            return false;
	            }
			$.pdialog.open(url, rel, title, options);
           event.preventDefault();
       }); 
   });
	// 这里放其他第三方jQuery插件...
	// oper
	$('#itemselector').omItemSelector({
		 availableTitle : '准备选择权限',
         selectedTitle : '已选择权限',
         dataSource:'role/roleData',
         width:100,
         autoSore:true,
         onSuccess:function(data, textStatus, event){
       	  var roleIdArr = $("#roleIds").val().split(',');
       	  if(roleIdArr!="")
          $("#itemselector").omItemSelector({value:roleIdArr});
         },
         onItemSelect:function(itemDatas,event){
        	$("#roleIds").val($('#itemselector').omItemSelector('value').join(','));
         }
    });


	
}


