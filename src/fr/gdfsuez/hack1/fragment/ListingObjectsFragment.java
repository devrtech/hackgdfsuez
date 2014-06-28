package fr.gdfsuez.hack1.fragment;

import fr.gdfsuez.hack1.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListingObjectsFragment extends Fragment {

	public ListingObjectsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
		return rootView;
	}

}
