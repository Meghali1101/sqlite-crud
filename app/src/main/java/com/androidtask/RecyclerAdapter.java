package com.androidtask;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtask.fragments.SecondFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelClass> modelClasses;
    String userId;

    public RecyclerAdapter(Context context, ArrayList<ModelClass> modelClasses) {
        this.context = context;
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText(modelClasses.get(position).getName());
        holder.txt_email.setText(modelClasses.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.et_name)
        TextView txt_name;
        @BindView(R.id.et_email)
        TextView txt_email;
        @BindView(R.id.cardview)
        CardView cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cardview:
                    showDialog(getAdapterPosition(), modelClasses.get(getAdapterPosition()).getUserid(), txt_name, txt_email);
                    break;
            }
        }
    }

    private void showDialog(int position,String id, TextView name, TextView email) {

        final Dialog dialogDelete = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);
        dialogDelete.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDelete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDelete.setContentView(R.layout.dialog_update_delete);
        dialogDelete.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialogDelete.setCanceledOnTouchOutside(true);

        TextView txt_update = dialogDelete.findViewById(R.id.txt_update);
        TextView txt_delete = dialogDelete.findViewById(R.id.txt_delete);
        txt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("camefrom","first");
                bundle.putString("userName",name.getText().toString());
                bundle.putString("userEmail",email.getText().toString());
                bundle.putString("userId",id);
                AppCompatActivity activity = (AppCompatActivity) context;
                SecondFragment myFragment = new SecondFragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, myFragment).addToBackStack(null).commit();
                dialogDelete.dismiss();
            }
        });

        txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper myHelper = new MyHelper(context);
                myHelper.deleteDetail(Integer.valueOf(id));
                modelClasses.remove(position);
                notifyDataSetChanged();
                dialogDelete.dismiss();
            }
        });

        dialogDelete.show();
    }

}
