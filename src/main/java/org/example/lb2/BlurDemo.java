package org.example.lb2;

import nu.pattern.OpenCV;
import org.example.utils.DirectoryUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.example.LabConstants.CAT_PATH;
import static org.example.LabConstants.RESOURCE_PATH;

public class BlurDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }

    public static void run() {
        Mat src = Imgcodecs.imread(CAT_PATH);

        Mat dst = new Mat();
        Imgproc.GaussianBlur(src, dst, new Size(81, 81), 0, 0);

        String output = RESOURCE_PATH + "lb2/blur";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }

        output = output + "/blur.jpg";
        Imgcodecs.imwrite(output, dst);
    }
}
