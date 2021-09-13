<#list items as item>
<li>${item.index + 1} <#list item.question as q>${q}</#list></li>
</#list>