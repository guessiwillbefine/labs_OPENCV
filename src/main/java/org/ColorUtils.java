package org;

import org.opencv.core.Scalar;

import java.util.Random;

public final class ColorUtils {
    private ColorUtils() { /*utility*/}
    private static final Random random = new Random();
    public static Scalar generateColor() {
        int maxColor = 256;
        return new Scalar(
                random.nextInt(maxColor),
                random.nextInt(maxColor),
                random.nextInt(maxColor));
    }
}
