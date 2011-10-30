package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Event;

public class EventTogether {
	private long id;
	private Event event;
	private int year;
	private int month;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "EventTogether [id=" + id + ", event=" + event + ", year="
				+ year + ", month=" + month + "]";
	}

}
