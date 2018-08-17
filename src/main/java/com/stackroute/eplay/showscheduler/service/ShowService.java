package com.stackroute.eplay.showscheduler.service;

import com.stackroute.eplay.showscheduler.domain.Show;

public interface ShowService {
	
	public Show saveShow(Show show);
	
	public Iterable<Show> getAllshows();
	
	public Show updateShow(Show show, int showId);

}
