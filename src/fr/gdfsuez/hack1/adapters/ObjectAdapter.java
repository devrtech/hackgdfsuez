package fr.gdfsuez.hack1.adapters;

import java.sql.Array;
import java.util.List;

import fr.gdfsuez.hack1.GDFSuezApplication;
import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.model.ElectricObject;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

public class ObjectAdapter extends ArrayAdapter<ElectricObject> {

	public ObjectAdapter(Context context) {
		super(context, R.layout.view_object_item, GDFSuezApplication.getAppInstance().getObjects());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// On attribue le xml
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_object_item, null);
			ImageView picture = (ImageView) convertView.findViewById(R.id.picture);
			TextView nametv = (TextView) convertView.findViewById(R.id.text_name);
			TextView brandmodeltv = (TextView) convertView.findViewById(R.id.text_brandmodel);
			TextView theoricaltv = (TextView) convertView.findViewById(R.id.text_theorical);
			TextView estimatedtv = (TextView) convertView.findViewById(R.id.text_estimated);
			TextView statustv = (TextView) convertView.findViewById(R.id.text_status);
			SeekBar gradient = (SeekBar) convertView.findViewById(R.id.list_row_gradient);
			TextView energyLabel = (TextView) convertView.findViewById(R.id.list_row_energyLabel);
			ElectricObject object = getItem(position);

			nametv.setText(object.getName());
			brandmodeltv.setText(object.getBrand() + "-" + object.getModel());

			theoricaltv.setText(getContext().getResources().getString(R.string.consumption,
					object.getTheoricalConsumption()));

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
			energyLabel.setText(object.getEnergyLabel());
			gradient.setMax(100);
			int pos = 0;
			if (object.getEnergyLabel().startsWith("A+++"))
				pos = 0;
			else if (object.getEnergyLabel().startsWith("A++"))
				pos = 10;
			else if (object.getEnergyLabel().startsWith("A+"))
				pos = 20;
			else if (object.getEnergyLabel().startsWith("A"))
				pos = 30;
			else if (object.getEnergyLabel().startsWith("B"))
				pos = 40;
			else if (object.getEnergyLabel().startsWith("C"))
				pos = 50;
			else if (object.getEnergyLabel().startsWith("D"))
				pos = 60;
			else
				pos = 70;
			gradient.setProgress(pos);

			if (object.getName().equals("Aspirateur")) {
				picture.setImageResource(R.drawable.icon_aspi);
			} else if (object.getName().equals("Téléviseur")) {
				picture.setImageResource(R.drawable.icon_tv);
			} else if (object.getName().equals("Lave-Linge")) {
				picture.setImageResource(R.drawable.icon_washing);
			} else if (object.getName().equals("Réfrigérateur")) {
				picture.setImageResource(R.drawable.icon_frigo);
			} else if (object.getName().equals("Micro-ondes")) {
				picture.setImageResource(R.drawable.icon_micro);
			}
		}
		return convertView;
	}
}
