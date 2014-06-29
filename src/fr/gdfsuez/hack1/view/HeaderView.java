package fr.gdfsuez.hack1.view;

import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeaderView extends LinearLayout {

	// Affichage des consommations
	protected TextView eq_textview;
	protected TextView av_textview;

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// On attribue le xml
		View globalview = LayoutInflater.from(context).inflate(R.layout.view_header, this, true);

		// estimationtextview = (TextView) findViewById(R.id.text_estimation);
		eq_textview = (TextView) globalview.findViewById(R.id.text_equipment);
		av_textview = (TextView) globalview.findViewById(R.id.text_average);

		int e = GDFSuezApplication.getAppInstance().getEstimatedConsumption();
		int r = GDFSuezApplication.getAppInstance().getRealTimeConsumption();

		// estimationtextview.setText("" + e);
		av_textview.setText(getResources().getString(R.string.consumption_wh, r));

	}

	public void setConsumption(int c) {
		eq_textview.setText(getResources().getString(R.string.consumption_wh, c));
	}

}
