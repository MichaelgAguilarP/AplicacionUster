package uy.app.web.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uy.app.web.app.model.entity.EntDrivers;

public interface IDriversServ {
	
	public List<EntDrivers> findAll();
	
	public Page<EntDrivers> findAll(Pageable pageable);

	public void save(EntDrivers drv);
	
	public EntDrivers findOne(Long id);
	
	public void delete(Long id);
	
	public List<EntDrivers> buscaLicenciaRequerida(Date date, String licencia);
	
	public Optional<EntDrivers> findByID(Long id);
}
