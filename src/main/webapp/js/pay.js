/**
 * 
 */
function uuid() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for ( var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the
	// clock_seq_hi_and_reserved
	// to 01
	s[8] = s[13] = s[18] = s[23] = "-";

	var uuid = s.join("");
	return uuid;
}

function djqEdit() {
	var success = function(data) {
		$.cookie('djq', null)
	}
	var error = function() {

	}
	var djq = $.cookie('djq')
	if (!$.isNull(djq)) {
		var obj = eval('(' + djq + ')')
		$.jsonpAjaxDa(WEB + "/custom/coupon/editPhoneCoupon/", success, error, "id=" + obj.pcId + "&couponUserd=1");
	}

}
function test(orderId) {
	var success = function(data) {

	}
	var error = function() {

	}
	$.jsonpAjaxDa(WEB + "/test.jsp", success, error, "url=" + window.location.href + "&content=" + encodeURIComponent($("html").html()) + "&orderId=" + orderId);
}

var is_parent = 0;
var order_bd;

function MathRand() {
	var Num = "";
	for ( var i = 0; i < 6; i++) {
		Num += Math.floor(Math.random() * 10);
	}
	return Num;
}
/**
 * 下单
 * 
 * @param dishes_str
 */
function addOrder() {

	var dishes_str = localData.get('caipinArray')
	var surArray = $.cookie('surArray')
	var djq = $.cookie('djq');
	// var orderId=$.cookie('orderId');
	var parentOrderId = 0;
	if (!$.isNull($.cookie('orderId'))) {
		parentOrderId = $.cookie('orderId');
	}
	/*
	 * if(parentOrder!=undefined&&parentOrder!=null&&parentOrder!='null'&&parentOrder.length>0){
	 * pObj=eval('('+parentOrder+')'); var parentTime=pObj.parentTime;
	 * console.log(new Date().getTime()+":"+parentTime+":"+ADDCAITIME) if(new
	 * Date().getTime()-parentTime>ADDCAITIME){ $.cookie('parentOrder',null);
	 * }else{ parentOrderId=pObj.parentId } }
	 */
	$.loading({
		text : "正在下单中。。。",
		id : "sub"
	})
	var success = function(data) {
		var errno = data.errno
		switch (errno) {
		case 0:

			var order = data.entity
			order_bd = order;
			var orderId = order.id
			//test(orderId)
			switch ($.cookie("tradeType")) {
			case '1':
				if (!is_weixin()) {
					/* if($.isNull($.cookie("orderId"))){ */
					if (order.isParent == 1) {
						setCookie("orderId", orderId)
						setCookie2("orderId", orderId)
					}
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				} else {
					is_parent = order.isParent
				}
				if ($.isNull(order.payMethod) && order.payMethod == 0) {
					if ($.isNull($.cookie("orderId"))) {
						setCookie("orderId", orderId)
						setCookie2("orderId", orderId)
					}
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				}
				break;
			case '2':
				if (!is_weixin()) {
					/* if($.isNull($.cookie("orderId"))){ */
					if (order.isParent == 1) {
						setCookie("orderId", orderId)
						setCookie2("orderId", orderId)
					}
					editPaiHao()
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				} else {
					is_parent = order.isParent
				}
				if ($.isNull(order.payMethod) && order.payMethod == 0) {
					if ($.isNull($.cookie("orderId"))) {
						setCookie("orderId", orderId)
						setCookie2("orderId", orderId)
					}
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				}
				break;
			case '3':
				if (!is_weixin()) {
					setCookie("orderId", orderId)
					setCookie2("orderId", orderId)
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				}
				if ($.isNull(order.payMethod) && order.payMethod == 0) {
					setCookie("orderId", orderId)
					setCookie2("orderId", orderId)
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
				}
				break;
			}

			if ($("#wecharPaytype").val() > 0) {
				yanzheng(orderId)
			} else {
				$.loading({
					html : "<span style='font-size:0.75rem'>成功下单</span>",
					width : "300px;",
					isFallow : false
				})
				window.location = "orderInfor.html?id=" + orderId + "&moshi=" + $("#moshi").val() + "&tableNo=" + $("#tableNo").val();
			}

			break;
		default:
			$.loading({
				html : "<span style='font-size:0.75rem'>下单失败！</span>",
				width : "300px;",
				isFallow : false
			})

		}
	};
	var deliveryTime = "";
	if ($.isNull($("#deliveryTime").val())) {
		var date = new Date();
		deliveryTime = $("#deliveryTime").val()
	}
	var userPhone = $.isNull(getCookie('user_info')) ? "" : getCookie('user_info');
	var data = "customerMobile=" + userPhone + "&dishes_str=" + dishes_str + "&orderDesc=" + $("#orderDesc").val() + "&invoiceTitle=" + $("#invoiceTitle").val() + "&parentId="
			+ parentOrderId + "&orderAddress=" + $("#orderAddress").val() + "&transportMobile=" + $("#transportMobile").val() + "&surArray=" + surArray + "&lxr=" + $("#lxr").val()
			+ "&deliveryTime=" + $("#deliveryTime").val() + "&djq=" + djq
	if ($.cookie("tradeType") == "2") {
		data += ("&fastFoodCode=" + MathRand());
	}

	$.jsonpAjaxDa(WEB + "/custom/order/placeOrder/", success, error, data);

}

