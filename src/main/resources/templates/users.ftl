<#import "parts/common.ftl" as l>
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
</div>

<#if sessionRole=="[ADMIN]">
<div id="left">
       <#-- ${message}-->
    <form method="post" action="user/new">
        <input type="text" name="username" placeholder="username"/>
        <input type="password" name="password" placeholder="password">
        <input type="text" name="firstName" placeholder="firstname">
        <input type="text" name="lastName" placeholder="lastname">
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
</#if>

<form method="post" action="byname">
    <input type="text" name="name" placeholder="Имя для поиска">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Найти</button>
</form>

<form method="post" action="byrole">
    <select name="role">
        <option>ADMIN</option>
        <option>USER</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Найти</button>
</form>

<#if sessionRole=="[ADMIN]">
<form method="post" action="delete">
    <input type="text" name="name" placeholder="Имя для удаления">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Удалить</button>
</form>
</#if>

<div id="right" style="position: center">
    <div style="border: black">Список пользователей:</div>

    <#list users as user>
        <div>
            <span>${user.username}</span>
            <i>${user.active?string('[ACTIVE]', '[INACTIVE]')}</i>
            <i><#list user.roles as role>${role}<#sep>, </#list></i>
            <i>${user.createdAt}</i>
            <form method="post" action="user/${user.id}">
                <!--<input type="hidden" name="id" value={{id}}>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">Просмотр</button>
            </form>
                <#if sessionRole=="[ADMIN]">
                    <form method="get" action="user/${user.id}/edit">
                        <!--<input type="hidden" name="id" value={{id}}>-->
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Изменить</button>
                    </form>
            </#if>
        </div>
        <#else>
        No results
    </#list>
</div>

</body>
</html>