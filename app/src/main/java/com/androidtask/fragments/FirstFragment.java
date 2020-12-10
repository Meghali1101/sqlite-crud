package com.androidtask.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidtask.ModelClass;
import com.androidtask.MyHelper;
import com.androidtask.R;
import com.androidtask.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements View.OnClickListener{

    RecyclerView recyclerView;
    ArrayList<ModelClass> data;
    FloatingActionButton btn_fab;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);

        recyclerView= view.findViewById(R.id.recylerView);
        btn_fab = view.findViewById(R.id.fab);
        btn_fab.setOnClickListener(this);
        data= new ArrayList<>();

        Cursor cursor = new MyHelper(getActivity()).getDetails();
        while (cursor.moveToNext()){
            ModelClass modelClass = new ModelClass(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            data.add(modelClass);
        }

        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(),data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                SecondFragment fragment = new SecondFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.placeholder, fragment);
                fragmentTransaction.commit();
                break;
        }
    }
}