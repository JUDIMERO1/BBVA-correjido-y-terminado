<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Bills</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark">
			<div>
				<img style="height: 10vh" alt="Logo BBVA"
					src="https://imgs.search.brave.com/I56CLl8ubHVXT8Pzwb4ubTiA-SJy3_ze0Wpj9EWULdA/rs:fit:1200:748:1/g:ce/aHR0cHM6Ly93d3cu/aW52ZXN0b3BlZGlh/LmNvbS90aG1iL1Z3/Z2VudFdWekh4eWhR/X0Q2NjJkSTFWYi1V/az0vMjUwMHg3NDgv/ZmlsdGVyczpub191/cHNjYWxlKCk6bWF4/X2J5dGVzKDE1MDAw/MCk6c3RyaXBfaWNj/KCkvYmJ2YS1sb2dv/LWM2NWVhNjE5YzBk/NzQ5YTE4MDQzMWMx/NzI5ODVkOGZhLnBu/Zw">
			</div>
		</nav>
	</header>

	<hr size="15" noshade="noshade" style="background-color: blue;" />
	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action=BillServlet?action=/insert method="post">
					
					<fieldset class="form-group py-2">
						<label>Descripción</label> <br />
						<textarea name="observation" rows="3" style="width: 100%;"></textarea>
					</fieldset>

					<fieldset class="form-group d-flex py-2">
						<label>Tipo de movimiento</label> <input type="radio" value=1
							class="form-control" name="movimiento" /> Ingreso <br /> <input
							type="radio" value=2 class="form-control" name="movimiento" />
						Gasto
					</fieldset>

					<fieldset class="form-group py-2">
						<label>Valor</label> <br /> <input type="text" value=""
							class="form-control" name="value" />
					</fieldset>

					<div class="d-flex justify-content-around">
						<button type="submit" class="btn mt-2"
							style="background-color: blue; color: white;">Registrar</button>
						<a href="<%=request.getContextPath()%>/BillServlet?action=listar"
							class="mt-4" style="text-decoration: none;" type="button">Cancelar</a>
					</div>
					
				</form>

				<div class="pt-2">
					<p class="lead bold pt-2" style="color: red;">

						<%
						String resultado = (String) request.getAttribute("mensaje");
						String mensaje = "";
						if (resultado != null) {
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