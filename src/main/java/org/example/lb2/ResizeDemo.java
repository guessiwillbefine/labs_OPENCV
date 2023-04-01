package org.example.lb2;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.example.LabConstants.CAT_PATH;
import static org.example.LabConstants.RESOURCE_PATH;

public class ResizeDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }
    public static void run() {
        Mat src = Imgcodecs.imread(CAT_PATH);

        Mat dst = new Mat();
        Imgproc.resize(src, dst, new Size(81,81));

        String output = RESOURCE_PATH + "lb2/resize/resize.jpg";
        Imgcodecs.imwrite(output, dst);
    }
}
