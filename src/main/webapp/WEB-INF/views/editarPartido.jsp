<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>


<%@page import="es.altair.springhibernate.bean.Usuarios"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html lang="en">
<head>
<script type="text/javascript">
function cambiar(event) {
	 if(event.value==1){
		 document.getElementById("ganador1").value=document.getElementById("1jugador").value;
		 document.getElementById("ganador2").value=document.getElementById("2jugador").value;
	 }else{
		 document.getElementById("ganador1").value=document.getElementById("3jugador").value;
		 document.getElementById("ganador2").value=document.getElementById("4jugador").value;
	 }
}

</script>

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
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="/gestionarPartidos"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Gestionar Partidos
					</a></li>
					<li class="nav-item"><a class="nav-link"
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
						<a class="dropdown-item" href="<c:url value="/editar"/>"><em
							class="fa fa-cog mr-1"></em> Editar Perfil</a> <a
							class="dropdown-item" href="<c:url value="/logout"/>"><em
							class="fa fa-power-off mr-1"></em> Cerrar Sesion</a>
					</div>
				</div>
				<div class="clear"></div>
			</header>
			<div class="row">				
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="card border-primary rounded-0">
							<div class="card-header p-0">
								<div class="bg-primary text-white text-center py-2">
									<h3>
										 Editar Partido
									</h3>
								</div>
							</div>
							<div class="card-body p-3">
							<c:url value="/modificarFechaPista" var="editFechaPista"></c:url>
							<f:form role="form" method="POST" action="${editFechaPista }"
								commandName="partido" class="form-check">
								<input type="number" value="${partido.getPista().getIdPista()}" style="display: none;" name="pista1"
						name="idPista"  />
								<div class="form-group">
								<label style="margin-right: 3px;">Fecha: </label>
									<div class="input-group">
										<div class="well">
											<div id="datetimepicker1" class="input-append date">
											
												<input name="fechaPartido" value="${partido.getFechaPartido().getYear() + 1900}-${String.format('%02d', partido.getFechaPartido().getMonth()+1)}-${String.format('%02d', partido.getFechaPartido().getDate())}T${String.format('%02d', partido.getFechaPartido().getHours())}:${String.format('%02d', partido.getFechaPartido().getMinutes())}"  type="datetime-local"/>						
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
									<label style="margin-right: 3px;">Pista: </label>
								<select name="pistas">
									<c:forEach items="${pistas}" var="pista">
										<c:choose>
											<c:when test="${pista.getNombre()==partido.getPista().getNombre()}">
												<option selected="sel" value="${pista.getIdPista()}">${pista.getNombre()}</option>
											</c:when>
											<c:otherwise>
												<option value="${pista.getIdPista()}">${pista.getNombre()}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								</div></div>
								<button type="submit" id="btnEditar"
									class="btn btn-primary btn-block rounded-0 py-2">
									 Editar
								</button>
							</f:form>
						</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2">
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4">
						<div class="card border-primary rounded-0">
							<div class="card-header p-0">
								<div class="bg-primary text-white text-center py-2">
									<h3>
										 Ganadores Partido
									</h3>
								</div>
							</div>
							<div class="card-body p-3">
							<c:url value="/ganadoresPartido" var="fin"></c:url>
							<f:form role="form" method="POST" action="${fin }"
								commandName="partido" class="form-check">
						<input type="number" style="display: none;" value="${partido.idJugador1.idUsuario }" 
						 id="1jugador" />
						<input type="number" style="display: none;" value="${partido.idJugador2.idUsuario }"
						id="2jugador"  />
						<input type="number" style="display: none;" value="${partido.idJugador3.idUsuario }"
						id="3jugador"  />
						<input type="number" style="display: none;" value="${partido.idJugador4.idUsuario }"
						id="4jugador"  />
								<f:input type="number" style="display: none;" path="idGanador1"
						name="idGanador1" id="ganador1" />
								<f:input type="number" style="display: none;" path="idGanador2"
						name="idGanador2" id="ganador2" />
								<div class="form-group">
									<div class="input-group">
										<div class="form-group">
											<div class="input-group mb-2 mb-sm-0">
											<div class="col-5">
											<div class="card" style="padding: 2px;">
												<input type="radio" required="required" onchange="cambiar(this)" name="tipode" id="ganadores1"
													value="1"  /> ${partido.idJugador1.nombre }  ${partido.idJugador2.nombre } <br>
											</div>
											</div>
											<div class="col-2"></div>
											<div class="col-5">
											<div class="card" style="padding: 2px;">
												<input type="radio" required="required" onchange="cambiar(this)" name="tipode" id="ganadores2" value="2"
													 /> ${partido.idJugador3.nombre }  ${partido.idJugador4.nombre }
											</div>
											</div>
											</div>
										</div>
									</div>
								</div>
								<a data-toggle="modal" data-target="#actualizar">
								<button id="btnRegistrar"
									class="btn btn-primary btn-block rounded-0 py-2">
									 Elegir Ganadores
								</button>
								</a>
								<div class="modal fade"
											id="actualizar" tabindex="-1"
											role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Ganadores</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">Si eliges los ganadores se cerrara el partido y se actualizará la clasificacion.¿Estás seguro?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">No</button>
														<button type="submit" class="btn btn-primary"
															>Sí</button>
													</div>
												</div>
											</div>
										</div>
								
								
							</f:form>
							
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
	$(document).ready(function(){
		$("select[name=pistas]").change(function(){
			$('input[name=pista1]').val($(this).val());
	            
	        });
		
	});
	
		window.onload = function() {
			$('#datetimepicker').data("DateTimePicker").FUNCTION()
			var chart1 = document.getElementById("line-chart").getContext("2d");
			window.myLine = new Chart(chart1).Line(lineChartData, {
				responsive : true,
				scaleLineColor : "rgba(0,0,0,.2)",
				scaleGridLineColor : "rgba(0,0,0,.05)",
				scaleFontColor : "#c5c7cc"
			});
		};
	</script>
	<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>
	

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>

</body>
</html>