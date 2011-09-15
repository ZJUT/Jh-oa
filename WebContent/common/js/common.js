var Constant = {
	//皮肤配置键
	SKIN_PAGE_PRE_KEY : 'skin_page_pre_key',
	SKIN_PAGE_NOW_KEY : 'skin_page_now_key',
	SKIN_BODYCONTAINER_PRE_KEY : 'skin_bodyContainer_pre_key',
	SKIN_BODYCONTAINER_NOW_KEY : 'skin_bodyContainer_now_key',
	//登录用键
	LOGIN_UID_KEY : 'login_uid_key',
	// cookie选项
	OPTIONS : {
		expires : 31,
		path : '/'
	}
};

function goUrl(url){
	location.href=url;
}

function load_skin() {

//	var page_pre = parseInt($.cookie(Constant.SKIN_PAGE_PRE_KEY)) || '';
	var page_now = parseInt($.cookie(Constant.SKIN_PAGE_NOW_KEY)) || '';
//	var bodyContainer_pre = parseInt($.cookie(Constant.SKIN_BODYCONTAINER_PRE_KEY)) || '';
	var bodyContainer_now = parseInt($.cookie(Constant.SKIN_BODYCONTAINER_NOW_KEY)) || '';

//	console.log('LOAD SKIN PARAM:[page_pre:' + page_pre + ',page_now:'
//			+ page_now + ',bodyContainer_pre:' + bodyContainer_pre
//			+ ',bodyContainer_now:' + bodyContainer_now + ']');
	// 加载皮肤信息
	if (page_now != '')
		$('#page').css("width", page_now+'px');
	if (!isNaN(bodyContainer_now) && bodyContainer_now != '')
		$('.bodyContainer').css("width", bodyContainer_now+'px');
	
	// 根据当前状态更改换肤的点击状态
	if (page_now == '' || parseInt(page_now) == 960) {
		$('.switch-skin').html('切换到宽屏');
		$('.switch-skin').toggle(switchToBig, switchToFormal);
	} else {
		$('.switch-skin').html('回到窄屏');
		$('.switch-skin').toggle(switchToFormal, switchToBig);
	}
}

