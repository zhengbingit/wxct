/**
 * 货站 jQuery 的 Ajax 函数功能
 */
jQuery.extend({
	nullToStr:function(d){
		return d==undefined||d==null||d=='null'||d=='undefined'?'':d;
	},
	isArrayNull:function(d){
		return !(d!=undefined&&d!=null&&d!='null'&&d.length>0);
	},
	isNull:function(d){
		return d==undefined||d==null||d=='null'||d=='undefined'||d=='';
	},
	/**
	 * 自定义jsonpajax url 请求链接， successCallback成功回调 errorCallback失败回调
	 */
    jsonpAjax: function( url ,successCallback,errorCallback) {
    	$.ajax({
		     type: 'POST',
		     url:  url,
		     dataType: "json", 
		    success:function(data){
		    	if(data.errno==0){
		    		successCallback(data);
		    	}else{
		    		errorCallback(data);
		    	}
		    }, error: function(XMLHttpRequest, textStatus, errorThrown) {
		    	   }
		});
    },
    jsonpAjaxDa: function( url ,successCallback,errorCallback,data) {
    	$.ajax({
		     type: 'POST',
		     url:  url,
		     data:data,
		    
		    success:function(data){
		    	if(data.errno==0){
		    		successCallback(data);
		    	}else{
		    		errorCallback(data);
		    	}
		    }, error: function(XMLHttpRequest, textStatus, errorThrown) {
		    	$.loadingClose();
		    	   }
		});
    },
    jsonpAjaxDaAsync: function(url, successCallback, errorCallback, data) {
    	$.ajax({
		    type : 'POST',
		    url : url,
		    data : data,
			async : false,
		    success : function(data){
                console.info("回调成功。。。")
		    	// if(parseInt(data.errno)==0){
		    		successCallback(data);
		    	// }else{
		    	// 	errorCallback(data);
		    	// }
		    },
			error : function(XMLHttpRequest, textStatus, errorThrown){
		    	console.info("回调失败。。。")
			}
		});
    },
    eachEntity:function(entity){
    	$.each(entity,function(i,val){
			var idSelecter=$("#"+i);
			if(idSelecter.length>0){
				idSelecter.html(val);
			}
		})
    },
});