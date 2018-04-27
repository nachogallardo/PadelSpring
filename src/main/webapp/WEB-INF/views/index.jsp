<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html lang="en">
<head>
<script>
	function comprobarClave() {
		clave1 = document.getElementById("clave").value;
		clave2 = document.getElementById("clave1").value;
		boton = document.getElementById("btnRegistrar");
		claves = document.getElementById("claveDistinta");
		if (clave1 == clave2) {
			claves.innerHTML = "";
			boton.disabled = false;
		} else {
			claves.innerHTML = "Las contraseñas tienen que ser iguales";
			boton.disabled = true;
		}
	}
	function validarTelefono() {
		var telefono = document.getElementById("telefono").value;
		error = document.getElementById("telefonoError");
		boton = document.getElementById("btnRegistrar");
		if (telefono.length<9||telefono.length>9) {
			error.innerHTML = "El telefono debe tener 9 digitos";
			boton.disabled = true;
		} else {
			error.innerHTML = "";
			boton.disabled = false;
		}

	}
</script>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="description" content="">

<meta name="author" content="">



<title>Padel</title>

<link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>">
<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/dist/css/bootstrap.min.css"/>" rel="stylesheet">

<!--Fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Icons -->
<link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>
<body>
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<nav
				class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">


				<div style="height: 5%;" class="row"></div>
				<h1 class="site-title">
					<em class="fa fa-futbol-o" aria-hidden="true"></em>Inicie Sesion
				</h1>
				<c:url value="/inicioSesion" var="iniciar"></c:url>
				<f:form role="form" method="POST" action="${iniciar }" commandName="usuario"
					class="form-check">
					<div class="row">
						<div class="input-group col-md-12">

							<f:input type="text" path="nombre" class="form-control" id="usuario"
								name="usuario" placeholder="Usuario" autofocus="autofocus"
								required="required"/>
						</div>

						<div class="col-12"></div>
						<div class="input-group col-md-12">

							<f:input path="contrasenia" class="form-control" type="password" id="password"
								name="password" placeholder="Contraseña" required="required"/>
						</div>

						<div class="col-12"></div>
						<button type="submit" class="btn btn-md btn-info col-12">
							<i class="fa fa-sign-in"></i> Iniciar Sesion
						</button>
						 
						<c:choose>
						<c:when test="${fallo!='' }">
						<div style="color: black;"
							class="alert alert-warning alert-dismissable form-control">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">x</button>
							<strong>Info!</strong>
							${fallo }
						</div>
						</c:when>
						</c:choose>
					</div>
				</f:form>




			</nav>

			<div
				class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
				<header class="page-header row justify-center">
					<div class="col-md-6 col-lg-8">
						<h1 class="float-left text-center text-md-left">Bienvenido a
							la pagina de padel</h1>
					</div>

					<div
						class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right">
					</div>
				</header>


				<div class="row">
					<div class="col-md-8">
						<div class="row">
						 
							
						</div>
					</div>
					<div class="col-md-4">

						<div class="card border-primary rounded-0">
							<div class="card-header p-0">
								<div class="bg-primary text-white text-center py-2">
									<h3>
										<i class="fa fa-address-card"></i> Registrar
									</h3>

								</div>
							</div>
							<div class="card-body p-3">
								<c:choose>
								<c:when test="${mensaje!='' }">
								<div style="color: black;"
									class="alert alert-warning alert-dismissable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">x</button>
									<strong>Info!</strong>
									${mensaje }
								</div>
								</c:when>
								</c:choose>
								
								<c:url value="/addUsuario" var="addUsu"></c:url>
								<f:form role="form" method="POST" action="${addUsu }" commandName="usuario"
									class="form-check">
									<div class="form-group">
										<div class="input-group">
											<div class="input-group-addon bg-light">
												<i class="fa fa-user text-primary"></i>
											</div>
											<f:input type="text" path="nombre" name="nombre" required="required"
												class="form-control" id="inlineFormInputGroupUsername"
												placeholder="Nombre"/>
										</div>
									</div>
									<div class="form-group">
										<div class="input-group mb-2 mb-sm-0">
											<div class="input-group-addon bg-light">
												<i class="fa fa-envelope text-primary"></i>
											</div>
											<f:input type="email" path="email" name="email" required="required"
												class="form-control" id="inlineFormInputGroupUsername"
												placeholder="Email"/>
										</div>
									</div>
									<div class="form-group">
										<div class="input-group mb-2 mb-sm-0">
											<div class="input-group-addon bg-light">
												<i class="fa fa-key prefix text-primary"></i>
											</div>
											
												<f:input type="password" path="contrasenia" id="clave" required="required"
												 class="form-control"							
												placeholder="contraseña"/>
										</div>
									</div>
									<div class="form-group">
										<div class="input-group mb-2 mb-sm-0">
											<div class="input-group-addon bg-light">
												<i class="fa fa-key prefix text-primary"></i>
											</div>
											<input type="password" id="clave1" required="required"
												onblur="comprobarClave()" class="form-control"
												
												placeholder="repite contraseña"/>

										</div>
									</div>
									<p id="claveDistinta" style="color: red; font-size: 12px;"></p>
									<div class="form-group">
										<div class="input-group mb-2 mb-sm-0">
											<div class="input-group-addon bg-light">
												<i class="fa fa-phone text-primary"></i>
											</div>
											<f:input type="number" path="telefono" id="telefono" onblur="validarTelefono()"
												name="telefono" required="required" class="form-control"
												placeholder="telefono"/>
										</div>
									</div>
									<p id="telefonoError" style="color: red; font-size: 12px;"></p>
									
									<button type="submit" id="btnRegistrar"
										class="btn btn-primary btn-block rounded-0 py-2">
										<i class="fa fa-sign-in"></i> Enviar
									</button>
								</f:form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
		<script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
	<script src="<c:url value="/resources/dist/js/bootstrap.min.js"/>"></script>

	<script src="<c:url value="/resources/js/chart.min.js"/>"></script>
	<script src="<c:url value="/resources/js/chart-data.js"/>"></script>
	<script src="<c:url value="/resources/js/easypiechart.js"/>"></script>
	<script src="<c:url value="/resources/js/easypiechart-data.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/custom.js"/>"></script>
	<script>
		window.onload = function() {
			var chart1 = document.getElementById("line-chart").getContext("2d");
			window.myLine = new Chart(chart1).Line(lineChartData, {
				responsive : true,
				scaleLineColor : "rgba(0,0,0,.2)",
				scaleGridLineColor : "rgba(0,0,0,.05)",
				scaleFontColor : "#c5c7cc"
			});
		};
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>

</body>
</html>