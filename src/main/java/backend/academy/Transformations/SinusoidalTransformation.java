package backend.academy.Transformations;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Color;
import backend.academy.Primitives.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static java.lang.Math.sin;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinusoidalTransformation extends Transformation {
    private static final double X_COEFFICIENT = 1;
    private static final double Y_COEFFICIENT = 1;

    private Color color = new Color();
    private Coefficients coefficients = new Coefficients();

    @Override
    protected double transformX(Point p) {
        return sin(p.x()) * X_COEFFICIENT;
    }

    @Override
    protected double transformY(Point p) {
        return sin(p.y()) * Y_COEFFICIENT;
    }

}
