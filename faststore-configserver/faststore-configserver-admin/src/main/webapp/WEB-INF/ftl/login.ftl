<title>登录</title>


<script>
    $("#checkCode").onclick = function changeImg() {
//        alter("a");
        var imgSrc = $("#checkCode");
        var src = imgSrc.attr("src");
//        imgSrc.attr("src", chgUrl(src));


        var timestamp = (new Date()).valueOf();
        var url = '${ctx}/checkCode?rand=' + Math.random();

        $.ajax({
            type: "POST",
            url: url,
            data: code,
            success: callback
        });

    };
    //时间戳
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 17);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }


    function isRightCode() {
        var code = $("#veryCode").attr("value");
        code = "c=" + code;
        $.ajax({
            type: "POST",
            url: "resultServlet",
            data: code,
            success: callback
        });
    }

    function callback(data) {
        var imgSrc = $("#checkCode");
        imgSrc.attr("src", data)
    )
    }
</script>

<form action="/logon" method="post">
    <table border="0">
        <tr>
            <td>
                <label>用户名:</label>
            </td>
            <td>
                <input type="text" name="userName">
            </td>
        </tr>
        <tr>
            <td>
                <label>密码:</label>
            </td>
            <td>
                <input type="password" name="password">
            </td>

        </tr>
        <tr>
            <td>
                <label class="tit">验证码：</label>
            </td>
            <td>
                <input type="text" name="answer"/>
            <#--<a href="javascript:void(0)" onclick="changeImg()">-->
                <img
                        id=" checkCode"
                        title="点击刷新验证码" height="25" src="${ctx}/checkCode">
            <#--</a>-->
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label style="color: red; font-size: 14px;">${message}</label>
                <input type="submit" class="redBtn" value="登录"/>
            </td>
        </tr>
    </table>
    <input type="hidden" id="target" name="target" value="${target}"/>
    </div>
</form>

