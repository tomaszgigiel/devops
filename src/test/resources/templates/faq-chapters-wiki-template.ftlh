<html>
<head>
  <title>DevOps</title>
</head>
<body>

<#list items as item>
  <h2>Item:</h2>

<#list item.question as q>
  <p>Q:${q}!</p>
</#list>

<#list item.answer as a>
  <p>A: ${a}!</p>
</#list>

<#list item.source as s>
  <p>S: ${s}!</p>
</#list>

<#list item.tag as t>
  <p>T: ${t}!</p>
</#list>

<#list item.chunk as c>
  <p>C: ${c}!</p>
</#list>

</#list>

</body>
</html>
