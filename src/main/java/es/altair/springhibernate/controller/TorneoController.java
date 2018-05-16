package es.altair.springhibernate.controller;


import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.helpers.DateTimeDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.Partidos;
import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.ClasificacionDao;
import es.altair.springhibernate.dao.PartidosDao;
import es.altair.springhibernate.dao.PistasDao;
import es.altair.springhibernate.dao.PistasDaoImp;
import es.altair.springhibernate.dao.TorneoDao;
import es.altair.springhibernate.dao.UsuariosDao;

@Controller
public class TorneoController {
	@Autowired
	private TorneoDao torneoDao;
	@Autowired
	private UsuariosDao usuariosDao;
	@Autowired
	private ClasificacionDao clasificacionDao;
	@Autowired
	private PartidosDao partidosDao;
	@Autowired
	private PistasDao pistasDao;
	
	@RequestMapping(value="/creaTorneo", method=RequestMethod.GET)
	public ModelAndView crearTorneo(@RequestParam(value="info",required=false,defaultValue="")String info,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index");
		}
		model.addAttribute("info",info);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		List<Usuarios> usuarios =usuariosDao.listarUsuarios();
		List<Usuarios> usuariosDisponibles=new ArrayList<Usuarios>();
		for (Usuarios usuarios2 : usuarios) {
			if(usuarios2.getTipoUsuario()==1) {
				continue;
			}else {
				usuariosDisponibles.add(usuarios2);
			}
		}
		model.addAttribute("listaUsuarios",usuariosDisponibles);	
		return new ModelAndView("crearTorneo","torneo",new Torneo());
	}
	
	
	@RequestMapping(value="/listaTorneos", method=RequestMethod.GET)
	public ModelAndView listaTorneo(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("index");
		}		
		return new ModelAndView("listarTorneos","listaTorneos",torneoDao.listarTorneos());
	}
	
	@RequestMapping(value="/addTorneo", method=RequestMethod.POST)
	public String addTorneo(HttpSession sesion,@ModelAttribute Torneo torneo,Model model,@RequestParam("jugones") String[] jugones) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}		
		int filas = 0;
		String msg = "";	
		List<Pistas> pistas;
		pistas=pistasDao.listarPistas();
		List<Usuarios> usuPartidos= new ArrayList<Usuarios>();
		if (!torneoDao.validarTorneo(torneo)) {
			filas = torneoDao.insertar(torneo);
			if (filas == 1) {
				msg = "Torneo creado";
				partidosDao.borrarPartidos();
				List<Usuarios> todosUsuarios = usuariosDao.listarUsuarios();
				for (Usuarios usuarios : todosUsuarios) {
					if(usuarios.getTipoUsuario()==1)
						continue;
					else
					usuariosDao.Editar(usuarios.getIdUsuario(), usuarios.getNombre(), usuarios.getEmail(), usuarios.getTelefono(), 2);
				}
				Torneo t1=torneoDao.torneoPorNombre(torneo.getNombre());
				sesion.setAttribute("torneo", t1);
				for (String id : jugones) {
					int idUsuario=Integer.parseInt(id);
					Usuarios usu = usuariosDao.usuarioPorId(idUsuario);
					usu.setTipoUsuario(3);
					usuPartidos.add(usu);
					usuariosDao.Editar(usu.getIdUsuario(), usu.getNombre(), usu.getEmail(), usu.getTelefono(), usu.getTipoUsuario());
					Clasificacion clasificacion = new Clasificacion(0, 0, t1, usu);
					clasificacionDao.insertar(clasificacion);
					
				}				
				Partidos p = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(0),
				usuPartidos.get( 1), usuPartidos.get( 2), usuPartidos.get(3), 0, 0, t1);
				partidosDao.insert(p);
				try {
				if(usuPartidos.get(7)!=null) {
					Partidos p1 = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(4),
					usuPartidos.get(5), usuPartidos.get(6), usuPartidos.get(7), 0, 0, t1);
					partidosDao.insert(p1);					
				}
				}catch (Exception e) {
				}
				try {
				if(usuPartidos.get(11)!=null) {
					Partidos p2 = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(8),
					usuPartidos.get(9), usuPartidos.get(10), usuPartidos.get(11), 0, 0, t1);
				    partidosDao.insert(p2);				 
				}
				}
				catch (Exception e) {
				}
				try {
				if(usuPartidos.get(15)!=null) {
					Partidos p3 = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(12),
					usuPartidos.get( 13), usuPartidos.get( 14), usuPartidos.get(15), 0, 0, t1);
					partidosDao.insert(p3);
				}
				}catch (Exception e) {
				}				
				return "redirect:/creaTorneo?info="+msg;
			}
			else {
				msg = "Error al crear el torneo";
				
				return "redirect:/creaTorneo?info="+msg;
			}
		} else {
			msg = "Nombre de torneo ya registrado. Inténtelo con otro";
			
			return "redirect:/creaTorneo?info="+msg;
			
		}
	
		
		
		
	}
}
