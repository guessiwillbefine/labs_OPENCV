package org.example.lb1;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.SwingConstants.HORIZONTAL;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

public class CannyDemo extends JFrame {

    private final JSlider slider;
    private final JSlider slider2;
    private int trash = 0;
    private int trash2 = 0;
    private ChangeListener listener;

    public CannyDemo() {
        super("Canny Demo");
        setSize(640, 200);

        slider = new JSlider(HORIZONTAL, 0, 255, 100);
        slider.addChangeListener(listener);
        add(slider, NORTH);

        slider2 = new JSlider(HORIZONTAL, 0, 255, 100);
        slider2.addChangeListener(listener);
        add(slider2, SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        listener = e -> {
            if (e.getSource() == slider) {
                trash = slider.getValue();
            }
            if (e.getSource() == slider2) {
                trash2 = slider2.getValue();
            }
        };
    }

    public static void main(String[] args) {
        OpenCV.loadLocally();
        CannyDemo demo = new CannyDemo();
        demo.run();
    }

    public void run() {
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened())
            return;

        Mat frame = new Mat();
        Mat grayFrame = new Mat();
        Mat edges = new Mat();

        while (camera.read(frame)) {
            Imgproc.cvtColor(frame, grayFrame, COLOR_BGR2GRAY);
            Imgproc.Canny(grayFrame, edges, trash, trash2);
            HighGui.imshow("Edges", edges);
            if (HighGui.waitKey(10) == 27) {
                break;
            }
        }
    }
}
