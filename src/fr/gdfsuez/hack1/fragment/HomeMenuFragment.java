package fr.gdfsuez.hack1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.gdfsuez.hack1.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeMenuFragment extends Fragment {

	public HomeMenuFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		return rootView;
	}
}
