package com.example.registros;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderProfession extends RecyclerView.ViewHolder{
    TextView tvName;
    View view;

    public ViewHolderProfession(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
        tvName = itemView.findViewById(R.id.tvName);

    }

    private ViewHolderProfession.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolderProfession.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}
