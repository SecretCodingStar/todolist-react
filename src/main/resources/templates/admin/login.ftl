<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Admin Sign in</title>
	<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
	<link href="/webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet" media="screen"/>
	<link href="/resources/css/login.css" rel="stylesheet" media="screen"/>
	<script type="text/javascript" src="/webjars/jquery/3.0.0/jquery.js"></script>
</head>
<body class="red-bg">
<div class="container">
	<form class="login-form" action="#" method="post">
		<h2>Admin Login</h2>
		<hr />
		<div class="form-group">
			<input type="text" name="username" class="form-control" placeholder="Username"/>
		</div>
		<div class="form-group">
			<input type="password" name="password" class="form-control" placeholder="Password"/>
		</div>
		<div class="checkbox">
			<label>
				<input type="checkbox" name="remember-me" class="checkbox-login"/> Remember me
			</label>
		</div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit" class="btn btn-info btn-login">Sign in</button>
	</form>
</div>
</body>
</html>
