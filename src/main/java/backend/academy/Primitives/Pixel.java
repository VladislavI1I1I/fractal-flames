package backend.academy.Primitives;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private Color color = new Color();
    private double normalized;
    private int hitCount;

    @SuppressWarnings("MagicNumber")
    public int getColor() {
        return color.red() << 16 | color.green() << 8 | color.blue();
    }
}
