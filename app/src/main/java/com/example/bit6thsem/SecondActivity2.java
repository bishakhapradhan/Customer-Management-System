package com.example.bit6thsem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.adapters.CustomerViewAdapter;
import com.example.bit6thsem.content.BitSqliteHelper;





public class SecondActivity2
        extends AppCompatActivity
        implements CustomerViewAdapter.CustomerAdapterInterface{

    //declare variable
    RecyclerView recyclerView;
    BitSqliteHelper dbhelper;
    Button buttonUpdateRecord;
    SharedPreferences sharedPreferences;
    String key;




    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        //initialize variable
        recyclerView = findViewById(R.id.rvStudentList2);
        dbhelper= new BitSqliteHelper(this);


        sharedPreferences= getSharedPreferences("mySharedPrefs",MODE_PRIVATE);
        key = sharedPreferences.getString("key","");
        Log.d("SecondActivity","preference value passed from manin Activity:"+key);

        populateRecyclerView();


    }
    private void populateRecyclerView(){
        //define layout manager type to be used in recyclerview
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //data can be passed to the adapter class as a
        CustomerViewAdapter adapter=new CustomerViewAdapter(this
        ,dbhelper.getALlCustomerRecords(),this
        );

        //get all records from database and pass it to the setData() odf adapter class
        adapter.setData(dbhelper.getALlCustomerRecords());

        //finally set adapter to the recyclerview
        recyclerView.setAdapter(adapter);
    }

    private void updateRecord(){
        Intent intent =new Intent();
//        intent.putExtra("customerdata")


    }

    @Override
    public void customerRecordUpdate(CustomerData customerData) {
        Intent intent = new Intent(SecondActivity2.this, MainActivity.class);
        intent.putExtra("customerData", customerData);
        startActivity(intent);

    }

    @Override
    public void deleteCustomerRecord(int CustomerId) {

    }

}
