import backend.academy.Primitives.Point;
import backend.academy.Transformations.DiskTransformation;
import backend.academy.Transformations.HeartTransformation;
import backend.academy.Transformations.PolarTransformation;
import backend.academy.Transformations.SinusoidalTransformation;
import backend.academy.Transformations.SphericalTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformationsTests {

    @Test public void sinusoidalTransformationTest() {
        Point point = new Point(1, 1);
        SinusoidalTransformation transformation = new SinusoidalTransformation();
        Point transformedPoint = transformation.transform(point);
        assertEquals(0.8414709848078965,transformedPoint.x());
        assertEquals(0.8414709848078965,transformedPoint.y());
    }
    @Test public void diskTransformationTest() {
        Point point = new Point(1, 1);
        DiskTransformation transformation = new DiskTransformation();
        Point transformedPoint = transformation.transform(point);
        assertEquals(-0.722926899637408,transformedPoint.x());
        assertEquals(-0.13312767102070783,transformedPoint.y());
    }
    @Test public void heartTransformationTest() {
        Point point = new Point(1, 1);
        HeartTransformation transformation = new HeartTransformation();
        Point transformedPoint = transformation.transform(point);
        assertEquals(1.1657891608243354,transformedPoint.x());
        assertEquals(-0.3139666116489087,transformedPoint.y());

    }
    @Test public void polarTransformationTest() {
        Point point = new Point(1, 1);
        PolarTransformation transformation = new PolarTransformation();
        Point transformedPoint = transformation.transform(point);
        assertEquals(0.75,transformedPoint.x());
        assertEquals(0.41421356237309515,transformedPoint.y());
    }
    @Test public void sphericalTransformationTest() {
        Point point = new Point(1, 1);
        SphericalTransformation transformation = new SphericalTransformation();
        Point transformedPoint = transformation.transform(point);
        assertEquals(0.75,transformedPoint.x());
        assertEquals(0.5,transformedPoint.y());
    }
}
