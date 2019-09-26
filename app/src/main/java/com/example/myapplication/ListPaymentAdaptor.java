package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListPaymentAdaptor extends ArrayAdapter{

    List list = new ArrayList();

    public ListPaymentAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
    }


    static class LayoutHandler {
        TextView payID , HouseID , date, phone;
    }


    @Override
    public void add(@Nullable Object object) {
        super.add(object);

        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.crow_layout,parent,false);
            layoutHandler = new LayoutHandler();

            layoutHandler.payID     = row.findViewById(R.id.PaymentID);
            layoutHandler.HouseID    = row.findViewById(R.id.houseID);
            layoutHandler.date   = row.findViewById(R.id.date);
            layoutHandler.phone     = row.findViewById(R.id.phone);

            row.setTag(layoutHandler);

        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        PaymentProvoder paymentProvoder = (PaymentProvoder) this.getItem(position);
        layoutHandler.payID.setText(paymentProvoder.getPayID());
        layoutHandler.HouseID.setText(paymentProvoder.getHouseID());
        layoutHandler.date.setText(paymentProvoder.getDate());
        layoutHandler.phone.setText(paymentProvoder.getPhone());

        return row;
    }
}