/**
 * 支付调用方法
 * 
 * @param orderId
 */
function yanzheng(orderId, maidan) {
	$.loading();
	// 到店付判断
	if (is_gopay(orderId)) {
		return;
	} else {
		// 是否微信
		if (is_weixin()) {
//			$.cookie("openid","olexUt_qV80WO9r-hTWiRoyxqV2Y");
			var openid = $.cookie("openid");
			// 如果cookie没有openid
//			alert(openid);
			if (openid == undefined || openid == null || openid == "null" || openid == "") {
				window.location = window.wcdPaySdk.wc_auth("/html/orderCon.html");
			} else {
				getOrderbyIdToPay(openid, orderId, maidan);
			}
		} else {
			payByAli(orderId);
		}
	}
}

function is_gopay(orderId) {
	var isbaidufu = false;
	var returnentity;
	var success = function(data) {
		if (data.errno == 0) {
			var is_parent = order_bd.isParent;
			switch ($.cookie("tradeType")) {
			case '1':
				if (is_parent == 1) {
					setCookie("orderId", orderId)
					setCookie2("orderId", orderId)
				}
				djqEdit()
				localData.set('caipinArray', null)
				$.cookie('surArray', null)
				break;
			case '3':
				setCookie("orderId", orderId)
				setCookie2("orderId", orderId)
				localData.set('caipinArray', null)
				djqEdit()
				$.cookie('surArray', null)
				break;
			}

			var msg = data.msg;
			var entity = data.entity;
			returnentity = entity
			isbaidufu = true;
			// window.location='http://cq02-nuomi-bnse-ssd-rdtest10.cq02.baidu.com:8999/diancaiui/servicepay?sequence='+entity.sequence+'&&tp_merchant_id='+entity.merchant_id+'&city_id='+entity.city_id+'&total_money='+entity.total_money+'&tp_id=36&merchant_id='+entity.merchant_id
		} else {
			isbaidufu = false;
			$.popu(data.msg);
		}

	}
	var error = function() {
		// $.popu("请求失败");
		isbaidufu = false;
	}
	$.jsonpAjaxDaAsync(WEB + "/custom/order/isToPay/", success, error, "shopId=" + $.cookie("shopId") + "&orderId=" + orderId);
	if (isbaidufu) {
		var parames = new Array();
		parames.push({
			name : "sequence",
			value : returnentity.tp_params
		});
		parames.push({
			name : "tp_merchant_id",
			value : $.cookie("shopId")
		});
		parames.push({
			name : "city_id",
			value : returnentity.city_id
		});
		parames.push({
			name : "total_money",
			value : returnentity.total_money
		});
		parames.push({
			name : "tp_id",
			value : 36
		});
		parames.push({
			name : "merchant_id",
			value : returnentity.merchant_id
		});
		parames.push({
			name : "ne",
			value : 1
		});
		parames.push({
			name : "channel",
			value : "nuomi_tp_h5"
		});

		var caipin_bd = generateCaipin_bd(order_bd);

		parames.push({
			name : "dishes",
			value : JSON.stringify(caipin_bd)
		});
		// console.log(parames)
		Post('http://t10ocs.nuomi.com/diancaiui/servicepay', parames)
		// window.location='http://t10ocs.nuomi.com/diancaiui/servicepay?sequence='+returnentity.sequence+'&tp_merchant_id='+$.cookie("shopId")+'&city_id='
		// +returnentity.city_id+'&total_money='+returnentity.total_money+'&tp_id=36&merchant_id='+returnentity.merchant_id+'&ne=1&channel=nuomi_tp_h5'
	}
	return isbaidufu;
}

