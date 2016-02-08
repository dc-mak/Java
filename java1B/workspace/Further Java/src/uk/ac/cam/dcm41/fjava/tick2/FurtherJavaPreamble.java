package uk.ac.cam.dcm41.fjava.tick2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FurtherJavaPreamble {
	enum Ticker {A, B, C, D};
	String author();
	String date();
	String crsid();
	String summary();
	Ticker ticker();
}