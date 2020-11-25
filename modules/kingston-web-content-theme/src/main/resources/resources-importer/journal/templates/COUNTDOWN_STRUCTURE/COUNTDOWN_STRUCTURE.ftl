<div id="countDown">
</div>
 
<script type="text/javascript">
  var text = '$preamble.data';
  $('#countDown').countdown('$countdownDate.data', function(event) {
      $(this).html(event.strftime('<p>' + text + '</p>'
      + '<div class="countNumber">%D days '
      + '%H Hours '
      + '%M Minutes</div>'));
  });
</script>