package fr.gdfsuez.hack1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.Utils;
import fr.gdfsuez.hack1.view.CameraSurfaceView;

/**
 * Réalité augmentée
 * 
 * @author remi
 * 
 */
public class ScanObjectsActivity extends Activity {

	// RequestCode pour finish
	static final public int SCAN_COMPLETED_REQUESTCODE = 1;

	// SurfaceView d'affichage de preview
	protected CameraSurfaceView photo_surfaceview;

	// Affichage des consommations
	protected TextView estimationtextview;
	protected TextView realtextview;

	// ID de la camera à utiliser
	protected int cameraid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Définition du background de l'actionbar
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		// Création de la View
		setContentView(R.layout.activity_tracking);
		// On extrait les widgets
		photo_surfaceview = (CameraSurfaceView) findViewById(R.id.surface_camera);
		// estimationtextview = (TextView) findViewById(R.id.text_estimation);
		realtextview = (TextView) findViewById(R.id.text_real);
		// Utilisation de la camera arrière
		cameraid = Utils.getBackCameraId();
		// On défini l'id de camera
		photo_surfaceview.setCameraId(cameraid);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// int e = GDFSuezApplication.getAppInstance().getEstimatedConsumption();
		int r = GDFSuezApplication.getAppInstance().getRealTimeConsumption();

		// estimationtextview.setText("" + e);
		realtextview.setText(getResources().getString(R.string.consumption_realtime, r));

	}

	/* *** HANDLERS *** */

	public void onClickScanActivity(View view) {
		this.finish();
	}

}
