package fr.gdfsuez.hack1.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import fr.gdfsuez.hack1.R;
import fr.gdfsuez.hack1.model.Promo;

public class PromoListArrayAdapter extends ArrayAdapter<Promo> {

	private ArrayList<Promo>	 	objs;
	private static LayoutInflater 		mInflater = null;
	private Promo 						promo;

	
	public PromoListArrayAdapter(Context context, int layout, ArrayList<Promo> objs) {
		super(context, layout, objs);
		this.objs = objs;
		PromoListArrayAdapter.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		promo = objs.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_row_promo, parent, false);
			holder.image = (ImageView) convertView.findViewById(R.id.list_row_image);
			holder.name = (TextView) convertView.findViewById(R.id.list_row_name);
			holder.brand = (TextView) convertView.findViewById(R.id.list_row_brand);
			holder.model = (TextView) convertView.findViewById(R.id.list_row_model);
			holder.gradient = (SeekBar) convertView.findViewById(R.id.list_row_gradient);
			holder.energyLabel = (TextView) convertView.findViewById(R.id.list_row_energyLabel);
			holder.price = (TextView) convertView.findViewById(R.id.list_row_price);
			holder.newPrice = (TextView) convertView.findViewById(R.id.list_row_new_price);
			holder.nbBuyers = (TextView) convertView.findViewById(R.id.list_row_nb_buyers);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		String mediaUrl = promo.getImgUrl();
		if (mediaUrl != null && mediaUrl.length() > 0) {
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(mediaUrl, holder.image);
		}
		holder.name.setText(promo.getDeviceName());
		holder.brand.setText(promo.getBrand());
		holder.model.setText(promo.getModel());
		holder.gradient.setMax(100);
		
		int pos = 0;
		if (promo.getEnergyLabel().startsWith("A+++"))
			pos = 0;
		else if (promo.getEnergyLabel().startsWith("A++"))
			pos = 10;
		else if (promo.getEnergyLabel().startsWith("A+"))
			pos = 20;
		else if (promo.getEnergyLabel().startsWith("A"))
			pos = 30;
		else if (promo.getEnergyLabel().startsWith("B"))
			pos = 40;
		else if (promo.getEnergyLabel().startsWith("C"))
			pos = 50;
		else if (promo.getEnergyLabel().startsWith("D"))
			pos = 60;
		else
			pos = 70;
		holder.gradient.setProgress(pos);
		holder.gradient.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		holder.energyLabel.setText(promo.getEnergyLabel());
		holder.price.setText(promo.getDevicePrice());
		holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		String t = holder.price.getText().toString();
		String[] intsToParse = t.split("€");
		int p = Integer.valueOf(intsToParse[0]);
		p  = p - (p * 20 / 100);
		holder.newPrice.setText(String.valueOf(p) + "€");
		holder.nbBuyers.setText(promo.getNbBought() + " voisins ont acheté cet article !");
		
		return convertView;
	}

	private static class ViewHolder {
		ImageView	image;
		TextView	name;
		TextView	brand;
		TextView	model;
		SeekBar		gradient;
		TextView 	energyLabel;
		TextView 	price;
		TextView	newPrice;
		TextView	nbBuyers;
	}

}