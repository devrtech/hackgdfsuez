package fr.gdfsuez.hack1.fragment;

import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.view.ObjectAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListingObjectsFragment extends Fragment {

	private ListView objectslistview;

	public ListingObjectsFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listing, container, false);
		objectslistview = (ListView) rootView.findViewById(R.id.list_objects);
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		objectslistview.setAdapter(new ObjectAdapter(getView().getContext()));
	}

}
