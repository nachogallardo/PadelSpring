package es.altair.springhibernate.controller;



import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

import com.mysql.jdbc.Util;

import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.PartidoString;
import es.altair.springhibernate.bean.Partidos;
import es.altair.springhibernate.bean.Pistas;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.ClasificacionDao;
import es.altair.springhibernate.dao.PartidosDao;
import es.altair.springhibernate.dao.PistasDao;
import es.altair.springhibernate.dao.UsuariosDao;

@Controller
public class PartidoController {
	@Autowired
	private PartidosDao partidosDao;
	@Autowired
	private PistasDao pistasDao;
	@Autowired
	private ClasificacionDao clasificacionDao;
	@Autowired
	private UsuariosDao usuariosDao;
	
	@RequestMapping(value="/gestionarPartidos", method=RequestMethod.GET)
	public ModelAndView gestionarPartidos(@RequestParam(value="terminar",required=false,defaultValue="")String terminar,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return new ModelAndView("redirect:/");
		}
		List<Partidos>partidosSinAcabar=new ArrayList<Partidos>();
		partidosSinAcabar=partidosDao.listarPartidosSinGanador();
		
		if(partidosSinAcabar.size()==0) {
			terminar="siguiente";
		}
		model.addAttribute("terminar",terminar);
		List<Partidos>parti=new ArrayList<Partidos>();
		List<PartidoString> partidos= new ArrayList<PartidoString>();
		parti=partidosDao.listarPartidosSinGanador();
		for (Partidos p : parti) {
			partidos.add(new PartidoString(p.getIdJugador1().getNombre(), p.getIdJugador2().getNombre(), p.getIdJugador3().getNombre(), p.getIdJugador4().getNombre(), p.getFechaPartido().getYear()+1900,p.getFechaPartido().getMonth()+1,p.getFechaPartido().getDate(),p.getFechaPartido().getHours(),p.getFechaPartido().getMinutes(), p.getPista().getNombre(), p.getNumJornada(),p.getIdPartido()));
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
		sesion.setAttribute("idPartido",p.getIdPartido());
		sesion.setAttribute("jug1", p.getIdJugador1());
		sesion.setAttribute("jug2", p.getIdJugador2());
		sesion.setAttribute("jug3", p.getIdJugador3());
		sesion.setAttribute("jug4", p.getIdJugador4());
		model.addAttribute("pistas",pistas);
		model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
		return new ModelAndView("editarPartido");
	}
	
	
	@RequestMapping(value="/ganadoresPartido",method= RequestMethod.POST)
	public String elegirGanador(@ModelAttribute Partidos partido,Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		clasificacionDao.SumarPartidoJugado((Usuarios)sesion.getAttribute("jug1"),(Usuarios)sesion.getAttribute("jug2"),(Usuarios)sesion.getAttribute("jug3"),(Usuarios)sesion.getAttribute("jug4"),(Torneo)sesion.getAttribute("torneo"));
		int idTorneo=((Torneo)sesion.getAttribute("torneo")).getIdTorneo();
		Clasificacion c=clasificacionDao.ClasificacionPorIdUsuarioyIdTorneo(partido.getIdGanador1(),idTorneo);
		clasificacionDao.Editar(c.getPuntos()+3, c.getPartJugados()+1, idTorneo, partido.getIdGanador1());
		Clasificacion c1=clasificacionDao.ClasificacionPorIdUsuarioyIdTorneo(partido.getIdGanador2(),idTorneo);
		clasificacionDao.Editar(c.getPuntos()+3, c.getPartJugados()+1, idTorneo, partido.getIdGanador2());
		
		partidosDao.EditarGanadores(partido.getIdGanador1(),partido.getIdGanador2(),(int)sesion.getAttribute("idPartido"));
		
		return "redirect:/gestionarPartidos";
	}
	@RequestMapping(value="/generarProximos",method= RequestMethod.POST)
	public String proximaJornada(Model model,HttpSession sesion) {
		if(sesion.getAttribute("usuLogeado")==null) {
			return "redirect:/";
		}
		
		List<Pistas> pistas;
		pistas=pistasDao.listarPistas();
		List<Usuarios> usuPartidos= new ArrayList<Usuarios>();
		usuPartidos=usuariosDao.listarUsuariosJugadores();
		List<Integer> numeros=new ArrayList<Integer>();
		
		numeros = generarJugadoresAleatorios(usuPartidos.size());
		int numJornada = partidosDao.sacarNumeroJornada();
		numJornada++;
				Partidos p = new Partidos(new Date(), numJornada, pistas.get(0), usuPartidos.get(numeros.get(0)),
				usuPartidos.get(numeros.get(1)), usuPartidos.get( numeros.get(2)), usuPartidos.get(numeros.get(3)), 0, 0, ((Torneo)sesion.getAttribute("torneo")));
				partidosDao.insert(p);
				try {
				if(usuPartidos.get(7)!=null) {
					Partidos p1 = new Partidos(new Date(), numJornada, pistas.get(0), usuPartidos.get(numeros.get(4)),
					usuPartidos.get(numeros.get(5)), usuPartidos.get(numeros.get(6)), usuPartidos.get(numeros.get(7)), 0, 0, ((Torneo)sesion.getAttribute("torneo")));
					partidosDao.insert(p1);					
				}
				}catch (Exception e) {
				}
				try {
				if(usuPartidos.get(11)!=null) {
					Partidos p2 = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(numeros.get(8)),
					usuPartidos.get(numeros.get(9)), usuPartidos.get(numeros.get(10)), usuPartidos.get(numeros.get(11)), 0, 0, ((Torneo)sesion.getAttribute("torneo")));
				    partidosDao.insert(p2);				 
				}
				}
				catch (Exception e) {
				}
				try {
				if(usuPartidos.get(15)!=null) {
					Partidos p3 = new Partidos(new Date(), 1, pistas.get(0), usuPartidos.get(numeros.get(12)),
					usuPartidos.get( numeros.get(13)), usuPartidos.get( numeros.get(14)), usuPartidos.get(numeros.get(15)), 0, 0, ((Torneo)sesion.getAttribute("torneo")));
					partidosDao.insert(p3);
				}
				}catch (Exception e) {
				}
		
		return "redirect:/gestionarPartidos";
	}	
	public List<Integer> generarJugadoresAleatorios(int numJugadores){
		List<Integer> listaRamdom = new ArrayList<Integer>();
		
		Random rn=new Random();
		boolean repetido=false;
		for (int i = 0; i < numJugadores; i++) {
			
			do {
			int num=rn.nextInt(numJugadores);
			if(listaRamdom.contains(num)) {
				repetido=true;
			}else {
				listaRamdom.add(num);
				repetido=false;
			}
			}while(repetido);
		}
		for (Integer integer : listaRamdom) {
			System.out.println(integer);
		}
		return listaRamdom;
	}
}