package com.suka.dsc.difableapp.ui.dashboard.allbooks;


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
import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.ui.allbooksextend.AllbooksExtendActivity;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;
import com.suka.dsc.difableapp.ui.allbooksextend2.AllbooksExtend2Activity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBooksFragment extends Fragment implements AllbooksView {
    private RecyclerView rvAllbook;
    private ProgressBar progressBar;
    private AllbooksAdapter mAdapter;
    private AllbooksPresenter mPresenter;
    private View view;


    public AllBooksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.blind_all_book_fragment, container,false);

        initView();
        initComponent();

        return view;
    }

    private void initView() {
        rvAllbook = view.findViewById(R.id.rv_all_book);
        rvAllbook.setLayoutManager(new GridLayoutManager(getContext(), 2));

        progressBar = view.findViewById(R.id.progressbar_allbook);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initComponent() {
        mPresenter = new AllbooksPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllbooks();
    }

    @Override
    public void showData(final List<AudioResponsesData> data) {
        mAdapter = new AllbooksAdapter(data, new AllbooksAdapter.OnClickListener() {
            @Override
            public void onClick(AudioResponsesData clickedData) {
                Intent intent = new Intent(getActivity(), AllbooksExtend2Activity.class);
                intent.putExtra("book_detail", clickedData);
                startActivity(intent);
            }
        });
        rvAllbook.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        rvAllbook.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rvAllbook.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
