package uk.ac.cam.dcm41.tick1star;

public class InspectDouble {

 public static void main(String[] args) throws Exception {

  double d = Double.parseDouble(args[0]);

  // return the bits which represent the floating point number
  long bits = Double.doubleToLongBits(d);

  // Sign bit located in bit 63
  // Suggested Mask 0x8000000000000000L
  // Format 1 => number is negative
  // TODO: fill in the XXXX
  boolean negative = (bits & 0x8000000000000000L)!= 0;

  // Exponent located in bits 52 - 62
  // Suggested Mask 0x7ff0000000000000L
  // format Sum( 2^n * e(n)) - 1023 (binary number with bias)
  // TODO: fill in the XXXX
  long exponent = ((bits & 0x7ff0000000000000L)>>52) - 1023L;
  
  // Mantissa located in bits 0 - 51
  // Mask left as an exercise for the reader
  // format 1 + Sum(2^-(n+1) * m(n) )
  // TODO: fill in the XXXX
  long mantissabits = bits & 0x000fffffffffffffL;
                  
  double mantissa = mantissaToDecimal(mantissabits);
  
  System.out.println((negative ? "-" : "") + mantissa + " x 2^" + exponent);
 }

 private static double mantissaToDecimal(long mantissabits) {
  long one = 0x0010000000000000L;
  return (double)(mantissabits + one) / (double)one;
 }
}
