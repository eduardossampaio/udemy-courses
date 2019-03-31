package br.com.esampaio.opencv.facedetection;

import org.opencv.core.Core;

public class WebcamTest {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);	
		System.out.println("opencv version: "+Core.VERSION);
		
		WebcamForm.showForm();
	}
}
