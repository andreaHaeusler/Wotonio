package com.rocketeercoders.wotonio;

public class VerticalAxisScaler {

	public int axisHeight(int maxVal) {
		if(maxVal==0)
			return 1;
		int firstDigit = maxVal;
		int places = 0;
		while (firstDigit >= 10) {
			places++;
			firstDigit /= 10;
		}
		if(firstDigit > 4)
			places++;
		int resultFirstDigit;
		if (firstDigit == 1)
			resultFirstDigit = 2;
		else if (firstDigit > 1 && firstDigit < 5)
			resultFirstDigit = 5;
		else
			resultFirstDigit = 1;

		int result = (int) (resultFirstDigit * Math.pow(10,places));

		return result;
	}
}
