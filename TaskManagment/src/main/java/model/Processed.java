package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.attribute.IntegerSyntax;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class Processed {
	public ArrayList<MonitoredData> date;
	public Map<String, Long> actiune;

	
	public Processed(){
		date=new ArrayList<MonitoredData>();
		actiune=new HashMap<String,Long>(); 
		
	}
	
	public void readFile()
	   {
	
		try {
			Stream<String> stream = null;
			String space="\t\t";
		    stream = Files.lines(Paths.get("Activities.txt")); 	
		    stream.forEach(line->{	
		        String[] words=line.split(space);
		    	date.add(new MonitoredData(words[0],words[1],words[2]));
		    });
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
	public Long distinctDays()
	   {
		
		int value=10;
	 Long ziledistincte= date.stream().map(s->(s.getEnd_time().substring(0,value))).distinct().count();
		  System.out.println("Numarul de zile distincte este:"+ziledistincte+"");
		  return ziledistincte;
	   }
	public Map<String,Long> action() {
		
		actiune=date.stream().collect(Collectors.groupingBy(MonitoredData::getLabel,Collectors.counting()));
	
	 //String[] lines=null;
	 //int i=0;
	
	 
	
		actiune.forEach((action,appearance)->{
	
			System.out.println(action+" "+appearance);
			
		
			
		});
		return actiune;
		
	
		///appearance of each activity
		
			
	}
	///task1
	public Iterable<String> show() throws IOException {
		
		Iterable<String> show=(Iterable<String>) date.stream().map(MonitoredData::afisare)::iterator;
	
		BufferedWriter task1 = new BufferedWriter(new FileWriter("TASK_1.txt"));
		 
		 show.forEach(line -> {
			  try {
				task1.write(line);
				task1.write("\n");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
			  
			});
		 task1.close();
		 return show;
	}
	
	public Map<Integer, Map<String, Long>>count() throws IOException {
		BufferedWriter task4 = new BufferedWriter(new FileWriter("TASK4.txt"));
		
		 Map<Integer, Map<String, Long>> map =date.stream()
	                .collect(Collectors.groupingBy(
	                        element -> Integer.parseInt(element.getStart_time().substring(8, 10)),
	                        Collectors.groupingBy(MonitoredData::getLabel, Collectors.counting())));
		
		 map.entrySet().forEach(e -> {
			 System.out.println("day "+e.getKey()+e.getValue());
			 try {
				task4.write("day "+e.getKey()+e.getValue());
				task4.newLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 });
		 
		 task4.close();
		 return map;
	}
	
	public Map<String, Integer> task()throws IOException  {
		BufferedWriter task5 = new BufferedWriter(new FileWriter("TASK5.txt"));
		Map<String, Integer> map = date.stream()
                .collect(Collectors.groupingBy(MonitoredData::getLabel,
                        Collectors.summingInt(MonitoredData::duration)));
      map.entrySet().forEach(e->{
    	  System.out.println(e.getKey()+"  "+e.getValue()+" min");
    	  try {
    		  task5.write(e.getKey()+"  "+e.getValue()+" min");
    		  task5.newLine();
    		  
    	  }catch(IOException e1) {
    		  e1.printStackTrace();
    		  
    	  }
      });
      task5.close();
      return map;

	}

}
