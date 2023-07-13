package com.example.bit6thsem.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.MainActivity;
import com.example.bit6thsem.R;
import com.example.bit6thsem.SecondActivity2;
import com.example.bit6thsem.content.BitSqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class Fragment2
        extends Fragment {
    private android.widget.EditText Fname;
    private android.widget.EditText Lname;
    private android.widget.EditText mobileEmail;
    private android.widget.EditText edtPassword;
    private android.widget.RadioGroup radioGroup;

    private android.widget.Button button3;

    private android.widget.Spinner countrySpinner;
    private android.widget.Spinner yearSpinner;
    private android.widget.Spinner daySpinner;
    private android.widget.Spinner monthSpinner;
    View view;

    String firstname, lastname, mobile_email, password, year, month, day, gender, country;

    BitSqliteHelper bitSqliteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bitSqliteHelper = new BitSqliteHelper(getContext());

        //assign variables
        Fname = view.findViewById(R.id.edtFname);
        Lname = view.findViewById(R.id.edtLname);
        mobileEmail = view.findViewById(R.id.edtMobile);
        edtPassword = view.findViewById(R.id.edtPassword);

        button3 = view.findViewById(R.id.btnSignUp);

        radioGroup = view.findViewById(R.id.radio_group_gender);
        countrySpinner = view.findViewById(R.id.spnCountry);
        yearSpinner = view.findViewById(R.id.spnYear);
        monthSpinner = view.findViewById(R.id.spnMonth);
        daySpinner = view.findViewById(R.id.spnDay);
        submitData();
        handleCountryData();
        handleYearData();
        handleMonthData();
        handleDayData();
        handleGenderData();
        /*     Exit();*/


    }

    private void submitData() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                List<CustomerData> datalist = new ArrayList<>();

                datalist.add(new CustomerData(
                        firstname,
                        lastname,
                        mobile_email,
                        password,
                        year,
                        month,
                        day,
                        gender,
                        country));
                bitSqliteHelper.updateCustomerRecord(datalist);
            }

            {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("edtFname", firstName);
//                intent.putExtra("edtLname", lastName);
//                intent.putExtra("edtMobile", mobile_email);
//                intent.putExtra("edtPassword", password);
//
//                intent.putExtra("country", countrySpinner.getSelectedItem().toString());
//                intent.putExtra("year", yearSpinner.getSelectedItem().toString());
//                intent.putExtra("month", monthSpinner.getSelectedItem().toString());
//                intent.putExtra("day", daySpinner.getSelectedItem().toString());
//
//                startActivity(intent);
//                handleMyStudentFormData();
//                createNotification();
//                androidPreferenceExample();


                //first get root activity's instance
                //then => cast that instance to the activity where our method is present
                //then => access member methods
                ((FragmentHolder) getActivity()).replaceFragment();
            }

        });

    }

    private void handleCountryData() {
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //assign value to 'country'
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void handleYearData() {
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handleMonthData() {
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void handleDayData() {
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void handleGenderData() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //male 098ab00a
                //female 098ab00c
                RadioButton radioButton = view.findViewById(i);
                gender = radioButton.getText().toString();
            }
        });
    }

 /*   private void Exit() {
        ImageButton exitbtn = (ImageButton) view.findViewById(R.id.btnclose);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Fragment2.this);
                builder.setTitle("Exit");
                builder.setMessage("Are you sure, you want to exit?");
                builder.setPositiveButton("Yes.Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }

                    private void finish() {
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }*/

}
