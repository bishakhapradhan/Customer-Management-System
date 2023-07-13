package com.example.bit6thsem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
/*import android.widget.ImageButton;*/

import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bit6thsem.content.BitSqliteHelper;
import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.fragments.FragmentHolder;
import com.example.bit6thsem.utils.MyConstants;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    private android.widget.EditText Fname;
    private android.widget.EditText Lname;
    private android.widget.EditText mobileEmail;
    private android.widget.EditText edtPassword;
    private android.widget.RadioGroup radioGroup;

    private android.widget.Button button3;//Submit button

    private android.widget.Spinner countrySpinner;
    private android.widget.Spinner yearSpinner;
    private android.widget.Spinner daySpinner;
    private android.widget.Spinner monthSpinner;

    private Button showRecords;
   /* private Button serviceButton1;
    private Button serviceButton2;*/

    private BitSqliteHelper bitSqliteHelper;

    String firstname, lastname, mobile_email, password, year, month, day, gender, country;

    NotificationChannel notificationChannel;
    NotificationManager notificationManager;
    NotificationCompat.Builder notificationBuilder;
    Button buttonStartService;
    Button buttonStopService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        bitSqliteHelper = new BitSqliteHelper(this);

        showRecords = findViewById(R.id.btnShowAllRecords);
        buttonStartService = findViewById(R.id.btnStartService);
        buttonStopService = findViewById(R.id.btnStopService);

        countrySpinner = findViewById(R.id.spnCountry);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MyConstants.countryArray);
        adapter.setDropDownViewResource(R.layout.spinner_country_txt);
        countrySpinner.setAdapter(adapter);


        yearSpinner = findViewById(R.id.spnYear);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MyConstants.yearArray);
        adapter.setDropDownViewResource(R.layout.spinner_country_txt);
        yearSpinner.setAdapter((adapter1));


        monthSpinner = findViewById(R.id.spnMonth);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MyConstants.monthArray);
        adapter.setDropDownViewResource(R.layout.spinner_country_txt);
        monthSpinner.setAdapter((adapter2));


        daySpinner = findViewById(R.id.spnDay);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, MyConstants.dayArray);
        adapter.setDropDownViewResource(R.layout.spinner_country_txt);
        daySpinner.setAdapter((adapter3));

