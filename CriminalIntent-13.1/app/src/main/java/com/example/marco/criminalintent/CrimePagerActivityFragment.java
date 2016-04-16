package com.example.marco.criminalintent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class CrimePagerActivityFragment extends Fragment {

    private static final String TAG = "CrimePagerActivityFr";

    public CrimePagerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crime_pager, container, false);
    }
}
