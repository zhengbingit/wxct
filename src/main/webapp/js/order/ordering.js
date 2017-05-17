/**
 * 结算价格
 */
function clearingTab(price, type) {
    console.info("jiesuanTab")
    var zongshuTab = parseInt($("#zShu").html()); // 获取购物车菜品总数，购物车上的红数字
    var allPrice = parseFloat($(".allprice").html()); // 获取购物车菜品总价，页面下方的合计价格
    // 增加按钮
    if (type == "add") {
        zongshuTab++;
        allPrice += price;
    }
    // 减少按钮
    else if (type == "mu") {
        zongshuTab--;
        allPrice -= price;
    }
    // 如果购物车，菜品总数大于0
    if (zongshuTab > 0) {
        // 购物车不为空的样式
        gouwucheShow();
    }
    // 如果购物车，菜品总数等于0
    else {
        // 将购物车降落的样式
        gouDown();
        // 将购物车隐藏的样式
        gouwucheHide();
    }
    // 更新购物车的总数和总价
    $(".allNum").html(zongshuTab);
    $(".allprice").html(allPrice.toFixed(2));
}

//cookie加菜 剪菜操作
function addDishesToCookie(id, type, number) {
    var foodArray = localData.get('foodArray');
    console.info("addDishesToCookie(), id = " + id + ", foodArray = " + foodArray);
    var li = $("#li" + id);
    var numInput = li.find(".caishu");
    var num = numInput.val();
    if (!$.isNull(number)) {
        num = number;
    }
    var unit = $("#danwei"+id).html();
    //正常价
    var yuanprice = parseFloat($("#price"+id).html());
    var entity = {
        foodId: id,
        dishesName: $("#dishName"+id).html(),
        dishesPrice: yuanprice,
        realPrice: yuanprice*num,
        dishesNum: num,
        unit : unit,
        dishesRemarks : ''
    };
    // 如果 cookie 中不存在已选的菜品
    if (foodArray == undefined || foodArray == null || foodArray.length < 1 || foodArray == 'null') {
        array = new Array();
        array.push(entity);
        localData.set('foodArray', JSON.stringify(array));
    } else {
        var array = eval('(' + foodArray + ')');
        var flag = false;
        for (var i = 0; i < array.length; i++) {
            if (array[i].foodId == id) {
                flag = true;
                //加
                switch (type) {
                    case "add" :
                        console.info("addDishesToCookie() add");
                        array[i].dishesNum = (parseInt(array[i].dishesNum) + 1);
                        break;
                    case "mu":
                        console.info("addDishesToCookie() mu");
                        var num = (parseInt(array[i].dishesNum) - 1);
                        if (num == 0) {
                            array.splice(i, 1);
                        } else {
                            array[i].dishesNum = num;
                        }
                        break;
                }
                //减
                localData.set('foodArray', JSON.stringify(array));
                break;
            }
        }
        if (!flag) {
            if (type != "gg") {
                array.push(entity);
                localData.set('foodArray', JSON.stringify(array));
            }
        }
    }
}

/**
 * 菜品数量加一
 * id : 菜品id
 * bol :
 */
function plus(id, bol, number) {
    console.info("plus(), id = " + id + ", number = " + number);
    $("#plushao" + id).hide();
    $("#plushao" + id).next().show();
    var dishNum = parseInt($("#" + id).val()) + 1; // input 结点
    if (!$.isNull(number)) {
        dishNum = number + 1; // 菜品数量加一
    }
    $("#" + id).val(dishNum); // 修改菜品数量
    $("#box"+id).val(dishNum); // 修改购物车中菜品数量
    var price = parseFloat($("#price" + id).html()); // 获取菜品总价

    clearingTab(price, "add"); // 结算总价

    //抛物线特效
    var flag = $("#five_texiao").html();
    if (bol && flag == 0) {
        // offset() 方法返回或设置匹配元素相对于文档的偏移（位置）
        var x = $("#jia" + id).offset().left;
        var y = $("#jia" + id).offset().top;
        // 生成抛物线特效
        pwxTex(x, y);
    }
    addDishesToCookie(id, "add", dishNum);
    return $("#" + id).val();
}

// 增加购物车菜品的数量
function plusCart(id, bol) {
    console.info("plusCart(), id = " + id);
    var idStr = "";
    var number=parseInt($("#box"+idStr+id).val());
    plus(id,bol,number);
}

