package br.com.esampaio.opencv.facedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Exercice3 {
	public static void main(String[] args) {
		System.out.println("opencv version: "+Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);	
		Mat imgage = Imgcodecs.imread("outros/gato3.jpg");
		
		Mat grayedImage = new Mat();
		Imgproc.cvtColor(imgage, grayedImage, Imgproc.COLOR_BGR2GRAY);
		
		CascadeClassifier cascadeClassifier  = new CascadeClassifier("cascades/haarcascade_frontalcatface.xml");
		MatOfRect detectedFaces = new MatOfRect();
		cascadeClassifier.detectMultiScale(
				grayedImage, 
				detectedFaces,
				1.05,
				4,
				0,
				new Size(30,30),
				new Size(500,500)
				
				);
		System.out.println("detected Faces: "+detectedFaces.toArray().length);
		
		for(Rect rect : detectedFaces.toArray()) {
			System.out.println(rect.x+ " "+rect.y+ " "+rect.width+" "+rect.height);
			Imgproc.rectangle(
					imgage, 
					new Point(rect.x,rect.y),
					new Point(rect.x + rect.width,rect.y + rect.height),
					new Scalar(0,0,255),2);
		}
		
		Utilitarios utilitarios = new Utilitarios();
		utilitarios.mostraImagem(utilitarios.convertMatToImage(imgage));
	}
}
