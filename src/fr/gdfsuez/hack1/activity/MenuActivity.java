package fr.gdfsuez.hack1.activity;

import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.fragment.ListingObjectsFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

abstract public class MenuActivity extends Activity {

	// Layout du menu
	protected DrawerLayout menulayout; // Layout Principal
	private View leftview;

	// ActionBar
	protected ActionBarDrawerToggle menutoggle;

	abstract protected void defineLayout();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Définition du background de l'actionbar
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		defineLayout();
		leftview = (View) findViewById(R.id.menu_left);
		menulayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		menutoggle = new ActionBarDrawerToggle(this, menulayout, R.drawable.ic_launcher, R.string.menu_open,
				R.string.menu_close) {

			// Exécutée à la fermeture du menu
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(R.string.app_name);
				invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}

			// Exécutée à l’ouverture du menu
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(R.string.menu);
				invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
			}
		};
		menulayout.setDrawerListener(menutoggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = menulayout.isDrawerOpen(leftview);
		menu.findItem(R.id.action_open).setVisible(!drawerOpen);
		menu.findItem(R.id.action_close).setVisible(drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		menutoggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		menutoggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (menutoggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	/* *** HANDLERS *** */

	public void onClickListingObjectsActivity(View view) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		ListingObjectsFragment fragment = new ListingObjectsFragment();
		ft.replace(R.id.container, fragment);
		ft.commit();

	}

	public void onClickCameraActivity(View view) {
		Intent intent = new Intent(this, ScanObjectsActivity.class);
		startActivity(intent);
	}

}
