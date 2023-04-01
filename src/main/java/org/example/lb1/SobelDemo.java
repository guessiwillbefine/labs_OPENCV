package org.example.lb1;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import static org.opencv.highgui.HighGui.*;


public class SobelDemo {
    public static void main(String[] args) {
        OpenCV.loadLocally();
        SobelDemo demo = new SobelDemo();
        demo.run();
    }

    public void run() {
        VideoCapture capture = new VideoCapture();
        capture.open(0);

        if (!capture.isOpened()) {
            return;
        }

        String windowName = "Sobel Camera Example";
        namedWindow(windowName);

        Mat frame = new Mat();
        do {
            capture.read(frame);

            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

            Mat gradX = new Mat();
            Mat gradY = new Mat();
            Mat absGradX = new Mat();
            Mat absGradY = new Mat();
            Imgproc.Sobel(gray, gradX, CvType.CV_16S, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
            Imgproc.Sobel(gray, gradY, CvType.CV_16S, 0, 1, 3, 1, 0, Core.BORDER_DEFAULT);
            Core.convertScaleAbs(gradX, absGradX);
            Core.convertScaleAbs(gradY, absGradY);
            Mat dst = new Mat();
            Core.addWeighted(absGradX, 0.5, absGradY, 0.5, 0, dst);

            imshow(windowName, dst);

        } while (waitKey(30) < 0);

        capture.release();
        destroyAllWindows();
    }
}