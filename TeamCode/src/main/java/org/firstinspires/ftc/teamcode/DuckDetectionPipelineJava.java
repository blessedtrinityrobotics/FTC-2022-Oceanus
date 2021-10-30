package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.ConfigConstants.*;
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

public class DuckDetectionPipelineJava extends OpenCvPipeline {
    private Scalar lower_bound = new Scalar(lowHue, lowSat, lowVal);
    private Scalar upper_bound = new Scalar(hue, sat, val);
    private Mat empty = new Mat();
    private Mat mask = new Mat();
    private Mat result = new Mat();
    private Mat hierarchy = new Mat();
    private Mat kernel = new Mat();
    private Mat opening = new Mat();
    private List<MatOfPoint> contours = new ArrayList<>();
    private Telemetry telemetry;
    private MatOfPoint largestAreaMat = new MatOfPoint();
    private int increment = CAM_RES_X/3;

    // Default zone is center, be warned, this could be a problem
    int level = 0;

    public DuckDetectionPipelineJava(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat inputMat) {
        // Very important, so that the stuff doesn't build up over frames
        contours.clear();

        Imgproc.cvtColor(inputMat, inputMat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(inputMat, new Scalar(lowHue, lowSat, lowVal), new Scalar(hue, sat, val), mask);

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
        Imgproc.putText(inputMat, "Level: " + level, new Point(10, 25), 2, 1, new Scalar(255, 255, 255), 2, Imgproc.LINE_AA);
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
            level = 1;
        } else if (middleX < increment * 2) {
            level = 2;
        } else {
            level = 3;
        }
        telemetry.addData("Level", level);
    }

}