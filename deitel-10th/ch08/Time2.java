// Ex 8.5: Modifiying internal representation.

public class Time2 {

	private static final int MIN = 60;
	private static final int HR  = MIN * MIN;
	private int seconds;

	public Time2() { this(0, 0, 0); } 

	public Time2(int hour)  { this(hour, 0, 0); } 

	public Time2(int hour, int minute)  { this(hour, minute, 0); } 

	public Time2(int hour, int minute, int second)  { 
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");

		if (minute < 0 || minute >= MIN)
			throw new IllegalArgumentException("minute must be 0-59");

		if (second < 0 || second >= MIN)
			throw new IllegalArgumentException("second must be 0-59");

		seconds = HR*hour+MIN*minute+second;
	} 

	public Time2(Time2 time) {
		this(time.getHour(), time.getMinute(), time.getSecond());
	} 

	public void setTime(int hour, int minute, int second) {
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");

		if (minute < 0 || minute >= MIN)
			throw new IllegalArgumentException("minute must be 0-59");

		if (second < 0 || second >= MIN)
			throw new IllegalArgumentException("second must be 0-59");

		seconds = HR*hour+MIN*minute+second;
	} 

	public void setHour(int hour)  { 
		if (hour < 0 || hour >= 24)
			throw new IllegalArgumentException("hour must be 0-23");

		seconds = seconds % HR + HR*hour;
	} 

	public void setMinute(int minute)  { 
		if (minute < 0 || minute >= MIN)
			throw new IllegalArgumentException("minute must be 0-59");

		seconds = seconds - seconds % HR
					+ MIN * minute
					+ seconds % HR % MIN;
	} 

	public void setSecond(int second)  { 
		if (second < 0 || second >= MIN)
			throw new IllegalArgumentException("second must be 0-59");

		seconds = seconds - seconds % HR % MIN + second;
	} 

	public int getHour()  { return seconds / HR; } 

	public int getMinute()  { return seconds % HR / MIN; } 

	public int getSecond()  { return seconds % HR % MIN; } 

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
