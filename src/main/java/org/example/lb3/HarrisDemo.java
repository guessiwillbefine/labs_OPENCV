package org.example.lb3;

import nu.pattern.OpenCV;
import org.DirectoryUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.example.LabConstants.*;

public class HarrisDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }
    public static void run() {
        Mat image = Imgcodecs.imread(RECTANGLES_PATH);
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply Harris corner detection
        Mat corners = new Mat();
        Imgproc.cornerHarris(gray, corners, 2, 3, 0.04);

        // Normalize and threshold the result
        Mat normalized = new Mat();
        Core.normalize(corners, normalized, 0, 255, Core.NORM_MINMAX);
        Mat thresholded = new Mat();
        Imgproc.threshold(normalized, thresholded, 150, 255, Imgproc.THRESH_BINARY);

        // Draw circles around the detected corners
        for (int y = 0; y < corners.rows(); y++) {
            for (int x = 0; x < corners.cols(); x++) {
                double[] value = corners.get(y, x);
                if (value[0] > 0.01) {
                    Point point = new Point(x, y);
                    Imgproc.circle(image, point, 5, new Scalar(0, 255, 0), 2);
                }
            }
        }

        String output = RESOURCE_PATH + "lb3/harris";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }
        output = output + "/harris.png";
        Imgcodecs.imwrite(output, image);
    }
}
