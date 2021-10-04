package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.ConstantsKt.*;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

enum Zone {
    LEFT,
    CENTER,
    RIGHT
}

public class DuckDetectionPipelineJava extends OpenCvPipeline {
    Scalar lower_bound = new Scalar(10, 200, 150);
    Scalar upper_bound = new Scalar(20, 255, 255);
    Mat empty = new Mat();
    Mat mask = new Mat();
    Mat result = new Mat();
    Mat hierarchy = new Mat();
    Mat kernel = new Mat();
    Mat opening = new Mat();
    List<MatOfPoint> contours = new ArrayList<>();
    Telemetry telemetry;
    MatOfPoint largestAreaMat = new MatOfPoint();
    int increment = CAM_RES_X/3;
    Zone zone = Zone.CENTER;

    public DuckDetectionPipelineJava(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat inputMat) {
        // Very important, so that the stuff doesn't build up over frames
        contours.clear();

        Imgproc.cvtColor(inputMat, inputMat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(inputMat, lower_bound, upper_bound, mask);

        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.morphologyEx(mask, opening, Imgproc.MORPH_OPEN, kernel);

        Imgproc.findContours(opening, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = -1.0;
        for (MatOfPoint c : contours) {
            double area = Imgproc.contourArea(c);
            if (area > maxArea) {
               maxArea = area;
               largestAreaMat = c;
            }
        }

        Rect r = Imgproc.boundingRect(largestAreaMat);
        Imgproc.rectangle(inputMat, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height),
                new Scalar(0, 0, 255));
        // inputMat.setTo(lower_bound);
        updateZone();
        Imgproc.cvtColor(inputMat, inputMat, Imgproc.COLOR_HSV2RGB);
        // Nice little debug message for the current zone
        Imgproc.putText(inputMat, "Zone: " + zone, new Point(10, 25), 2, 1, new Scalar(255, 255, 255), 2, Imgproc.LINE_AA);
        return inputMat;
    }

    /**
     * Evaluates the potential duck location on the screen and
     * sets the Zone variable accordingly
     */
    void updateZone() {
        Rect r = Imgproc.boundingRect(largestAreaMat);
        // get the center of the rect, since x is defined as the top left
        int middleX = r.x + r.width/2;
        // Check for each zone, regardless of height
        if (middleX < increment) {
            zone = Zone.LEFT;
        } else if (middleX < increment * 2) {
            zone = Zone.CENTER;
        } else {
            zone = Zone.RIGHT;
        }
    }

}