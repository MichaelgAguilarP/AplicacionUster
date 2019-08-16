package uy.app.web.app.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uy.app.web.app.model.entity.EntTrip;

public interface ITripServ {
	
	public List<EntTrip> findAll();
	
	public Page<EntTrip> findAll(Pageable pageable);

	public void save(EntTrip drv);
	
	public EntTrip findOne(Long id);
	
	public void delete(Long id);
}
