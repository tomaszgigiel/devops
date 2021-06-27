<p class="auto-cursor-target">
  <br/>
</p>
<table class="wrapped">
  <colgroup> <col/> <col/> <col/> <col/> </colgroup>
  <tbody>
    <tr>
      <th class="numberingColumn" colspan="1">Lp.</th>
      <th colspan="1">Pytanie</th>
      <th colspan="1">Odpowiedź</th>
      <th colspan="1">Źródło</th>
      <th colspan="1">Uwagi</th>
    </tr>

    <#list items as item>
    <tr>
      <td class="numberingColumn">${item.index + 1}</td>
      <td><#list item.question as q><p>${q}</p></#list></td>
      <td><p><#list item.answer as a>${a}<#if a?has_next><br></#if></#list></p></td>
      <td><ul><#list item.source as s><#if s?index != 0><li>${s}</li></#if></#list></ul></td>
      <td><ul><#list item.tag as t><li>${t}</li></#list></ul></td>
    </tr>
    </#list>

  </tbody>
</table>
<p class="auto-cursor-target">
  <br/>
</p>
