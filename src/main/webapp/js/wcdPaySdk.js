/**
 * http://wecaidan.com/
 */
(function() {
	window.wcdPaySdk = {
		env : {
			// 获取微信配置信息的url
			configLocation : WEB + '/custom/wcdpay/wc_config',
			// 回调地址主域名
			domain : 'http://wap.wecaidan.com/wecaidan',
			// 商户Appid
			aid : window.localStorage.getItem('wcd_pay_wc_appid'),
			// 用户openId
			oid : window.localStorage.getItem('wcd_pay_wc_openid_' + window.localStorage.getItem('wcd_pay_wc_appid'))
		},
		init : function(appId) {
			if (appId) {
				window.localStorage.setItem('wcd_pay_wc_appid', appId);
			}
		},
		/**
		 * 支付
		 * 
		 * @param nextUrl 微信授权完成后自动跳转的页面 跳转完成后，会追加参数 openid 同时传递值为微信用户的openId
		 */
		wc_auth : function(nextUrl) {
			if (!nextUrl) {
				return false;
			}
			var appId = 'wxd89c10d73b292eb4';
			var redrict='http://pay.wecaidan.com/wecaidanpay/pay/getFormalWxOpenId.action';
			window.localStorage.setItem('wcd_pay_wc_appid', appId);
			return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + encodeURI(redrict) + "&response_type=code"
			+ "&scope=snsapi_base" + "&state=" + (window.wcdPaySdk.env.domain + nextUrl) + "#wechat_redirect";
		},
		/**
		 * 存储商户openId，此openId根据不同的商户ID（appId）变动
		 */
		wc_hold_openid : function(openId) {
			window.localStorage.setItem('wcd_pay_wc_openid_' + window.wcdPaySdk.env.aid, openId);
		},
		/**
		 * 发起js支付流程
		 * 
		 * @param signData:[支付签名返回的数据]，callback:[支付完成后的结果处理]
		 */
		wc_pay : function(signData, callback) {
			if (!signData) {
				callback(null);
			} else {
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : signData.appid,
					"timeStamp" : signData.time,
					"nonceStr" : signData.nonce_str,
					"package" : "prepay_id=" + signData.prepay_id,
					"signType" : "MD5",
					"paySign" : signData.sign
				}, function(res) {
					callback(res);
				});
			}
		},
		ali_pay : function(strHtml) {
			if (strHtml) {
				strHtml = decodeURIComponent(strHtml);
				if (strHtml.indexOf('<html>') > -1) {
					document.write(strHtml);
				} else {
					window.location = strHtml;
				}
			}
		}
	};

})()