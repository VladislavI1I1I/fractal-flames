package backend.academy;

import backend.academy.Primitives.Coefficients;
import backend.academy.Primitives.Pixel;
import backend.academy.Primitives.Point;
import backend.academy.Transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Slf4j
public class Generator {

    final static double X_MIN = -1.777;
    final static double Y_MIN = -1.0;
    final static double X_MAX = 1.777;
    final static double Y_MAX = 1.0;
    final static int COEFFICIENTS_AMOUNT = 10;
    final static int SPECIFYING_STEPS = 30;
    final static ArrayList<Coefficients> COEFFICIENTS_LIST = new ArrayList<>(COEFFICIENTS_AMOUNT);

    public ArrayList<ArrayList<Pixel>> initializeMultipleThreads(
        int xResolution,
        int yResolution,
        int iterationCount,
        ArrayList<Transformation> transformationList,
        int dotsAmount,
        int symmetry,
        int threads
    ) {
        ArrayList<ArrayList<Pixel>> picture = new ArrayList<>();
        for (int i = 0; i <= xResolution; i++) {
            ArrayList<Pixel> row = new ArrayList<>();
            for (int j = 0; j <= yResolution; j++) {
                row.add(new Pixel());
            }
            picture.add(row);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < COEFFICIENTS_AMOUNT; i++) {
            COEFFICIENTS_LIST.add(new Coefficients());
        }

        List<Callable<Void>> tasks = new ArrayList<>(dotsAmount);
        for (int i = 0; i < dotsAmount; i++) {
            tasks.add(() -> {
                handlePoint(
                    iterationCount,
                    symmetry,
                    transformationList,
                    xResolution,
                    yResolution,
                    picture
                );
                return null;
            });
        }

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            log.error("Interrupted while waiting for tasks to complete", e);
        }
        return picture;
    }

    private void handlePoint(
        int iterationCount,
        int symmetry,
        ArrayList<Transformation> transformationList,
        int xResolution,
        int yResolution, ArrayList<ArrayList<Pixel>> picture
    ) {
        int xCoord;
        int yCoord;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Point newPoint = new Point(random.nextDouble(X_MIN, X_MAX), random.nextDouble(Y_MIN, Y_MAX));

        for (int curIteration = -SPECIFYING_STEPS; curIteration <= iterationCount; curIteration++) {
            Transformation transformation = transformationList.get(random.nextInt(transformationList.size()));
            Coefficients coefficients = COEFFICIENTS_LIST.get(random.nextInt(COEFFICIENTS_LIST.size()));

            newPoint = coefficients.useCoefficients(newPoint);
            newPoint = transformation.transform(newPoint);

            if (curIteration >= 0 && newPoint.y() >= Y_MIN && newPoint.y() <= Y_MAX
                && newPoint.x() >= X_MIN && newPoint.x() <= X_MAX) {

                double alpha = 0.0;
                for (int j = 0; j < symmetry; alpha += PI * 2 / symmetry, j++) {
                    Point rotatedPoint = rotate(newPoint, alpha);
                    if (rotatedPoint.x() < X_MIN || rotatedPoint.x() > X_MAX || rotatedPoint.y() < Y_MIN
                        || rotatedPoint.y() > Y_MAX) {
                        continue;
                    }

                    xCoord = (int) (xResolution - (((X_MAX - rotatedPoint.x()) / (X_MAX - X_MIN) * (xResolution))));
                    yCoord = (int) (yResolution - (((Y_MAX - rotatedPoint.y()) / (Y_MAX - Y_MIN) * (yResolution))));

                    Pixel pixel = picture.get(xCoord).get(yCoord);
                    synchronized (pixel) {
                        if (pixel.hitCount() != 0) {
                            pixel.color().red((pixel.color().red() + coefficients.color().red()) / 2);
                            pixel.color().green((pixel.color().green() + coefficients.color().green()) / 2);
                            pixel.color().blue((pixel.color().blue() + coefficients.color().blue()) / 2);
                        } else {
                            pixel.color().red(coefficients.color().red());
                            pixel.color().green(coefficients.color().green());
                            pixel.color().blue(coefficients.color().blue());
                        }
                        pixel.hitCount(pixel.hitCount() + 1);
                    }
                }
            }
        }
    }

    private Point rotate(Point p, double angle) {
        return new Point(p.x() * cos(angle) - p.y() * sin(angle), p.x() * sin(angle) + p.y() * cos(angle));
    }
}
