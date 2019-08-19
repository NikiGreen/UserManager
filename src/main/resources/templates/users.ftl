<#import "parts/pager.ftl" as p>
<#import "parts/main.ftl" as c>

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

    table {
        width: 300px; /* Ширина таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
        text-align: center; /* Выравниваем текст по центру ячейки */
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
        <table style="position: center" >
            <caption>Данные пользователя</caption>
            <tr>
                <th>Никнейм</th>
                <th>Статус</th>
                <th>Роль</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Дата регистрации</th>
                <th></th>
            </tr>
            <#list page.content as userAccount>
            <tr>
                <td>${userAccount.username}</td>
                <td>${userAccount.active?string('[ACTIVE]', '[INACTIVE]')}</td>
                <td><#list userAccount.roles as role>${role}<#sep>, </#list></td>
                <td>${userAccount.firstName}</td>
                <td>${userAccount.lastName}</td>
                <td>${userAccount.createdAt}</td>
                    <td> <form method="post" action="user/${userAccount.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit">Просмотр</button>
                        </form>
                        <#if sessionRole=="[ADMIN]">
                            <form method="get" action="user/${userAccount.id}/edit">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit">Изменить</button>
                            </form>
                        </#if></td>

            </tr>
            </#list>
        </table>
    </div>
</@c.page>

