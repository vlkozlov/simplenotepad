<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Login
<@l.login "/login" />
<a href="/registration">Add user</a>
</@c.page>