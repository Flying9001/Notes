web笔记：

1. ajax中的success方法是在ajax能够成功到达后台之后自动调用的，而error方法是ajax后台无法访问到后台时候调用	的

2. ajax中的data属性是发送到服务器的数据,dataType是服务器返回的数据类型

3. 使用ajax返回的json数据实际上是json的字符串，需要将其转化之后才可以调用其中的属性,
	具体做法为 var obj = eval("("+data+")"); data为从后台获取的json格式的字符串,obj为json对象,其键/均为
	object类型
4. 使用浏览器console.log()取代alert()来进行js的调试
5. js用于和页面交互时，出现中文乱码问题,eg:alert()方法的中文乱码,解决方案：
	在引用js的时候设置编码为gb2312:
	<script charset="gb2312" type="text/javascript" src="js/xxx.js"></script>
6.IE不支持console调试，在js中包含console会提示网页有错误，并且停止执行
8.window.localtion.href在不同的浏览器执行的效果不一样(主要是IE)，IE是当前的页面路径，chrome为当前项目的路径(根路径)
	解决方案：
	(1) 在js中获取basePath：
		var location = (window.location+'').split('/');
		var basePath = location[0]+'//'+location[2]+'/'+location[3];                                                                                                 
		var url = basePath + '/js/jquery.js';
	(2)在HTML中设置basePath:
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%  String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
			request.getServerPort() + path + "/";	
	%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
			<h2>前端主页</h2>

	</body>
	</html>
9.IE在使用EL表达式取值的时候如果没有取到值，会默认赋值：1,而不是 null
10.js代码优化-输入验证
	可以将输入验证写成一个方法，满足要求返回 true,不满足返回 false,这样就不需要每一次来进行单独判断;
	eg:校验输入是否为空
	function inputCheck(inputs){
		if(inputs != "" && inputs != undefined && inputs != null){
			return true;
		}else{
			return false;
		}
	}
11.js的变量可以为对象，可以直接将对象赋值给某个变量，然后调用该变量的属性
	eg:
		var username = $("#username");
		username.focus(function(event) {
			/* Act on the event */
		});
12.js支持链式编程
	eg:
	$("#message").text("请输入账号!").show();

13.div定位position
	相对定位和绝对定位
	定位标签：position 
	包含属性：relative（相对） absolute（绝对） 
	(1).position:relative; 如果对一个元素进行相对定位，首先它将出现在它所在的位置上。然后通过设置垂直或水平位置，让这个元素"
		相对于"它的原始起点进行移动。（再一点，相对定位时，无论是否进行移动，元素仍然占据原来的空间。因此，移动元素会导致它覆盖其他框）
	(2).position:absolute; 表示绝对定位，位置将依据浏览器左上角开始计算。 绝对定位使元素脱离文档流，因此不占据空间。普通文档
		流中元素的布局就像绝对定位的元素不存在时一样。（因为绝对定位的框与文档流无关，所以它们可以覆盖页面上的其他元素并可以通过z-index来控制它层级次序。z-index的值越高，它显示的越在上层。）
	(3).父容器使用相对定位，子元素使用绝对定位后，这样子元素的位置不再相对于浏览器左上角，而是相对于父窗口左上角	
	(4).相对定位和绝对定位需要配合top、right、bottom、left使用来定位具体位置，这四个属性只有在该元素使用定位后才生效，其它
		情况下无效。另外这四个属性同时只能使用相邻的两个，不能即使用上又使用下，或即使用左，又使用右。

13. [switch]在使用switch时，case选项需要用单引号（‘’）括起来

14. [select]下拉列表联动，在设置联动列表之前需要清空原来列表中的值
	var shi=document.getElementById("add2");
		var sun=shi.children;
		for(var i=0,len=sun.length;i<len;i++){
			shi.removeChild(sun[0]);
			}

15. 二维不规则数组定义
	var m=4;
	var arr1=new Array(4);
	arr1[0]=['请选择城市'];
	arr1[1]=['请选择城市','北京'];
	arr1[2]=['请选择城市','济南','青岛','枣庄','东营'];
	arr1[3]=['请选择城市','武汉','襄阳','枣阳','随州','老河口'];

