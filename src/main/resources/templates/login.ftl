<#import "parts/login.ftl" as l>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
    <link href="/styles/style.css" rel="stylesheet">
</head>
<style>
    div {
        position: center;
    }

    a {
        position: center;
    }

    #left {
        text-align: center; /* Выравниваем по центру */
        clear: both;
    }
</style>
<body>
<div id="left">

    Вход
    <@l.login "/login" />
    <a href="/registration">Регистрация нового пользователя</a>

</div>
</body>
</html>