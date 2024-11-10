$(function(){
		var fwidth=$("#focus").width();//获得外层容器的宽度
		var adv_count=$("#focus ul li").length;//得到广告的数量
		var index=0;//当前显示的广告
		var picTimer;//设置控制图片切换的定时器
		//每个广告都对应其一个span
		var btn="<div class='icon'>";
		for(var i=0;i<adv_count;i++){
			btn+="<span></span>";

		}
		//左右移动的按钮
		btn+="</div><div class='prenext pre'></div><div class='prenext next'></div>";
		//添加btn元素到focus
		$("#focus").append(btn);

		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$("#focus ul").css("width",fwidth * (adv_count));
		$("#focus .icon span").css("opacity",0.6).mouseover(function(){
			index=$("#focus .icon span").index(this);//获取移动到span所在span数组的下标
			showPics(index);
		}).eq(0).trigger("mouseover");//让程序模拟操作
		
		//显示图片函数，根据接收的index值显示相应的内容
		function showPics(index){
			var nowLeft = -index*fwidth; //根据index值计算ul元素的left值
			$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position,true:代表清空未执行完的动画队列，false代表是否直接将正在执行的动画跳转到末状态。
			$("#focus .icon span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
			$("#focus .icon span").stop(true,false).animate({"opacity":"0.6"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果设置为不透明
		}

		//上一页、下一页按钮透明度处理
		$("#focus .prenext").css("opacity","0").hover(function() {
			$(this).stop(true,false).animate({"opacity":"0.6"},300);
		},function() {
			$(this).stop(true,false).animate({"opacity":"0"},300);
		});
		
		//上一页按钮
		$("#focus .pre").click(function() {
			index -= 1;
			if(index == -1) {index = adv_count - 1;}
			showPics(index);
		});

		//下一页按钮
		$("#focus .next").click(function() {
			index += 1;
			if(index == adv_count) {index = 0;}
			showPics(index);
		});

		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$("#focus").hover(function() {
			clearInterval(picTimer);
		},function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if(index == adv_count) {index = 0;}
			},3000); //此3000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");


	});