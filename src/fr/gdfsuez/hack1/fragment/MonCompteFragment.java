package fr.gdfsuez.hack1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.gdfsuez.hack1.R;

public class MonCompteFragment extends Fragment {

	
	public MonCompteFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mon_compte, container, false);
		
		return rootView;
	}

}
