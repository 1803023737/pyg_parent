<html>
<head>
    <title>freemarker</title>
    <meta charset="UTF-8">
</head>
<body>
<#--include指令  可以引入其他的魔板对象  抽取通用的  达到复用性-->
<#include "head.ftl">

<#-- 注释一些-->

${name},你好。${message}<br>

<#--定义变量 相当于map多一个值-->
<#assign linkman="mr 周">
${linkman}<br>

<#--if指令-->
<#if success==true>
    变量success为true
<#else>
   变量success为false
</#if>
<br>
<#--list指令-->
<#list goodsList as goods>
<#--下标-->
${goods_index+1}
  商品名称：${goods.name}
商品价格：${goods.price}<br>
</#list>
<#--内建函数  1 获得集合list的长度-->
一共 ${goodsList?size} 条记录<br>

<#--内建函数2 data转换字符串为json对象 -->
<#assign text="{'bank':'工商银行','account':'100.0'}">
<#assign data=text?eval>
开户行：${data.bank} 账号：${data.account}<br><br>


<#--内建函数2 data转换字符串为json对象 -->
<#assign text1="[{'bank':'建设银行','account':'101.0'},{'bank':'农业银行','account':'102.0'}]">
<#assign data1=text1?eval>
<#list data1 as data>
    开户行：${data.bank} 账号：${data.account}
</#list>
<br><br><br>

<#--日期时间-->
日期：${today?date}<br>
时间：${today?time}<br>
日期+时间:${today?datetime}<br>
日期格式化：${today?string('yyyy年MM月')}
<br><br>

<#--数字-->
数字：${point}<br>
去掉逗号内置函数数字：${point?c}<br>

<br>
<br>

<#--判断存在运算符-->
<#if aaa??>
   变量aaa存在${aaa}
<#else>
   变量aaa不存在
</#if>

<br>

<#--存在运算符2-->
${bbb!'bbb没有实际值'}



<br><br><br>
<#--对象类型-->
<#assign info={'mobile':123456,'address':'北京'}>
${info.mobile}######${info.address}
</body>
</html>