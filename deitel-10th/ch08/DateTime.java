// Ex 8.13: Combining Date and Time.

public class DateTime {
	private Tick t;
	private Date d;
	
	public DateTime(int d, int mo, int y) {
		this(0,0,0,d,mo,y);
	}

	public DateTime(int h, int d, int mo, int y) {
		this(0,0,h,d,mo,y);
	}

	public DateTime(int mi, int h, int d, int mo, int y) {
		this(0,mi,h,d,mo,y);
	}

	public DateTime(int s, int mi, int h, int d, int mo, int y) {
		this.t = new Tick(s,mi,h);
		this.d = new Date(d,mo,y);
	}
	
	public void tick() {
		t.tick();
		if (t.getHour() == 0 && t.getMinute() == 0 && t.getSecond() == 0)
			d.nextDay();
	}

	public void tickMinute() {
		t.tickMinute();
		if (t.getHour() == 0 && t.getMinute() == 0)
			d.nextDay();
	}

	public void tickHour() {
		t.tickHour();
		if (t.getHour() == 0)
			d.nextDay();
	}

	public void nextDay() { d.nextDay(); }

	public String toString() {
		return t.toString() + ", " + d.toString();
	}
}