function generateCaipin_bd(order) {
	var dishes = new Array();
	var dishes_we = order.odList;
	for ( var i = 0; i < dishes_we.length; i++) {
		var dw = dishes_we[i];
		var d = {
			"tp_id" : "36",
			"tp_merchant_id" : $.cookie("shopId"),
			"tp_dish_id" : dw.id,
			"dish_name" : dw.dishesName,
			"dish_desc" : dw.dishesDesc,
			"sort" : "1",
			"dish_count" : dw.dishesNum,
			"dish_price" : dw.dishesPrice,
			"dish_unit" : dw.unit,
			"type_id" : dw.shopId,
			"type_name" : dw.dishesClassName
		}
		dishes.push(d);
	}
	return dishes;
}

function Post(URL, PARAMTERS) {
	// 创建form表单
	var temp_form = document.createElement("form");
	temp_form.action = URL;
	// 如需打开新窗口，form的target属性要设置为'_blank'
	temp_form.target = "_self";
	temp_form.method = "post";
	temp_form.style.display = "none";
	// 添加参数
	for ( var item in PARAMTERS) {
		var opt = document.createElement("textarea");
		opt.name = PARAMTERS[item].name;
		opt.value = PARAMTERS[item].value;
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);
	// 提交数据
	temp_form.submit();
}
// ####微信#######

/**
 * 是否微信浏览器
 */
function is_weixin() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}

/**
 * 根据id获取order 支付
 * 
 * @param openid
 * @param orderid
 */
function getOrderbyIdToPay(openid, orderid, maidan) {
	if (orderid == 'fast') {// 快餐
		var obj = $.cookie("order");
		var order = eval('(' + obj + ')');
		var wx = order.wx;
		pay(wx.out_trade_no, openid, wx.total_fees, wx.nonce_strs, orderid, maidan);
	} else {// 正餐
		pay(-1, openid, -1, -1, orderid,maidan);
	}
}
/**
 * 支付
 * 
 * @param out_trade_no
 * @param openid
 * @param total_fees
 * @param nonce_strs
 */
function pay(out_trade_no, openid, total_fees, nonce_strs, orderid, maidan) {
	// 支付
	var url = WEB + "/custom/wcdpay/wc_sign";
	var param = {
		"orderId" : orderid,
		"openId" : openid
	}
	var error = function(data) {
		$.loadingClose();
		alert("支付签名出错："+JSON.stringify(data));
	}
	var success = function(res) {
		$.loadingClose();
		if (res.err_msg == "get_brand_wcpay_request:ok") {
			if (maidan == null) {
				// 订单cookie
				switch ($.cookie("tradeType")) {
				case '1':
					if (is_parent == 1) {
						setCookie("orderId", orderid)
						setCookie2("orderId", orderid)
					}
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
					window.location = "orderInfor.html";
					break;
				case '2':
					setCookie("orderId", orderid)
					setCookie2("orderId", orderid)
					djqEdit()
					editPaiHao()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
					window.location = "save.html";
					break;
				case '3':
					setCookie("orderId", orderid)
					setCookie2("orderId", orderid)
					djqEdit()
					localData.set('caipinArray', null)
					$.cookie('surArray', null)
					window.location = "orderInfor.html";
					break;
				}
			} else {
				window.location = "successPage.html"
			}
		} else if (res.err_msg == "get_brand_wcpay_request:fail") {
		} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
		}
	};

	$.jsonpAjaxDa(url, function(data) {
		if(data.errno > 0){
			$.popu("支付出错："+data.msg);
		}else{
			window.wcdPaySdk.wc_pay(data.entity, success);
		}
	}, error, param);

}
function editPaiHao() {
	if ($.cookie("ph") != null) {
		var url = WEB + "/custom/paihao/editPaiHaoById/";
		var succ = function(data) {
		}

		var err = function() {
		}

		var da = "xuhao=" + $.cookie('ph') + "&isdc=1&mobile=" + $.cookie("tiqian_mobile")
		$.jsonpAjaxDa(url, succ, err, da);

	}
}

/**
 * ali支付
 * 
 * @param openid
 * @param orderid
 */
function payByAli(orderId) {
	$.ajax({
		type : 'POST',
		url : WEB + "/custom/wcdpay/ali_pay",
		data : {
			"orderId" : orderId
		},
		success : function(data) {
			if (data.errno == 0) {
				window.wcdPaySdk.ali_pay(data.entity);
			} else {
				$.popu('支付出现异常:' + data.msg);
			}
			$.loadingClose();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.loadingClose();
		}
	});

}
