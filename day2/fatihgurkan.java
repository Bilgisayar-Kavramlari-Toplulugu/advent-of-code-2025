package solutions;

import util.AoCUtil;

import java.util.List;

public class fatihgurkan {

    public long part1() {
        long sum = 0;
        String line = AoCUtil.getLine("input2.txt");
        String[] ranges = line.split(",");
        for (String range : ranges) {
            String[] rangeBounds = range.split("-");
            int lowBoundDigitCount = rangeBounds[0].length();
            int highBoundDigitCount = rangeBounds[1].length();

            if (lowBoundDigitCount % 2 == 1 && highBoundDigitCount % 2 == 1) // ikisi de tek sayıda basamaklı
                continue;

            if (lowBoundDigitCount % 2 == 1) {
                int half = highBoundDigitCount / 2;
                String firstHalfHighBoundStr = rangeBounds[1].substring(0, half);
                String lastHalfHighBoundStr = rangeBounds[1].substring(half);

                if (lastHalfHighBoundStr.compareTo(firstHalfHighBoundStr) >= 0) {
                    sum += Long.parseLong(firstHalfHighBoundStr + firstHalfHighBoundStr);
                }
                long halfHighBound = Long.parseLong(firstHalfHighBoundStr);
                long halfLowBound = (long) Math.pow(10, lowBoundDigitCount - half);
                for (long i = halfHighBound - 1; i >= halfLowBound; i--) {
                    sum += i * (long) Math.pow(10, half) + i;
                }
            } else if (highBoundDigitCount % 2 == 1) {
                int half = lowBoundDigitCount / 2;
                String firstHalfLowBoundStr = rangeBounds[0].substring(0, half);
                String lastHalfLowBoundStr = rangeBounds[0].substring(half);

                if (firstHalfLowBoundStr.compareTo(lastHalfLowBoundStr) >= 0) {
                    sum += Long.parseLong(firstHalfLowBoundStr + firstHalfLowBoundStr);
                }
                long halfLowBound = Long.parseLong(firstHalfLowBoundStr);
                long halfHighBound = (long) Math.pow(10, lowBoundDigitCount - half);
                for (long i = halfLowBound + 1; i < halfHighBound; i++) {
                    sum += i * (long) Math.pow(10, half) + i;
                }
            } else {
                int half = lowBoundDigitCount / 2;
                String firstHalfLowBoundStr = rangeBounds[0].substring(0, half);
                String lastHalfLowBoundStr = rangeBounds[0].substring(half);

                if (firstHalfLowBoundStr.compareTo(lastHalfLowBoundStr) >= 0) {
                    sum += Long.parseLong(firstHalfLowBoundStr + firstHalfLowBoundStr);
                }

                String firstHalfHighBoundStr = rangeBounds[1].substring(0, half);
                if (firstHalfLowBoundStr.equals(firstHalfHighBoundStr)) continue;

                String lastHalfHighBoundStr = rangeBounds[1].substring(half);
                if (lastHalfHighBoundStr.compareTo(firstHalfHighBoundStr) >= 0) {
                    sum += Long.parseLong(firstHalfHighBoundStr + firstHalfHighBoundStr);
                }
                long halfLowBound = Long.parseLong(firstHalfLowBoundStr);
                long halfHighBound = Long.parseLong(firstHalfHighBoundStr);
                for (long i = halfLowBound + 1; i < halfHighBound; i++) {
                    sum += i * (long) Math.pow(10, half) + i;
                }
            }
        }
        return sum;
}

public long part2() {
    long sum = 0;
    String line = AoCUtil.getLine("input2.txt");
    String[] ranges = line.split(",");
    for (String range : ranges) {
        String[] rangeBounds = range.split("-");
        long min = Long.parseLong(rangeBounds[0]);
        long max = Long.parseLong(rangeBounds[1]);
        for (long i = min; i <= max; i++) {
            String numberStr = String.valueOf(i);
            if (AoCUtil.isAllDigitsEqual(numberStr)) {
                sum += i;
                continue;
            }
            int digitCount = numberStr.length();
            if (AoCUtil.isPrime(digitCount))
                continue;

            List<Integer> divisors = AoCUtil.findDivisors(digitCount);
            for (Integer divisor : divisors) {
                if (AoCUtil.existPattern(numberStr, divisor)) {
                    sum += i;
                    break;
                }
            }
        }
    }
    return sum;
}

}
