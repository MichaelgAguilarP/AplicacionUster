package uy.app.web.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uy.app.web.app.model.entity.EntVehiculo;

public interface IVehiculoServ {
	
	public List<EntVehiculo> findAll();
	
	public Page<EntVehiculo> findAll(Pageable pageable);

	public void save(EntVehiculo veh);
	
	public EntVehiculo findOne(Long id);
	
	public void delete(Long id);

	Optional<EntVehiculo> findByID(Long id);
	
	public List<EntVehiculo> buscaFechaViaje(Date date);

}
