<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title><c:out value="日報管理システム"/></title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrwpper">
            <div id="header">
                <div id="headea_menu">
                    <h1>日報管理システム</h1>
                </div>
            </div>
            <div id="content">${param.content}</div>
            <diV id="footer">by Taro Kirameki.</diV>
        </div>
    </body>
</html>