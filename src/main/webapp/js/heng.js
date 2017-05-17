/**
 * Created by zhengbinMac on 2017/5/4.
 */
var str = '<div id="orientLayer" class="mod-orient-layer" style="display: none;">'
    +'<div class="mod-orient-layer__content"><i class="icon mod-orient-layer__icon-orient"></i>'
    +'<div class="mod-orient-layer__desc">为了更好的体验，请使用竖屏浏览</div>'
    +'</div>'
    +'</div>'

$('body').append(str);

function orientInit(){
    var orientChk = document.documentElement.clientWidth > document.documentElement.clientHeight?'landscape':'portrait';
    if(orientChk =='landscape'){
        //这里是横屏下需要执行的事件
        document.getElementById('orientLayer').style.display = 'block';
    }else{
        //这里是竖屏下需要执行的事件
        document.getElementById('orientLayer').style.display = 'none';
    }
}