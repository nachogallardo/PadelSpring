<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>


<%@page import="es.altair.springhibernate.bean.Usuarios"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html lang="en">
<head>
<style>
.title-header {
	padding: .75rem 1.25rem;
	background-color: #f5f5f5;
	border-bottom: 1px solid transparent;
}

.title-header h3 {
	font-size: 0.80rem;
	margin: 0;
}

.movies {
	margin-top: 2rem;
}

.img-card {
	width: 100%;
	margin-bottom: .40rem;
}

.movies {
	margin-bottom: .60rem;
}

.series {
	margin-bottom: .60rem;
}

.footer {
	padding: 1rem 0;
	margin-top: 2rem;
	font-size: 80%;
	text-align: left;
}

.footer p {
	margin: 0;
}

.footer-links {
	padding-left: 0;
	margin-bottom: 1rem;
}

.footer-links li {
	display: inline-block;
}

.footer a {
	font-weight: 500;
	color: inherit;
}

.footer-links li+li {
	margin-left: 1rem;
}
/* Bug Bootstrap V4.0.6 - Mobile - SCSS _navbar*/
.navbar {
	display: block;
}
</style>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="description" content="">

<meta name="author" content="">



<title>Padel</title>
<link rel="icon" href="<c:url value="/resourcesimages/favicon.ico"/>">
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
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="usuario"/>"><em
							class="fa fa-user-circle mr-1"></em> Principal <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/editar"/>"><em class="fa fa-cog mr-1"></em>
							Editar Perfil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/clasificacion"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Clasificacion
							Actual
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/listaTorneos"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Torneos
					</a></li>	
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/listaPagos"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Ver mis pagos
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
						<a class="dropdown-item" href="<c:url value="/editar"/>"><em
							class="fa fa-cog mr-1"></em> Editar Perfil</a> <a
							class="dropdown-item" href="<c:url value="/logout"/>"><em
							class="fa fa-power-off mr-1"></em> Cerrar Sesion</a>

					</div>
				</div>
				<div class="clear"></div>
			</header>
			<div class="row">
			<div class="col-4"></div>
				<div class="col-4">
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
					<c:choose>
						<c:when test="${asistencia!=''&&info==''}">
							<a data-toggle="modal"
								data-target="#asistencia">
								<button class="btn btn-sm btn-outline-danger" value="left"
									type="button">
									Cancelar Asistencia al Partido
								</button>
							</a>
							<div class="modal fade" id="asistencia"
								tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Confirmar Asistencia Partido</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">¿Quieres que se envie un correo electrónico a todos los jugadores reserva para que alguno juegue en su lugar?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">No</button>
											<button type="button" class="btn btn-primary"
												onclick="location.href='<c:url value="/asistencia"/>'">Sí</button>
										</div>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
				</div>
				
			</div>
			<br>
			<div class="row">
				<c:forEach items="${listaPartidos}" var="p">
					<div class="col-lg-6 col-md-6 col-sm-6">
						<article class="card">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12" style="margin: 2%;">
									<h5 class="tagline card-text text-xs-center"
										style="text-align: center;">${p.jug1 } y ${p.jug2 }</h5>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<p style="text-align: center;">Pista: ${p.pista }</p>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<p style="text-align: center;">Jornada: ${p.numJornada }</p>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<p style="text-align: center;">Fecha: ${p.dia }/${p.mes }/${p.anio }
										${p.hora }:${p.minutos }</p>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12" style="margin: 2%;">
									<h5 class="tagline card-text text-xs-center"
										style="text-align: center;">${p.jug3 } y ${p.jug4 }</h5>
								</div>
							</div>
						</article>
					</div>
				</c:forEach>
			</div>
			<br>
			<div class="row">
				<div class="col-6">
					<p>Pagar la mensualidad del club:</p>
					<form id="form2" action=<c:url value="/pagarMes"/>></form>								
					<form id="form1" action="https://www.paypal.com/cgi-bin/webscr" method="post"
						target="_top" >
						<input type="hidden" name="cmd" value="_s-xclick"> <input
							type="hidden" name="hosted_button_id" value="3V4BERJLSCZ26">
							
						<input type="image"
							src="https://www.paypalobjects.com/es_ES/ES/i/btn/btn_paynowCC_LG.gif"
							border="0" name="submit"
							alt="PayPal, la forma rápida y segura de pagar en Internet." formtarget="_blank">
							
						<img alt="" border="0"
							src="https://www.paypalobjects.com/es_ES/i/scr/pixel.gif"
							width="1" height="1">							
					</form>	
								
					
				</div>
				<div class="col-6">
					<c:choose>
						<c:when test="${infoPago!=''}">
							<div style="color: black;"
								class="alert alert-warning alert-dismissable">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">x</button>
								<strong>Info!</strong> ${infoPago }
							</div>
						</c:when>
					</c:choose>
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
		 $("#form1").on('submit', function(evt){
			    evt.preventDefault();  
			    $("#form2").submit();
			 });
	</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>

</body>
</html>