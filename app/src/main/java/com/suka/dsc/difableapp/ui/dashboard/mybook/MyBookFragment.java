package com.suka.dsc.difableapp.ui.dashboard.mybook;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.adapter.MyBookAdapter;
import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.model.UserData;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;
import com.suka.dsc.difableapp.ui.allbooksextend2.AllbooksExtend2Activity;
import com.suka.dsc.difableapp.utils.SessionManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookFragment extends Fragment implements MyBookView {
    private RecyclerView rvMyBook;
    private ProgressBar progressBar;
    private MyBookAdapter mAdapter;
    private MyBookPresenter mPresenter;
    private ApiInterface apiInterface;
    private String userId; //get user id from shared preference;
    private SessionManager mSessionManager;


    public MyBookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blind_my_book_fragment, container, false);

        rvMyBook = view.findViewById(R.id.rv_my_book);
        rvMyBook.setLayoutManager(new GridLayoutManager(getContext(), 2));

        progressBar = view.findViewById(R.id.progressbar_mybook);
        progressBar.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mPresenter = new MyBookPresenter(this, apiInterface);
        mSessionManager = new SessionManager(getContext());
        userId = mSessionManager.getUserData().getUserId();
        mPresenter.getMyBookList(userId);

    }

    @Override
    public void showData(List<AudioResponsesData> data) {
        mAdapter = new MyBookAdapter(data, new MyBookAdapter.OnClickListener() {
            @Override
            public void onClick(AudioResponsesData clickedData) {
                Intent intent = new Intent(getActivity(), AllbooksExtend2Activity.class);
                intent.putExtra("book_data", clickedData);
                startActivity(intent);
                //Toast.makeText(context, "Clicked on " + clickedData.getDocid(), Toast.LENGTH_SHORT).show();
            }
        });
        rvMyBook.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        rvMyBook.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        rvMyBook.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