// 减按钮（菜品数量减一）
function SUB(id, number) {
    console.info("SUB(), id = " + id + ", number = " + number);
    var idStr = "";
    var v = parseInt($("#" + id).val()) - 1;
    if (!$.isNull(number)) {
        v = number - 1;
    }
    if (v < 0) {
        return 0;
    }
    $("#" + id).val(v);
    $("#box" + idStr + id).val(v);
    var price = parseFloat($("#price" + id).html());
    clearingTab(price, "mu");
    if (v == 0) {
        shou(id);
        $("#bottom" + idStr + id).remove();
    }
    addDishesToCookie(id, "mu", number);
    return $("#" + id).val();
}
function subCart(id) {
    console.info("subCart(), id = " + id);
    var idStr = "";
    var number = parseInt($("#box" + idStr + id).val());
    SUB(id, number);
}
function openJiaJian(id) { //+-展开
    console.info("openJiaJian(), id = " + id);
    plus(id, true);
    gouwucheShow();
}
function openBro(id) { //+-展开
    console.info("openBro(), id = " + id);
    $("#plushao" + id).hide();
    $("#plushao" + id).next().show();
    $("#plushao" + id).next(".plusbox").children(".jia").click();
}
function shou(id) { //+-收起
    console.info("shou(), id = " + id);
    $("#plusbox" + id).hide();
    $("#plushao" + id).show();
}
function zhankai(id) { //+-展开
    console.info("zhankai(), id = " + id);
    $("#plusbox" + id).show();
    $("#plushao" + id).hide();
}

/**
 * 购物车样式修改函数 begin
 */
// 购物车不为空的样式
function gouwucheShow() {
    console.info("gouwucheShow()");
    $('.pic_icon').css('display', 'none');
    $('.pic_icon1').css('display', 'block');
    $('.none').css('display', 'none');
    $('.zTotal').css('display', 'block');
    $('.ok').css('background', 'red');
}
// 购物车为空的样式
function gouwucheHide() {
    console.info("gouwucheHide()");
    $('.pic_icon').css('display', 'block');
    $('.pic_icon1').css('display', 'none');
    $('.none').css('display', 'block');
    $('.zTotal').css('display', 'none');
    $('.ok').css('background', '#cccccc');
}
// 点击购物车升起
function gouUp() {
    console.info("gouUp()");
    $('.bgUp').fadeIn(300);
    var iH = $('.toUp').height();
    $('.toUp').css({
        'display': 'block',
        'bottom': -iH
    });
    $('.b_foot').hide();
    $('.toUp').animate({'bottom': 0}, 200)
    //listfood
    $("#five_texiao").html(1);
    initGwc();

}
//点击购物车降落
function gouDown() {
    console.info("gouDown()");
    $('.bgUp').fadeOut(300);
    var iH = $('.toUp').height();
    $('.toUp').css({
        'display': 'none',
        'bottom': -iH
    });
    $('.b_foot').show();
    $('.toUp').animate({'bottom': -iH}, 200, function () {
        $('.toUp').css({
            'display': 'none'
        })
    })
    $("#five_texiao").html(0);
}

// 清空购物车
function clearDishes() {
    console.info("clearDishes()");
    $("#listfood").html('');
    $("#listSur .sur_input").val(0);
    //加减菜
    $(".caishu").val(0);
    var foodArray = eval('(' + localData.get('foodArray') + ')');
    for (i = 0; i < foodArray.length; i++) {
        shou(foodArray[i].foodId);
    }
    localData.set('foodArray', null);
    $.cookie('surArray', null);
    $(".allNum").html(0);
    $(".allprice").html(0);
    gouwucheHide();
    gouDown();
}

// 初始化购物车
function initGwc() {
    console.info("initGwc()");
    var foodArray = localData.get('foodArray');
    var lihtml = '';
    var array = eval('(' + foodArray + ')');
    if (array != undefined && array != null && array.length > 0 && array != "null") {
        for (var i = 0; i < array.length; i++) {
            var val = array[i];
            var dshtml = "";
            var idStr = "";
            var li = '<li id="bottom' + idStr + val.foodId + '" class="clearfix">'
                + dshtml
                + '<span class="caititle">' + val.dishesName + '</span>'
                + '<div class="pbox">'
                + '	<div onclick="subCart(' + val.foodId + ',' + val.dishesSpecif + ')" class="upjian">'
                + '		<i></i>'
                + '	</div>'
                + '	<input class="input_txt" id="box' + idStr + val.foodId + '" type="number" value="' + val.dishesNum + '" readonly="readonly">'
                + '	<div  ontouchend="plusCart(' + val.foodId + ',true,' + val.dishesSpecif + ')"  class="upjia">'
                + '		<i></i>'
                + '	</div>'
                + '</div>'
                + '<span class="alert-money"><i>￥</i><b>' + val.dishesPrice + '</b></span>'
                + '</li>';
            lihtml += li;
        }
    }
    $("#listfood").html(lihtml);
}
/**
 * 购物车样式修改函数 end
 */

