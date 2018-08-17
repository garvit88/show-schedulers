package com.stackroute.eplay.showscheduler.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.showscheduler.domain.Show;
import com.stackroute.eplay.showscheduler.service.ShowService;
import com.stackroute.eplay.showscheduler.service.ShowServiceImpl;

@Service
public class ShowJob implements Job {

	// public static final Show NAME;

	// private Show show;
	//
	// @Autowired
	// public ShowJob(Show show) {
	// super();
	// this.show = show;
	// }

	//private ShowServiceImpl showServiceImpl;
	
	public ShowJob() {
		
	}

//	@Autowired
//	public ShowJob(ShowServiceImpl showServiceImpl) {
//		super();
//		this.showServiceImpl = showServiceImpl;
//	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("jobss");
		// JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		// String name = dataMap.getString(NAME);

		SchedulerContext schedulerContext;
		try {
			schedulerContext = context.getScheduler().getContext();
			Show show =  (Show) schedulerContext.get("pass0");
			System.out.println("inside job show: " + show);
			System.out.println("Before: " + show.isStatus());
			show.setStatus(true);
			System.out.println(show + " " + show.getShowId());
//			Show updatedShow = showServiceImpl.updateShow(show, show.getShowId());
			System.out.println("After: " + show.isStatus());
			
			Show show1 =  (Show) schedulerContext.get("pass1");
			System.out.println("inside job show: " + show1);
			System.out.println("Before: " + show1.isStatus());
			show1.setStatus(true);
			System.out.println(show1 + " " + show1.getShowId());
//			Show updatedShow = showServiceImpl.updateShow(show, show.getShowId());
			System.out.println("After: " + show1.isStatus());
			
		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// show.setStatus(true);
		// System.out.println("After: " + show.isStatus());
	}

}
