package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.Runtime;
import java.util.List;
import java.util.LinkedList;

public class ProcessManager {
	
	public List<String> allProcesses() {
		List<String> processes = new LinkedList<String>();
		try {
		    String line;
		    Process p = null;
		    if(System.getProperty("os.name").toLowerCase().contains("win")) {
			    p = Runtime.getRuntime().exec
			    	    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
		    } else {
			    p = Runtime.getRuntime().exec("ps -e");
		    }
		    BufferedReader input =
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((line = input.readLine()) != null) {
		    	processes.add(line);
		    }
		    input.close();
		} catch (Exception err) {
		    err.printStackTrace();
		}
		
		return processes;
	}
}
