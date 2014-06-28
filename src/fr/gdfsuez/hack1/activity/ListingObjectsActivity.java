package fr.gdfsuez.hack1.activity;

import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.fragment.ListingObjectsFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListingObjectsActivity extends MenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new ListingObjectsFragment())
					.commit();
		}
	}

	@Override
	protected void defineLayout() {
		// setContentView(R.layout.activity_home);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
