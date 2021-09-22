package org.firstinspires.ftc.teamcode

import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

class DuckDetectionPipeline : OpenCvPipeline() {
    // HSV Color bounds for inRange() check
    val lowerBound = Scalar(10.0, 200.0, 150.0)
    val upperBound = Scalar(20.0, 255.0, 255.0)

    // Mats defined in order of how they are used in processFrame()
    var hsv = Mat() // inputMat converted from RGB to HSV
    var mask = Mat() // hsv with isolated parts of interest color
    val kernel: Mat =  Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(3.0, 3.0))
    var cleanMask = Mat() // same as mask, but eroded and dilated to clean up stray pixels
    var result = Mat() // The original image with boxes around color areas
    var hierarchy = Mat()
    var largestAreaMat = MatOfPoint()

    val contours = ArrayList<MatOfPoint>()

    var duckZone = -1
        private set

    override fun processFrame(input: Mat?): Mat {
        contours.clear()

        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV)
        Core.inRange(hsv, lowerBound, upperBound, mask)
        Imgproc.morphologyEx(mask, cleanMask, Imgproc.MORPH_OPEN, kernel)
        Imgproc.findContours(cleanMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)

        result.setTo(input)

        var maxArea = 0.0

        for (c in contours) {
            val area = Imgproc.contourArea(c)
            if (area > maxArea) {
                maxArea = area
                largestAreaMat = c

                val boundingRect = Imgproc.boundingRect(c)
                val pt1 = Point(boundingRect.x.toDouble(), boundingRect.y.toDouble())
                val pt2 = Point(
                    (boundingRect.x + boundingRect.width).toDouble(),
                    (boundingRect.y + boundingRect.height).toDouble()
                )
                // Draw white box around contour bounds
                Imgproc.rectangle(result, pt1, pt2, Scalar(0.0, 0.0, 255.0))
            }
        }

        updateZone(largestAreaMat)

        Imgproc.cvtColor(result, result, Imgproc.COLOR_HSV2RGB)
        return result
    }

    private fun updateZone(contour: MatOfPoint) {
        val boundingRect = Imgproc.boundingRect(contour)
        var middle = (boundingRect.x + boundingRect.width) / 2
        // Check if the middle is within certain ranges
        // TODO: fix the hardcoded values, and make an enum to store ints
        duckZone = when (middle) {
            in 0..106 -> 0
            in 106..213 -> 1
            in 213..320 -> 2
            else -> -1
        }
    }

}