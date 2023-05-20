<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/20/2023
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        tr{
            border: 1px solid #ffffff;
        }
        .box1{
            border-right: 1px solid #ffffff;
        }
        .box1 img{
            width: 200px;
            height: 250px;
        }
    </style>
</head>
<body>
<h2>${requestScope['message']}</h2>
<%--<table>--%>
<%--    <c:forEach items="players" var="player">--%>
<%--        <tr>--%>
<%--            <td class="box1"><img src="${player.getImage()}" alt=""></td>--%>
<%--            <td class="box2"><p>Player: ${player.getName()}</p>--%>
<%--                <br>Age: ${player.getAge()}--%>
<%--                <br>Game play: ${player.getGames()}--%>
<%--            </td>--%>
<%--            <td><a href="/Admin?action=chat&id=${player.getId()}">Chat</a></td>--%>
<%--            <td><a href="/Admin?action=gift&id=${player.getId()}">Gift</a></td>--%>
<%--        </tr>--%>


<%--    </c:forEach>--%>
<%--</table>--%>
</body>
</html>
