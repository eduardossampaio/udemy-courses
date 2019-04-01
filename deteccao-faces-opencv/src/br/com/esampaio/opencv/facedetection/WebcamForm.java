package br.com.esampaio.opencv.facedetection;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class WebcamForm extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6168407813181813966L;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 800;
	
	JPanel jPanel;
	public WebcamForm() {
		this.jPanel = new JPanel();
		this.jPanel.setSize(WIDTH,HEIGHT);
		this.setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(jPanel);
	}
	
	public void showVideo() {
		Mat frame =  new Mat();
		VideoCapture videoCapture = new VideoCapture(0);
		if ( videoCapture.isOpened()) {
			while(isVisible()) {
				videoCapture.read(frame);
				if(!frame.empty()) {
					setSize(frame.width(),frame.height());
					Rect[] detectedFaces = detectFaces(frame);
					for (Rect rect : detectedFaces) {
						Imgproc.rectangle(frame, rect, new Scalar(0,255,0));
					}
					
					BufferedImage convertedFrame = new Utilitarios().convertMatToImage(frame);
					Graphics graphics = jPanel.getGraphics();
					graphics.drawImage(convertedFrame, 0, 0,convertedFrame.getWidth(),convertedFrame.getHeight(),null);
					
				}
			}
		}
	}
	public static void showForm() {
		WebcamForm webcamForm = new WebcamForm();
		webcamForm.setVisible(true);
		webcamForm.showVideo();
	}
	
	public Rect[] detectFaces(Mat source) {
		Mat grayedImage = new Mat();
		Imgproc.cvtColor(source, grayedImage, Imgproc.COLOR_BGR2GRAY);
		CascadeClassifier cascadeClassifier  = new CascadeClassifier("cascades/haarcascade_frontalface_default.xml");
		MatOfRect detectedFaces = new MatOfRect();
		cascadeClassifier.detectMultiScale(
				grayedImage, 
				detectedFaces,
				1.19,
				2,
				0,
				new Size(100,100));
		return detectedFaces.toArray();
		
	}

}
