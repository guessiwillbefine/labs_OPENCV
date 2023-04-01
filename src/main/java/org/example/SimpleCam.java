package org.example;


import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

/**
 *
 */
public class SimpleCam {

    public static void main(String[] args) {
        OpenCV.loadLocally();
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            return;
        }

        Mat frame = new Mat();

        while (camera.read(frame)) {
            HighGui.imshow("Original", frame);
            if (HighGui.waitKey(10) == 27) {
                break;
            }
        }
        camera.release();
        HighGui.destroyAllWindows();
    }
}
