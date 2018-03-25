package com.fileindie.saksham.clgindie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fileindie.saksham.clgindie.R;
import com.fileindie.saksham.clgindie.other.NoticeList;
import com.fileindie.saksham.clgindie.other.NoticeListAdapter;
import com.fileindie.saksham.clgindie.other.RecyclerTouchListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by SAKSHIM on 21/03/2018.
 */

public class NoticeFragment extends Fragment {
    private List<NoticeList> noticeList= new ArrayList<>();
    private RecyclerView recyclerView;
    private NoticeListAdapter mAdapter;
    private StorageReference mStorageRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View retView= inflater.inflate(R.layout.fragment_notice, container, false);
        final FragmentActivity fragmentBelongActivity = getActivity();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        recyclerView = (RecyclerView) retView.findViewById(R.id.recycler_view);

        mAdapter = new NoticeListAdapter(noticeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(fragmentBelongActivity.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentBelongActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(fragmentBelongActivity.getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NoticeList noticeLists = noticeList.get(position);
                Toast.makeText(fragmentBelongActivity.getApplicationContext(), noticeLists.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareData();
        return retView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Notice");
    }

    private void prepareData(){
        NoticeList notice=new NoticeList("Welcome", "22/03/2018");
        noticeList.add(notice);

        notice=new NoticeList("Hi", "21/03/2018");
        noticeList.add(notice);

        mAdapter.notifyDataSetChanged();
    }
}
