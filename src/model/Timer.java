package model;

public class Timer {
	
	private int time=0;
	private long timeMili = 0L;
	public long startGameTime = System.currentTimeMillis();
	private boolean initStartTime = true;
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void countDown(){
		this.timeMili = (180*1000) - (System.currentTimeMillis() - startGameTime);
		this.time = (int) this.timeMili / 1000;
		//System.out.println(time);
		if (this.time <= 0) {
			this.time = 0;
		}
	}
	
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

	public void countUpStop(int stopTime) {
		if (time <= stopTime) {
			if (this.initStartTime) {
				this.initStartTime = false;
				startGameTime = System.currentTimeMillis();
			}
			this.timeMili = System.currentTimeMillis() - startGameTime;
			this.time = (int) timeMili/1000;
			System.out.println(this.getTime());
		}
	}
	
	public long getStartGameTime() {
		return startGameTime;
	}

	public long getTimeMili() {
		return timeMili;
	}
}