// 抛物线特效
function pwxTex(x, y) {
    // 获得购物车图标的偏移量
    var offset = $('.pic_icon1').offset();
    // document ： 文档对象
    var div = document.createElement('div');
    div.className = 'pao';
    div.style.cssText = 'transform: translate3d(0, 0, 0);' +
                        'width: 0.75rem;' +
                        'height: 0.75rem;' +
                        'border-radius: 50%;' +
                        'background: red; ' +
                        'position: fixed;' +
                        'z-index: 99999999;' +
                        'top:'+x+'px;left:'+y+'px';
    // 将生成的 div 写入 body 标签下
    $('body').append(div);
    // 获得生成的抛物线效果对象
    var flyer = $('.pao');
    flyer.fly({
        start: {
            left: x - 10,
            top: y - 30
        },
        end: {
            left: (offset.left + $('.pic_icon').width() / 2), //结束位置
            top: (offset.top + $('.pic_icon').height() / 1)
        },
        speed: 3, // 越大越快，默认1.2
        onEnd: function () { // 结束回调
            $('.pic_icon1').css({'transform': 'scale(1)'}, 100);
            this.destory(); // 销毁这个对象
        }
    });
}

// 下一步 、 选好了，进入下单页面
function next() {
    console.info("next()");
    var foodArray = localData.get('foodArray');
    if (foodArray == undefined || foodArray == null || foodArray == '' || foodArray == '[]' || foodArray == 'null') {
        $.popu("请选择菜品");
        return;
    }
    if ($.isNull($.cookie("orderId")) && $("#fj_flag") && $("#fj_flag").val() == 1 && $("#jc_num") && $("#jc_num").val() == 0) {
        $(".bgUp2").fadeIn(200);
        $(".toUp2").slideDown(100);
        gouDown();
        return;
    }
    window.location = ('orderCon.html');
}

//截取名称
function cutDishesName(name) {
    console.info("cutDishesName(), name = " + name);
    if ($.isNull(name)) {
        return "";
    }
    var maxwidth = 11;
    if (name.length >= maxwidth) {
        name = name.substring(0, maxwidth);
        return name + '...';
    }
    return name;
}

//初始化从cookie中取出的菜品
function initSelectedDishesFromCookie() {
    console.info("initSelectedDishesFromCookie()");
    var foodArray = localData.get('foodArray');

    if (foodArray != undefined && foodArray != null && foodArray.length > 0 && foodArray != "null") {
        var dishesarray = eval('(' + foodArray + ')');
        if (dishesarray.length > 0) {
            var sum = 0;
            var allprice = 0;
            for (var i = 0; i < dishesarray.length; i++) {
                var dishes = dishesarray[i];
                var price = dishes.dishesPrice;
                allprice += (price * dishes.dishesNum);
                sum += parseInt(dishes.dishesNum);
                $("#plushao" + dishes.foodId).hide();
                $("#plushao" + dishes.foodId).next().show();
                $("#" + dishes.foodId).val(dishes.dishesNum);
                if (dishes.dishesSpecif != undefined) {
                    $("#dishesSpecificationName" + dishes.foodId).val(dishes.dishesSpecif);
                }
            }
            $(".allNum").html(sum);
            $(".allprice").html(allprice.toFixed(2));
            // 初始化购物车 内容
            initGwc();
            // 初始化购物车 样式
            gouwucheShow();
        }
    }
}

