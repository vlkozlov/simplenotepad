<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Add user
${message?ifExists}
<@l.login "/registration" />
</@c.page>