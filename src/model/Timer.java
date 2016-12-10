package model;

import java.io.Serializable;

public class Timer implements Serializable{
	
	private int time=0;
	private long timeMili = 0L;
	public long startGameTime = System.currentTimeMillis();
	private boolean initStartTime = true;
	
	/**
	 * Getter for the attribute time.
	 * @return The current instance of time.
	 */
	public int getTime() {
		return time;
	}
	/**
	 * Setter for the attribute time.
	 * @param time Measurement of time in seconds.
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * Counts down from 180 seconds to 0.
	 */
	public void countDown(){
		this.timeMili = (180*1000) - (System.currentTimeMillis() - startGameTime);
		this.time = (int) this.timeMili / 1000;
		//System.out.println(time);
		if (this.time <= 0) {
			this.time = 0;
		}
	}
	
	/**
	 * Counts up from 0 to stopTime, then loops.
	 * @param stopTime int An integer that sets how long the function countUp counts up for.
	 */
	public void countUp(int stopTime) {
		if (this.initStartTime) {
			this.initStartTime = false;
			startGameTime = System.currentTimeMillis();
		}
		if (time >= stopTime) {
			startGameTime = System.currentTimeMillis();
		}
		this.timeMili = System.currentTimeMillis() - startGameTime;
		this.time = (int) timeMili/1000;
		
	}
	/**
	 * Counts up from 0 to stopTime, then loops.
	 * @param stopTime double A double that sets how long the function countUp counts up for.
	 */
	public void countUp(double stopTime) {
		if (this.initStartTime) {
			this.initStartTime = false;
			startGameTime = System.currentTimeMillis();
		}
		if (timeMili >= stopTime) {
			startGameTime = System.currentTimeMillis();
		}
		this.timeMili = System.currentTimeMillis() - startGameTime;
		
	}
	/**
	 * Counts up from 0 to stopTime, then stops.
	 * @param stopTime int An integer that sets how long the function countUp counts up for.
	 */
	public void countUpStop(int stopTime) {
		if (time <= stopTime) {
			if (this.initStartTime) {
				this.initStartTime = false;
				startGameTime = System.currentTimeMillis();
			}
			this.timeMili = System.currentTimeMillis() - startGameTime;
			this.time = (int) timeMili/1000;
			//System.out.println(this.getTime());
		}
	}
	/**
	 * Getter for the attribute startGameTime.
	 * @return The current instance of startGameTime.
	 */
	public long getStartGameTime() {
		return startGameTime;
	}
	/**
	 * Getter for the attribute timeMili.
	 * @return The current instance of timeMili.
	 */
	public long getTimeMili() {
		return timeMili;
	}
	/**
	 * Setter for the attribute startGameTime.
	 * @param startGameTime long The current time in milliseconds of the system when the timer starts.
	 */
	public void setStartGameTime(long startGameTime) {
		this.startGameTime = startGameTime;
	}
}
