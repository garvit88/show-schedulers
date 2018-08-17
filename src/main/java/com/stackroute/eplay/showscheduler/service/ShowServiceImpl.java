package com.stackroute.eplay.showscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.repository.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService{

	private ShowRepository showRepository;

	@Autowired
	public ShowServiceImpl(ShowRepository showRepository) {
		super();
		this.showRepository = showRepository;
	}

	@Override
	public Show saveShow(Show show) {
		return showRepository.save(show);
	}

	@Override
	public Iterable<Show> getAllshows() {
		return showRepository.findAll();
	}

	public void changeStatus() {
		
	}
	
	@Override
	public Show updateShow(Show show, int showId) {
		show.setShowId(showId);
		return showRepository.save(show);
	}

}
