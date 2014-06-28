package fr.gdfsuez.hack1;

import android.hardware.Camera;

public class Utils {

	/**
	 * Récupère le cameraId de la camera frontale
	 * 
	 * @return cameraId ou -1 s'il y en a pas
	 */
	static final public int getBackCameraId() {
		// Nombre des caméras
		int num = Camera.getNumberOfCameras();
		// TODO
		System.out.println("cameras : " + num);
		// Pracours des cameras
		for (int i = 0; i < num; i++) {
			// On instancie les infos cameras
			Camera.CameraInfo info = new Camera.CameraInfo();
			// On récupère les infos
			Camera.getCameraInfo(i, info);
			// TODO
			System.out.println("camera : " + i + " infos :  " + info);
			// On vérifie si on affaire à la caméra frontale
			if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				// On a une camera arrière
				return i;
			}
		}
		// On en a pas
		return -1;
	}

}
