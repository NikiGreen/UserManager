<#import "parts/login.ftl" as l>
<html>
<head>
    <link href="/styles/style.css" rel="stylesheet">
</head>

<body>
<style>
    #left {
        float: left;
        clear: both;
    }

    #right {
        text-align: center; /* Выравниваем по центру */
        clear: both;

    }

</style>
<div>
    <@l.logout/>
    <a href="/user">Перейти к списку пользователей</a>
</div>


<div id="left">
    Пользователь:<b>${username}</b>
    <form method="post" action="/user/${id}/edit">
        <input type="text" name="username" value=${username}>
        <input type="password" name="password" value=${password}>
        <input type="text" name="firstname" value=${firstName}>
        <input type="text" name="lastname" value=${lastName}>
        <input type="text" name="createdAt" value=${createdAt}>
        <select name="status">
            <option>ACTIVE</option>
            <option>INACTIVE</option>
        </select>
        <select name="role">
            <option>ADMIN</option>
            <option>USER</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Изменить</button>
    </form>
</div>

</body>
</html>