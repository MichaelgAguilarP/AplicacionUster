package uy.app.web.app.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import uy.app.web.app.model.entity.EntDrivers;
import uy.app.web.app.model.entity.EntTrip;
import uy.app.web.app.model.entity.EntVehiculo;
import uy.app.web.app.model.service.IDriversServ;
import uy.app.web.app.model.service.ITripServ;
import uy.app.web.app.model.service.IVehiculoServ;

@Controller
@SessionAttributes("principal")
public class PrincipalController {
	Logger log = LoggerFactory.getLogger(TripController.class);
	@Autowired
	IVehiculoServ vehSe;
    @Autowired
    IDriversServ condSe;
    @Autowired
    ITripServ tripService;
    @Autowired
	private ITripServ tr;
    
    private static final String VIAJE = "trips";

    @RequestMapping(value = { "principal", "/", "" })
	public String listar(Model mod) {
		mod.addAttribute("titulo", "Bienvenido a Uster, No te Quedes sin Reservar");
		return "principal";
	}
	
	@GetMapping("/creaViaje")
    public String getCreateViaje(Model model) {
		log.info("En el Crear Viaje");
        model.addAttribute(VIAJE, new EntTrip());
        return "redirect:/vistaDate";
    }

	@GetMapping("/vistaDate")
    public String getCreateTrip(Model model) {
		log.info("En el Vista a Viaje");
        model.addAttribute(VIAJE, new EntTrip());
        return "/viajes/formpickdate";
    }
	
    @PostMapping("/vistaDate")
    public String postVerDate(@ModelAttribute("trips") EntTrip trip, Model model, HttpSession session) {
    	log.info("En el ControllerVeh");
        session.setAttribute(VIAJE, trip);
        return "redirect:/controllerVista";
    }
    
    @GetMapping("/controllerVista")
    public String getVerVehiculo(@ModelAttribute(value = "trips") EntTrip trip, Model model, HttpSession session) throws ParseException {
    	EntTrip actualTrip = (EntTrip) session.getAttribute(VIAJE);
    	log.info("despues del actualtrip");
        model.addAttribute("datosVehiculos", vehSe.buscaFechaViaje(actualTrip.getDate()));
        model.addAttribute(VIAJE, session.getAttribute(VIAJE));
        model.addAttribute("vehicle", new EntVehiculo());
        return "/viajes/form2vehicle";
    }
    
    @PostMapping("/controllerVista")
    public String getVerVehiculo(Model model, HttpServletRequest request, HttpSession session) {
        EntTrip trip = (EntTrip) session.getAttribute(VIAJE);
        trip.setVeh(getBuscaFechaVehiculo(request));
        return "redirect:/contDrivers";
    }

    @GetMapping("/contDrivers")
    public String getVerConductores(Model model, HttpSession session) {
        EntTrip actualTrip = (EntTrip) session.getAttribute(VIAJE);
        String licensedRequiredForDriver = actualTrip.getVeh().getLicense();
        model.addAttribute("datosConductor", condSe.buscaLicenciaRequerida(actualTrip.getDate(), licensedRequiredForDriver));
        model.addAttribute("driver", new EntDrivers());
        return "/viajes/form3drivers";
    }
    
    @PostMapping("/pickDriver")
    public String postVerConductores(Model model, HttpServletRequest request, HttpSession session) {
    	EntTrip trip = (EntTrip) session.getAttribute(VIAJE);
        trip.setEnts(getBuscaFechaConductor(request));
        tr.save(trip);
        session.removeAttribute(VIAJE);
        return "redirect:/listtrips";
    }
    
    private EntVehiculo getBuscaFechaVehiculo(HttpServletRequest request) {
        Long idVehicle = Long.valueOf(request.getParameter("datosVehiculos"));
        return vehSe.findByID(idVehicle).get();
    }
    
    private EntDrivers getBuscaFechaConductor(HttpServletRequest request) {
        Long idDriver = Long.valueOf(request.getParameter("datosConductor"));
        return condSe.findByID(idDriver).get();
    }
}