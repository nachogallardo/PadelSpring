package es.altair.springhibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.altair.springhibernate.bean.Email;
import es.altair.springhibernate.bean.PartidoString;
import es.altair.springhibernate.bean.PartidoStringGanador;
import es.altair.springhibernate.bean.Partidos;
import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.PartidosDao;
import es.altair.springhibernate.dao.PistasDao;
import es.altair.springhibernate.dao.TorneoDao;
import es.altair.springhibernate.dao.UsuariosDao;



@Controller
public class UsuarioController {
	@Autowired
	private UsuariosDao usuariosDao;
	@Autowired
	private TorneoDao torneoDao;
	@Autowired
	private PartidosDao partidosDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView inicio(HttpSession sesion,@RequestParam(value="fallo",required=false,defaultValue="") String fallo,Model model,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje) {	
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("fallo",fallo);
		List<Torneo> torneos= torneoDao.listarTorneos();
		int idTorneo=0;
		Torneo torneoActual=new Torneo();
		for (Torneo torneo : torneos) {
			if(torneo.getIdTorneo()>idTorneo) {
				idTorneo=torneo.getIdTorneo();
				torneoActual=torneo;
			}
		}
		List<Partidos>parti=new ArrayList<Partidos>();
		List<PartidoString> partidos= new ArrayList<PartidoString>();
		parti=partidosDao.listarPartidosSinGanador();
		for (Partidos p : parti) {
			partidos.add(new PartidoString(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDate(),p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada()));
		}
		sesion.setAttribute("listaPartidos", partidos);
		List<Partidos>partid=new ArrayList<Partidos>();
		List<PartidoStringGanador> partidos1= new ArrayList<PartidoStringGanador>();
		partid=partidosDao.listarPartidosConGanador();
		for (Partidos p : partid) {
			Usuarios ganador1 = usuariosDao.usuarioPorId(p.getIdGanador1());
			Usuarios ganador2 = usuariosDao.usuarioPorId(p.getIdGanador2());
			partidos1.add(new PartidoStringGanador(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDate(),p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada(),ganador1.getNombre(),ganador2.getNombre()));
			
		}
		sesion.setAttribute("listaPartidosTerminados", partidos1);
		sesion.setAttribute("torneo", torneoActual);
		return new ModelAndView("index","usuario",new Usuarios());
	}
	
	@RequestMapping(value="/editarClave",method= RequestMethod.POST)
	public String editarClave(@RequestParam String clave,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		
		usuariosDao.EditarClave(((Usuarios)sesion.getAttribute("usuLogeado")).getIdUsuario(),clave);
		
		if(((Usuarios)sesion.getAttribute("usuLogeado")).getTipoUsuario()==1) {
			return "redirect:/editarAdmin?info=Clave editada";
		}else {
			return "redirect:/editar?info=Clave editada";
		}
		
	}
	@RequestMapping(value="/addUsuario", method=RequestMethod.POST)
	public String addUsuario(@ModelAttribute Usuarios usu) {
	
		int filas = 0;
		String msg = "";		
		usu.setAsistir(0);
		if (!usuariosDao.validarUsuario(usu)) {
			filas = usuariosDao.insertar(usu);
			if (filas == 1) {
				msg = "Usuario Registrado";
				
				return "redirect:/?mensaje="+msg;
			}
			else {
				msg = "Error al Registrar al Usuario";
				
				return "redirect:/?mensaje="+msg;
			}
		} else {
			msg = "Nombre de usuario ya registrado. Intentelo con otro";
			
			return "redirect:/?mensaje="+msg;
		}
	}
	@RequestMapping(value="/inicioSesion", method=RequestMethod.POST)
	public String login(@ModelAttribute Usuarios usu,HttpSession sesion) {
		usu=usuariosDao.comprobarUsuario(usu.getNombre(), usu.getContrasenia());
		if (usu!=null) {
			// Usuario correcto
			// Poner al usuario en sesión
		
			sesion.setAttribute("usuLogeado", usu);
			
			switch (usu.getTipoUsuario()) {
			case 3:
				// Jugador 
				return "redirect:/usuario";
			case 2:
				// Jugador Reserva
				return "redirect:/usuario";
				
			case 1:
				// Administrador
				return "redirect:/administrador";
				
		} 
		}
			// Error Usuario
		return "redirect:/?fallo=Usuario y/o Password Incorrecto";
				
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession sesion) {
		sesion.setAttribute("usuLogeado", null);
		return "redirect:/";
	}
	@RequestMapping(value="/asistencia",method=RequestMethod.GET)
	public String asistencia(HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		Usuarios usu=(Usuarios)sesion.getAttribute("usuLogeado");
		usu.setAsistir(0);
		usuariosDao.editarAsistir(usu);
		Email email= new Email();
		List<Usuarios> usuarios = usuariosDao.listarUsuariosReserva();
		String mensaje="Hay una plaza libre para sustituir a " + ((Usuarios)sesion.getAttribute("usuLogeado")).getNombre()+" en un partido si estas interesado ponte en contacto con el administrador.";
		for (Usuarios usuarios2 : usuarios) {
			
			email.enviarConGMail(usuarios2.getEmail(), "Asistir Partido", mensaje);
		}
		
		return "redirect:/usuario?info=Correo enviado a los jugadores reserva";
	}
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public ModelAndView usuarioNormal(@RequestParam(value="info",required=false,defaultValue="") String info,@RequestParam(value="infoPago",required=false,defaultValue="") String infoPago,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		List<Partidos>parti=new ArrayList<Partidos>();
		List<PartidoString> partidos= new ArrayList<PartidoString>();
		parti=partidosDao.listarPartidosSinGanador();
		for (Partidos p : parti) {
			partidos.add(new PartidoString(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDate(),p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada()));
		}
		model.addAttribute("info",info);
		System.out.println(info);
		model.addAttribute("asistencia","");
		if(((Usuarios)sesion.getAttribute("usuLogeado")).getTipoUsuario()==3&&((Usuarios)sesion.getAttribute("usuLogeado")).getAsistir()==1) {
			model.addAttribute("asistencia","si");
		}
		
		model.addAttribute("infoPago",infoPago);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("usuario","listaPartidos",partidos);
	}
	@RequestMapping(value="/administrador", method=RequestMethod.GET)
	public String administrador(@RequestParam(value="info",required=false,defaultValue="") String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("info",info);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		model.addAttribute("listaUsuarios",usuariosDao.listarUsuarios());	
		return "administrador";
	}
	
