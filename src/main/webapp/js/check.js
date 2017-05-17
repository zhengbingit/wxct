/**
 * Created by zhengbinMac on 2017/5/4.
 */
/**
 * 手机号验证
 */
function checkPhone(phone){
    if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
        $.popu("手机号码有误");
        return false;
    }
    return true;
}

function checkMoney(money){
    var reg=/^[1-9]{1}\d*(\.\d{1,2})?$/;
    return reg.test(money)
}