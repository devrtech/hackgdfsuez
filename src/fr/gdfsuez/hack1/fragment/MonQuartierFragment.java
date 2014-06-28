package fr.gdfsuez.hack1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import fr.gdfsuez.hack1.R;

public class MonQuartierFragment extends Fragment {

	private int nbClicks;
	
	public MonQuartierFragment() {
		super();
		nbClicks = 0;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mon_quartier, container, false);
		
		ImageView neighboors = (ImageView) rootView.findViewById(R.id.image_mon_quartier);
		neighboors.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "nbClicks = " + nbClicks, Toast.LENGTH_SHORT).show();
				nbClicks++;
			}
		});
		
		return rootView;
	}

}
