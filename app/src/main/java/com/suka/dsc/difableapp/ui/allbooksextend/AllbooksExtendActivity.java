package com.suka.dsc.difableapp.ui.allbooksextend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.adapter.AllbooksExtendAdapter;
import com.suka.dsc.difableapp.model.AudioResponses;
import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.model.CategoryResponseData;
import com.suka.dsc.difableapp.ui.allbooksextend2.AllbooksExtend2Activity;
import com.suka.dsc.difableapp.model.BookCategoriesData;
import com.suka.dsc.difableapp.model.BookData;
import com.suka.dsc.difableapp.service.ApiClient;
import com.suka.dsc.difableapp.service.ApiInterface;

import java.util.List;

public class AllbooksExtendActivity extends AppCompatActivity implements AllbooksExtendView{
    private RecyclerView rvAllbookExtend;
    private ProgressBar pbAllbookExtend;
    private AllbooksExtendAdapter mAdapter;
    private AllbooksExtendPresenter mPresenter;
    private ApiInterface mApiInterface;
    private CategoryResponseData bookCategoriesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        bookCategoriesData = intent.getParcelableExtra("categories_data");
        renderView();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mPresenter = new AllbooksExtendPresenter(this, mApiInterface);
        mPresenter.getAllbooks(bookCategoriesData.getCategory());

    }

    private void renderView() {
        setContentView(R.layout.blind_all_book_extend);

        rvAllbookExtend = findViewById(R.id.rv_all_book_extend);
        rvAllbookExtend.setLayoutManager(new GridLayoutManager(this, 2));
        pbAllbookExtend = findViewById(R.id.progressbar_allbook_extend);
        pbAllbookExtend.setVisibility(View.INVISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(bookCategoriesData.getCategory());
    }

    @Override
    public void showData(final AudioResponses data) {
        mAdapter = new AllbooksExtendAdapter(data.getData(), new AllbooksExtendAdapter.OnClickListener() {
            @Override
            public void onClick(AudioResponsesData clickedData) {
                Intent intent = new Intent(AllbooksExtendActivity.this, AllbooksExtend2Activity.class);
                intent.putExtra("book_data", clickedData);
                startActivity(intent);
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
