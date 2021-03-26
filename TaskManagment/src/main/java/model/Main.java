package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;



public class Main {
	public static void main(String[]args) throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter("TASK_2.txt"));
		Processed process=new Processed();
		process.readFile();
		try {String s = String.valueOf(process.distinctDays());
			out.write("Numarul de zile distincte este:"+s);
			 out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		
		////task3
		BufferedWriter task3 = new BufferedWriter(new FileWriter("TASK_3.txt"));
		Map<String,Long> result=process.action();
		try {
			task3.write("How many times each activity has appeared over the entire monitoring period\n\n");
			java.util.Iterator<Entry<String, Long>> it =result.entrySet().iterator();
		  int i=0;
		    while (it.hasNext()) {
		    	Map.Entry pair = (Map.Entry)it.next();
		    	System.out.println(pair.getKey() + "   " + pair.getValue());
		    
		    	String text=pair.getKey() +"    "+pair.getValue()+"\n";
		    	
		    	task3.write(text);
		    	
		    	}
		    task3.close();
	}catch(Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		

		///TASK1
		
		process.show();//task1
		process.count();//task4
		process.task();//task5
		
		
		    
	}
	}

	
	