/*
        exitBtn = (ImageButton) findViewById(R.id.btnClose);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });
    */


        Fname = findViewById(R.id.edtFname);
        Lname = findViewById(R.id.edtLname);
        mobileEmail = findViewById(R.id.edtMobile);
        edtPassword = findViewById(R.id.edtPassword);

        button3 = findViewById(R.id.btnSignUp);

        radioGroup = findViewById(R.id.radio_group_gender);

      /*  serviceButton1 = findViewById(R.id.btnStartService);
        serviceButton2 =findViewById(R.id.btnStopService);
*/
        handleGenderData();
        handleCountryData();
        submitData();
        showAllRecords();
        handleYearData();
        handleMonthData();
        handleDayData();
        Exit();
        ServiceButton();
        androidPreferenceExample();
        createNotification();
        /*stopServiceButton();*/


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleMyStudentFormData();
            }
        });


        handleCustomerRecordUpdate();
        Toast.makeText(this, "Records Submitted Successfully ! ", Toast.LENGTH_SHORT).show();
    }

    private void submitData() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,SecondActivity2.class);
                intent.putExtra("First Name",Fname.getText().toString());
                intent.putExtra("Last Name",Lname.getText().toString());
                intent.putExtra("Mobile/Email",mobile_email.getBytes(StandardCharsets.UTF_8).toString());
                /* if(getIntent().getBooleanExtra("showUpdate",true))

                    CustomerData datalist=new CustomerData(customerData.getId(),
                                                           firstname,
                            lastname,mobile_email,password,year,month,day,gender,country);
                    bitSqliteHelper.updateCustomerRecord(datalist);
                }else*/
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
                startActivity(new Intent(MainActivity.this, FragmentHolder.class));
            }

        });

    }

    private void handleMyStudentFormData() {

        Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
        intent.putExtra("name", "peter");

        startActivity(intent);


        //string array= new String[]{"test","test1"};

        List<CustomerData> customerDataList = new ArrayList<>();


        firstname = Fname.getText().toString();
        lastname = Lname.getText().toString();
        mobile_email = mobileEmail.getText().toString();
        password = edtPassword.getText().toString();
        year = yearSpinner.getSelectedItem().toString();
        month = monthSpinner.getSelectedItem().toString();
        day = daySpinner.getSelectedItem().toString();

        customerDataList.add(new CustomerData(
                firstname,
                lastname,
                mobile_email,
                password,
                year,
                month,
                day,
                gender,
                country));

        bitSqliteHelper.insertCustomerRecord(customerDataList);

        startActivity(new Intent(MainActivity.this, SecondActivity2.class));
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
                RadioButton radioButton = findViewById(i);
                gender = radioButton.getText().toString();
            }
        });
    }

    private void showAllRecords() {
        showRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CustomerData data : bitSqliteHelper.getALlCustomerRecords()) {
                    Log.d(TAG, "showAllRecords: first name: " + data.getFirstname());
                    Log.d(TAG, "showAllRecords: last name: " + data.getLastname());
                }
            }
        });


    }

    private void Exit() {
        ImageButton exitbtn = (ImageButton) findViewById(R.id.btnclose);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Are you sure, you want to exit?");
                builder.setPositiveButton("Yes.Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
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

    }

    private void createNotification() {
        Intent intent = new Intent(this, SecondActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("100", "Notification Description", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);


        }
        notificationBuilder = new NotificationCompat.Builder(this, "100")
                .setContentTitle("Notification")
                .setContentText("yeah,Record is inserted!")
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(pendingIntent);
        notificationManager.notify(100, notificationBuilder.build());

    }

    private void handleCustomerRecordUpdate() {
        CustomerData customerData = (CustomerData) getIntent().getSerializableExtra("customerData");
        if (customerData != null) {
            Fname.setText(customerData.getFirstname());
            Lname.setText(customerData.getLastname());
            mobileEmail.setText(customerData.getMobile_email());
            edtPassword.setText(customerData.getPassword());
            yearSpinner.setPrompt(customerData.getYear());
            monthSpinner.setPrompt(customerData.getMonth());
            daySpinner.setPrompt((customerData.getDay()));

            countrySpinner.setPrompt(customerData.getCountry());
            switch (customerData.getGender()) {
                case "Male":
                    RadioButton radioMale = radioGroup.findViewById(R.id.rbtnMale);
                    radioMale.setChecked(true);
                    break;

                case "Female":
                    RadioButton radioFemale = radioGroup.findViewById(R.id.rbtnFemale);
                    radioFemale.setChecked(true);
                    break;


            }
            /*radioGroup.setOnCheckedChangeListener(customerData.getGender());*/

            /* bitSqliteHelper.updateCustomerRecord(new CustomerData());*/
        }
    }

    private void androidPreferenceExample() {
        SharedPreferences preferences = getSharedPreferences("mySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preferences.edit();

        prefEditor.putString("key", Fname.getText().toString()).apply();

    }

    private void ServiceButton(){
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent(SourceClass, DestinationClass)
                startService(new Intent(MainActivity.this, CustomService.class));
                /*stopService(new Intent(getApplicationContext(), com.example.bit6thsem.CustomService.class));*/
            }
        });

        buttonStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent(SourceClass, DestinationClass)
                stopService(new Intent(MainActivity.this, CustomService.class));
                /*stopService(new Intent(getApplicationContext(), com.example.bit6thsem.CustomService.class));*/
            }
        });



    }
   /* private void stopServiceButton(){
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), CustomService.class));
            }
        });
*/

   /* private void handleRecyclerView(){

    }*/


}