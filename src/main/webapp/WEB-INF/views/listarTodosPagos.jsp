<!DOCTYPE html>

<%@page import="es.altair.springhibernate.bean.Usuarios"%>
<html lang="en">
<head>

<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="description" content="">

<meta name="author" content="">


<title>Padel</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
				<h1 class="site-title">
					<a href="#"><em class="fa fa-futbol-o" aria-hidden="true"></em>
						Padel</a>
				</h1>

				<a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em
					class="fa fa-bars"></em></a>

				<ul class="nav nav-pills flex-column sidebar-nav">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="administrador"/>"><em
							class="fa fa-user-circle mr-1"></em> Editar Usuarios <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/editarAdmin"/>"><em
							class="fa fa-cog mr-1"></em> Editar Perfil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/gestionPistas"/>"><em
							class="fa fa-cog mr-1"></em> Gestionar Pistas</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/creaTorneo"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Creación
							Torneo
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/clasificacion"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Clasificacion Actual
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/gestionarPartidos"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Gestionar Partidos
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/listarTodosPagos"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Listar Pagos
					</a></li>
				</ul>

				<a href="<c:url value="/logout"/>" class="logout-button"><em
					class="fa fa-power-off"></em> Cerrar Sesion</a>
			</nav>

			<main
				class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
			<header class="page-header row justify-center">
				<div class="col-md-6 col-lg-8">
					<h1 class="float-left text-center text-md-left">Bienvenido
						${usuLogeado.nombre }</h1>
				</div>

				<div
					class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right">
					<a class="btn btn-stripped dropdown-toggle" href="#"
						id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">


						<div class="username mt-1">
							<h4 class="mb-1">${usuLogeado.nombre }</h4>

							<h6 class="text-muted">Opciones</h6>
						</div>
					</a>

					<div class="dropdown-menu dropdown-menu-right"
						style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item" href="<c:url value="/editarAdmin"/>"><em
							class="fa fa-cog mr-1"></em> Editar Perfil</a> <a
							class="dropdown-item" href="<c:url value="/logout"/>"><em
							class="fa fa-power-off mr-1"></em> Cerrar Sesion</a>
					</div>
				</div>

				<div class="clear"></div>
			</header>
			<c:url value="/pdfAdmin" var="iniciar"></c:url>
			<f:form role="form" method="POST" action="${iniciar }" 
					class="form-check">
					
						<button type="submit" class="btn btn-md btn-info col-12">
							 Generar Pdf
						</button>
						 
				</f:form>
				<c:choose>
						<c:when test="${info!='' }">
						<div style="color: black;"
							class="alert alert-warning alert-dismissable form-control">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">x</button>
							<strong>Info!</strong>
							${info }
						</div>
						</c:when>
						</c:choose>
			<div style="margin-left: 15%;" class="col-md-6">
			<div class="row"><h3>Mis Pagos</h3></div>
			<div class="row">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Nombre</th>
							<th scope="col">Fecha</th>
							<th scope="col">Pago</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagosString}" var="p">
							<tr>
								<td>${ p.nombre}</td>
								<td>${ p.fecha}</td>
								<td>20 euros</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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