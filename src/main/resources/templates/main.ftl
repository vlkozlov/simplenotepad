<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post">
            <input type="text" name="text" placeHolder="Новая заметка" />
            <input type="text" name="priority" placeHolder="Важность" />
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Найти</button>
    </form>
    <div>Список заметок</div>
    <#list notes as note>
    <div>
        <b>${priority}</b>
        <span>${text}</span>
    </div>
    <#else>
    Список пуст
    </#list>
</@c.page>