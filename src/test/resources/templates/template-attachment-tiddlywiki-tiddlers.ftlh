<#list items as item>
<div created="20210101000000001" modified="20210101000000001" type="${item.mimetype}" tags="${item.tags} toc" title="${item.title}">
<pre>${item.content}</pre>
</div>
</#list>