<#import "parts/login.ftl" as l>
<html>
<head>
    <link href="/styles/style.css" rel="stylesheet">
</head>
<body>
<style>
    form {
        overflow: hidden;
    }

    #left {
        float: right;
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
    ${message?ifExists}
    <form method="post" action="user/new">
        <input type="text" name="userName" placeholder="userName"/>
        <input type="password" name="password" placeholder="password">
        <input type="text" name="firstName" placeholder="firstName">
        <input type="text" name="lastName" placeholder="lastName">
        <select name="status">
            <option>ACTIVE</option>
            <option>INACTIVE</option>
        </select>
        <select name="role">
            <option>ADMIN</option>
            <option>USER</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
</div>

<form method="post" action="byName">
    <input type="text" name="name" placeholder="Имя для поиска">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Найти</button>
</form>

<form method="post" action="byRole">
    <select name="role">
        <option>ADMIN</option>
        <option>USER</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Найти</button>
</form>

<form method="post" action="delete">
    <input type="text" name="name" placeholder="Имя для удаления">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Удалить</button>
</form>

<div id="right" style="position: center">
    <div style="border: black">Список пользователей:</div>
    <#list users as userAccount>
        <div>
            <span>${userAccount.userName}</span>
            <i>${userAccount.active}</i>
            <i>${userAccount.roles}</i>
            <i>${userAccount.createdAt}</i>
            <form method="post" action="user/${userAccount.id}">
                <!--<input type="hidden" name="id" value={{id}}>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">Просмотр</button>
            </form>
            <form method="get" action="user/${userAccount.id}/edit">
                <!--<input type="hidden" name="id" value={{id}}>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">Изменить</button>
            </form>
        </div>
    </#list>
</div>

</body>
</html>