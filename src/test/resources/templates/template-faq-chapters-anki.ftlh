<#list items as item>
"<#list item.question as q>${q}</#list>","<p style=""text-align:left;""><#list item.answer as a>${a}<#if a?has_next><br/></#if></#list></p>","<#list item.tag as t>${t}</#list> <#list item.source as s>${s}</#list>"
</#list>
