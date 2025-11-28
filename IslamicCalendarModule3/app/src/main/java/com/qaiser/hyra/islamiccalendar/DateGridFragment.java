package com.qaiser.hyra.islamiccalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;


public class DateGridFragment extends Fragment {

	private GridView gridView;
	private CaldroidGridAdapter gridAdapter;
	private OnItemClickListener onItemClickListener;
	private OnItemLongClickListener onItemLongClickListener;

	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	
	public OnItemLongClickListener getOnItemLongClickListener() {
		return onItemLongClickListener;
	}
	
	public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	public CaldroidGridAdapter getGridAdapter() {
		return gridAdapter;
	}

	public void setGridAdapter(CaldroidGridAdapter gridAdapter) {
		this.gridAdapter = gridAdapter;
	}

	public GridView getGridView() {
		return gridView;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		try {
			View rootView = inflater.inflate(R.layout.date_grid_fragment,container,false);
			gridView= (GridView) rootView.findViewById(R.id.calendar_gridview);


			// gridView.setAdapter();


			// Client normally needs to provide the adapter and onItemClickListener
			// before the fragment is attached to avoid complex crash due to
			// fragment life cycles
			if (gridAdapter != null) {
				gridView.setAdapter(gridAdapter);

			}

			if (onItemClickListener != null) {
				gridView.setOnItemClickListener(onItemClickListener);


			}
			if (onItemLongClickListener != null) {
				gridView.setOnItemLongClickListener(onItemLongClickListener);

			}

			if(gridView.getParent()!=null)
				((ViewGroup)gridView.getParent()).removeView(gridView);



		}
		catch (Exception e) {
          e.printStackTrace();
		}

		return gridView;
	}

}
