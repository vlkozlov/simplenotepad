<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User List</a></span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeHolder="Новая заметка" />
            <input type="text" name="priority" placeHolder="Важность" />
            <input type="file" name="file" />
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>
    <div>Список заметок</div>
    <#list notes as note>
    <div>
        <b>${note.priority}</b>
        <span>${note.text}</span>
        <div>
            <#if note.filename??>
                <img src="/img/${note.filename}">

            </#if>
        </div>
    </div>
    <#else>
    Список пуст
    </#list>
</@c.page>