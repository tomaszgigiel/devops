<#list items as item>
<div created="20210101000000001" modified="20210101000000001" type="text/plain" title="${item.index + 1} <#list item.question as q>${q}</#list>" source="<#list item.source as s><#if s?index != 0>${s}</#if></#list>" tags="<#list item.tag as t>${t} </#list>toc" order="${100 + item.index + 1}">
<pre><#list item.answer as a>${a}<#if a?has_next>${'\n'}</#if></#list></pre>
</div>
</#list>