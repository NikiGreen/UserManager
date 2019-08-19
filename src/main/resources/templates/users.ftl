<#import "parts/pager.ftl" as p>
<#import "parts/common.ftl" as c>

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

    <#if sessionRole=="[ADMIN]">
        <div id="left">

            <form method="post" action="user/new">
                <input type="text" name="username" placeholder="username" minlength="3" maxlength="16" required
                       pattern="^[a-zA-Z]+$">
                <input type="password" name="password" placeholder="password" minlength="3" maxlength="16" required
                       pattern="^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{3,16}$">
                <input type="text" name="firstName" placeholder="firstname" minlength="1" maxlength="16" required
                       pattern="^[a-zA-Z]+$">
                <input type="text" name="lastName" placeholder="lastname" minlength="1" maxlength="16" required
                       pattern="^[a-zA-Z]+$">
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

    <#if sessionRole=="[ADMIN]">
        <form method="post" action="delete">
            <input type="text" name="name" placeholder="Имя для удаления">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Удалить</button>
        </form>
    </#if>

    <form method="get" action="/user">
        <button type="submit">Сбросить настройки</button>
    </form>

    <@p.pager url page/>
    <div id="right" style="position: center">
        <div style="border: black">Список пользователей:</div>


        <div class="card-columns" id="message-list">
            <#list page.content as userAccount>
                <div>
                    <span>${userAccount.username}</span>
                    <i>${userAccount.active?string('[ACTIVE]', '[INACTIVE]')}</i>
                    <i><#list userAccount.roles as role>${role}<#sep>, </#list></i>
                    <i>${userAccount.createdAt}</i>
                    <form method="post" action="user/${userAccount.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit">Просмотр</button>
                    </form>
                    <#if sessionRole=="[ADMIN]">
                        <form method="get" action="user/${userAccount.id}/edit">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit">Изменить</button>
                        </form>
                    </#if>
                </div>
            <#else>
                No results
            </#list>
        </div>
    </div>
</@c.page>

