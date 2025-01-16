package backend.academy.Transformations;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Color;
import backend.academy.Primitives.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeartTransformation extends Transformation {

    private static final double X_COEFFICIENT = 0.92;
    private static final double Y_COEFFICIENT = 0.5;
    private Color color = new Color();
    private Coefficients coefficients = new Coefficients();

    private double sqrtOfCoordinateSquares(Point p) {
        return Math.sqrt(Math.pow(p.x(), 2) + Math.pow(p.y(), 2));
    }

    private double atanYDivX(Point p) {
        return atan(p.y() / p.x());
    }

    @Override
    protected double transformX(Point p) {
        return sqrtOfCoordinateSquares(p) * sin(sqrtOfCoordinateSquares(p) * atanYDivX(p)) * X_COEFFICIENT;
    }

    @Override
    protected double transformY(Point p) {
        return -sqrtOfCoordinateSquares(p) * cos(sqrtOfCoordinateSquares(p) * atanYDivX(p)) * Y_COEFFICIENT;
    }

}
