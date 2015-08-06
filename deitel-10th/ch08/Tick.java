// Ex 8.7: Adding a Tick to Tick.

public class Tick {
	private int hour;
	private int minute;
	private int second;

	public Tick() { this(0, 0, 0); } 
	public Tick(int hour)  { this(hour, 0, 0); } 
	public Tick(int hour, int minute)  { this(hour, minute, 0); } 

	public Tick(int hour, int minute, int second)  { 
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");
		if (minute < 0 || minute >= 60)
			throw new IllegalArgumentException("minute must be 0-59");
		if (second < 0 || second >= 60)
			throw new IllegalArgumentException("second must be 0-59");
		this.hour = hour;
		this.minute = minute; 
		this.second = second; 
	} 

	public Tick(Tick time) {
		this(time.getHour(), time.getMinute(), time.getSecond());
	} 

	public void setTime(int hour, int minute, int second) {
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");
		if (minute < 0 || minute >= 60)
			throw new IllegalArgumentException("minute must be 0-59");
		if (second < 0 || second >= 60)
			throw new IllegalArgumentException("second must be 0-59");
		this.hour = hour;
		this.minute = minute; 
		this.second = second; 
	} 

	public int getHour()   { return hour;   }
	public int getMinute() { return minute; }
	public int getSecond() { return second; }

	public void setHour(int hour)  { 
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");
		this.hour = hour;
	} 

	public void setMinute(int minute)  { 
		if (minute < 0 || minute >= 60)
			throw new IllegalArgumentException("minute must be 0-59");
		this.minute = minute; 
	} 

	public void setSecond(int second)  { 
		if (second < 0 || second >= 60)
			throw new IllegalArgumentException("second must be 0-59");
		this.second = second; 
	} 

	// I'm really proud of this.
	public void tick() {
		setSecond((getSecond()+1)%60);
		if (getSecond() == 0) tickMinute();
	}

	public void tickMinute() {
		setMinute((getMinute()+1)%60);
		if (getMinute() == 0) tickHour();
	}

	public void tickHour() {
		hour = (hour + 1) % 24;
	}

	public String toUniversalString() {
		return String.format(
				"%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	} 

	public String toString() {
		return String.format("%d:%02d:%02d %s", 
				((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
				getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
	} 
}
