<#import "login.ftl" as l>
<ul id="navbar" style="background-color: #333">
    <li><a href="/">Home</a></li>
    <li><a href="/">News</a></li>
    <li><a href="/">Contact</a></li>
    <li><a href="/">About</a></li>
    <li style="float: right"><a><@l.logout/></a></li>
</ul>
<#--<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Sweater</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>-->
<#--<#if userAccount??>
    <li class="nav-item">
        <a class="nav-link" href="/main">Messages</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/userAccount-messages/${currentUserId}">My messages</a>
    </li>
</#if>
<#if isAdmin>
    <li class="nav-item">
        <a class="nav-link" href="/userAccount">User list</a>
    </li>
</#if>
<#if userAccount??>
    <li class="nav-item">
        <a class="nav-link" href="/userAccount/profile">Profile</a>
    </li>
</#if>-->
<#-- </ul>

 &lt;#&ndash;<div class="navbar-text mr-3"><#if userAccount??>${name}<#else>Please, login</#if></div>&ndash;&gt;
 <@l.logout />
</div>
</nav>-->