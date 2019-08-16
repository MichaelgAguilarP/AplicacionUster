package uy.app.web.app.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import uy.app.web.app.model.entity.EntDrivers;

public interface IDriversDao extends PagingAndSortingRepository <EntDrivers, Long> {

	@Query("SELECT v FROM EntDrivers v where v.id not in (select t.veh from EntTrip t where t.date=?1 ) and v.license=?2")
    List<EntDrivers> buscaLicenciaRequerida(Date date, String licencia);
}
