package org.example.lb4;

import nu.pattern.OpenCV;
import org.example.utils.DirectoryUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.example.LabConstants.*;

public class HaarDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        run();
    }
    public static void run() {
        Mat image = Imgcodecs.imread(FACES_PATH);
        CascadeClassifier faceDetector = new CascadeClassifier(HAAS_CASCADE_PATH);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 2);
        }
        String output = RESOURCE_PATH + "lb4/haar";

        if (!DirectoryUtils.isDirPresent(output)) {
            DirectoryUtils.createDir(output);
        }
        output = output + "/haar.jpg";
        Imgcodecs.imwrite(output, image);
    }
}
