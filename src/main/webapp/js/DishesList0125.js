/**
 * Created by zhengbinMac on 2017/5/4.
 */
window.onload = showselectedpage(3);
var zengcount = 3;
var zengArr = new Array();
function judgeselpric(val) {
    var intval = parseInt(val);
    if (intval >= 100) {
        for (i = 0; i < 100; i++) {
            if (intval < 100) {
                return intval;
            } else {
                intval -= 100;
            }
        }
    }
    return intval;
}
function inputchanged(val) {
    var time=val
    var d = $("#test_select1").attr("name");
    if (d == "timedate") {
        var dt = new Date();
        var dhourSpan = val.split(':');
        var dateStr=new Date().pattern('yyyy-MM-dd')
        if (dhourSpan.length == 2) {


            if (parseInt(dt.getHours()) >= parseInt(dhourSpan[0]) && parseInt(dt.getMinutes()) >= parseInt(dhourSpan[1])) {
                dateStr=GetDate(1).pattern('yyyy-MM-dd');
                val = "次日" + val;
            }
            var typetime = $("#TimeHiddenSelect").val();
            switch (parseInt(typetime)) {
                case 2:
                    $("#TmDiv").attr("title", val).html(val);
                    break;
                default:
                    $("#timedatediv").attr("title", 1).css("display", "block").html("已选" + val + "送达");
                    break;
            }
            $("#deliveryTime").val(dateStr+" "+time);
        }
    }

}



