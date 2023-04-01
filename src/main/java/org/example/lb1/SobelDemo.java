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

        // проверка, удалось ли открыть камеру
        if (!capture.isOpened()) {
            System.out.println("Не удалось открыть камеру!");
            return;
        }

        // создание окна для вывода изображения
        String windowName = "Sobel Camera Example";
        namedWindow(windowName);

        // цикл для считывания изображений с камеры и их обработки
        Mat frame = new Mat();
        while (true) {
            // считывание изображения с камеры
            capture.read(frame);

            // преобразование изображения в градации серого
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

            // применение эффекта Собеля
            Mat grad_x = new Mat();
            Mat grad_y = new Mat();
            Mat abs_grad_x = new Mat();
            Mat abs_grad_y = new Mat();
            Imgproc.Sobel(gray, grad_x, CvType.CV_16S, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
            Imgproc.Sobel(gray, grad_y, CvType.CV_16S, 0, 1, 3, 1, 0, Core.BORDER_DEFAULT);
            Core.convertScaleAbs(grad_x, abs_grad_x);
            Core.convertScaleAbs(grad_y, abs_grad_y);
            Mat dst = new Mat();
            Core.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, dst);

            // вывод изображения на экран
            imshow(windowName, dst);

            // ожидание нажатия клавиши для выхода из цикла
            if (waitKey(30) >= 0) break;
        }

        // освобождение ресурсов
        capture.release();
        destroyAllWindows();
    }
}