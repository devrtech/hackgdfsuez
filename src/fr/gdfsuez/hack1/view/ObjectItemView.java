package fr.gdfsuez.hack1.view;

import fr.gdfsuez.hack1.ElectricObject;
import fr.gdfsuez.hack1.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ObjectItemView extends LinearLayout {

	// View globale
	protected View globalview;

	protected TextView nametv;
	protected TextView theoricaltv;
	protected TextView estimatedtv;
	protected TextView statustv;

	public ObjectItemView(Context context) {
		this(context, null);
	}

	public ObjectItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// On attribue le xml
		globalview = LayoutInflater.from(context).inflate(R.layout.view_object_item, this, true);
		nametv = (TextView) findViewById(R.id.text_name);
		theoricaltv = (TextView) findViewById(R.id.text_theorical);
		estimatedtv = (TextView) findViewById(R.id.text_estimated);
		statustv = (TextView) findViewById(R.id.text_status);
	}

	public void setItem(Object object) {
		if (object instanceof ElectricObject) {
			setItem((ElectricObject) object);
		}
	}

	public void setItem(ElectricObject object) {
		nametv.setText(object.getName());
		theoricaltv.setText(object.getTheoricalConsumption());
		estimatedtv.setText(object.getEstimatedConsumption());
		int status = object.getStatus();
		if (status == ElectricObject.STATUS_OFF) {
			statustv.setTextColor(getResources().getColor(R.color.red));
		} else if (status == ElectricObject.STATUS_ON) {
			statustv.setTextColor(getResources().getColor(R.color.green));
		} else if (status == ElectricObject.STATUS_SLEEP) {
			statustv.setTextColor(getResources().getColor(R.color.blue));
		}
		statustv.setText(object.getStatusString());
	}

}
