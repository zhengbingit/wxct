<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no" />
    <title>微信餐厅</title>
    <script type="text/javascript" src="./js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="./js/iscroll-lite.js"></script>
    <script type="text/javascript" src="./js/jquery.callback.extend.js"></script>
    <script type="text/javascript" src="./js/jquery.callback.mobile.js"></script>
    <script type="text/javascript" src="./js/jquery.cookie.js"></script>
    <script type="text/javascript" src="./js/constant.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/saveData.js"></script>
    <script type="text/javascript" charset="utf-8">
        var iWidth = document.documentElement.clientWidth;
        document.getElementsByTagName('html')[0].style.fontSize = iWidth / 16 + 'px';

        // 跳转
        $(function(){
            // 桌台的主键id
            var tableId = $("#tableId").val();
            // 桌台名，大厅1号桌
            var tableName = $('#tableName').val();
            localData.set('tableId', tableId);
            localData.set('tableName', tableName);
            // 点餐按钮跳转至菜单页面
            $("#diancan").bind('click',function(){
                window.location="./html/ordering.html";
            });
        })
    </script>
    <link rel="stylesheet" href="./css/css_reset.css" />
    <link rel="stylesheet" href="./css/index.css" />
    <style>
        /* 背景图 动画效果 */
        .home_bg img{
            width:16rem;
            height:12rem;
            -webkit-transform: scale(1);
            transform: scale(1);
            -webkit-animation:myAn 1.5s;
            animation: myAn 1.5s;
        }
        @-webkit-keyframes myAn{
            0%{
                -webkit-transform: scale(1.5);
                transform: scale(1.5);
            }
            100%{
                -webkit-transform: scale(1);
                transform: scale(1);
            }
        }
        /* 创建动画 */
        @keyframes myAn{
            /* 初始时图片大小 */
            0%{
                transform: scale(1.5);
            }
            /* 结束时图片大小 */
            100%{
                transform: scale(1);
            }
        }
    </style>
</head>
<body class="home_body" style="background:#f5f5f5;">
<input type="hidden" id="moshi" />
<input type="hidden" id="tableNo" />
<article class="home">
    <div class="home_bg home_two">

        <a class="home_bg">
            <img id="backimg" src="./img/home/openHeadShow.png" alt="" />
        </a>
    </div>
    <div class="home_infor">
        <div class="home_icon">
            <div class="circle1">
                <a>
                    <%--<img alt="" id="logo" src="./img/home/defaultlogo.png">--%>
                    <img alt="" id="logo" src="./img/log.png">
                </a></div>
            <div class="circle2"></div>
        </div>
        <div class="home_des">
            <p class="clearfix">&nbsp;&nbsp;
                <span id="name" style="color:black;">${status.values['diningName']}</span>
                <input id="tableId" type="hidden" value="${status.values['tableId']}">
                <input id="tableName" type="hidden" value="${status.values['tableName']}">
            </p>
            <p>
                <i></i>
                <span>全天</span>
            </p>
            <p id="wifi_info">
                <i></i>
                <span id="wifiname">&nbsp;&nbsp;wxct&nbsp;&nbsp;密码:88888888</span>
            </p>
        </div>
        <div class="home_order">
            <div class="home_show">

            </div>
            <ul class="home_hide" style="display:none;">
            </ul>
        </div>
    </div>

    <div class="home_order">
        <div class="home_show">
        </div>
        <ul class="home_hide" style="display:none;">
        </ul>
    </div>
    <div id="diancai_content" class="home_pic clearfix">
        <div class="pic_two" id="diancan">
            <img id="diancan_img" src="./img/home/diancan1.png" alt="" />
            <p>点餐</p>

        </div>
        <div class="pic_one" id="maidan">
            <img id="maidan_img" src="./img/home/maidan.png" alt="" />
            <p>我的订单</p>
        </div>
    </div>

    <footer class="home_title">
        <%--<a href="http://www.wecaidan.com/wapwcd.html">--%>
            <%--<img src="./img/home/LOGO.png" alt="" />--%>
        <%--</a>--%>
    </footer>
</article>
</body>

<script>
    window.addEventListener('onorientationchange' in window?'orientationchange':'resize', function(){
        setTimeout(orientInit, 100);
    },false);
</script>
</html>