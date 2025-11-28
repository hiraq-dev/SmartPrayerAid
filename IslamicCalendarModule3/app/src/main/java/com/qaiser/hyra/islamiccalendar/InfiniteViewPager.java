package com.qaiser.hyra.islamiccalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import hirondelle.date4j.DateTime;

/**
 * A {@link ViewPager} that allows pseudo-infinite paging with a wrap-around
 * effect. Should be used with an {@link InfinitePagerAdapter}.
 *
 */
public class InfiniteViewPager extends ViewPager {

	// ******* Declaration *********
	public static final int OFFSET = 1000;

	/**
	 * datesInMonth is required to calculate the height correctly
	 */
	private ArrayList<DateTime> datesInMonth;

	/**
	 * Enable swipe
	 */
	private boolean enabled = true;

	/**
	 * A calendar height is not fixed, it may have 4, 5 or 6 rows. Set
	 * fitAllMonths to true so that the calendar will always have 6 rows
	 */
	private boolean sixWeeksInCalendar = false;

	/**
	 * Use internally to decide height of the calendar
	 */
	private int rowHeight = 0;

	// ******* Setter and getters *********
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSixWeeksInCalendar() {
		return sixWeeksInCalendar;
	}

	public ArrayList<DateTime> getDatesInMonth() {
		return datesInMonth;
	}

	public void setDatesInMonth(ArrayList<DateTime> datesInMonth) {
		this.datesInMonth = datesInMonth;
	}

	public void setSixWeeksInCalendar(boolean sixWeeksInCalendar) {
		this.sixWeeksInCalendar = sixWeeksInCalendar;
		rowHeight = 0;
	}

	// ************** Constructors ********************
	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InfiniteViewPager(Context context) {
		super(context);
	}

	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
		// offset first element so that we can scroll to the left
		setCurrentItem(OFFSET);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (enabled) {
			return super.onTouchEvent(event);
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (enabled) {
			return super.onInterceptTouchEvent(event);
		}
		return false;
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);


		// Calculate row height
		int rows = datesInMonth.size() / 7;

		boolean wrapHeight = View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.AT_MOST;

		int height = getMeasuredHeight();
		if (wrapHeight && rowHeight == 0) {


			int width = getMeasuredWidth();

			// Use the previously measured width but simplify the calculations
			widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width,
					View.MeasureSpec.EXACTLY);


			if (getChildCount() > 0) {
				View firstChild = getChildAt(0);


				firstChild.measure(widthMeasureSpec, View.MeasureSpec
						.makeMeasureSpec(height, View.MeasureSpec.AT_MOST));

				height = firstChild.getMeasuredHeight();
				rowHeight = height / rows;
			}
		}

		// Calculate height of the calendar
		int calHeight = 0;

		// If fit 6 weeks, we need 6 rows
		if (sixWeeksInCalendar) {
			calHeight = rowHeight * 6;
		} else { // Otherwise we return correct number of rows
			calHeight = rowHeight * rows;
		}

		// Prevent small vertical scroll
		calHeight += 3;

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(calHeight,
				MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
