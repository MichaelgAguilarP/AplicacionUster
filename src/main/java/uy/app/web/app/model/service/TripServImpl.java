package uy.app.web.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.app.web.app.model.dao.ITripDao;
import uy.app.web.app.model.entity.EntTrip;

@Service
public class TripServImpl implements ITripServ {

	@Autowired
	private ITripDao trip;

	@Override
	@Transactional(readOnly=true)
	public List<EntTrip> findAll() {
		return (List<EntTrip>) trip.findAll();
	}

	@Override
	@Transactional
	public void save(EntTrip ent) {
		trip.save(ent);
	}

	@Override
	@Transactional(readOnly = true)
	public EntTrip findOne(Long id) {
		return trip.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		trip.deleteById(id);;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EntTrip> findAll(Pageable pageable) {
		return trip.findAll(pageable);
	}
}
