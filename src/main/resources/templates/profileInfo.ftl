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
        <div>
            <span>${username}</span>
            <i>${firstname}</i>
            <i>${lastname}</i>
            <i>${createdAt}</i>
        </div>
</div>

</body>
</html>