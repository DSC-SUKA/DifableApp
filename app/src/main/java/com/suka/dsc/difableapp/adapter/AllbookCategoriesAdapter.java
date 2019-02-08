package com.suka.dsc.difableapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.model.AllbookCategoriesData;

import java.util.List;

public class AllbookCategoriesAdapter extends RecyclerView.Adapter<AllbookCategoriesAdapter.ViewHolder> {

    private List<AllbookCategoriesData> mAllbookItems;
    private OnClickListener mListener;
    private int colorIndex = 8;
    private int drawablePath;

    public AllbookCategoriesAdapter(List<AllbookCategoriesData> allbookItems, OnClickListener listener) {
        this.mAllbookItems = allbookItems;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mAllbookItems.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mAllbookItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAllbookCategories;
        private TextView tvAllbookCategory;
        public ViewHolder(View itemView) {
            super(itemView);
            imgAllbookCategories = itemView.findViewById(R.id.img_all_book_item);
            tvAllbookCategory = itemView.findViewById(R.id.tv_allbook_categories);
        }

        public void bind(final AllbookCategoriesData allbookCategoriesData, final OnClickListener mListener) {
            if (colorIndex == 0){
                colorIndex = 8;
            }

            colorIndex = colorIndex - 1;
            setBookImageForIndex(colorIndex);

            Glide.with(itemView.getContext()).load(drawablePath).into(imgAllbookCategories);
            tvAllbookCategory.setText(allbookCategoriesData.getBookCategory());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(allbookCategoriesData);
                }
            });
        }

        private void setBookImageForIndex(int colorIndex) {
            if (colorIndex == 1) drawablePath = R.drawable.ic_books_color_1;
            else if (colorIndex == 2) drawablePath = R.drawable.ic_books_color_2;
            else if (colorIndex == 3) drawablePath = R.drawable.ic_books_color_3;
            else if (colorIndex == 4) drawablePath = R.drawable.ic_books_color_4;
            else if (colorIndex == 5) drawablePath = R.drawable.ic_books_color_5;
            else if (colorIndex == 6) drawablePath = R.drawable.ic_books_color_6;
            else if (colorIndex == 7) drawablePath = R.drawable.ic_books_color_7;
            else drawablePath = R.drawable.ic_books_color_8;
        }
    }

    public interface OnClickListener{
        void onClick(AllbookCategoriesData clickedData);
    }
}
