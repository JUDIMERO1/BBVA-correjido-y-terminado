<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login BBVA</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand">BBVA</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Usuarios</a></li>
			</ul>
		</nav>
	</header>
	
	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					
				<form action=loged method="post">
				
				<fieldset class="form-group">
					<label>User:</label> <input type="text"
						value="" class="form-control"
						name="username" required="required" />
				</fieldset>

				<fieldset class="form-group pt-2">
					<label>Password:</label> <input type="password"
						value="" class="form-control"
						name="pass" required="required"/>
				</fieldset>
					<div class="pt-2">
						<button type="submit" class="btn" style="background-color: blue; color:white;">Log In</button>
					</div>
					
				
				</form>	
				
				<div class="pt-2">
					<p class="lead bold pt-2" style="color:red;">
					
						<%
							String resultado = (String)request.getAttribute("mensaje");
							String mensaje="";
							if(resultado != null){
								mensaje = resultado;
							}
						%>
						<%=mensaje%>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>