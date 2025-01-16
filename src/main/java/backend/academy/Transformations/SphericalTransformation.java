package backend.academy.Transformations;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Color;
import backend.academy.Primitives.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static java.lang.Math.pow;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SphericalTransformation extends Transformation {
    private static final double X_COEFFICIENT = 1.5;
    private static final double Y_COEFFICIENT = 1;

    private Color color = new Color();
    private Coefficients coefficients = new Coefficients();

    @Override
    public Point transform(Point p) {

        return new Point(transformX(p), transformY(p));
    }

    private double sumOfCoordinateSquares(Point p) {
        return pow(p.x(), 2) + pow(p.y(), 2);
    }

    @Override
    protected double transformX(Point p) {
        return p.x() / sumOfCoordinateSquares(p) * X_COEFFICIENT;
    }

    @Override
    protected double transformY(Point p) {
        return p.y() / sumOfCoordinateSquares(p) * Y_COEFFICIENT;
    }

}
