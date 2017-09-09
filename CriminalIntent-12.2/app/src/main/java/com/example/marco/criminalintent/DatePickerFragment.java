package com.example.marco.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "extra_date";

    private static final String TAG = "DatePickerFragment";
    private static final String ARG_DATE = "date";

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        // see ar34z answer here: https://stackoverflow.com/questions/4661459/dialog-problem-requestfeature-must-be-called-before-adding-content
        if (getShowsDialog()) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        return getView(layoutInflater, viewGroup);
    }

    @NonNull
    private View getView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.fragment_date_picker, viewGroup, false);
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePicker mDatePicker = (DatePicker) view.findViewById(R.id.date_picker_fragment_date_picker);
        mDatePicker.init(year, month, day, null);

        Button mPositiveButton = (Button) view.findViewById(R.id.date_picker_fragment_ok);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick");
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        final DatePicker mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        Fragment fragment = getTargetFragment();
        // if we want to communicate with the host activity directly
        // this will be used on smartphones
        if (fragment == null) {
            Log.d(TAG, "Fragment is null");
            getActivity().setResult(Activity.RESULT_OK, intent);
        }
        // or if we want to send the result back to the target fragment (still possible, if we
        // don't remove the code to handle Fragment-to-Fragment communication)
        // This will be used on Tablets
        else {
            Log.d(TAG, "Fragment is not null");
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }

    }
}