	@RequestMapping(value="/borrarUsuario",method=RequestMethod.GET)
	public String borrarUsuario(@RequestParam("idUsuario") String idUsuario, Model model,HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		int id=Integer.parseInt(request.getParameter("idUsuario"));		
		Usuarios usu= usuariosDao.usuarioPorId(id);
		if(usu.getTipoUsuario()==3) {
			return "redirect:/administrador?info=Imposible borrar mientras juege en el torneo activo";
		}
		usuariosDao.borrarUsuario(id);
		return "redirect:/administrador";
	}
	
	@RequestMapping(value="/editarAdmin",method= RequestMethod.GET)
	public ModelAndView editarAdmin(@RequestParam(value="info",required=false,defaultValue="") String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/","usuario",new Usuarios());
		}
		model.addAttribute("info",info);
		sesion.setAttribute("usuEditar",((Usuarios)sesion.getAttribute("usuLogeado")).getNombre());
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPerfilAdmin","usuario",sesion.getAttribute("usuLogeado"));
	}
	
	@RequestMapping(value="/editaPerfilAdministrador",method= RequestMethod.POST)
	public String editarPerfilAdmin(@ModelAttribute Usuarios usu,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		if (!sesion.getAttribute("usuEditar").equals(usu.getNombre())) {
			if (usuariosDao.validarUsuario(usu)) {

				return "redirect:/editarAdmin?info=No se ha podido editar, nombre en uso";
			}
		}
		usuariosDao.Editar(usu.getIdUsuario(),usu.getNombre(),usu.getEmail(),usu.getTelefono(),1);
		sesion.setAttribute("usuLogeado", usu);
		
		  return "redirect:/editarAdmin?info=Usuario editado";
		
	}
	
	
	@RequestMapping(value="/editaPerfil",method= RequestMethod.POST)
	public String editaPerfil(@ModelAttribute Usuarios usu,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		if (!sesion.getAttribute("usuEditar").equals(usu.getNombre())) {
			if (usuariosDao.validarUsuario(usu)) {

				return "redirect:/editar?info=No se ha podido editar, nombre en uso";
			}
		}
		usuariosDao.Editar(usu.getIdUsuario(),usu.getNombre(),usu.getEmail(),usu.getTelefono(),2);
		sesion.setAttribute("usuLogeado", usu);
		
		  return "redirect:/editar?info=Usuario Editado";
		
	}
	
	
	@RequestMapping(value="/editar",method= RequestMethod.GET)
	public ModelAndView editar(@RequestParam(value="info",required=false,defaultValue="") String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/","usuario",new Usuarios());
		}
		model.addAttribute("info",info);
		sesion.setAttribute("usuEditar",((Usuarios)sesion.getAttribute("usuLogeado")).getNombre());
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPerfil","usuario",sesion.getAttribute("usuLogeado"));
	}
	@RequestMapping(value="/editarOtroUsuario",method=RequestMethod.GET)
	public ModelAndView editarOtroUsuario(@RequestParam("idUsuario") String idUsuario,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/","usuario",new Usuarios());
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		
		int id=Integer.parseInt(request.getParameter("idUsuario"));		
		Usuarios usu=usuariosDao.usuarioPorId(id);
		sesion.setAttribute("usuEditar",usu.getNombre());
		return new ModelAndView("editarOtroPerfil","usuario",usu);
	}
	
	@RequestMapping(value="/editarOtroPerfil",method= RequestMethod.POST)
	public String editaOtroPerfil(@ModelAttribute Usuarios usuario,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		
		if (!sesion.getAttribute("usuEditar").equals(usuario.getNombre())) {
			if (usuariosDao.validarUsuario(usuario)) {

				return "redirect:/administrador?info=No se ha podido editar, nombre en uso";
			}
		}
		usuariosDao.Editar(usuario.getIdUsuario(),usuario.getNombre(),usuario.getEmail(),usuario.getTelefono(),usuario.getTipoUsuario());
		  return "redirect:/administrador?info=Usuario editado";
		
	}
	
}