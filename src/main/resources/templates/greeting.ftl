<#import "parts/login.ftl" as l>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>-->
    <link href="/styles/style.css" rel="stylesheet">
</head>
<body>
<style>
    div {
        position: center;
    }

    #left {
        text-align: center; /* Выравниваем по центру */
    }
</style>
<div>
    <@l.logout/>
</div>
<div id="left">
    <div>Добро пожаловать</div>
    <a href="/user">Перейти к списку пользователей</a>
</div>
</body>
</html>