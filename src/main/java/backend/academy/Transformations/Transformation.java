package backend.academy.Transformations;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Color;
import backend.academy.Primitives.Point;
import lombok.Getter;

@Getter
public abstract class Transformation {
    Color color = new Color();
    Coefficients coefficients = new Coefficients();

    public Point transform(Point p) {
        return new Point(transformX(p), transformY(p));
    }

    public Point useCoefficients(Point p) {
        return new Point(p.x() * coefficients.a() + p.y() * coefficients.b() + coefficients.c(),
                        p.x() * coefficients.d() + p.y() * coefficients.e() + coefficients.f());
    }

    protected abstract double transformX(Point p);

    protected abstract double transformY(Point p);
}

