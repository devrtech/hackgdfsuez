package fr.gdfsuez.hack1.activity;

import java.util.List;

import fr.gdfsuez.hack1.ElectricObject;
import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import android.os.Bundle;
import android.widget.TextView;

public class ListingObjectsActivity extends MenuActivity {

	// Affichage des consommations
	protected TextView estimationtextview;
	protected TextView realtextview;
	private TextView tvlisting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Extraction des widgets
		tvlisting = (TextView) findViewById(R.id.text_listing);
		estimationtextview = (TextView) findViewById(R.id.text_estimation);
		realtextview = (TextView) findViewById(R.id.text_real);
	}

	@Override
	protected void defineLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_listing);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		List<ElectricObject> objects = GDFSuezApplication.getAppInstance().getObjects();
		StringBuilder listing = new StringBuilder();
		for (ElectricObject object : objects) {
			listing.append(object.toString() + "\n");
		}
		tvlisting.setText(listing.toString());
		int e = GDFSuezApplication.getAppInstance().getEstimatedConsumption();
		int r = GDFSuezApplication.getAppInstance().getRealTimeConsumption();

		estimationtextview.setText("" + e);
		realtextview.setText("" + r);
	}

}
