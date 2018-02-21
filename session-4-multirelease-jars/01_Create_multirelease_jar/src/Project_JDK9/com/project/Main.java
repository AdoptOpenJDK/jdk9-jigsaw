package com.project;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<String> processes = new ProcessManager().allProcesses();
		processes.forEach(process -> {System.out.println(process);});
	}
}
