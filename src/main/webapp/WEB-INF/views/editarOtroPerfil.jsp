<!DOCTYPE html>
<%@page import="es.altair.springhibernate.dao.UsuariosDaoImp"%>
<%@page import="es.altair.springhibernate.dao.UsuariosDao"%>
<%@page import="es.altair.springhibernate.bean.Usuarios"%>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<head>
<script>
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
	function validarTipo() {
		if (document.getElementById("tipoUser").value > 2) {
			document.getElementById("tipoUser").value = 2;
		} else if (document.getElementById("tipoUser").value < 1) {
			document.getElementById("tipoUser").value = 1;
		} else {
			return;
		}
	}
	function cambiar(event) {
		document.getElementById("tipo").value = event.value;
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
<link href="<c:url value="/resources/dist/css/bootstrap.min.css"/>"
	rel="stylesheet">

<!--Fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Icons -->
<link href="<c:url value="/resources/css/font-awesome.css"/>"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

</head>
<body>


	<div class="container-fluid" id="wrapper">
		<div class="row">
			<nav
				class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
				<h1 class="site-title">
					<a href="#"><em class="fa fa-rocket"></em> Viajes</a>
				</h1>

				<a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em
					class="fa fa-bars"></em></a>

				<ul class="nav nav-pills flex-column sidebar-nav">
					<li class="nav-item"><a class="nav-link active"
						href="<c:url value="administrador"/>"><em
							class="fa fa-user-circle mr-1"></em> Editar Usuarios <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/editarAdmin"/>"><em
							class="fa fa-cog mr-1"></em> Editar Perfil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/gestionPistas"/>"><em class="fa fa-cog mr-1"></em>
							Gestionar Pistas</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/creaTorneo"/>">
						<em class="fa fa-plus-circle" aria-hidden="true"></em> Creación Torneo</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/clasificacion"/>"> <em
							class="fa fa-plus-circle" aria-hidden="true"></em> Clasificacion Actual
					</a></li>
					<li class="nav-item"><a class="nav-link"
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
					<h1 class="float-left text-center text-md-left">
						Bienvenido
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
			<div style="margin-left: 15%;" class="col-md-6">
				<div class="bg-success text-white text-center py-2">
					<h3>
						<i class="fa fa-address-card"></i> Editar
					</h3>

				</div>
				<br>
				<%
					String info = request.getParameter("info");
					if (info != null) {
				%>
				<div style="color: black;"
					class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">x</button>
					<strong>Info!</strong>
					<%=info%>
				</div>
				<%
					}
				%>
				<c:url value="/editarOtroPerfil" var="editaOtro"></c:url>
				<f:form role="form" commandName="usuario" method="POST"
					action="${editaOtro }" class="form-check">
					<f:input style="display: none;" type="number" path="idUsuario"
						name="idUsuario" required="required" class="form-control"
						placeholder="Nombre" />
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon bg-light">
								<i class="fa fa-user text-primary"></i>
							</div>
							<f:input type="text" path="nombre" name="nombre"
								required="required" class="form-control" placeholder="Nombre" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group mb-2 mb-sm-0">
							<div class="input-group-addon bg-light">
								<i class="fa fa-envelope text-primary"></i>
							</div>
							<f:input type="email" path="email" name="email"
								required="required" class="form-control" placeholder="Email" />
						</div>
					</div>
					<f:input type="number" style="display: none;" path="tipoUsuario"
						name="tipoUsuario" id="tipo" />
					<div class="form-group">
						<div class="input-group mb-2 mb-sm-0">
						
							<input type="radio" name="tipode" id="administrador" value="1" onchange="cambiar(this)"/> Administrador
							<br>
							<input type="radio" name="tipode" id="usu" value="2" onchange="cambiar(this)"/> Usuario
							
						</div>
					</div>					
					<div class="form-group">
						<div class="input-group mb-2 mb-sm-0">
							<div class="input-group-addon bg-light">
								<i class="fa fa-phone text-primary"></i>
							</div>
							<f:input type="number" path="telefono" id="telefono"
								onblur="validarTelefono()" name="telefono" required="required"
								class="form-control" placeholder="telefono" />
						</div>
					</div>
					<p id="telefonoError" style="color: red; font-size: 12px;"></p>
					<button type="submit" id="btnRegistrar"
						class="btn btn-info btn-block rounded-0 py-2">
						<i class="fa fa-pencil" aria-hidden="true"></i> Enviar
					</button>
				</f:form>

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
	function permisos(){
		var permiso=document.getElementById("tipo").value;
		if(permiso==2){
			document.getElementById("administrador").checked=false;
			document.getElementById("usu").checked=true;
		}
		if(permiso==1){
			document.getElementById("usu").checked=false;
			document.getElementById("administrador").checked=true;
		}
		
	}
		window.onload = function() {
			permisos();
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