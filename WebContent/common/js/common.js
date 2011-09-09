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
	
});