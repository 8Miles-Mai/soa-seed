package com.miles.seed.schedule;

import org.springframework.stereotype.Component;

@Component
public class SampleSchedule {
	public void run(){
		System.out.println("schedule is running");
	}
}
