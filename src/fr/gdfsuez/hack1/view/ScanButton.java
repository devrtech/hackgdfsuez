package fr.gdfsuez.hack1.view;

import fr.gdfsuez.hack1.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScanButton extends LinearLayout {

	public ScanButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// On attribue le xml
		View globalview = LayoutInflater.from(context).inflate(R.layout.view_scanbutton, this, true);
	}

}
