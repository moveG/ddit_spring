<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<title>LoginPage</title>

<head></head>

<body>
<div class="hold-transition login-page" style="min-height:496.781px;">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>Admin</b> Login</a>
		</div>
		<div class="card">
			<div class="card-body">
				<p class="login-box-msg">Sign in to start your session</p>
				
				<form action="<%=request.getContextPath() %>/common/login.do" method="post">
					<div class="input-group mb-3 has-feedback">
						<input type="text" class="form-control" name="id" placeholder="Enter ID" value="">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3 has-feedback">
						<input type="password" class="form-control" name="pwd" placeholder="Enter Password" value="">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>	
					<div class="row">
						<div class="col-sm-8">
							<div class="icheck-primary">
								<input type="checkbox" id="remember">
								<label for="remember">
									Remember Me
								</label>
							</div>
						</div>
						<div class="col-sm-4">
				            <button type="submit" class="btn btn-primary btn-block">LOGIN</button>
						</div>
					</div>
				</form>
				
				<p class="mb-1">
					<a href="#">I forgot my Id/Password</a>
				</p>
			</div>
		</div>
	</div>
</div>
</body>
