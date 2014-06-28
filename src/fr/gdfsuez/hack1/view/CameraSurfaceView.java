package fr.gdfsuez.hack1.view;

import fr.gdfsuez.hack1.GDFSuezApplication;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * SurfaceView
 * 
 * @author remi
 * 
 */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback,
		Camera.PictureCallback {

	// View d'affichage d'une photo
	private ImageView placephoto_view;

	// ID de la camera à utiliser
	protected int cameraid;

	/**
	 * Constructeur
	 */
	public CameraSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	/* *** SETTERS *** */

	public void setImageView(ImageView view) {
		this.placephoto_view = view;
	}

	public void setCameraId(int cameraid) {
		this.cameraid = cameraid;
	}

	/* *** IMPLEMENTED METHODS *** */

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// On récupère le nombre de camera
		int numofcamera = Camera.getNumberOfCameras();
		// On vérifie si on une cohérence avec le cameraid
		if (cameraid >= numofcamera) {
			// On initialise à la 1ere camera
			cameraid = 0;
		}
		// On vérifie si on a une caméra
		if (numofcamera > 0) {
			// On initilise la novelle camera
			GDFSuezApplication.initCamera(cameraid, getWidth(), getHeight());
			// On démarre la camera
			GDFSuezApplication.startCamera(holder, this);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// On stoppe la camera
		GDFSuezApplication.stopCamera();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		// Création du bitmap obtenu
		Bitmap rawbitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		// On récupère l'orientation
		Display display = ((WindowManager) getContext().getSystemService(Activity.WINDOW_SERVICE))
				.getDefaultDisplay();
		// Angle de rotation à appliquer
		int angle = 0;
		// On vérifie et on applique la rotation
		if (display.getRotation() == Surface.ROTATION_0) {
			// Angle de rotation
			angle = 90;
		} else if (display.getRotation() == Surface.ROTATION_90) {
			// Angle de rotation
			angle = 0;
		} else if (display.getRotation() == Surface.ROTATION_180) {
			// Angle de rotation
			angle = 270;
		} else if (display.getRotation() == Surface.ROTATION_270) {
			// Angle de rotation
			angle = 180;
		}
		// Matrice de correction : rotation
		Matrix rotationmatrix = new Matrix();
		// On instancie les infos cameras
		Camera.CameraInfo info = new Camera.CameraInfo();
		// On récupère les infos
		Camera.getCameraInfo(cameraid, info);
		// On vérifie si on affaire à la caméra frontale
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			// Matrice de correction : inversion (miroir)
			Matrix mirormatrix = new Matrix();
			mirormatrix.preScale(-1, 1);
			// Cacul matriciels
			rotationmatrix.postConcat(mirormatrix);
		}
		// On vérifie si on a besoin de rotation
		if (angle != 0) {
			// On applique la rotation à la matrice
			rotationmatrix.postRotate(angle);
		}
		// Création du bitmap avec correction (rotation et/ou inversion)
		Bitmap capturedbitmap = Bitmap.createBitmap(rawbitmap, 0, 0, rawbitmap.getWidth(),
				rawbitmap.getHeight(), rotationmatrix, true);
		// On scale le bitmap our fit la view
		capturedbitmap = Bitmap.createScaledBitmap(capturedbitmap, getWidth(), getHeight(), true);
		// On vérifie si on a une view sur laquelle appliquer la capture
		if (placephoto_view != null) {
			// On place enfin la photo
			placephoto_view.setVisibility(View.VISIBLE);
			// On place enfin la photo
			placephoto_view.setImageBitmap(capturedbitmap);
			// placephoto_view.setImageBitmap(rawbitmap);
			// placephoto_view.setScaleType(ScaleType.CENTER_CROP);
		}
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
	}

}