function switchToBig() {
	var page_pre = parseInt($.cookie(Constant.SKIN_PAGE_PRE_KEY)) || '';
	var page_now = parseInt($.cookie(Constant.SKIN_PAGE_NOW_KEY)) || '';
	var bodyContainer_pre =parseInt($.cookie(Constant.SKIN_BODYCONTAINER_PRE_KEY)) || '';
	var bodyContainer_now = parseInt($.cookie(Constant.SKIN_BODYCONTAINER_NOW_KEY)) || '';

//	console.log('旧值 :'+page_pre+','+page_now+','+bodyContainer_pre+','+bodyContainer_now);
	
	// 容器
	page_pre = $('#page').width()+'px';
	var new_page_now = "95%";
	$('#page').css("width", new_page_now);
	page_now = $('#page').width()+'px';

	// 内容容器
	bodyContainer_pre = $('.bodyContainer').width()+'px';
	var new_bodyContainer_now = '';
	if (page_now >= page_pre)
		new_bodyContainer_now = $('.bodyContainer').width() + parseInt(page_now)
				- parseInt(page_pre);
	else
		new_bodyContainer_now = $('.bodyContainer').width() - parseInt(page_pre)
				+ parseInt(page_now);
//	console.log('[new_bodyContainer_now]->'+new_bodyContainer_now);
	$('.bodyContainer').css("width", new_bodyContainer_now +'px');
	bodyContainer_now = new_bodyContainer_now+'px';

	
//	console.log('写入新值 :'+page_pre+','+page_now+','+bodyContainer_pre+','+bodyContainer_now);
	// 设置新值
	$.cookie(Constant.SKIN_PAGE_PRE_KEY, page_pre, Constant.OPTIONS);
	$.cookie(Constant.SKIN_PAGE_NOW_KEY, page_now, Constant.OPTIONS);
	$.cookie(Constant.SKIN_BODYCONTAINER_PRE_KEY, bodyContainer_pre,
			Constant.OPTIONS);
	$.cookie(Constant.SKIN_BODYCONTAINER_NOW_KEY, bodyContainer_now,
			Constant.OPTIONS);

	$(this).html('回到窄屏');
//	console.log('toBig -> UPDATE SKIN PARAM:[page_pre:' + page_pre + ',page_now:'
//			+ page_now + ',bodyContainer_pre:' + bodyContainer_pre
//			+ ',bodyContainer_now:' + bodyContainer_now + ']');
}
function switchToFormal() {
	var page_pre = parseInt($.cookie(Constant.SKIN_PAGE_PRE_KEY)) || '';
	var page_now = parseInt($.cookie(Constant.SKIN_PAGE_NOW_KEY)) || '';
	var bodyContainer_pre = parseInt($.cookie(Constant.SKIN_BODYCONTAINER_PRE_KEY)) || '';
	var bodyContainer_now = parseInt($.cookie(Constant.SKIN_BODYCONTAINER_NOW_KEY)) || '';

	// 容器
	page_pre = $('#page').width()+'px';
	var new_page_now = "960px";
	$('#page').css("width", new_page_now);
	page_now = new_page_now;

	// 内容容器
	bodyContainer_pre = $('.bodyContainer').width()+'px';
	var new_bodyContainer_now = '814px';
	$('.bodyContainer').css("width", new_bodyContainer_now);
	bodyContainer_now = new_bodyContainer_now;

	// 设置新值
	$.cookie(Constant.SKIN_PAGE_PRE_KEY, page_pre, Constant.OPTIONS);
	$.cookie(Constant.SKIN_PAGE_NOW_KEY, page_now, Constant.OPTIONS);
	$.cookie(Constant.SKIN_BODYCONTAINER_PRE_KEY, bodyContainer_pre,
			Constant.OPTIONS);
	$.cookie(Constant.SKIN_BODYCONTAINER_NOW_KEY, bodyContainer_now,
			Constant.OPTIONS);

	$(this).html('切换到宽屏');
//	console.log('toFormal -> UPDATE SKIN PARAM:[page_pre:' + page_pre + ',page_now:'
//			+ page_now + ',bodyContainer_pre:' + bodyContainer_pre
//			+ ',bodyContainer_now:' + bodyContainer_now + ']');
}

$(function() {
	// 表格鼠标行色交替
	$('tr').mouseover(function() {
		$(this).addClass('on');
	});
	$('tr').mouseout(function() {
		$(this).removeClass('on');
	});
	// 默认表现交替换色的行
	$('.dataTable tr:even').addClass('even');

	// 全选，全不选，反选
	$('.common-checkbox').click(function() {
		if ($(this).attr('checked')) {
			$(this).parent().parent().addClass('onselect');
		} else {
			$(this).parent().parent().removeClass('onselect');
		}
	});
	$('.selectAll').click(function() {
		$('.common-checkbox').attr('checked', true);
		$('.common-checkbox').parent().parent().addClass('onselect');
	});
	$('.selectNone').click(function() {
		$('.common-checkbox').attr('checked', false);
		$('.common-checkbox').parent().parent().removeClass('onselect');
	});
	$('.selectReverse').click(function() {
		$('.common-checkbox').each(function() {
			if ($(this).attr("checked")) {
				$(this).attr("checked", false);
				$(this).parent().parent().removeClass('onselect');
			} else {
				$(this).attr("checked", true);
				$(this).parent().parent().addClass('onselect');
			}
		});
	});

	// 按钮移上、移开、按下，放开
	$('.bt').mouseover(function() {
		$(this).addClass('bt-hover');
	});
	$('.bt').mousedown(function() {
		$(this).addClass('bt-active');
	});
	$('.bt').mouseout(function() {
		$(this).removeClass('bt-hover');
		$(this).removeClass('bt-active');
	});
	$('.bt').mouseup(function() {
		$(this).removeClass('bt-hover');
		$(this).removeClass('bt-active');
	});

	// DOM完成后
	load_skin();

	
});