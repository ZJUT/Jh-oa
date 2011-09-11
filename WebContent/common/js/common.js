var Constant={
	SKIN_KEY : 'skin_key',
	EXPIRE : {expires : 10000}
};

function load_skin(){
	var cache=$.cookie(Constant.SKIN_KEY) ;
	console.log('load_skin: '+cache);
	//加载皮肤信息
	if(cache!=''){
		$('#page').css("width",cache['page_now']);
		$('.bodyContainer').css("width",cache['bodyContainer_now']);
	}
}

$(function(){
	// 表格鼠标行色交替
	$('tr').mouseover(function() {
		$(this).addClass('on');
	});
	$('tr').mouseout(function() {
		$(this).removeClass('on');
	});
	// 默认表现交替换色的行
	$('tr:even').addClass('even');
	
	//全选，全不选，反选 
	$('.common-checkbox').click(function(){
		if($(this).attr('checked')){
			$(this).parent().parent().addClass('onselect');
		}
		else{
			$(this).parent().parent().removeClass('onselect');
		}
	});
	$('.selectAll').click(function(){
		$('.common-checkbox').attr('checked',true);
		$('.common-checkbox').parent().parent().addClass('onselect');
	});
	$('.selectNone').click(function(){
		$('.common-checkbox').attr('checked',false);
		$('.common-checkbox').parent().parent().removeClass('onselect');
	});
	$('.selectReverse').click(function(){
		$('.common-checkbox').each(function(){
			if($(this).attr("checked")){
				$(this).attr("checked",false);
				$(this).parent().parent().removeClass('onselect');
			}else{
				$(this).attr("checked",true);
				$(this).parent().parent().addClass('onselect');
			}
		});
	});
	
	//按钮移上、移开、按下，放开
	$('.bt').mouseover(function(){
		$(this).addClass('bt-hover');
	});
	$('.bt').mousedown(function(){
		$(this).addClass('bt-active');
	});
	$('.bt').mouseout(function(){
		$(this).removeClass('bt-hover');
		$(this).removeClass('bt-active');
	});
	$('.bt').mouseup(function(){
		$(this).removeClass('bt-hover');
		$(this).removeClass('bt-active');
	});
	
	
	/*切换到宽屏*/
	$('.switch-skin').click(function(){
		var cache=$.cookie(Constant.SKIN_KEY) || {};
		
		//容器
		cache['page_pre']=$('#page').width();
		var new_page_now="90%";
		$('#page').css("width",new_page_now);
		cache['page_now']=$('#page').width();
		
		//内容容器
		cache['bodyContainer_pre']=$('.bodyContainer').width();
		var new_bodyContainer_now=$('.bodyContainer').width()+cache['page_now']-cache['page_pre'];
		$('.bodyContainer').css("width",new_bodyContainer_now);
		cache['bodyContainer_now']=new_bodyContainer_now;
		
		//设置新值
		$.cookie(Constant.SKIN_KEY,cache);
		console.log('new_config: '+cache);
	});
	
	//DOM完成后
	load_skin();
});