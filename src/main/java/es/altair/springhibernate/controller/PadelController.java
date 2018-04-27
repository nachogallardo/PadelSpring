package es.altair.springhibernate.controller;

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

import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.PistasDao;
import es.altair.springhibernate.dao.UsuariosDao;



@Controller
public class PadelController {
	@Autowired
	private UsuariosDao usuariosDao;
	@Autowired
	private PistasDao pistasDao;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView inicio(@RequestParam(value="fallo",required=false,defaultValue="") String fallo,Model model,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje) {	
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("fallo",fallo);
		return new ModelAndView("index","usuario",new Usuarios());
	}
	@RequestMapping(value="/addUsuario", method=RequestMethod.POST)
	public String addUsuario(@ModelAttribute Usuarios usu) {
	
		int filas = 0;
		String msg = "";		
		
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
			msg = "Nombre de usuario ya registrado. Int�ntelo con otro";
			
			return "redirect:/?mensaje="+msg;
		}
	}
	@RequestMapping(value="/inicioSesion", method=RequestMethod.POST)
	public String login(@ModelAttribute Usuarios usu,HttpSession sesion) {
		usu=usuariosDao.comprobarUsuario(usu.getNombre(), usu.getContrasenia());
		
		if (usu!=null) {
			// Usuario correcto
			// Poner al usuario en sesi�n
		
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
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public String usuarioNormal(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return "usuario";
	}
	@RequestMapping(value="/administrador", method=RequestMethod.GET)
	public String administrador(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		model.addAttribute("listaUsuarios",usuariosDao.listarUsuarios());	
		return "administrador";
	}
	@RequestMapping(value="/gestionPistas", method=RequestMethod.GET)
	public ModelAndView gestionPistas(@RequestParam(value="info",required=false,defaultValue="")String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index");
		}
		model.addAttribute("info",info);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		model.addAttribute("listaPistas",pistasDao.listarPistas());	
		return new ModelAndView("gestionarPistas","pista",new Pistas());
	}
	@RequestMapping(value="/addPista", method=RequestMethod.POST)
	public String addPista(@ModelAttribute Pistas pista,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		int filas = 0;
		String msg = "";		
		
		if (!pistasDao.validarPista(pista)) {
			filas = pistasDao.insertar(pista);
			if (filas == 1) {
				msg = "Pista creada";
				
				return "redirect:/gestionPistas?info="+msg;
			}
			else {
				msg = "Error al crear la pista";
				
				return "redirect:/gestionPistas?mensaje="+msg;
			}
		} else {
			msg = "Nombre de pista ya registrado. Int�ntelo con otro";
			
			return "redirect:/gestionPistas?info="+msg;
		}
	}
	@RequestMapping(value="/borrarPista",method=RequestMethod.GET)
	public String borrarPista(@RequestParam("idPista") String idPista, Model model,HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		int id=Integer.parseInt(request.getParameter("idPista"));		
		
		pistasDao.borrarPista(id);
		return "redirect:/gestionPistas";
	}
	@RequestMapping(value="/borrarUsuario",method=RequestMethod.GET)
	public String borrarUsuario(@RequestParam("idUsuario") String idUsuario, Model model,HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		int id=Integer.parseInt(request.getParameter("idUsuario"));		
		
		usuariosDao.borrarUsuario(id);
		return "redirect:/administrador";
	}
	
	@RequestMapping(value="/editarAdmin",method= RequestMethod.GET)
	public ModelAndView editarAdmin(@RequestParam(value="info",required=false,defaultValue="") String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index","usuario",new Usuarios());
		}
		model.addAttribute("info",info);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPerfilAdmin","usuario",sesion.getAttribute("usuLogeado"));
	}
	
	@RequestMapping(value="/editaPerfilAdministrador",method= RequestMethod.POST)
	public String editarPerfilAdmin(@ModelAttribute Usuarios usu,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		usuariosDao.Editar(usu.getIdUsuario(),usu.getNombre(),usu.getEmail(),usu.getTelefono(),1);
		sesion.setAttribute("usuLogeado", usu);
		if (usuariosDao.validarUsuario(usu)) {
			
		    return "redirect:/editarAdmin?info=Usuario Editado";
		}else {
		  return "redirect:/editarAdmin";
		}
	}
	
	
	@RequestMapping(value="/editaPerfil",method= RequestMethod.POST)
	public String editaPerfil(@ModelAttribute Usuarios usu,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		usuariosDao.Editar(usu.getIdUsuario(),usu.getNombre(),usu.getEmail(),usu.getTelefono(),2);
		sesion.setAttribute("usuLogeado", usu);
		if (usuariosDao.validarUsuario(usu)) {
			
		    return "redirect:/editar?info=Usuario Editado";
		}else {
		  return "redirect:/editar";
		}
	}
	
	
	@RequestMapping(value="/editar",method= RequestMethod.GET)
	public ModelAndView editar(@RequestParam(value="info",required=false,defaultValue="") String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index","usuario",new Usuarios());
		}
		model.addAttribute("info",info);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPerfil","usuario",sesion.getAttribute("usuLogeado"));
	}
	@RequestMapping(value="/editarOtroUsuario",method=RequestMethod.GET)
	public ModelAndView editarOtroUsuario(@RequestParam("idUsuario") String idUsuario,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index","usuario",new Usuarios());
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		int id=Integer.parseInt(request.getParameter("idUsuario"));		
		Usuarios usu=usuariosDao.usuarioPorId(id);
		return new ModelAndView("editarOtroPerfil","usuario",usu);
	}
	
	@RequestMapping(value="/editarOtroPerfil",method= RequestMethod.POST)
	public String editaOtroPerfil(@ModelAttribute Usuarios usuario,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		usuariosDao.Editar(usuario.getIdUsuario(),usuario.getNombre(),usuario.getEmail(),usuario.getTelefono(),usuario.getTipoUsuario());
		if (!usuariosDao.validarUsuario(usuario)) {
			
		    return "redirect:/administrador";
		}else {
		  return "redirect:/administrador";
		}
	}
	
}