// 菜品初始化
function initDishesClass() {
    console.info("localData, tableId = " + localData.get('tableId'));
    // 以下内容是，回调成功后，执行的内容
    var successCallback = function (data) {
        // 菜品信息（菜品分类：[菜品1, 菜品2]）
        var entity = data;
        // 菜品分类的个数
        var length = entity.length;
        var liHtml = "";
        var dishUL = '';
        $("#dishUL").html("");
        $("#diffCai").html("");
        if (length > 0) {
            // 循环的所有菜品分类
            for (var j = 0; j < length; j++) {
                // 某个分类的所有信息
                var objClass = entity[j];
                // 某个分类的所有菜品
                var dishList = objClass.foodList;
                // 某个分类的菜品个数
                var tableLength = 0;
                if (dishList != null) {
                    tableLength = dishList.length;
                }
                // 添加左侧的导航
                if (tableLength != 0) {
                    //左侧导航
                    liHtml += '<li class="unactive" data="' + objClass.id + '" id="li' + objClass.id + '" >' + objClass.name + '(<a class="show_all">' + tableLength + '</a>)<i class="show_all_hide">' + tableLength + '</i></li>';
                }
                // 循环所有的该分类菜品
                if (dishList != null && dishList.length > 0) {
                    for (var i = 0; i < dishList.length; i++) {
                        var dishObj = dishList[i];
                        //菜品详情start
                        imgurl = '../img/index/order/niurou.png'; // 延迟加载图片
                        $("#swiper").append('<div class="swiper-slide">'
                            + '	<h4 class="h4">' + dishObj.name + '<span>68</span>元/个</h4>'
                            + '	<div class="content">'
                            + '		<p>' + $.nullToStr(dishObj.detail) + '</p>'
                            + '	</div>'
                            + ' </div>');
                        //菜品详情end
                        //普通菜价格
                        var price;
                        // 价格后加 .00（如(1.1).toFixed()，等于 1.10）
                        price = (dishObj.price).toFixed(2);
                        var imgurl = "", imgurl2 = '../img/index/order/niurou.png';
                        var btnHtml = '<img id="plushao' + dishObj.id + '" onClick="plus(' + dishObj.id + ',true)"  class="plushao" style="display:block;" src="../img/index/order/pulsCircle.png" alt="">';
                        if (dishObj.small_img != null) {
                            imgurl = dishObj.small_img;
                        }
                        if (dishObj.big_img != null) {
                            imgurl2 = dishObj.big_img;
                        }
                        var objDishesName = cutDishesName(dishObj.name);
                        var behindHtml = "";
                        // 已估清（已售完）
                        if (dishObj.off_stock == 1) {
                            behindHtml = '<img id="guqing' + dishObj.id + '"  class="guqing plushao" style="display:block;" onclick="guqing()" src="../img/index/order/guqing.png" alt="">';
                        } else {
                            behindHtml = (btnHtml
                            + '	<div id="plusbox' + dishObj.id + '"  class="plusbox clearfix" style="display:none;">'
                            + '	<div   ontouchend="SUB(' + dishObj.id + ')"  id="jian' + dishObj.id + '" class="jian"><i></i></div>'
                            + '	<input ontouchstart="numShow(' + dishObj.id + ')" readonly="readonly" id="' + dishObj.id + '" type="number" value="0" class="input caishu">'
                            + '	<div id="jia' + dishObj.id + '"  ontouchend="openJiaJian(' + dishObj.id + ')"  class="jia">'
                            + '		<i></i>'
                            + '	</div>'
                            + '</div>');
                        }
                        // 确定菜品的单位，默认为 /份
                        var uuit = dishObj.unit;
                        if ($.isNull(uuit)) {
                            uuit = "/份";
                        }
                        // 拼装出一个完整的菜品 html
                        dishUL += '	<li  id="li' + dishObj.id + '" data="' + objClass.id + '" class="ab1 pub li' + objClass.id + '"><input type="hidden" class="search_key" value="' + dishObj.name + '_' + dishObj.spell + '" />'
                            + '		<p class="food_infor" style="color:#000;height:1rem;line-height:1rem;">'
                            + '		<s id="dishName' + dishObj.id + '" style="height:1rem;line-height:1rem;width:4rem;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">' + objDishesName + '</s>&nbsp;<span>￥</span><span  id="price' + dishObj.id + '" class="singlePrice" style="font-size:0.7rem;color:#FF1717">' + price + '</span><s class="danwei" id="danwei' + dishObj.id + '">' + uuit + '</s>'
                            + '		</p>'
                            + '		<div class="food_des">'
                            + '		<div class="food_img" style="background:#ededed">'
                            + '		<a id="swip_a" href="' + imgurl2 + '" ><img data-id="' + dishObj.id + '" data-original="' + imgurl + '"  alt="' + objDishesName + "(￥" + price + ')"></a></div>'
                            + '		<div class="food_img" style="border:0">'
                            + '		<span >已售<strong>' + (dishObj.sell_num == null ? 0 : dishObj.sell_num) + '</strong>份</span>'
                            + '		</div>'
                            + '		<div style="width:100%;height:0.001rem;clear:both"></div>'
                            + '	</div>'
                            + '		<b class="sport"></b>'
                            + behindHtml
                            + '<p class="food_note">' + $.nullToStr(dishObj.detail) + '</p>'
                            + '</li>'
                    }
                }
            }
        }
        // 完全拼装好后（菜品、分类等html源码）
        $("#main").show();
        // 分类和分类对应的菜品一起添加
        $("#dishUL").html(dishUL);
        $("#diffCai").html(liHtml);
        // 取出cookie菜品（之前选好的菜品）
        initSelectedDishesFromCookie();
        $("#right_wrap").trigger('scroll');
        // 菜品详情框
        (function (window, $, PhotoSwipe) {
            var options = {
                enableMouseWheel: false,
                enableKeyboard: false,
                captionAndToolbarAutoHideDelay: 0,
                slideshowDelay: 1500,
                getToolbar: function () {
                    return "<div class='lfjtdiv ps-toolbar-previous'><img src='http://www.wecaidan.com/systemimg/Jleft.png'  class='fimg'/></div><div class='cenjtdiv'><div class='roudOdDiv'><div class='wjiandiv' id='DownDishes'><img src='http://www.wecaidan.com/Images/Writereduce.png' id='DownDishesImg'  class='jianIcon'/></div><div id='Num_Text'>0</div><div class='addBuyDiv' id='buyDishes'><img src='http://www.wecaidan.com/Images/Wirteadd.png' id='buyDishesImg'  class='jiaIcon'></div></div></div><div class='rijtdiv ps-toolbar-next'><img src='http://www.wecaidan.com/systemimg/Jright.png' class='fimg'/></div>";
                }
            };
            rightWrapScroll.refresh();
            //菜单详情
            var instance = $("#dishUL #swip_a").photoSwipe(options);
            instance.addEventHandler(PhotoSwipe.EventTypes.onDisplayImage, function (e) {
                var obj = $(instance.getCurrentImage().refObj.firstElementChild);
                var id = obj.attr("data-id");
                var dishesNum = $("#" + id).val();
                var price = $("#price" + id).html();
                if (dishesNum) {
                    $("#Num_Text").html(dishesNum)
                } else {
                    $("#Num_Text").html(0)
                }
            });
            instance.addEventHandler(PhotoSwipe.EventTypes.onToolbarTap, function (e) {
                var action = e.tapTarget.id;
                var obj = $(instance.getCurrentImage().refObj.firstElementChild);
                var id = obj.attr("data-id");
                switch (action) {
                    case "buyDishes":
                    case "buyDishesImg":
                        plus(id)
                        $("#Num_Text").html(parseInt($("#Num_Text").html()) + 1)
                        break;
                    case "DownDishes":
                    case "DownDishesImg":
                        SUB(id)
                        if (parseInt($("#Num_Text").html()) > 0) {
                            $("#Num_Text").html(parseInt($("#Num_Text").html()) - 1)
                        }
                        break;
                }
            });

        }(window, window.jQuery, window.Code.PhotoSwipe));
        $("#diffCai li").eq(0).click();
        //跳转顶部
        leftWrapScroll.scrollTo(0, 0, 100, IScroll.utils.ease.elastic);
        rightWrapScroll.scrollTo(0, 0, 100, IScroll.utils.ease.elastic);
    };
    var errorCallback = function (data) {
    };
    var tableId = localData.get('tableId');
    var url = "../home";
    // POST 请求入参
    var data = 'tableId='+tableId;
    // 执行回调函数
    $.jsonpAjaxDaAsync(url, successCallback, errorCallback, data);
}

