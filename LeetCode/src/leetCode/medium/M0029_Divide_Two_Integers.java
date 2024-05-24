package leetCode.medium;

public class M0029_Divide_Two_Integers {
	// solution 1: brute-force(Time Limit Exceeded)
	public static int divide1(int dividend, int divisor) {
		long longDividend = dividend;
		long longDivisor = divisor;
		int sign = 1;
		if ((dividend < 0 && divisor > 0) || dividend > 0 && divisor < 0) {
			sign = -1;
		}

		longDividend = Math.abs(longDividend);
		longDivisor = Math.abs(longDivisor);

		long quotient = 0;
		while (longDividend >= longDivisor) {
			quotient++;
			longDividend -= longDivisor;
		}

		if (quotient * sign >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (quotient * sign <= Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) (quotient * sign);
	}

	// solution 2: acceleration
	public static int divide2(int dividend, int divisor) {
		long longDividend = dividend;
		long longDivisor = divisor;
		int sign = 1;
		if ((dividend < 0 && divisor > 0) || dividend > 0 && divisor < 0) {
			sign = -1;
		}

		longDividend = Math.abs(longDividend);
		longDivisor = Math.abs(longDivisor);

		long quotient = 0;
		long curDivisor = longDivisor;
		long multi = 1;
		while (longDividend >= longDivisor) {
			// acceleration
			if (longDividend >= curDivisor) {
				quotient += multi;
				longDividend -= curDivisor;
				multi += multi;
				curDivisor += curDivisor;
			} else {
				quotient++;
				longDividend -= longDivisor;
				curDivisor = longDivisor;
				multi = 1;
			}
		}

		if (quotient * sign >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (quotient * sign <= Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) (quotient * sign);
	}

	// solution 3
	public static int divide3(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //Cornor case when -2^31 is divided by -1 will give 2^31 which doesnt exist so overflow 
        
        boolean negative = dividend < 0 ^ divisor < 0; //Logical XOR will help in deciding if the results is negative only if any one of them is negative
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0, subQuot = 0;
        
        while (dividend - divisor >= 0) {
            for (subQuot = 0; dividend - (divisor << subQuot << 1) >= 0; subQuot++);
            quotient += 1 << subQuot; //Add to the quotient
            dividend -= divisor << subQuot; //Substract from dividend to start over with the remaining
        }
        
        return negative ? -quotient : quotient;
	}

	public static void main(String[] args) {
//		System.out.println(divide1(10, 3));
//		System.out.println(divide1(7, -3));
//		System.out.println(divide1(-2147483648, -1));
//		System.out.println(divide1(-2147483648, 2));

		System.out.println(divide2(10, 3));
		System.out.println(divide2(7, -3));
		System.out.println(divide2(-2147483648, -1));
		System.out.println(divide2(-2147483648, 2));

		System.out.println(divide3(10, 3));
		System.out.println(divide3(7, -3));
		System.out.println(divide3(-2147483648, -1));
		System.out.println(divide3(-2147483648, 2));
	}
}
