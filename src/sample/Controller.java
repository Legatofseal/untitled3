package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

import static org.opencv.highgui.Highgui.imread;


public class Controller {
    static {
        nu.pattern.OpenCV.loadShared();
    }
    @FXML
    private ImageView img;


    public void start(ActionEvent actionEvent) {
        Image image = new Image("file:///C:/1.jpg");
        img.setImage(image);
     //   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File fileName1 = new File("file:///C:/1.jpg");
        Mat initial_Image = Highgui.imread(fileName1.getAbsolutePath());
        Mat hsvImage = new Mat();
        Mat mask = new Mat();
        Mat morphOutput = new Mat();

        Imgproc.cvtColor(initial_Image, hsvImage, Imgproc.COLOR_BGR2HSV);
        Scalar minValues = new Scalar(160,0,0);
        Scalar maxValues = new Scalar(180,255,255);
        Core.inRange(hsvImage, minValues, maxValues, mask);
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

        Imgproc.erode(mask, morphOutput, erodeElement);
        Imgproc.erode(mask, morphOutput, erodeElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
            // for each contour, display it in blue
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
            {
                Imgproc.drawContours(initial_Image, contours, idx, new Scalar(250, 0, 0));
            }
        }
    }
}
