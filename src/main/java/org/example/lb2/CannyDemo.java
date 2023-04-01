package org.example.lb2;

import nu.pattern.OpenCV;
import org.example.utils.DirectoryUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.example.LabConstants.CAT_PATH;
import static org.example.LabConstants.RESOURCE_PATH;

public class CannyDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run(100, 100);
    }
    public static void run(int trash, int trash2) {
        Mat src = Imgcodecs.imread(CAT_PATH);


        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);


        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, trash, trash2);

        String output = RESOURCE_PATH + "lb2/canny";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }

        output = output + "/canny.jpg";
        Imgcodecs.imwrite(output, edges);
    }

}