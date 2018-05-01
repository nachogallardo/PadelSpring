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
import es.altair.springhibernate.dao.PistasDao;
@Controller
public class PistaController {
	@Autowired
	private PistasDao pistasDao;
	
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
				
				return "redirect:/gestionPistas?info="+msg;
			}
		} else {
			msg = "Nombre de pista ya registrado. Inténtelo con otro";
			
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
}
