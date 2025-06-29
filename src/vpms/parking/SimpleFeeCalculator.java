package vpms.parking;

import vpms.interface_.FeeCalculator;

public class SimpleFeeCalculator implements FeeCalculator {
    private static final double RATE_PER_HOUR = 20.0;

    public double calculateFee(long durationMinutes) {
        return Math.ceil(durationMinutes / 60.0) * RATE_PER_HOUR;
    }
}
