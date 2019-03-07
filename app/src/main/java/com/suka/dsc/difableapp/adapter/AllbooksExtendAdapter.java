package com.suka.dsc.difableapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.model.AudioResponsesData;
import com.suka.dsc.difableapp.model.BookData;

import java.util.List;

public class AllbooksExtendAdapter extends RecyclerView.Adapter<AllbooksExtendAdapter.ViewHolder> {

    private List<AudioResponsesData> mBookData;
    private OnClickListener mListener;
    private int colorIndex = 8;
    private int drawablePath;

    public AllbooksExtendAdapter(List<AudioResponsesData> bookData, OnClickListener listener) {
        this.mBookData = bookData;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mBookData.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mBookData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAllbook;
        public ViewHolder(View itemView) {
            super(itemView);
            imgAllbook = itemView.findViewById(R.id.img_my_book);
        }

        public void bind(final AudioResponsesData bookData, final OnClickListener mListener) {
            if (colorIndex == 0){
                colorIndex = 8;
            }

            colorIndex = colorIndex - 1;
            setBookImageForIndex(colorIndex);

            Glide.with(itemView.getContext()).load(drawablePath).into(imgAllbook);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(bookData);
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
        void onClick(AudioResponsesData clickedData);
    }
}
