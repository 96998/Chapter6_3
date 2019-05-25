<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 2019/5/25
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript">
        function check(form) {
            with (form) {
                if (name.value() == "") {
                    alert("姓名不能控")
                    return false;
                }
                if(age.value()==""){
                    alert("年龄不能空")
                    return false;
                }
            }
        }
    </script>
</head>
<body>
<%--错误的写法 <form action="/servlet/addPerson" method="post">--%>
<form action="servlet/addPerson" method="post">
    name: <input type="text" name="name">
    <br>
    age: <input type="text" name="age">
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
