package backend.academy.Transformations;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Color;
import backend.academy.Primitives.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static java.lang.Math.PI;
import static java.lang.Math.atan;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolarTransformation extends Transformation {
    private static final double X_COEFFICIENT = 3;
    private static final double Y_COEFFICIENT = 1;

    private Color color = new Color();
    private Coefficients coefficients = new Coefficients();

    private double sqrtOfCoordinateSquares(Point p) {
        return Math.sqrt(Math.pow(p.x(), 2) + Math.pow(p.y(), 2));
    }

    @Override
    protected double transformX(Point p) {
        return atan(p.y() / p.x()) / PI * X_COEFFICIENT;
    }

    @Override
    protected double transformY(Point p) {
        return (sqrtOfCoordinateSquares(p) - 1) * Y_COEFFICIENT;
    }
}
