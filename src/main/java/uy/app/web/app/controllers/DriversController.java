package uy.app.web.app.controllers;

import java.util.Map;

import javax.validation.Valid;

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
import uy.app.web.app.model.service.IDriversServ;
import uy.app.web.app.util.PageRender;

@Controller
@SessionAttributes("drivers")
public class DriversController {
	
	@Autowired
	private IDriversServ serv;
	
	@RequestMapping(value="listdrivers")
	public String listar (@RequestParam(name="page", defaultValue="0") int page,Model mod){
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<EntDrivers> drvs = serv.findAll(pageRequest);
		PageRender<EntDrivers> pageRender = new PageRender<EntDrivers>("/listdrivers", drvs);
		mod.addAttribute("titulo", "Listado de Conductores");
		mod.addAttribute("drivers", drvs);
		mod.addAttribute("page", pageRender);
		return "/conductores/listdrivers";
	}
	
	@RequestMapping(value="/formdrivers")
	public String crear (Model mod){
		EntDrivers ent = new EntDrivers(); 
		mod.addAttribute("titulo", "Crear Vehiculo");
		mod.addAttribute("drivers", ent);
		return "/conductores/formdrivers";
	}
	
	@RequestMapping(value="/formdrivers/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes flas) {
		
		EntDrivers drv = null;
		
		if(id > 0) {
			drv = serv.findOne(id);
			if (drv == null) {
				flas.addFlashAttribute("error", "El Id del Conductor no Existe");
				return "redirect:/listdrivers";
			}
		} else {
			flas.addFlashAttribute("error", "El Id del Conductor no Puede Ser 0");
			return "redirect:/listdrivers";
		}
		model.put("drivers", drv);
		model.put("titulo", "Editar Automovil");
		return "/conductores/formdrivers";
	}
	
	@PostMapping(value="/formdrivers")
	public String guardar (@Valid @ModelAttribute("drivers") EntDrivers veh, BindingResult re,Model mod, SessionStatus sts,RedirectAttributes flas){
		if (re.hasErrors()) {
			mod.addAttribute("titulo","Crear Conductor");
			return("/conductores/formdrivers");
		}
		serv.save(veh);
		sts.setComplete();
		flas.addFlashAttribute("success", "Conductor Guardado Exitosamente");
		return "redirect:listdrivers";
	}
	
	@RequestMapping(value="/eliminardrivers/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flas) {
		
		if(id > 0) {
			serv.delete(id);
		}
		flas.addFlashAttribute("success", "Conductor Eliminado Exitosamente");
		return "redirect:/listdrivers";
	}

}
