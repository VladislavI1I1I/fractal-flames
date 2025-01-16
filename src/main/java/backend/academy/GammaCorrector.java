package backend.academy;

import backend.academy.Primitives.Pixel;
import java.util.ArrayList;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrector {
    static private final double GAMMA = 2.2;

    public void correctImage(ArrayList<ArrayList<Pixel>> picture) {
        double max = 0.0;
        for (ArrayList<Pixel> value : picture) {
            for (Pixel pixel : value) {
                if (pixel.hitCount() != 0) {
                    pixel.normalized(log10(pixel.hitCount()));
                    if (pixel.normalized() > max) {
                        max = pixel.normalized();
                    }
                }
            }
        }
        for (ArrayList<Pixel> pixels : picture) {
            for (Pixel pixel : pixels) {
                pixel.normalized(pixel.normalized() / max);
                pixel.color().red(
                    (int) (pixel.color().red() * pow(pixel.normalized(), 1.0 / GAMMA)));
                pixel.color().green(
                    (int) (pixel.color().green() * pow(pixel.normalized(), 1.0 / GAMMA)));
                pixel.color().blue(
                    (int) (pixel.color().blue() * pow(pixel.normalized(), 1.0 / GAMMA)));
            }
        }
    }
}
