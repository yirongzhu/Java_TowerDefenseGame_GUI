package com.tower.resource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	private Properties p;
	
	public PropertiesUtil(){
		
		
		try {
			p=new Properties();
			
			p.load(new FileReader("wave.properties"));
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	 public  String getProperty(int key){  
		 
		 
		 
	        return p.getProperty("wave"+key);  
	    }  
	
	
	
	
	
}
