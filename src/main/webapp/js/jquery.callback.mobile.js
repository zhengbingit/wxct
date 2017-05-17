/**
 *
 */
jQuery.extend({
    load_div:function(ajaxMobileOptions){
        //显示加载器.for jQuery Mobile 1.1.0
        $.mobile.loadingMessage = ajaxMobileOptions.loadMessage;     //显示的文字
        $.mobile.loadingMessageTextVisible = ajaxMobileOptions.loadMessageVisible; //是否显示文字
        $.mobile.loadingMessageTheme = ajaxMobileOptions.loadMessageTheme;        //加载器主题样式a-e
        $.mobile.showPageLoadingMsg();             //显示加载器
        $.mobile.shadow=true;
        $.mobile.loader.prototype.overlayTheme='a';
        $("body").append('      <div id="show_zhezhao" style="background-color: #000;filter:alpha(opacity=50);opacity:0.5;position: absolute;top:0;left:0;width:100%;height:100%;z-index:2000;"></div>')

    },
    jsonpAjaxMobile: function( ajaxMobileOptions) {
        //显示加载器.for jQuery Mobile 1.1.0
        $.load_div(ajaxMobileOptions);
        $.ajax({
            type: 'POST',
            url:  ajaxMobileOptions.url,
            dataType: "json",
            success:function(data){
                if(data.errno==0){
                    $.mobile.hidePageLoadingMsg();
                    $("#show_zhezhao").hide();
                    ajaxMobileOptions.successCallback(data);
                }else{
                    ajaxMobileOptions.errorCallback(data);
                }
            }, error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.mobile.hidePageLoadingMsg();
            }
        });
    },
    loadingClose:function(){
        $(".loading_12345").remove();
        $(".show_zhezhao").remove();

    },
    loading:function(option){
        var id="loading_12345"
        var closeFn=function(){
            $("#loading_12345").remove();
            $("#show_zhezhao").remove();
        }
        var op={
            text:"",
            html:"",
            css:"",
            width:"150px",
            height:"80px",
            btn:[],
            close:closeFn,
            id:"",
            isFallow:true,//是否有遮罩
            isLoading:false,//是否为菊花
            isClickClose:false,
            touming:true
        }
        //头像域名
        var imgurl='http://www.wecaidan.com/'
        $.extend(op,option);
        $("#loading_12345").remove();
        $("body").append('<div id="loading_12345" class="loading_12345" style="">'
            +'<img class="loading_img" src="'+$.cookie("shopimg")+'" style="" alt="" style="display: inline-block;">'
            +'<p style="font-size:0.75rem;color: #fff;"></p>'
            +'</div>');
        if(op.isLoading){
            $("#show_zhezhao").remove();
        }

        if(op.isFallow){
            $("body").append('      <div id="show_zhezhao" class="show_zhezhao" style="background-color: #484848;'+(op.touming?'filter:alpha(opacity=50);opacity:0.5;':'')+'position: absolute;top:0;left:0;width:100%;height:100%;z-index:2000;"></div>')
        }
        if(op.isClickClose){
            $(document).bind('touchend',function(e) {
                var target = $(e.target);
                var bol
                if(op.id!=undefined&&op.id!=""&&op.id!=null&&op.id!="null"){
                    bol=target.closest('#loading_12345').length == 0&&target.closest('#'+op.id).length == 0;
                }else{
                    bol=target.closest('#loading_12345').length == 0
                }

                if(bol){
                    $('#loading_12345').remove();
                    $("#show_zhezhao").remove();
                }
            });
        }
    },
    popu:function(str,width,callback){
        var w='7rem';
        if(width!=undefined){
            w=width;
        }
        $("#pop123456").remove();
        $("body").append('<div style="width:100%;position: absolute;top:40%; z-index: 10000;text-align: center;line-height: 1rem;" id="pop123456"><div  style="border-radius:5px;background-color: #000;width:'+w+';height:1rem;margin-left:auto;margin-right:auto ;font-size:0.75rem;color: #fff">'+str+'</div></div>');
        $("#pop123456").fadeOut(2000,callback)
        $(document).bind('touchend',function(e) {
            $('#pop123456').remove();
        });
    },
    inputPopu:function(btnId,returnId,innerText){
        $('.fButton_all1').unbind('touchend')
        $('.mark_all').unbind('touchend')
        $("body").append('<div class="mark_all"></div>');
        $("body").append('<div class="fInput_all">'
            +	'<div class="fDiv">'
            +	'	<textarea id="input_alltext" placeholder="'+innerText+'">'+$("#"+returnId).val()+'</textarea>'
            +	'</div>'
            +	'<div class="fButton">'
            +	'	<div class="fButton_all1">确  定</div>'
            +	'</div>'
            +	'</div>');
        $(".fInput_all").show();
        $(".mark_all").fadeIn(100,function(){
            $('.fButton_all1').bind('touchend',function(){ //确定
                var val = $('.fInput_all').eq($('.fInput_all').length-1).find('textarea').val();
                $('#'+returnId).val(val)
                if(!$.isNull(val)){
                    $('#'+returnId+"_b").html(val);
                }
                $("#input_alltext").html("")
                $(".fInput_all").fadeOut(200)
                $(".mark_all").fadeOut(200)
                $(".fInput_all").remove()
                $(".mark_all").remove()
            });
            $('.mark_all').bind('touchend',function(){
                $(".fInput_all").fadeOut(200)
                $(".mark_all").fadeOut(200)
                $(".fInput_all").remove()
                $(".mark_all").remove()
            });
            $('#input_alltext').blur(function(){
                var val = $('.fInput_all').eq($('.fInput_all').length-1).find('textarea').val();
                $('#'+returnId).val(val)
                if(!$.isNull(val)){
                    $('#'+returnId+"_b").html(val);
                }
                $(".fInput_all").fadeOut(200)
                $(".mark_all").fadeOut(200)
                $(".fInput_all").remove()
                $(".mark_all").remove()
            })
        });
        $("#input_alltext").focus()
    }
});