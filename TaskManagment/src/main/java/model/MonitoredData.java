package model;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MonitoredData {
	private String start_time;
	private String end_time;
	private String label;
	
	public MonitoredData(String start_time,String end_time,String label) {
		this.start_time=start_time;
		this.end_time=end_time;
		this.label=label;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String afisare() {
		return "StartTime:"+start_time+"      "+"EndTime:"+end_time+"     "+"LabelActivity:"+label+"\n";

		
	}
	public Integer duration(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(start_time, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(end_time, formatter);
        Duration duration = Duration.between(dateStart, dateEnd);
        long result=duration.toMinutes();
        Integer minutes = ((int)result);
        return minutes;
    }



}
