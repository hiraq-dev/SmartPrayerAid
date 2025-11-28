package com.qaiser.hyra.islamiccalendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivity extends FragmentActivity {
	private boolean undo = false;
	private CaldroidFragment caldroidFragment;
	private CaldroidFragment dialogCaldroidFragment;




	private void setCustomResourceForDates() {
		Calendar cal = Calendar.getInstance();
		if (CurrentTimeAPI.global1.mylocation!=CaldroidGridAdapter.myGlobal.timezoneID)
			cal.setTimeZone(TimeZone.getTimeZone(CurrentTimeAPI.global1.mylocation));
		// Min date is last 7 days
		cal.add(Calendar.DATE, -18);
		Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		if (CurrentTimeAPI.global1.mylocation!=CaldroidGridAdapter.myGlobal.timezoneID)
			cal.setTimeZone(TimeZone.getTimeZone(CurrentTimeAPI.global1.mylocation));




		cal.add(Calendar.DATE, 16);
		Date greenDate = cal.getTime();

		if (caldroidFragment != null) {
		//	caldroidFragment.setBackgroundResourceForDate(R.color.white,	blueDate);
		//	caldroidFragment.setBackgroundResourceForDate(R.color.white, greenDate);
		//	caldroidFragment.setTextColorForDate(R.color.caldroid_darker_gray, blueDate);
		//	caldroidFragment.setTextColorForDate(R.color.caldroid_black, greenDate);
		}
	}




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);




		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		// Setup caldroid fragment
		// **** If you want normal CaldroidFragment, use below line ****
		caldroidFragment = new CaldroidFragment();

		// //////////////////////////////////////////////////////////////////////
		// **** This is to show customized fragment. If you want customized
		// version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

		// Setup arguments

		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			if (CurrentTimeAPI.global1.mylocation!=CaldroidGridAdapter.myGlobal.timezoneID)
				cal.setTimeZone(TimeZone.getTimeZone(CurrentTimeAPI.global1.mylocation));

			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);



			// Uncomment this to customize startDayOfWeek
			// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
			// CaldroidFragment.TUESDAY); // Tuesday

      // Uncomment this line to use Caldroid in compact mode
      // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

			caldroidFragment.setArguments(args);
		}

		setCustomResourceForDates();

		// Attach to the activity
    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
    t.replace(R.id.calendar1, caldroidFragment);
    t.commit();



		// Setup listener
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Intent intent = new Intent(Intent.ACTION_INSERT);
				intent.setType("vnd.android.cursor.item/event");

				Calendar cal = Calendar.getInstance();
				long startTime = cal.getTimeInMillis();
				long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

				intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
				intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
				intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

				intent.putExtra(CalendarContract.Events.TITLE, "Neel Birthday");
				intent.putExtra(CalendarContract.Events.DESCRIPTION,  "This is a sample description");
				intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Guest House");
				intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
				startActivity(intent);
				//Toast.makeText(getApplicationContext(), formatter.format(date), Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onChangeMonth(int month, int year) {
			//	String text = "month: " + month + " year: " + year;
			//	Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLongClickDate(Date date, View view) {
				//Toast.makeText(getApplicationContext(), "Long click " + formatter.format(date), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCaldroidViewCreated() {
				//if (caldroidFragment.getLeftArrowButton() != null) {
				//	Toast.makeText(getApplicationContext(), "Caldroid view is created", Toast.LENGTH_SHORT).show();
				//}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);

		//final TextView textView = (TextView) findViewById(R.id.textview);

	/*	final Button customizeButton = (Button) findViewById(R.id.customize_button);

		// Customize the calendar
		customizeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (undo) {
					customizeButton.setText(getString(R.string.customize));
					textView.setText("");

					// Reset calendar
					caldroidFragment.clearDisableDates();
					caldroidFragment.clearSelectedDates();
					caldroidFragment.setMinDate(null);
					caldroidFragment.setMaxDate(null);
					caldroidFragment.setShowNavigationArrows(true);
					caldroidFragment.setEnableSwipe(true);
					caldroidFragment.refreshView();
					undo = false;
					return;
				}

				// Else
				undo = true;
				customizeButton.setText(getString(R.string.undo));
				Calendar cal = Calendar.getInstance();

				// Min date is last 7 days
				cal.add(Calendar.DATE, -7);
				Date minDate = cal.getTime();

				// Max date is next 7 days
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 14);
				Date maxDate = cal.getTime();

				// Set selected dates
				// From Date
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 2);
				Date fromDate = cal.getTime();

				// To Date
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 3);
				Date toDate = cal.getTime();

				// Set disabled dates
				ArrayList<Date> disabledDates = new ArrayList<Date>();
				for (int i = 5; i < 8; i++) {
					cal = Calendar.getInstance();
					cal.add(Calendar.DATE, i);
					disabledDates.add(cal.getTime());
				}

				// Customize
				caldroidFragment.setMinDate(minDate);
				caldroidFragment.setMaxDate(maxDate);
				caldroidFragment.setDisableDates(disabledDates);
				caldroidFragment.setSelectedDates(fromDate, toDate);
				caldroidFragment.setShowNavigationArrows(false);
				caldroidFragment.setEnableSwipe(false);

				caldroidFragment.refreshView();

				// Move to date
				// cal = Calendar.getInstance();
				// cal.add(Calendar.MONTH, 12);
				// caldroidFragment.moveToDate(cal.getTime());

				String text = "Today: " + formatter.format(new Date()) + "\n";
				text += "Min Date: " + formatter.format(minDate) + "\n";
				text += "Max Date: " + formatter.format(maxDate) + "\n";
				text += "Select From Date: " + formatter.format(fromDate)
						+ "\n";
				text += "Select To Date: " + formatter.format(toDate) + "\n";
				for (Date date : disabledDates) {
					text += "Disabled Date: " + formatter.format(date) + "\n";
				}

				textView.setText(text);
			}
		});

		Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);

		final Bundle state = savedInstanceState;
		showDialogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Setup caldroid to use as dialog
				dialogCaldroidFragment = new CaldroidFragment();
				dialogCaldroidFragment.setCaldroidListener(listener);

				// If activity is recovered from rotation
				final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
				if (state != null) {
					dialogCaldroidFragment.restoreDialogStatesFromKey(
							getSupportFragmentManager(), state,
							"DIALOG_CALDROID_SAVED_STATE", dialogTag);
					Bundle args = dialogCaldroidFragment.getArguments();
					if (args == null) {
						args = new Bundle();
						dialogCaldroidFragment.setArguments(args);
					}
				} else {
					// Setup arguments
					Bundle bundle = new Bundle();
					// Setup dialogTitle
					dialogCaldroidFragment.setArguments(bundle);
				}

				dialogCaldroidFragment.show(getSupportFragmentManager(),
						dialogTag);
			}
		});*/
	}

	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
		//	caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

		if (dialogCaldroidFragment != null) {
		//	dialogCaldroidFragment.saveStatesToKey(outState, "DIALOG_CALDROID_SAVED_STATE");
		}
	}

}
