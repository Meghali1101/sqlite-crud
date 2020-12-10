package com.androidtask.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtask.MyHelper;
import com.androidtask.R;

public class SecondFragment extends Fragment implements View.OnClickListener{

    EditText et_name,et_email;
    Button btn_submit;
    String first, id;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, v);

        et_name = v.findViewById(R.id.editTextTextPersonName);
        et_email = v.findViewById(R.id.editTextTextEmailAddress);
        btn_submit = v.findViewById(R.id.button);
        btn_submit.setOnClickListener(this);

        if(this.getArguments()!=null) {
            Bundle bundle = this.getArguments();
            first = bundle.getString("camefrom");
            String namee = bundle.getString("userName");
            String email = bundle.getString("userEmail");
            id = bundle.getString("userId");

            et_name.setText(namee);
            et_email.setText(email);
        }

        return v;
    }

    private void processData (String name,String email){

        boolean result = new MyHelper(getActivity()).insertDetails(name,email);
        Log.e("result",""+result);
        et_name.getText().clear();
        et_email.getText().clear();
    }


    private boolean checkValidation(){
        boolean ret=true;
        String strUserName = et_name.getText().toString();
        String strUserEmail = et_email.getText().toString();

        if(TextUtils.isEmpty(strUserName)) {
            ret=false;
            Toast.makeText(getActivity(), R.string.enter_name, Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(strUserEmail)) {
            ret=false;
            Toast.makeText(getActivity(), R.string.enter_email, Toast.LENGTH_LONG).show();
        }
        return ret;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                if (checkValidation()) {
                    if (first!=null) {
                        if (first.equals("first")) {
                            boolean updateData = new MyHelper(getActivity()).updateDetails(Integer.valueOf(id), et_name.getText().toString(), et_email.getText().toString());
                            if (updateData==true){
                                FirstFragment fragment = new FirstFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.placeholder, fragment);
                                fragmentTransaction.commit();
                            }
                        }
                    } else {
                        FirstFragment fragment = new FirstFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.placeholder, fragment);
                        fragmentTransaction.commit();
                        processData(et_name.getText().toString(), et_email.getText().toString());
                    }
                }
                break;
        }
    }
}