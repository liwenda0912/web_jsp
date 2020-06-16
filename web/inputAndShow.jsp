<%--
  Created by IntelliJ IDEA.
  User: HZ
  Date: 2020/5/5
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="car" class="tom.jiafei.Car" scope="request"></jsp:useBean>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="blue">
<form name='form' action="" method="post">
    <p>汽车车牌号
          <input name="number" type="text">
        </p>
    <p>汽车名称
   <input name="carname" type="text">
        </p>
    <p>生产日期
       <input name="data" type="text">
    </p>
    <input vule="提交" type="submit">
   </form>
<jsp:setProperty name="car" property="*"></jsp:setProperty>
<table border=1>
    <tr>
        <th>汽车牌号</th>

        <th>名字</th>

        <th>生产日期</th>

    </tr>
    <tr>
        <th><jsp:getProperty name="car" property="number"/></th>

        <th><jsp:getProperty name="car" property="name"/></th>

        <th><jsp:getProperty name="car" property="data"/></th>

    </tr>

</body>
</html>
