package fr.gdfsuez.hack1.activity;

import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.fragment.HomeMenuFragment;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Activité mère (menu home)
 * 
 * @author remi
 * 
 */
public class HomeActivity extends MenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new HomeMenuFragment()).commit();
		}
	}

	@Override
	protected void defineLayout() {
		// setContentView(R.layout.activity_home);
		setContentView(R.layout.activity_main);
	}

}
