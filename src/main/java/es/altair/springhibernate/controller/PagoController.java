package es.altair.springhibernate.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;

import es.altair.springhibernate.bean.Clasificacion;
import es.altair.springhibernate.bean.ClasificacionString;
import es.altair.springhibernate.bean.Pagos;
import es.altair.springhibernate.bean.PagosString;
import es.altair.springhibernate.bean.Pdf;
import es.altair.springhibernate.bean.Torneo;
import es.altair.springhibernate.bean.Usuarios;
import es.altair.springhibernate.dao.PagosDao;

@Controller
public class PagoController {
	 @Autowired
	private PagosDao pagosDao;
	 @RequestMapping(value="/pagarMes", method=RequestMethod.GET)
		public String addPago(Model model,HttpSession sesion) {
			if(sesion.getAttribute("usuLogeado")==null) {
				return "redirect:/";
			}
			System.out.println("entra en el metodo");
			try {
				Usuarios usu = ((Usuarios)sesion.getAttribute("usuLogeado"));
				pagosDao.insert(new Pagos(new Date(), usu));
				return "redirect:/usuario?infoPago=El pago se ha realizado correctamente.";
			}catch (Exception e) {
				return "redirect:/usuario?infoPago=Pago no realizado correctamente.";
			}
		}
	 
	 @RequestMapping(value="/pdf", method=RequestMethod.POST)
		public String guardarPdf(HttpSession sesion,Model model) {
			if(sesion.getAttribute("usuLogeado")==null) {
				return "redirect:/";
			}
			int id=((Usuarios)sesion.getAttribute("usuLogeado")).getIdUsuario();
			String nombre=((Usuarios)sesion.getAttribute("usuLogeado")).getNombre();
			List<Pagos> pagos =pagosDao.listarPagos(id);
			List<PagosString> pagosString = new ArrayList<PagosString>();
			for (Pagos p : pagos) {
				String fecha = p.getUltimoPago().getDate()+"/"+(p.getUltimoPago().getMonth()+1)+"/"+(p.getUltimoPago().getYear()+1900);
				pagosString.add(new PagosString(nombre,fecha));
			}
			Pdf pdf = new Pdf();
			try {
				pdf.writePDF(pagosString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:/listaPagos?info=Ha habido un error al generar pdf.";
			}
			return "redirect:/listaPagos?info=Pdf generado en c:/pagosPDF";
			
		}
	 @RequestMapping(value="/pdfAdmin", method=RequestMethod.POST)
		public String guardarPdfAdmin(HttpSession sesion,Model model) {
			if(sesion.getAttribute("usuLogeado")==null) {
				return "redirect:/";
			}
			List<Pagos> pagos =pagosDao.listarTodosPagos();
			List<PagosString> pagosString = new ArrayList<PagosString>();
			for (Pagos p : pagos) {
				String nombre=p.getUsuario().getNombre();
				String fecha = p.getUltimoPago().getDate()+"/"+(p.getUltimoPago().getMonth()+1)+"/"+(p.getUltimoPago().getYear()+1900);
				pagosString.add(new PagosString(nombre,fecha));
			}
			
			Pdf pdf = new Pdf();
			try {
				pdf.writePDF(pagosString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:/listarTodosPagos?info=Ha habido un error al generar pdf.";
			}
			return "redirect:/listarTodosPagos?info=Pdf generado en c:/pagosPDF";
			
		}
	 @RequestMapping(value="/listaPagos",method=RequestMethod.GET)
		public ModelAndView misPagos(@RequestParam(value="info",required=false,defaultValue="") String info,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
			if(sesion.getAttribute("usuLogeado")==null) {
				return new ModelAndView("redirect:/","usuario",new Usuarios());
			}
			model.addAttribute("info",info);
			model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
			int id=((Usuarios)sesion.getAttribute("usuLogeado")).getIdUsuario();
			String nombre=((Usuarios)sesion.getAttribute("usuLogeado")).getNombre();
			List<Pagos> pagos =pagosDao.listarPagos(id);
			List<PagosString> pagosString = new ArrayList<PagosString>();
			for (Pagos p : pagos) {
				String fecha = p.getUltimoPago().getDate()+"/"+(p.getUltimoPago().getMonth()+1)+"/"+(p.getUltimoPago().getYear()+1900);
				pagosString.add(new PagosString(nombre,fecha));
			}
			
			return new ModelAndView("listarPagos","pagosString",pagosString);
		}
	 @RequestMapping(value="/listarTodosPagos",method=RequestMethod.GET)
		public ModelAndView todosPagos(@RequestParam(value="info",required=false,defaultValue="") String info,Model model, HttpServletResponse response, HttpServletRequest request,HttpSession sesion) {
			if(sesion.getAttribute("usuLogeado")==null) {
				return new ModelAndView("redirect:/","usuario",new Usuarios());
			}
			model.addAttribute("usuLogeado",sesion.getAttribute("usuLogeado"));
			model.addAttribute("info",info);
			int id=((Usuarios)sesion.getAttribute("usuLogeado")).getIdUsuario();		
			List<Pagos> pagos =pagosDao.listarTodosPagos();
			List<PagosString> pagosString = new ArrayList<PagosString>();
			for (Pagos p : pagos) {
				String nombre=p.getUsuario().getNombre();
				String fecha = p.getUltimoPago().getDate()+"/"+(p.getUltimoPago().getMonth()+1)+"/"+(p.getUltimoPago().getYear()+1900);
				pagosString.add(new PagosString(nombre,fecha));
			}
			
			return new ModelAndView("listarTodosPagos","pagosString",pagosString);
		}
	 
}
