package fr.gdfsuez.hack1.view;

import java.sql.Array;
import java.util.List;

import fr.gdfsuez.hack1.ElectricObject;
import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ObjectAdapter extends ArrayAdapter<ElectricObject> {

	public ObjectAdapter(Context context) {
		super(context, R.layout.view_object_item, GDFSuezApplication.getAppInstance().getObjects());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

			// On attribue le xml
			View globalview = LayoutInflater.from(getContext()).inflate(R.layout.view_object_item, null);
			TextView nametv = (TextView) globalview.findViewById(R.id.text_name);
			TextView modeltv = (TextView) globalview.findViewById(R.id.text_model);
			TextView theoricaltv = (TextView) globalview.findViewById(R.id.text_theorical);
			TextView estimatedtv = (TextView) globalview.findViewById(R.id.text_estimated);
			TextView statustv = (TextView) globalview.findViewById(R.id.text_status);

			ElectricObject object = getItem(position);

			nametv.setText(object.getName());
			modeltv.setText(object.getModel());
			theoricaltv.setText("" + object.getTheoricalConsumption());
			estimatedtv.setText("" + object.getEstimatedConsumption());
			int status = object.getStatus();
			if (status == ElectricObject.STATUS_OFF) {
				statustv.setTextColor(getContext().getResources().getColor(R.color.red));
			} else if (status == ElectricObject.STATUS_ON) {
				statustv.setTextColor(getContext().getResources().getColor(R.color.green));
			} else if (status == ElectricObject.STATUS_SLEEP) {
				statustv.setTextColor(getContext().getResources().getColor(R.color.blue));
			}
			statustv.setText(object.getStatusString());
			return globalview;

			// ObjectItemView view = new ObjectItemView(parent.getContext());
			// view.setItem(getItem(position));
			// return view;
		}
		return convertView;
	}

}
