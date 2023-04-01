package org.example.lb2;

import nu.pattern.OpenCV;
import org.DirectoryUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import static org.example.LabConstants.CAT_PATH;
import static org.example.LabConstants.RESOURCE_PATH;
import static org.opencv.core.Core.flip;

public class FlipDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }
    public static void run() {
        Mat src = Imgcodecs.imread(CAT_PATH);

        Mat dst = new Mat();
        flip(src, dst, 0);

        String output = RESOURCE_PATH + "lb2/flip";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }

        output = output + "/flip.jpg";
        Imgcodecs.imwrite(output, dst);
    }
}
