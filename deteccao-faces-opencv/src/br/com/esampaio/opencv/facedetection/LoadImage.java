package br.com.esampaio.opencv.facedetection;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class LoadImage {
	public static void main(String[] args) {
		System.out.println("opencv version: "+Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat loadedImageMat = imread("minerva5.jpg");	
		Utilitarios utilitarios = new Utilitarios();
	
		Mat grayedImageMat = new Mat();
		Imgproc.cvtColor(loadedImageMat, grayedImageMat, Imgproc.COLOR_BGR2GRAY);
		
		utilitarios.mostraImagem( utilitarios.convertMatToImage(grayedImageMat));
	}
}

//-Djava.library.path=""