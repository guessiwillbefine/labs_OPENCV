package org.example.lb3;

import nu.pattern.OpenCV;
import org.DirectoryUtils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.example.LabConstants.*;

public class CirclesDemo {
    public static void main(String[] args) {
            OpenCV.loadLocally();
            run();
        }
        public static void run() {
            Mat image = Imgcodecs.imread(CIRCLES_PATH);
            Mat gray = new Mat();
            Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

            // Blur image to reduce noise
            Mat blurred = new Mat();
            Imgproc.GaussianBlur(gray, blurred, new org.opencv.core.Size(9, 9), 2, 2);

            // Apply Hough Transform to detect circles
            Mat circles = new Mat();
            Imgproc.HoughCircles(blurred, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 20, 100, 30, 5, 50);

            // Draw detected circles
            for (int i = 0; i < circles.cols(); i++) {
                double[] c = circles.get(0, i);
                Point center = new Point(Math.round(c[0]), Math.round(c[1]));
                int radius = (int) Math.round(c[2]);
                Imgproc.circle(image, center, radius, new Scalar(0, 0, 255), 2);
            }

            String output = RESOURCE_PATH + "lb3/circles";

            if (!DirectoryUtils.isDirPresent(output)) {
                DirectoryUtils.createDir(output);
            }
            output = output + "/circles.jpg";
            Imgcodecs.imwrite(output, image);
    }
}