16. 网页滚动时导航栏局网页顶部
	获取那个导航DIV的top坐标，然后判断滚动条大于或等于top坐标的参数就给导航DIV添加position:fixed;top:0;样式就可以了反过来就是删除这些样式
	<script>
	jQuery(function(){
	var top=$(".top").offset().top;
	$(window).scroll(function () {
	  if ($(window).scrollTop() >= top) {
	    $(".top").attr("style", "position:fixed;top:0;left:0;z-index:10;");
	  } else {
	     $(".top").removeAttr("style");
	  }
	}).trigger("scroll");
	});
	</script>

17. 通过CSS元素选择器选择某一元素下的子元素时，其默认为数组，即使某一个元素下只有一个子元素
	eg:
		xx.html:
	<div class="body-commodityimg-up" >
		<img alt="Commodity Pic Big" src="">
	</div>
	
		xx.js:
	var imgSrc = $("#body-commodityimg-up img");
		imgSrc[0].src = "http://xxxxx";

18. 动态绘制的表格不能通过，其动态所赋 id 的 td，并不能通过元素选择器来获取到

19. 在提交表单时，默认不能够提交数据。
	eg：如果提交表单时，使用了 name 属性，而包含 name 属性的元素就会被 form 表单提交，如果其元素指向的
	是非 text 类型(如文件，图片，select组件)，则不能直接提交

20. 在 form 表单提交前修改表单元素属性
	// 此方法在 id 为 「scroll-form」的表单提交时触发
	$("form#scroll-form").submit(function(){
		// 获取 form 表单中的 input 输入框
		var key = $(this).find("input");
		// 设置/修改 input 输入框 「name」属性
		key.attr("name","commodity.cname");
		// 设置/修改 form 表单的 「action」
		$(this).attr("action","commodity/search");	
				
	}) ;

21. 在表单中有多个输入框需要 js 验证时，必须要为每一个需要验证的值创建一个变量，可以只创建一个变量，
	每次赋所需要验证的值,这样可以节约代码编写速度、减少代码修改量和减小内存开销
	eg: function validateStoreInfo(){
		var obj = $("#store-sname");
		if(obj.val() != null && obj.val() != undefined && obj.val() != ""){
			var obj = $("#store-address");
			if(obj.val() != null && obj.val() != undefined && obj.val() != ""){
				
				return true;
			}else{
				obj.focus();
				return false;
			}
		}else{
			obj.focus();
			return false;
		}

22. jQuery.alertable.js 语法
	需要注意的是这里的 alert 并不是阻碍线程的，与系统原生 alert 不同
	即：如果此 alert 后边还有语句，程序会继续往下执行，即使你没有点击确认
	
	<script src="js/jquery.alertable.js"></script>
	<script >
	$(function() {
	  // Standard alert
	  $('.alert-demo').on('click', function() {
		$.alertable.alert('Howdy!');
	  });
	  
	  // Standard confirm
	  $('.confirm-demo').on('click', function() {
		$.alertable.confirm('Are You Sure?').then(function() {
		  $.alertable.alert('You Clicked OK');
		}, function() {
		  console.log('Cancel');      
		});
	  });
	  
	  $('.prompt-demo').on('click', function() {
						$.alertable.prompt('How many?').then(function(data) {
							console.log('Prompt submitted', data);
						}, function() {
							console.log('Prompt canceled');
						});
					});

	  // Standard alert with custom show/hide functions (uses velocity.js)
	  $('.alert-vel').on('click', function() {
		$.alertable.alert('Howdy!', {
		  show: function() {
			$(this.overlay).velocity('transition.fadeIn');        
			$(this.modal).velocity('transition.flipBounceYIn');
		  },
		  hide: function() {
			$(this.overlay).velocity('transition.fadeOut');
			$(this.modal).velocity('transition.perspectiveUpOut');
		  } 
		});
	  }); 
	});
</script>

23. 获取鼠标点击的表格内容从而获取表格相关信息
	$(document).ready(function(){  
        $(".mytable td").click(function(){  
            var tdSeq = $(this).parent().find("td").index($(this)[0]);  
            var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);  
  
            alert("第" + (trSeq + 1) + "行，第" + (tdSeq + 1) + "列");  
        });  
    })  

    







