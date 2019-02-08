package com.suka.dsc.difableapp.allbooksextend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.adapter.AllbooksExtendAdapter;
import com.suka.dsc.difableapp.model.AllbookCategoriesData;
import com.suka.dsc.difableapp.model.BookData;
import com.suka.dsc.difableapp.network.ApiClient;
import com.suka.dsc.difableapp.network.ApiInterface;

import java.util.List;

public class AllbooksExtendActivity extends AppCompatActivity implements AllbooksExtendView{
    private RecyclerView rvAllbookExtend;
    private ProgressBar pbAllbookExtend;
    private AllbooksExtendAdapter mAdapter;
    private AllbooksExtendPresenter mPresenter;
    private ApiInterface mApiInterface;
    private AllbookCategoriesData allbookCategoriesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blind_all_book_extend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        allbookCategoriesData = intent.getParcelableExtra("categories_data");
        renderView();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mPresenter = new AllbooksExtendPresenter(this, mApiInterface);
        mPresenter.getAllbooks(allbookCategoriesData.getId());

    }

    private void renderView() {
        rvAllbookExtend = findViewById(R.id.rv_all_book_extend);
        rvAllbookExtend.setLayoutManager(new GridLayoutManager(this, 2));
        pbAllbookExtend = findViewById(R.id.progressbar_allbook_extend);
        pbAllbookExtend.setVisibility(View.INVISIBLE);
        getSupportActionBar().setTitle(allbookCategoriesData.getBookCategory());
    }

    @Override
    public void showData(final List<BookData> data) {
        mAdapter = new AllbooksExtendAdapter(data, new AllbooksExtendAdapter.OnClickListener() {
            @Override
            public void onClick(BookData clickedData) {
                Toast.makeText(getApplicationContext(), clickedData.getBookTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        rvAllbookExtend.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        rvAllbookExtend.setVisibility(View.INVISIBLE);
        pbAllbookExtend.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rvAllbookExtend.setVisibility(View.VISIBLE);
        pbAllbookExtend.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
