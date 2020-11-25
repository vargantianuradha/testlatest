<div class="intro">
$intro.getData()
</div>
<div class="accordion">
  #foreach ($section in $heading.getSiblings())
  <h3>$section.getData()</h3>
  <div class="accordionContent">
    $section.content.getData()
  </div>
  #end

</div>