package com.example.bit6thsem.adapters;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.R;

import java.util.List;

public class CustomerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CustomerData>dataList;
    CustomerAdapterInterface customerAdapterInterface;
    List<CustomerData> customerDataList;


    public CustomerViewAdapter(CustomerAdapterInterface adapterInterface) {
        this.customerAdapterInterface = adapterInterface;
    }


    public void setData(List<CustomerData>allList){
        dataList=allList;
        notifyDataSetChanged();
    }


    public  CustomerViewAdapter(Context context,
                                List<CustomerData> customerDataList,
                                CustomerAdapterInterface adapterInterface){
        this.mContext =context;
        this.dataList = customerDataList;
        this.customerAdapterInterface=adapterInterface;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.customer_list_item,parent,false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomerViewHolder customerViewHolder =(CustomerViewAdapter.CustomerViewHolder) holder;
        CustomerData data =dataList.get(position);

        customerViewHolder.FirstName.setText(data.getFirstname());
        customerViewHolder.LastName.setText(data.getLastname());
        customerViewHolder.MobileEmail.setText(data.getMobile_email());
        customerViewHolder.Password.setText(data.getPassword());
        customerViewHolder.Year.setText(data.getYear());
        customerViewHolder.Month.setText(data.getMonth());
        customerViewHolder.Day.setText(data.getDay());
        customerViewHolder.Gender.setText(data.getGender());
        customerViewHolder.Country.setText(data.getCountry());

        customerViewHolder.FirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerAdapterInterface.customerRecordUpdate(data);
            }
        });




    }
    @Override
    public int getItemCount() {
        return dataList == null ? 0: dataList.size();
    }


    public class CustomerViewHolder extends RecyclerView.ViewHolder{

        TextView FirstName;
        TextView LastName;
        TextView MobileEmail;
        TextView Password;
        TextView Year;
        TextView Month;
        TextView Day;
        TextView Gender;
        TextView Country;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            FirstName =itemView.findViewById(R.id.tvFname);
            LastName =itemView.findViewById(R.id.tvLname);
            MobileEmail=itemView.findViewById(R.id.tvMobileEmail);
            Password =itemView.findViewById(R.id.tvPassword);
            Year =itemView.findViewById(R.id.tvYear);
            Month =itemView.findViewById(R.id.tvMonth);
            Day =itemView.findViewById(R.id.tvDay);
            Gender =itemView.findViewById(R.id.tvGender);
            Country =itemView.findViewById(R.id.tvCountry);
        }

    }

    public interface CustomerAdapterInterface{
        public void customerRecordUpdate(CustomerData customerData);
        public void deleteCustomerRecord(int CustomerId);

    }
}
