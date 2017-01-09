package com.example.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.smalllemon.R;

import uk.co.senab.photoview.PhotoView;


/**
 * @author :   郗琛
 * @date :   2017/1/3
 */

public class PhotoFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        PhotoView view = (PhotoView) inflater.inflate(R.layout.photo_view, null);

        builder.setView(view);
        Glide.with(getActivity()).load(getArguments().getString("path")).into(view);

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(view);
        dialog.show();

        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = window.getWindowManager().getDefaultDisplay().getWidth();
        lp.alpha = 0.9f;
        window.setAttributes(lp);
        return dialog;
    }


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        ImageView view = (ImageView) inflater.inflate(R.layout.photo_view, container);
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        Glide.with(getActivity()).load(getArguments().getString("path")).into(view);
        return view;
    }*/


    /**
     * 获取一个photoView
     */
    public static PhotoFragment getPhotoViewInstance(String path) {
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }


}
