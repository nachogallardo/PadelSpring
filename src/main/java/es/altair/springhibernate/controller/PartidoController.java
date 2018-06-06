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

import es.altair.springhibernate.bean.PartidoString;
import es.altair.springhibernate.bean.Partidos;
import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.PartidosDao;
import es.altair.springhibernate.dao.PistasDao;

@Controller
public class PartidoController {
	@Autowired
	private PartidosDao partidosDao;
	@Autowired
	private PistasDao pistasDao;
	
	@RequestMapping(value="/gestionarPartidos", method=RequestMethod.GET)
	public ModelAndView gestionarPartidos(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		List<Partidos>parti=new ArrayList<Partidos>();
		List<PartidoString> partidos= new ArrayList<PartidoString>();
		parti=partidosDao.listarPartidos();
		for (Partidos p : parti) {
			partidos.add(new PartidoString(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDay()-1,p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada(),p.getIdPartido()));
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("gestionarPartidos","listaPartidos",partidos);
	}
	@RequestMapping(value="/editarPartido", method=RequestMethod.GET)
	public ModelAndView editarPartido(@RequestParam("idPartido") String idPartido,Model model,HttpSession sesion, HttpServletResponse response, HttpServletRequest request) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		int id=Integer.parseInt(idPartido);
		Partidos p = partidosDao.partidoPorIdPartido(id);
		List<Pistas> pistas= pistasDao.listarPistas();
		model.addAttribute("partido",p);
		model.addAttribute("pistas",pistas);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPartido");
	}
	
	/*
	 * @RequestMapping(value="/editarOtroUsuario",method=RequestMethod.GET)
	public ModelAndView editarOtroUsuario(@RequestParam("idUsuario") String idUsuario,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/","usuario",new Usuarios());
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
	 * 
	 * 
	 * */
	
}