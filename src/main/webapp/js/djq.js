/**
 * Created by zhengbinMac on 2017/5/4.
 */
/**
 *
 */


function addDjq(){
    var djqPhone=$.cookie("djqPhone");
    if(!$.isNull(djqPhone)){
        $("#djq_phonenumber").val(djqPhone)
    }
    $(".addDjqphone").show(0,function(){
        $("#djq_phonenumber").focus();
    });

}
function addDjqTocookie(money,pcId){
    var entity={id:1949920161107,dishesName:'代金券',price:-money,dishesNum:1,pcId:pcId};
    $.cookie('djq',JSON.stringify(entity));
    console.log($.cookie('djq'))
    $("#djq_content").html("使用"+(money*0.01).toFixed(2)+"元代金券");
    $("#endPrice").html(($("#endPrice").html()-money*0.01).toFixed(2))
    $("#djq_content").show();
    $(".addDjqphone").fadeOut(200);
}
function fanhui(){

    $(".addDjqphone").fadeOut(200,function(){$(".djqlist").hide()});
}
$(function(){
    $(".djq_console").click(function(){

        $(".addDjqphone").fadeOut(200);
    })
    $("#djq_sub").click(function(){
        var phone=$("#djq_phonenumber").val();
        if($.isNull(phone)||!checkPhone(phone)){
            $.popu("手机号有误")
        }else{
            var successDjq=function(data){
                $(".djqlist").show();
                var djqs=data.entity;
                if($.isNull(djqs)||djqs.length==0){
                    $(".djqlist").html('<div class="nodjq" style="/* display:none; */"><img src="../img/nodjq.png"><h4>暂无可用代金券</h4><div onclick="fanhui()" class="fanhui djq_console">返回</div></div>');
                }else{
                    var ul='<ul>';
                    for(var i=0;i<djqs.length;i++){
                        var em=djqs[i];
                        var couponMoney = (em.couponMoney*0.01).toFixed(2);
                        ul+='<li  onclick="addDjqTocookie('+em.couponMoney+','+em.id+')"><p class="p1"><span>￥</span><span>'+couponMoney+'</span><span>优惠券</span></p><p class="p2">仅限<span>'+$.cookie('shopName')+'</span>进店消费使用</p><p>有效期<span>'+new Date().pattern('yyyy.MM.dd')+'</span>-<span>'+new Date(em.couponEffectivetime).pattern('yyyy.MM.dd')+'</span></p></li>'
                    }
                    ul+='</ul>'
                    $(".djqlist").html(ul)
                }

                $.cookie("djqPhone",phone);
            }
            var errorDjq=function(){}

            $.jsonpAjaxDa( WEB+"/custom/coupon/getPhoneCouponByPhone/",successDjq,errorDjq,"phone="+phone+"&shopId="+$.cookie("shopId"));

        }
    })
})