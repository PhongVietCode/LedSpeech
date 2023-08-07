package com.example.mylibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private ArrayList<MyBook> listBook =  new ArrayList<>();
    private Context context;

    public BookAdapter(Context context ) {
        this.context=context;
    }
    public void setBook(ArrayList<MyBook> book)
    {
        this.listBook = book;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.my_recycler, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtBookNameAbove.setText(listBook.get(position).getBookName());
        holder.txtBookNameUnder.setText(listBook.get(position).getBookName());
//        holder.txtBookPage.setText(toString(listBook.get(position).getBookPages()));
        holder.txtbookAuthor.setText(listBook.get(position).getBookAuthor());
        holder.txtBookPage.setText(String.valueOf(listBook.get(position).getBookPages()));
        holder.txtShortDes.setText(listBook.get(position).getShortDes());
        Glide.with(context)
                .asBitmap()
                .load(listBook.get(position).getImgURL())
                .into(holder.imgView);
        if(listBook.get(position) != null){
            if(listBook.get(position).getExpanded()){
                TransitionManager.beginDelayedTransition(holder.parent);
                holder.lowerLayout.setVisibility(View.VISIBLE);
                holder.btnExpand.setVisibility(View.GONE);
            }
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("IntentWithNullActionLaunch") Intent intent = new Intent(context,BookActivity.class);
                intent.putExtra("bookID", listBook.get(position).getBookID());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return listBook.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        final private CardView parent;
        final private ImageView imgView, btnExpand, btnShowLess;
        final private TextView txtBookNameUnder, txtBookNameAbove,txtBookPage, txtbookAuthor, txtShortDes;
        final private RelativeLayout lowerLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgBookImage);
            txtBookNameAbove = itemView.findViewById(R.id.txtBookName);
            txtBookNameUnder = itemView.findViewById(R.id.txtBookNameUnder);
            txtBookPage = itemView.findViewById(R.id.txtPage);
            txtbookAuthor = itemView.findViewById(R.id.txtAuthorName);
            txtShortDes = itemView.findViewById(R.id.shortDes);
            btnExpand = itemView.findViewById(R.id.btnShowMore);
            parent = itemView.findViewById(R.id.parent);
            btnShowLess =itemView.findViewById(R.id.btnShowless);
            lowerLayout = itemView.findViewById(R.id.lowerLayout);
            btnExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyBook book = listBook.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnShowLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyBook book = listBook.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
