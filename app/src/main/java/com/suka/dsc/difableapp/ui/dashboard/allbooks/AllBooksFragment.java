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
import com.suka.dsc.difableapp.adapter.AllbookCategoriesAdapter;
import com.suka.dsc.difableapp.model.CategoryResponseData;
import com.suka.dsc.difableapp.model.CategoryResponses;
import com.suka.dsc.difableapp.ui.allbooksextend.AllbooksExtendActivity;
import com.suka.dsc.difableapp.model.BookCategoriesData;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllBooksFragment extends Fragment implements AllbooksView {
    private RecyclerView rvAllbookCategories;
    private ProgressBar progressBar;
    private AllbookCategoriesAdapter mAdapter;
    private AllbooksPresenter mPresenter;
    private ApiInterface apiInterface;
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

        rvAllbookCategories = view.findViewById(R.id.rv_all_book);
        rvAllbookCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));

        progressBar = view.findViewById(R.id.progressbar_allbook);
        progressBar.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mPresenter = new AllbooksPresenter(this, apiInterface);
        mPresenter.getCategoriesList();

    }

    @Override
    public void showData(final CategoryResponses data) {
        mAdapter = new AllbookCategoriesAdapter(data.getData(), new AllbookCategoriesAdapter.OnClickListener() {
            @Override
            public void onClick(CategoryResponseData clickedData) {
                Intent intent = new Intent(getActivity(), AllbooksExtendActivity.class);
                intent.putExtra("categories_data", clickedData);
                startActivity(intent);
            }
        });
        rvAllbookCategories.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        rvAllbookCategories.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rvAllbookCategories.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
