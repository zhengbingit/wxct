(function (exports) {
    var KeyBoard = function (input, options) {
        var body = document.getElementsByTagName('body')[0];
        var DIV_ID = 'divid';

        if (document.getElementById(DIV_ID)) {
            body.removeChild(document.getElementById(DIV_ID));
        }

        this.input = input;
        this.el = document.createElement('div');

        var self = this;
        var zIndex = options && options.zIndex || 1000;
        var width = options && options.width || '100%';
        var height = options && options.height || '260px';
        var fontSize = options && options.fontSize || '22px';
        var backgroundColor = options && options.backgroundColor || '#fff';
        var TABLE_ID = options && options.table_id || 'table_0909099';
        var type_length=options && options.type_length || 4;
        var mobile = typeof orientation !== 'undefined';
        this.el.id = DIV_ID;
        this.el.style.position = 'absolute';
        this.el.style.left = 0;
        this.el.style.right = 0;
        this.el.style.bottom = 0;
        this.el.style.zIndex = zIndex;
        this.el.style.width = width;
        this.el.style.height = height;
        this.el.style.backgroundColor = backgroundColor;
        this.el.style.display = "none";
        this.el.style.borderTop = "1px solid #ccc";
        //样式
        var cssStr = '<style type="text/css">';
        cssStr += '#' + TABLE_ID + '{text-align:center;width:100%;height:220px;border-top:1px solid #CECDCE;background-color:#FFF;}';
        cssStr += '#' + TABLE_ID + ' td{width:33%;height:auto;border:1px solid #ddd;border-right:0;border-top:0;font-size:30px;}';
        if (!mobile) {
            cssStr += '#' + TABLE_ID + ' td:hover{background-color:#1FB9FF;color:#FFF;}';
        }
        cssStr += '</style>';

        //Button
        var btnStr = '<div class="_ok" style="height:40px;position:relative;';
        btnStr += 'text-align:right;color:#0073ff;background:#f0f1f2;font-size:15px;';
        btnStr += 'line-height:40px;font-weight:600;padding-right:8px;"><img src="../img/leftkey.png" class="lfg" /><img src="../img/rightkey.png" class="rig"/>完成</div>';
        //table
        var tableStr = '<table id="' + TABLE_ID + '" border="0" cellspacing="0" cellpadding="0">';
        tableStr += '<tr><td >1</td><td>2</td><td>3</td></tr>';
        tableStr += '<tr><td >4</td><td>5</td><td>6</td></tr>';
        tableStr += '<tr><td>7</td><td>8</td><td>9</td></tr>';
        tableStr += '<tr><td style="background-color:#D3D9DF;">.</td><td>0</td>';
        tableStr += '<td style="background-color:#D3D9DF;font-size:17px;">删除</td></tr>';
        tableStr += '</table>';
        this.el.innerHTML = cssStr + btnStr + tableStr;
        function addEvent(e) {
            var ev = e || window.event;
            var clickEl = ev.element || ev.target;
            var value = clickEl.textContent || clickEl.innerText;
            if (clickEl.tagName.toLocaleLowerCase() === 'td' && value !== "删除") {
                if (self.input && self.input.value.length < type_length) {
                    self.input.value += value;
                }
            } else if (clickEl.tagName.toLocaleLowerCase() === 'div' && value === "完成") {
                $(self.el).slideUp(400, function () { $("#paydiv").show(); });

            } else if (clickEl.tagName.toLocaleLowerCase() === 'td' && value === "删除") {
                var num = self.input.value;
                if (num) {
                    var newNum = num.substr(0, num.length - 1);
                    self.input.value = newNum;
                }
            }
        }

        if (mobile) {
            this.el.ontouchend = addEvent;
        } else {
            this.el.onclick = addEvent;
        }
        body.appendChild(this.el);
    }

    exports.KeyBoard = KeyBoard;

})(window);
