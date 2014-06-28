package fr.gdfsuez.hack1.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.adapters.PromoListArrayAdapter;
import fr.gdfsuez.hack1.model.Promo;

public class PromoFragment extends Fragment {

	private ListView 			listview;
	private ArrayList<Promo>	promos;

	private Promo giveMeATestPromo(String brand, String deviceName, String devicePrice, String energyLabel, String model, int nbBought, double d, int type) {
		Promo p = new Promo();
		p.setBrand(brand);
		p.setDeviceName(deviceName);
		p.setDevicePrice(devicePrice);
		p.setEnergyLabel(energyLabel);
		p.setModel(model);
		p.setNbBought(nbBought);
		p.setPowerComsumption(d);
		p.setType(type);
		return p;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_promo, container, false);

		listview = (ListView) rootView.findViewById(android.R.id.list);

		promos = new ArrayList<Promo>();
		Promo p = giveMeATestPromo("Whirlpool", "frigo refroidissant", "450€", "C+", "4512-42W", 5, 241.24, 0);
		promos.add(p);
		p = giveMeATestPromo("Sonic", "frigo de herisson", "850€", "A+", "845W54-42A", 0, 881, 0);
		promos.add(p);
		p = giveMeATestPromo("Electrolux", "Lave linge vapeur top", "42€", "B-", " Ewt1366HZW", 2, 1366, 1);
		promos.add(p);
		p = giveMeATestPromo("Electrolux", "Lave linge super", "450€", "C+", " Ewt1367HZW", 1, 241.24, 0);
		promos.add(p);
		p = giveMeATestPromo("Samsung", "TV 3D special Coupe du monde !", "4500€", "A", "42PN455", 18, 241, 3);
		promos.add(p);
		p = giveMeATestPromo("Whirlpool", "frigo refroidissant", "450€", "C+", "4512-42W", 5, 241.24, 0);
		promos.add(p);
		p = giveMeATestPromo("Whirlpool", "Seche linge", "450€", "C+", "4512-42W", 5, 241.24, 0);
		promos.add(p);

		
		PromoListArrayAdapter adapter = new PromoListArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, promos);
		listview.setAdapter(adapter);
		listview.setDividerHeight(25);

		listview.setOnItemClickListener(new ShowItemDetails());
		return rootView;
	}

	private class ShowItemDetails implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getActivity(), "clicked on : " + position, Toast.LENGTH_SHORT).show();
		}
		
	}
}
