package model;

public class Timer {
	
	private int time=180;
	private long timeMili = 0L;
	public long startGameTime = System.currentTimeMillis();
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void countDown(){
		this.timeMili = 180 - (System.currentTimeMillis() - startGameTime) / 1000;
		this.time = (int) this.timeMili;
		//System.out.println(time);
		if (this.time <= 0) {
			this.time = 0;
		}
	}
	
	public void countUp() {
		this.timeMili = System.currentTimeMillis() - startGameTime;
		this.time = (int) timeMili/1000;
		if (time >= 5) {
			startGameTime = System.currentTimeMillis();
		}
	}

	public long getStartGameTime() {
		return startGameTime;
	}

	public long getTimeMili() {
		return timeMili;
	}
}
