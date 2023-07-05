<html>
	<head>
		<title>Login Form</title>
	</head>
	<body>
		<div class="container">
			<h1>Login</h1>
			<form method="post">
				username: <input type="text" name="username">
				password <input type="password" name="password">
				<input type="submit">
			</form>
			<pre>${errorMsg}</pre>
		</div>
	</body>
</html>