function selectTimeser(param1) {
    var meth = parseInt(param1);
    var selme = $("#timedate" + param1 + "");
    var sel = parseInt(selme.attr("class"));
    switch (meth) {
        case 1:
        {
            if (sel == 1) {
                //判断是外卖还是自取
                //var sporder = $("#shopid").attr("name");
                if (true) {
                    selme.attr("src", "../systemimg/cked.png");
                    $("#timedate2").attr("class", "1").attr("src", "../systemimg/nock.png");
                    var timess = $("#ShopTakeAwayTime").val(); //$("#timedatediv").attr("class");
                    var sttm = $("#ShopTakeAwayTime").attr("name");
                    if (sttm) { sttm = parseInt(sttm); } else { sttm = 30; }
                    if (timess) {
                        var arrtimes = timess.split('|');
                        if (arrtimes.length == 2) {
                            var beginarr = arrtimes[0].split(':');
                            var endarr = arrtimes[1].split(':');
                            var d = new Date();
                            if (beginarr[0] && beginarr[1] && endarr[0] && endarr[1]) {
                                var opstrlist = "";
                                var twoStrlist = "";
                                if (parseInt(beginarr[0]) == parseInt(endarr[0]) && parseInt(beginarr[1]) == parseInt(endarr[1])) { //表时间相同
                                    beginarr[0] = 0; endarr[0] = 24;
                                } else if (parseInt(endarr[0]) < parseInt(beginarr[0])) {
                                    endarr[0] = 24 + parseInt(endarr[0]);
                                } else if (parseInt(d.getHours()) > endarr[0]) {
                                    endarr[0] = 24 + parseInt(endarr[0]);
                                }
                                var timenum;
                                var housnub;
                                var minnunb;
                                if (parseInt(d.getHours()) <= parseInt(beginarr[0])) {
                                    housnub = parseInt(beginarr[0]);
                                } else {
                                    housnub = parseInt(d.getHours());
                                }

                                for (var z = parseInt(beginarr[0]) ; z <= endarr[0]; z++) {
                                    if (z == housnub) {
                                        timenum = z;
                                        z = z;
                                    } else {
                                        timenum = z;
                                    }
                                    if (z > 23) {
                                        timenum = z - 24;
                                    }
                                    var mins;
                                    for (var m = 0; m < 60; m = m + sttm) {
                                        if (z < endarr[0]) {
                                            if (z == housnub) {
                                                m = d.getMinutes() + sttm;
                                                if (m < sttm) {
                                                    if (m > d.getMinutes()) {
                                                        if (m < 10) {
                                                            if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                                twoStrlist += "<option value='" + timenum + ":0" + m + "'>次日" + timenum + ":0" + m + "</option>";
                                                            } else {
                                                                opstrlist += "<option value='" + timenum + ":0" + m + "'>" + timenum + ":0" + m + "</option>";
                                                            }
                                                        } else {
                                                            if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                                twoStrlist += "<option value='" + timenum + ":" + m + "'>次日" + timenum + ":" + m + "</option>";
                                                            } else {
                                                                opstrlist += "<option value='" + timenum + ":" + m + "'>" + timenum + ":" + m + "</option>";
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (timenum < 10) {
                                                    if (m < 10) {
                                                        if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                            twoStrlist += "<option value='0" + timenum + ":0" + m + "'>次日0" + timenum + ":0" + m + "</option>";
                                                        } else {
                                                            opstrlist += "<option value='0" + timenum + ":0" + m + "'>0" + timenum + ":0" + m + "</option>";
                                                        }
                                                    } else {
                                                        if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                            twoStrlist += "<option value='0" + timenum + ":" + m + "'>次日0" + timenum + ":" + m + "</option>";
                                                        } else {
                                                            opstrlist += "<option value='0" + timenum + ":" + m + "'>0" + timenum + ":" + m + "</option>";
                                                        }
                                                    }
                                                } else {
                                                    if (m < 10) {
                                                        if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                            twoStrlist += "<option value='" + timenum + ":0" + m + "'>次日" + timenum + ":0" + m + "</option>";
                                                        } else {
                                                            opstrlist += "<option value='" + timenum + ":0" + m + "'>" + timenum + ":0" + m + "</option>";
                                                        }
                                                    } else {
                                                        if ((z <= parseInt(d.getHours()) && m <= parseInt(parseInt(d.getMinutes()))) || z <= parseInt(d.getHours())) {
                                                            twoStrlist += "<option value='" + timenum + ":" + m + "'>次日" + timenum + ":" + m + "</option>";
                                                        } else {
                                                            opstrlist += "<option value='" + timenum + ":" + m + "'>" + timenum + ":" + m + "</option>";
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }

                                }
                                opstrlist += twoStrlist;
                            }
                        }
                    }
                } else if (sporder && parseInt(sporder) == 2) {
                    var timess = $("#timedatediv").attr("class");
                    if (timess) {
                        var arrtimes = timess.split('|');
                        if (arrtimes.length == 2) {
                            var beginarr = arrtimes[0].split(':');
                            var endarr = arrtimes[1].split(':');
                            if (beginarr[0] && beginarr[1] && endarr[0] && endarr[1]) {
                                var opstrlist = "";
                                if (parseInt(beginarr[0]) == parseInt(endarr[0]) && parseInt(beginarr[1]) == parseInt(endarr[1])) { //表时间相同
                                    beginarr[0] = 0; endarr[0] = 24;
                                } else if (parseInt(endarr[0]) < parseInt(beginarr[0])) {
                                    endarr[0] = 24 + parseInt(endarr[0]);
                                }
                                var d = new Date();
                                var timenum;
                                var housnub;
                                var minnunb;
                                if (parseInt(d.getHours()) <= parseInt(beginarr[0])) {
                                    housnub = parseInt(beginarr[0]);
                                } else {
                                    housnub = parseInt(d.getHours());
                                }
                                for (var z = housnub; z <= endarr[0]; z++) {
                                    if (z < endarr[0]) {
                                        if (z == housnub) {
                                            timenum = z;
                                            z = z;
                                        } else {
                                            timenum = z;
                                        }
                                        if (z > 23) {
                                            timenum = z - 24;
                                        }
                                        var mins;
                                        for (var m = 0; m < 60; m = m + 15) {
                                            if (z == housnub) {
                                                m = m + 30;
                                                if (m < 60) {
                                                    if (m > d.getMinutes()) {
                                                        if (m < 10) {
                                                            opstrlist += "<option value='" + timenum + ":0" + m + "'>" + timenum + ":0" + m + "</option>";
                                                        } else {
                                                            opstrlist += "<option value='" + timenum + ":" + m + "'>" + timenum + ":" + m + "</option>";
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (timenum < 10) {
                                                    if (m < 10) {
                                                        opstrlist += "<option value='0" + timenum + ":0" + m + "'>0" + timenum + ":0" + m + "</option>";
                                                    } else {
                                                        opstrlist += "<option value='0" + timenum + ":" + m + "'>0" + timenum + ":" + m + "</option>";
                                                    }
                                                } else {
                                                    if (m < 10) {
                                                        opstrlist += "<option value='" + timenum + ":0" + m + "'>" + timenum + ":0" + m + "</option>";
                                                    } else {
                                                        opstrlist += "<option value='" + timenum + ":" + m + "'>" + timenum + ":" + m + "</option>";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                $('#test_select1').attr("name", "timedate").html(opstrlist).click();
            }
            break;
        }
        case 2:

    }
}
function showselectedpage(str) {
    var wheelwd = $(window).width() - 34;
    var dfhei = $.cookie("dfheight");
    $('#test_select1').mobiscroll({
        preset: 'select', theme: 'android-ics light', mode: 'scroller', display: 'bottom', lang: 'zh', width: wheelwd,
    });
    $("#waimaiinfo").css("display", "block");
}
