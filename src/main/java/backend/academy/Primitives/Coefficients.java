package backend.academy.Primitives;

import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import static java.lang.Math.pow;

@Getter
public class Coefficients {
    private static final double LOWER_COEFFICIENT_BOUND = -1.5;
    private static final double UPPER_COEFFICIENT_BOUND = 1.5;

    private double a = 1;
    private double b = 1;
    private double c = 1;
    private double d = 1;
    private double e = 1;
    private double f = 1;
    Color color = new Color();
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public Coefficients() {
        while (!areCoefficientsCompressive()) {
            a = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
            b = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
            c = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
            d = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
            e = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
            f = random.nextDouble(LOWER_COEFFICIENT_BOUND, UPPER_COEFFICIENT_BOUND);
        }
    }

    public Point useCoefficients(Point p) {
        return new Point(a * p.x() + b * p.y() + c,
            d * p.x() + e * p.y() + f);
    }

    private boolean areCoefficientsCompressive() {
        return a * a + d * d < 1 && b * b + e * e < 1 && a * a + b * b + d * d + e * e < (1 + pow((a * e - b * d), 2));
    }

}
