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

    public static Scalar generateRed() {
        return new Scalar(255, 0, 0);
    }

    public static Scalar generateGreen() {
        return new Scalar(0, 255, 0);
    }

    public static Scalar generateBlue() {
        return new Scalar(0, 0, 255);
    }
}
