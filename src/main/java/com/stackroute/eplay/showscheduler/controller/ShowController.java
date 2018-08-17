package com.stackroute.eplay.showscheduler.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.showscheduler.domain.MovieEvent;
import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.service.ShowService;
import com.stackroute.eplay.showscheduler.trigger.ShowTrigger;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ShowController {

	private ShowTrigger showTrigger;
	private ShowService showService;

	@Autowired
	public ShowController(ShowTrigger showTrigger, ShowService showService) {
		super();
		this.showTrigger = showTrigger;
		this.showService = showService;
	}

	@PostMapping("/movieEventUpdate")
	public ResponseEntity<?> saveMovieEvent(@RequestBody MovieEvent movieEvent) throws SchedulerException {
		//try {
			// Environment variable property//
			// System.out.println( env.getProperty("com.stackroute.username"));
		
			showTrigger.trigger(movieEvent);
			//movieEventService.saveMovieEvent(movieEvent);
			return new ResponseEntity<MovieEvent>(movieEvent, HttpStatus.CREATED);
	//	} catch (MovieEventAlreadyExistException e) {
		//	logger.error("This is an MovieAlreadyExistsException error");
		//	return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//return null;
		}

	

	@PostMapping("/show")
	public ResponseEntity<?> saveShow(@RequestBody Show show) {
		// try {
		// MessageChannel messageChannel =
		// userRegistrationStream.outboundUserRegistration();
		// messageChannel.send(MessageBuilder
		// .withPayload(user)
		// .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
		// .build());
		return new ResponseEntity<Show>(showService.saveShow(show), HttpStatus.CREATED);
		// } catch (UserAlreadyExistsException e) {
		// return new ResponseEntity<String>("User Already Exists!",
		// HttpStatus.CONFLICT);
		// }
	}

//	@GetMapping("/shows")
//	public ResponseEntity<?> updateShows() throws SchedulerException {
//		Iterable<Show> allShows = showService.getAllshows();
//		showTrigger.trigger(allShows);
//		return new ResponseEntity<Iterable<Show>>(showService.getAllshows(), HttpStatus.OK);
//	}

}
