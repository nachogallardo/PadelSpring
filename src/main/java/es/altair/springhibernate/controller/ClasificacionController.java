package es.altair.springhibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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

import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.ClasificacionString;
import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.ClasificacionDao;
import es.altair.springhibernate.dao.PistasDao;
import es.altair.springhibernate.dao.TorneoDao;
@Controller
public class ClasificacionController {
	@Autowired
	private ClasificacionDao clasificacionDao;
	@Autowired
	private TorneoDao torneoDao;
	@RequestMapping(value="/clasificacion", method=RequestMethod.GET)
	public ModelAndView clasificacion(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		List<Clasificacion> clasi =clasificacionDao.listarClasificacion(sesion.getAttribute("torneo"));
		List<ClasificacionString> clasificacion = new ArrayList<ClasificacionString>();
		for (Clasificacion clasificacion1 : clasi) {
			
			clasificacion.add(new ClasificacionString(clasificacion1.getUsuario().getNombre(), clasificacion1.getTorneo().getNombre(), clasificacion1.getPuntos(), clasificacion1.getPartJugados()));
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		model.addAttribute("nombreTorneo",((Torneo)sesion.getAttribute("torneo")).getNombre());
		if(((Usuarios)sesion.getAttribute("usuLogeado")).getTipoUsuario()==1)
		 return new ModelAndView("clasificacionActualAdmin","clasificacion",clasificacion);
		else
			return new ModelAndView("clasificacionActualUsuario","clasificacion",clasificacion);
	}
	
	@RequestMapping(value="/otraClasificacion",method=RequestMethod.GET)
	public ModelAndView otraClasificacion(@RequestParam("idTorneo") String idTorneo,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/","usuario",new Usuarios());
		}
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		int id=Integer.parseInt(request.getParameter("idTorneo"));
		Torneo t1=torneoDao.torneoPorId(id);
		model.addAttribute("nombreTorneo",t1.getNombre());
		List<Clasificacion> clasi =clasificacionDao.listarClasificacion(t1);
		List<ClasificacionString> clasificacion = new ArrayList<ClasificacionString>();
		for (Clasificacion clasificacion1 : clasi) {
			
			clasificacion.add(new ClasificacionString(clasificacion1.getUsuario().getNombre(), clasificacion1.getTorneo().getNombre(), clasificacion1.getPuntos(), clasificacion1.getPartJugados()));
		}
		
		return new ModelAndView("clasificacionSegunTorneo","clasificacion",clasificacion);
	}
	
}
