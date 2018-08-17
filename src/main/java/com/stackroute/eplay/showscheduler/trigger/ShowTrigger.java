package com.stackroute.eplay.showscheduler.trigger;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.showscheduler.domain.MovieEvent;
import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.job.ShowJob;
import com.stackroute.eplay.showscheduler.service.ShowServiceImpl;

@Service
public class ShowTrigger {
	//
	// private Show show;
	//
	// @Autowired
	// public ShowTrigger(Show show) {
	// super();
	// this.show = show;
	// }

	private ShowServiceImpl showServiceImpl;

	@Autowired
	public ShowTrigger(ShowServiceImpl showServiceImpl) {
		super();
		this.showServiceImpl = showServiceImpl;
	}

	public void trigger(MovieEvent movieEvent) throws SchedulerException {

		List<Show> shows = movieEvent.getShows();
		System.out.println("show list: " + shows);

		int i = 0;
		for (Show show : shows) {
			System.out.println("shows: " + show);
			System.out.println("i: " + i);
			Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
			JobKey job1Key = JobKey.jobKey("job" + Integer.toString(i), "show-jobs" + Integer.toString(i));
			JobDetail job = JobBuilder.newJob(ShowJob.class).withIdentity(job1Key).build();
			long currentTime = System.currentTimeMillis();

			LocalTime showTime = show.getStartTime();
			LocalDate showDate = show.getDate();
			LocalDateTime showDateTime = LocalDateTime.of(showDate, showTime);
			System.out.println("date time: " + showDateTime);
			long showSeconds = Timestamp.valueOf(showDateTime).getTime();
			System.out.println("show in seconds: " + showSeconds);

			long secondsDiff = (showSeconds - currentTime) / 1000;
			System.out.println("time diff: " + (int) secondsDiff);

			TriggerKey tk1 = TriggerKey.triggerKey("trigger" + Integer.toString(i), "my-jobs" + Integer.toString(i));
			SimpleTrigger trig = TriggerBuilder.newTrigger().withIdentity(tk1)
					.startAt(futureDate((int) secondsDiff, IntervalUnit.SECOND)).withSchedule(simpleSchedule()).build();
			// job.getJobDataMap().put(ShowJob.NAME, show);
			sc.start();
			// if (sc.checkExists(job.getKey())){
			// sc.deleteJob(job.getKey());
			// }
			sc.scheduleJob(job, trig);
			sc.getContext().put("pass" + Integer.toString(i), show);
			i++;
			// showServiceImpl.updateShow(show, show.getShowId());
			// Show updatedShow = showServiceImpl.updateShow(show, show.getShowId());
			// return updatedShow;
		}

	}

}
