package fr.gdfsuez.hack1.view;

import android.hardware.Camera;
import android.hardware.Camera.Size;

/**
 * Couple de dimensions de capteurs
 * 
 * @author remi
 * 
 */
public class CoupledCameraSizes {

	// La ratio
	private float ratio;

	// Taille du capteur
	private Camera.Size picturesize;

	// Taille de la preview
	private Camera.Size previewsize;

	/* *** CONSTRUCTORS *** */

	public CoupledCameraSizes(float ratio, Size picturesize) {
		super();
		this.ratio = ratio;
		this.picturesize = picturesize;
	}

	/* *** GETTERS AND SETTERS *** */

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public Camera.Size getPictureSize() {
		return picturesize;
	}

	public void setPictureSize(Camera.Size picturesize) {
		this.picturesize = picturesize;
	}

	public Camera.Size getPreviewSize() {
		return previewsize;
	}

	public void setPreviewSize(Camera.Size previewsize) {
		this.previewsize = previewsize;
	}

}
