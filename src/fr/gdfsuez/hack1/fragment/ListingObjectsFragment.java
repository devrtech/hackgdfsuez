package fr.gdfsuez.hack1.fragment;

import java.util.List;
import java.util.Random;

import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.adapters.ObjectAdapter;
import fr.gdfsuez.hack1.model.ElectricObject;
import fr.gdfsuez.hack1.view.ScanButton;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListingObjectsFragment extends Fragment {

	private ListView objectslistview;

	// Affichage des consommations
	protected TextView eq_textview;
	protected TextView av_textview;

	public ListingObjectsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
		objectslistview = (ListView) rootView.findViewById(R.id.list_objects);

		// estimationtextview = (TextView) findViewById(R.id.text_estimation);
		eq_textview = (TextView) rootView.findViewById(R.id.text_equipment);
		av_textview = (TextView) rootView.findViewById(R.id.text_average);

		int e = GDFSuezApplication.getAppInstance().getEstimatedConsumption();
		int r = GDFSuezApplication.getAppInstance().getRealTimeConsumption();

		// estimationtextview.setText("" + e);
		av_textview.setText(getResources().getString(R.string.consumption_wh, r));

		// Button button = new Button(getActivity(), null, R.style.button);
		// button.setText("ReScan");

		ScanButton button = new ScanButton(getActivity(), null);
		// button.setLayoutParams(new ListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
		// LinearLayout.LayoutParams.WRAP_CONTENT));
		button.setGravity(Gravity.CENTER);

		objectslistview.addFooterView(button);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		objectslistview.setAdapter(new ObjectAdapter(getView().getContext()));
		List<ElectricObject> list = GDFSuezApplication.getAppInstance().getObjects();
		int c = 0;
		for (ElectricObject object : list) {
			c += object.getTheoricalConsumption();
		}
		Random random = new Random();
		int r = Math.abs(random.nextInt() % 110);
		c = (int) ((float) c * ((float) r / 1000F));
		eq_textview.setText(getResources().getString(R.string.consumption_wh, c));
	}

}
