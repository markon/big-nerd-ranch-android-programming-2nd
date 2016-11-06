package com.example.marco.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


public class ImageFragment extends DialogFragment {

    public final static String ARG_PATH = "com.example.marco.criminalintent.ARG_PATH";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        String path = (String) getArguments().getSerializable(ARG_PATH);
        final View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.image_view_detail, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.image_view_detail);

        final Bitmap bitmap = PictureUtils.getScaledBitmap(path, getActivity());
        imageView.setImageBitmap(bitmap);

        dialog.setContentView(imageView);
        return dialog;
    }

    public static ImageFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PATH, path);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
