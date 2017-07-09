<html>
<body>
    <#--这是一个注释-->
    <p>
        welcome ${user} to cola
    </p>

    <p>性别：
    <#if gender==0>
        女
    <#elseif gender==1>
        男
    <#else>
        保密
    </#if>
    </p>


    <h4>我的好友：</h4>
    <#list friends as item>
    姓名：${item.getName()} , 年龄${item.getAge()}
    <br>
    </#list>


    <#include "/footer.ftl" >
</body>
</html>