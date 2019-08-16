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

import uy.app.web.app.model.entity.EntVehiculo;
import uy.app.web.app.model.service.IVehiculoServ;
import uy.app.web.app.util.PageRender;

@Controller
//@RestController
@SessionAttributes("vehiculos")
public class VehiculoController {
	
	@Autowired
	private IVehiculoServ serv;
	
	@RequestMapping(value= "listar")
	//@GetMapping(value= "listar", produces = "application/json")
	public String listar (@RequestParam(name="page", defaultValue="0") int page,Model mod){
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<EntVehiculo> vehiculos = serv.findAll(pageRequest);
		PageRender<EntVehiculo> pageRender = new PageRender<EntVehiculo>("/listar", vehiculos);
		mod.addAttribute("titulo", "Listado de Vehiculos");
		mod.addAttribute("vehiculos", vehiculos);
		mod.addAttribute("page", pageRender);
		return "/vehiculos/listar";
	}
	
	@RequestMapping(value="/form")
	public String crear (Model mod){
		EntVehiculo ent = new EntVehiculo(); 
		mod.addAttribute("titulo", "Crear Vehiculo");
		mod.addAttribute("vehiculos", ent);
		return "/vehiculos/form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes flas) {
		
		EntVehiculo aut = null;
		
		if(id > 0) {
			aut = serv.findOne(id);
			if (aut == null) {
				flas.addFlashAttribute("error", "El Id del Vehiculo no Existe");
				return "redirect:/listar";
			}
		} else {
			flas.addFlashAttribute("error", "El Id del Vehiculo no Puede Ser 0");
			return "redirect:/listar";
		}
		model.put("vehiculos", aut);
		model.put("titulo", "Editar Automovil");
		return "/vehiculos/form";
	}
	
	@PostMapping(value="/form")
	public String guardar (@Valid @ModelAttribute("vehiculos") EntVehiculo veh, BindingResult re,Model mod, SessionStatus sts,RedirectAttributes flas){
		if (re.hasErrors()) {
			mod.addAttribute("titulo","Crear Vehiculo");
			return("/vehiculos/form");
		}
		serv.save(veh);
		sts.setComplete();
		flas.addFlashAttribute("success", "Vehiculo Guardado Exitosamente");
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flas) {
		
		if(id > 0) {
			serv.delete(id);
		}
		flas.addFlashAttribute("success", "Vehiculo Eliminado Exitosamente");
		return "redirect:/listar";
	}

}
