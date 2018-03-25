package com.fileindie.saksham.clgindie.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fileindie.saksham.clgindie.R;

import mehdi.sakout.aboutpage.AboutPage;

/**
 * Created by SAKSHIM on 21/03/2018.
 */

public class AboutFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        final FragmentActivity fragmentBelongActivity = getActivity();
        View aboutPage = new AboutPage(fragmentBelongActivity)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .addGroup("Connect with us")
                .addEmail("fileindie@gmail.com")
                .setDescription("This is a college interaction app for teachers and students.")
                .create();
        return aboutPage;//inflater.inflate(R.layout.fragment_about, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("About");
    }
}
