package uy.app.web.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uy.app.web.app.model.entity.EntDrivers;
import uy.app.web.app.model.entity.EntTrip;
import uy.app.web.app.model.entity.EntVehiculo;
import uy.app.web.app.model.service.DriversServImpl;
import uy.app.web.app.model.service.ITripServ;
import uy.app.web.app.model.service.VehiculoServImpl;
import uy.app.web.app.util.PageRender;

@Controller
@SessionAttributes("trips")
public class TripController {
	
	Logger log = LoggerFactory.getLogger(TripController.class);
	
	@Autowired
	private ITripServ tr;
	
	@Autowired
	private DriversServImpl serv;
	
	@Autowired
	private VehiculoServImpl sre;
	//@RequestMapping(value= {"listtrips", "/", ""})
	@RequestMapping(value="listtrips")
	public String listar (@RequestParam(name="page", defaultValue="0") int page,Model mod){
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<EntTrip> drvs = tr.findAll(pageRequest);
		PageRender<EntTrip> pageRender = new PageRender<EntTrip>("/listtrips", drvs);
		mod.addAttribute("titulo", "Listado de Viajes");
		mod.addAttribute("trips", drvs);
		mod.addAttribute("page", pageRender);
		return "listtrips";
	}
	
	@RequestMapping(value="/formtrips")
	public String crear(Model mod,RedirectAttributes flas) {
		List <EntDrivers> dr = serv.findAll();
		
		/*if (dr == null ) {
			flas.addFlashAttribute("error", "El Automovil No Existe");
			return "redirect/listdrivers";
		}
		*/
		EntTrip ent = new EntTrip(); 
		mod.addAttribute("listaDrivers",dr);
		
		List <EntVehiculo> vec = sre.findAll();
		mod.addAttribute("listaVehiculos",vec);
		
		//ent.setEnts(dr);
		mod.addAttribute("titulo", "Crear Viaje");
		mod.addAttribute("trips", ent);
		return "formtrips";
	}
	
	@RequestMapping(value="/formtrips/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes flas) {
		EntTrip drv = null;
		if(id > 0) {
			drv = tr.findOne(id);
			if (drv == null) {
				flas.addFlashAttribute("error", "El Id del Viaje no Existe");
				return "redirect:/listtrips";
			}
		} else {
			flas.addFlashAttribute("error", "El Id del Viaje no Puede Ser 0");
			return "redirect:/listtrips";
		}
		model.put("trips", drv);
		model.put("titulo", "Editar Viaje");
		return "formtrips";
	}
	
	@PostMapping(value="/formtrips")
	public String guardar (@Valid @ModelAttribute("trips") EntTrip veh, BindingResult re,Model mod, SessionStatus sts,RedirectAttributes flas) {
		if (re.hasErrors()) {
			mod.addAttribute("titulo","Crear Viajes");
			return("formtrips");
		}
		String pr= veh.getDate().toString();
		log.info("Fecha String  " + pr);
        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
        Date date = null;
		try {
			date = formatter.parse(pr);
			log.info("Fecha Parser  " + pr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        log.info("Fecha Formato " + date);
		System.out.println(veh.getDate());
		tr.save(veh);
		sts.setComplete();
		flas.addFlashAttribute("success", "Viaje Guardado Exitosamente");
		return "redirect:listtrips";
	}
	
	@RequestMapping(value="/eliminartrips/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flas) {
		if(id > 0) {
			tr.delete(id);
		}
		flas.addFlashAttribute("success", "Viaje Eliminado Exitosamente");
		return "redirect:/listtrips";
	}
}
