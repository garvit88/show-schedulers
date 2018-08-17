package com.stackroute.eplay.showscheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.showscheduler.domain.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, Integer>{

}
