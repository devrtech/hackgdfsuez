package fr.gdfsuez.hack1.fragment;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.adapters.ObjectAdapter;
import fr.gdfsuez.hack1.model.ElectricObject;
import fr.gdfsuez.hack1.view.HeaderView;
import fr.gdfsuez.hack1.view.ScanButton;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.util.TypedValue;
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

	private HeaderView headerview;

	public ListingObjectsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
		objectslistview = (ListView) rootView.findViewById(R.id.list_objects);

		// Button button = new Button(getActivity(), null, R.style.button);
		// button.setText("ReScan");

		headerview = new HeaderView(getActivity(), null);

		objectslistview.addHeaderView(headerview);

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
		TextView lastscan = new TextView(getActivity());
		lastscan.setText("Date de dernier scan : "
				+ df.format(GDFSuezApplication.getAppInstance().getScanDate()));

		lastscan.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		lastscan.setPadding(10, 10, 10, 10);
		objectslistview.addFooterView(lastscan);

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
		headerview.setConsumption(c);
	}

}
