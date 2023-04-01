package org.example.lb3;

import nu.pattern.OpenCV;
import org.DirectoryUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.ColorUtils.generateColor;
import static org.example.LabConstants.*;

public class ContoursDemo {

    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }

    public static void run() {
        Mat src = Imgcodecs.imread(CAT_PATH);

        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        Mat thresh = new Mat();
        Imgproc.threshold(gray, thresh, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Mat dst = src.clone();
        Scalar scalar = generateColor();
        for (int i = 0; i < contours.size(); i++) {
            Imgproc.drawContours(dst, contours, i, scalar, 2);
        }

        Mat rects = dst.clone();
        Scalar scalar2 = generateColor();
        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            Imgproc.rectangle(rects,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    scalar2, 2);
        }

        String output = RESOURCE_PATH + "lb3/contours";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }
        output = output + "/contours.jpg";
        Imgcodecs.imwrite(output, rects);
    }
}
