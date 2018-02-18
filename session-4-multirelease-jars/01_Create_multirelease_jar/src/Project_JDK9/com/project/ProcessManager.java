package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.Runtime;
import java.util.List;
import java.util.LinkedList;

public class ProcessManager {
	
	public List<String> allProcesses() {
		List<String> processes = new LinkedList();
		ProcessHandle.allProcesses().forEach(process -> 
			{processes.add(process.info().command().orElse("(empty)"));});
		return processes;
	}
}
