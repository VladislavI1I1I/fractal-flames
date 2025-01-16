package backend.academy.Primitives;

import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("MagicNumber")
public class Color {

    private int red;
    private int green;
    private int blue;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color() {
        red = random.nextInt(255);
        green = random.nextInt(255);
        blue = random.nextInt(255);
    }
}
