<h1>$title.getData()</h1>
<p>Please copy this code and paste it into the "Pin" field</p>
<input id="facebook-code" class="facebook-redirect-input-code" />

<script>
	AUI().ready('querystring', function(A) {
		var queryString = A.one('window').get('location').search;
		if(queryString) {
			queryString = queryString.substring(1);
			var code = A.QueryString.parse(queryString)['code'];
			A.one('#facebook-code').set('value', code);
		}
	});
</script>