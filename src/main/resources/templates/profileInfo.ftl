<#import "parts/main.ftl" as c>
<@c.page>

    <table style="position:center ;width:300px; margin:auto ">
        <caption>Данные пользователя</caption>
        <tr>
            <th>Никнейм</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Дата регистрации</th>
        </tr>
        <tr>
            <td>${username}</td>
            <td>${firstName}</td>
            <td>${lastName}</td>
            <td>${createdAt}</td>
        </tr>
    </table>
</@c.page>
