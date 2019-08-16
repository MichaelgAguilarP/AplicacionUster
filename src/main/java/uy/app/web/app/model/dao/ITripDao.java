package uy.app.web.app.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import uy.app.web.app.model.entity.EntTrip;

public interface ITripDao extends PagingAndSortingRepository <EntTrip, Long> {

}
