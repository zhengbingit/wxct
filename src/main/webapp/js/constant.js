/**
 * 
 * 
 */
var WEB = '/wxct';
var IMGURL = 'http://wap.wecaidan.com:8086/shop';
var WEBSTATIC = '/mobile';
var IMG = 'http://sms.wecaidan.cn/wecaidan/img/defaultpt'
// logo
var LOGOIMG = "logoimg"
// 配图
var PEITUIMG = "peituimg";
// 背景图
var BACKIMG = "backimg";
// home 默认logo
var DEFAULTLOGO = "";
// home 默认背景图
var DEFAULTBACKIMG = "";
// 加菜延时
var ADDCAITIME = 3600000;
// 域名
var doman = "sms.wecaidan.cn";

var USERPHONE = $.isNull($.cookie('user_info')) ? "" : $.cookie('user_info');
var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');

// 活动
var DAZHE = 1, JIANMIAN = 2, ZENGSONG = 3, MIANWAIMAI = 5, HUANGOU = 7, MIANCANHE = 15;

Math.uuid = function(len, radix) {
	var chars = CHARS, uuid = [], i;
	radix = radix || chars.length;

	if (len) {
		// Compact form
		for (i = 0; i < len; i++)
			uuid[i] = chars[0 | Math.random() * radix];
	} else {
		var r;
		uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		uuid[14] = '4';
		for (i = 0; i < 36; i++) {
			if (!uuid[i]) {
				r = 0 | Math.random() * 16;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
	}

	return uuid.join('');
};

function getRealTableNo() {
	// $.cookie("tableNo", "A2"); // 测试增加！！
	var tableId = $.cookie("tableId");
	if (!$.isNull(tableId) && tableId.length > 0) {
		if (tableId == 'wecAidan_qt' || tableId == 'wecaidan' || tableId == 'currency') {
			return "前台";
		}
        tableId = tableId.replace('(大厅)', '');
		var number = tableId.substr(tableId.length - 1, tableId.length);
		if (validateNum(number)) {
			return tableId + "号桌"
		} else {
			return tableId
		}
	}
	return '';
}

function validateNum(name) {
	var reg = new RegExp("^[0-9]*$");
	if (!reg.test(name)) {
		return false;
	}
	if (!/^[0-9]*$/.test(name)) {
		return false;
	}
	return true;
}
function getQueryStringWithDecode(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = decodeURI(window.location.search).substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	/*
	 * var y = dd.getFullYear(); var m = dd.getMonth()+1;//获取当前月份的日期 var d =
	 * dd.getDate();
	 */
	return dd.getTime();
}
function GetDateByDate(dd, AddDayCount) {
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	return dd.getTime();
}
function GetDate(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	return dd;
}
function setCookie(name, value) {
	var exp = new Date();
	var dd = new Date();
	dd.setHours(0);
	dd.setMinutes(0);
	dd.setSeconds(0);
	dd.setMilliseconds(0);
	var tomm = GetDateByDate(dd, 1);
	var timeLong = tomm - new Date().getTime();
	if (timeLong <= ADDCAITIME) {
		exp.setTime(tomm);
	} else {
		exp.setTime(exp.getTime() + ADDCAITIME);
	}

	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=" + WEB;
}
function clearCookie() {
	var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	if (keys) {
		for ( var i = keys.length; i--;)
			document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
	}
}
function setCookie2(name, value) {
	var Days = 30;
	var exp = new Date();
	var dd = new Date();
	dd.setHours(0);
	dd.setMinutes(0);
	dd.setSeconds(0);
	dd.setMilliseconds(0);
	var tomm = GetDateByDate(dd, 1);
	var timeLong = tomm - new Date().getTime();
	if (timeLong <= ADDCAITIME) {
		exp.setTime(tomm);
	} else {
		exp.setTime(exp.getTime() + ADDCAITIME);
	}
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=" + WEB + "/html";
}

function setCookie2year(name, value) {
	var Days = 365;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=" + WEB + "/html";
}
// 字符串拼接
function StringBuffer() {
	this.__strings__ = new Array();
}
StringBuffer.prototype.append = function(str) {
	this.__strings__.push(str);
	return this; // 方便链式操作
};
StringBuffer.prototype.toString = function() {
	return this.__strings__.join("");
};
function isWeixin() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.match(/MicroMessenger/i) == "micromessenger") {
		return true;
	} else {
		return false;
	}
}
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
if (navigator.onLine) {
} else {
	alert("您当前没有网络哦");
}
window.addEventListener("online", online, false);
window.addEventListener("offline", offline, false);
function online() {
	alert("网络已恢复");
	location.reload();
}
function offline() {
	alert("提示:您当前没有网络,请检查。");
}