var leftWrapScroll; // 左 菜品分类
var rightWrapScroll;// 右 所有菜品
function loaded() {
    // 左边菜单
    leftWrapScroll = new IScroll('#left_wrap', {
            mouseWheel: true,
            tap: true,
            click: true,
            checkDOMChanges: true
        } );
    // 右边菜单
    rightWrapScroll = new IScroll('#right_wrap', {
        mouseWheel: true,
        tap: true,
        click: true,
        bounce: false,
        checkDOMChanges: true,
        hScroll: true
    } );
}

//估清
function guqing() {
    $('.guq_mark').fadeIn(200);
}

// 页面初始函数
$(function () {
    console.info("页面入口，开始初始化。。。");
    initSelectedDishesFromCookie();
    // 加载菜品分类、所有菜品
    loaded();
    rightWrapScroll.on('scrollStart', function () {
        var da_li = $("#dishUL li");
        var dataid = '';
        for (var d_i = 0; d_i < da_li.length; d_i++) {
            var top = da_li.eq(d_i).offset().top;
            if (top > 0) {
                dataid = da_li.eq(d_i).attr('data')
                break;
            }
        }
        $("#diffCai li[data=" + dataid + "]").addClass("active");
        $("#diffCai li[data=" + dataid + "]").removeClass("unactive");
        $("#diffCai li[data=" + dataid + "]").siblings().removeClass("active");
        $("#diffCai li[data=" + dataid + "]").siblings().addClass("unactive");
        leftWrapScroll.scrollToElement(document.querySelector('#li' + dataid), 200, null, null, IScroll.utils.ease.circular);
    });
    rightWrapScroll.on('scrollEnd', function () {
        $("#right_wrap").trigger('scroll');
    });
    $('#right_wrap').trigger('scroll');

    $('#head').nextAll().on('touchend', function () {
        $('input').blur();
    });
    // 搜索功能
    $("#tableName").bind('input propertychange', function () {
        var tableName = $("#tableName").val();
        tableName = tableName.toLowerCase();
        var limenu = $("#diffCai li");
        for (var j = 0; j < limenu.length; j++) {
            var sum = 0;
            var limenuid = limenu.eq(j).attr("id");
            var li = $("#dishUL ." + limenuid);
            for (var i = 0; i < li.length; i++) {
                var search_key = li.eq(i).find(".search_key").val();
                var keys = search_key.split('_');
                var pipeiFlag = false;
                var reg = /^[A-Za-z]+$/;
                if (reg.test(keys[ki])) {
                    for (var ki = 0; ki < keys.length; ki++) {
                        if (tableName.length <= keys[ki].length) {
                            if (keys[ki].substring(0, tableName.length).toLowerCase() == tableName.toLowerCase()) {
                                pipeiFlag = true;
                            }
                        }
                    }
                } else {
                    for (var ki = 0; ki < keys.length; ki++) {
                        if (search_key.indexOf(tableName) > -1) {
                            pipeiFlag = true;
                        }
                    }
                }
                if (!pipeiFlag) {
                    li.eq(i).hide();

                } else {
                    li.eq(i).show();
                    sum++;
                }
            }
            limenu.eq(j).find(".show_all").html(sum)
            if (sum == 0) {
                limenu.eq(j).hide();
            } else {
                limenu.eq(j).show();
            }
        }
        leftWrapScroll.refresh();
        rightWrapScroll.refresh();
        //跳转顶部
        leftWrapScroll.scrollTo(0, 0, 100, IScroll.utils.ease.elastic);
        rightWrapScroll.scrollTo(0, 0, 100, IScroll.utils.ease.elastic);
    });

    //没菜和估清
    var iH = ($(window).height() - $('.timeDur').innerHeight()) / 2;
    var iW = ($(window).width() - $('.timeDur').innerWidth()) / 2;
    $('.timeDur').css({'left': iW, 'top': iH});
    $('.guqing').css({'left': iW, 'top': iH});

    //导航，左侧的菜品分类
    // on() 方法在被选元素及子元素上添加一个或多个事件处理程序。
    $('#diffCai').on('click', 'li', function () {
        var index = $(this).attr('data');
        $('#diffCai li').attr('class', 'unactive');
        $(this).attr('class', 'active');
        leftWrapScroll.refresh();
        rightWrapScroll.scrollToElement(document.querySelector('.li' + index), 200, null, null, IScroll.utils.ease.circular);
    });//左边tab切换右边滑屏效果

    $('.bgUp').click(function () {
        gouDown();
    });
    // 菜品初始化，Ajax 访问服务器
    initDishesClass();
    // 菜品图片，延迟加载效果
    $("img").lazyload({
        placeholder: "../img/dishLoad.png",
        threshold: 200,
        effect: 'fadeIn',
        container: $('#right_wrap'),
        failurelimit: 50,
        event: "scroll"
    });
    //自定义键盘
    var input2 = document.getElementById('cai_num');
    new KeyBoard(input2, {type_length: 2});
    $("#cai_num").bind("click", function () {
        $("#divid").slideDown(400, function () {
            $("#paydiv").hide()
        });
    })
});
