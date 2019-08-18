<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
    <link href="/styles/style.css" rel="stylesheet">
</head>
<body>
<style>
    div {
        position: center;
    }

    DIV.container {
        min-height: 10em;
        display: table-cell;
        vertical-align: middle;
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
<a href="/login">Вернуться на страницу входа</a>
<div id="left">
    Добавление нового пользователя
    ${message?ifExists}
    <form action="/registration" method="post" style="position: center">
        <div><label> Логин : <input type="text" name="username"/> </label></div>
        <div><label> Пароль: <input type="password" name="password"/> </label></div>
        <div><label> Имя : <input type="text" name="firstName"/> </label></div>
        <div><label> Фамилия:<input type="text" name="lastName"/> </label></div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Зарегестрировать"/></div>
    </form>
</div>
</body>
</html>