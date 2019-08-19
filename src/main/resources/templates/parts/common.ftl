<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>Sweater</title>
        <link rel="stylesheet" href="/styles/style.css"/>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    </head>
    <body>
    <#include "navbar.ftl">
    <div class="container mt-5">
        <#nested>
    </div>
    </body>
    </html>
</#macro>