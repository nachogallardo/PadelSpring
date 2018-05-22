package es.altair.springhibernate.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.altair.springhibernate.bean.PartidoString;
import es.altair.springhibernate.bean.Partidos;
import es.altair.springhibernate.dao.PartidosDao;

@Controller
public class PartidoController {
	@Autowired
	private PartidosDao partidosDao;
	
	
	@RequestMapping(value="/gestionarPartidos", method=RequestMethod.GET)
	public ModelAndView gestionarPartidos(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		List<Partidos>parti=new ArrayList<Partidos>();
		List<PartidoString> partidos= new ArrayList<PartidoString>();
		parti=partidosDao.listarPartidos();
		for (Partidos p : parti) {
			partidos.add(new PartidoString(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDay()-1,p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada()));
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("gestionarPartidos","listaPartidos",partidos);
	}
	@RequestMapping(value="/editarPartido", method=RequestMethod.GET)
	public ModelAndView editarPartido(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPartido");
	}
	
}