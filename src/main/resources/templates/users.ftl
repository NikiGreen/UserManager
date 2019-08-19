<#import "parts/login.ftl" as l>
<#import "parts/pager.ftl" as p>
<#import "parts/common.ftl" as c>


<#--<html>
<head>
    <link href="/styles/style.css" rel="stylesheet">
    &lt;#&ndash;<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS &ndash;&gt;
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

&ndash;&gt;
</head>
<body>-->

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
<@c.page>
<#--<div>
    <@l.logout/>
</div>-->
<#--
    <@p.pager url page/>
-->

<#--<#if sessionRole=="[ADMIN]">-->
    <div id="left">
        <#-- ${message}-->
        <form method="post" action="user/new">
            <input type="text" name="username" placeholder="username" minlength="3" maxlength="16" required pattern="^[a-zA-Z]+$">
            <input type="password" name="password" placeholder="password" minlength="3" maxlength="16" required pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{3,16}$">
            <input type="text" name="firstName" placeholder="firstname" minlength="1" maxlength="16" required pattern="^[a-zA-Z]+$">
            <input type="text" name="lastName" placeholder="lastname" minlength="1" maxlength="16" required pattern="^[a-zA-Z]+$">
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
<#--</#if>-->

    <form method="post" action="/byName">
        <input type="text" name="name" placeholder="Имя для поиска">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Найти</button>
    </form>

    <form method="post" action="/byRole">
        <select name="role">
            <option>ADMIN</option>
            <option>USER</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Найти</button>
    </form>

<#--<#if sessionRole=="[ADMIN]">-->
    <form method="post" action="delete">
        <input type="text" name="name" placeholder="Имя для удаления">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Удалить</button>
    </form>
    <form method="get" action="/user">
        <button type="submit">Сбросить поиск</button>
    </form>
<#--</#if>-->
    <@p.pager url page/>
    <div id="right" style="position: center">
        <div style="border: black">Список пользователей:</div>


        <div class="card-columns" id="message-list">
            <#list page.content as user>
                <div>
                    <span>${user.username}</span>
                    <i>${user.active?string('[ACTIVE]', '[INACTIVE]')}</i>
                    <i><#list user.roles as role>${role}<#sep>, </#list></i>
                    <i>${user.createdAt}</i>
                    <form method="post" action="user/${user.id}">
                        <#-- <input type="hidden" name="id" value={{id}}>-->
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Просмотр</button>
                    </form>
                    <#-- <#if sessionRole=="[ADMIN]">-->
                    <form method="get" action="user/${user.id}/edit">
                        <!--<input type="hidden" name="id" value={{id}}>-->
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Изменить</button>
                    </form>
                    <#--</#if>-->
                </div>
            <#else>
                No results
            </#list>
        </div>
    </div>
</@c.page>
<#--</body>
</html>-->
