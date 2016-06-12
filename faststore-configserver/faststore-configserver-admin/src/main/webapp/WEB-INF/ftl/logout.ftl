<title>注销成功</title>

<!-- js脚本异步注销-->
<script type="text/javascript">
    function logout(url) {

        $.post(url, function (data) {
            //
            alter(url);
        });
    }


    function logout2(url) {
        $.ajax({
                    dataType: 'jsonp',
                    jsonp: 'jsonCallBack',
                    url: url,
                    success: function (json) {
                    }
                }

        )
        ;


    }
</script>

<#list  logoutUrls as url>
<script>
    logout2("${url}");
</script>
</#list >



注销成功，点击重新<a href="${ctx}/login">登录</a>

