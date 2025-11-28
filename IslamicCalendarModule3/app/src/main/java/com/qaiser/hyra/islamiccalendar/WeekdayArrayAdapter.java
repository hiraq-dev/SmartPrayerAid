package com.qaiser.hyra.islamiccalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Customize the weekday gridview
 */
public class WeekdayArrayAdapter extends ArrayAdapter<String> {
	public static int textColor = Color.BLACK;

	public WeekdayArrayAdapter(Context context, int textViewResourceId,
							   List<String> objects) {
		super(context, textViewResourceId, objects);
	}

	// To prevent cell highlighted when clicked
	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	// Set color to gray and text size to 12sp
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// To customize text size and color
		TextView textView = (TextView) super.getView(position, convertView,
				parent);

		// Set content

		String item ;
		item= getItem(position);


		// Show smaller text if the size of the text is 4 or more in some
		// locale




		textView.setMaxLines(1);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

		textView.setTextColor(textColor);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(2,0,0,0);
	//	Toast.makeText(this.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
		//textView.setTextAlignment(10);


		return textView;
	}

}
