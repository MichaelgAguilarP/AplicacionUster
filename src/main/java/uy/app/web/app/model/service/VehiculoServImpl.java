package uy.app.web.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.app.web.app.model.dao.IVehiculoDao;
import uy.app.web.app.model.entity.EntVehiculo;

@Service
public class VehiculoServImpl implements IVehiculoServ {

	@Autowired
	private IVehiculoDao veh;
	
	@Override
	@Transactional(readOnly=true)
	public List<EntVehiculo> findAll() {
		return (List<EntVehiculo>) veh.findAll();
	}

	@Override
	@Transactional
	public void save(EntVehiculo ent) {
		veh.save(ent);
	}

	@Override
	@Transactional(readOnly = true)
	public EntVehiculo findOne(Long id) {
		return veh.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		veh.deleteById(id);;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EntVehiculo> findAll(Pageable pageable) {
		return veh.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Optional<EntVehiculo> findByID(Long id) {
        return veh.findById(id);
    }

	@Override
	public List<EntVehiculo> buscaFechaViaje(Date date) {
		return veh.buscaFechaViaje(date);
	}
}
