package com.stackroute.eplay.showscheduler.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

   @Id
   private int movieEventId;
   private int movieId;
   private int theatreId;
   private int showCount;
   private List<Show> shows;
   private String city;
   private String userName;
   private int week;
   private List<String> showTimes;

}