package uy.app.web.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.app.web.app.model.dao.IDriversDao;
import uy.app.web.app.model.entity.EntDrivers;

@Service
public class DriversServImpl implements IDriversServ {

	@Autowired
	private IDriversDao drv;

	@Override
	@Transactional(readOnly=true)
	public List<EntDrivers> findAll() {
		return (List<EntDrivers>) drv.findAll();
	}

	@Override
	@Transactional
	public void save(EntDrivers ent) {
		drv.save(ent);
	}

	@Override
	@Transactional(readOnly = true)
	public EntDrivers findOne(Long id) {
		return drv.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		drv.deleteById(id);;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EntDrivers> findAll(Pageable pageable) {
		return drv.findAll(pageable);
	}

	@Override
	public List<EntDrivers> buscaLicenciaRequerida(Date date, String licencia) {
		return drv.buscaLicenciaRequerida(date, licencia);
	}

	@Override
	public Optional<EntDrivers> findByID(Long id) {
		return drv.findById(id);
	}
}
