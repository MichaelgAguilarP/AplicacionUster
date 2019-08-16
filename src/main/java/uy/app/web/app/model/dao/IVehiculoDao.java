package uy.app.web.app.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import uy.app.web.app.model.entity.EntVehiculo;

public interface IVehiculoDao extends PagingAndSortingRepository<EntVehiculo, Long> {

	@Query("SELECT v FROM EntVehiculo v where v.id not in (select t.veh from EntTrip t where t.date=?1 )")
	List<EntVehiculo> buscaFechaViaje(Date date);
}