package me.oscar0713.EaseManage.Utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utilities {
	public static final String getRoundOneDecimal(double num) {
		 String format = "#.#";
		 DecimalFormat d = new DecimalFormat(format);
		 d.setRoundingMode(RoundingMode.HALF_UP);
		 return d.format(num);
	}
	
	public static int abs(int num) {
		return (num < 0 ? -num : num);
	}
	

}
