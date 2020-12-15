package com.example.registros;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ViewHolderUser extends RecyclerView.ViewHolder {
    TextView tvUser, tvMail, tvPhone;
    View view;
    public ViewHolderUser(@NonNull View itemView) {
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
        tvUser = itemView.findViewById(R.id.etUser);
        tvMail = itemView.findViewById(R.id.etMail);
        tvPhone = itemView.findViewById(R.id.etPhone);
    }
    private ViewHolderUser.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolderUser.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}