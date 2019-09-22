package com.tech.challenge.adapter;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech.challenge.R;
import com.tech.challenge.model.Response;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends BaseAdapter {

    private List<Response> mListResponse;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomAdapter(List<Response> mListResponse, Context mContext) {
        this.mListResponse = mListResponse;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListResponse.size();
    }

    @Override
    public Object getItem(int i) {
        return mListResponse.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = mLayoutInflater.inflate(R.layout.each_list_item, viewGroup, false);

        Response response = mListResponse.get(i);

        TextView dateOrderNumber = rootView.findViewById(R.id.orderDayNumber);
        TextView dateOrderMonth = rootView.findViewById(R.id.orderDayMonth);
        TextView orderMarkets = rootView.findViewById(R.id.orderMarkets);
        TextView orderDetails = rootView.findViewById(R.id.orderDetail);
        TextView orderStatus = rootView.findViewById(R.id.orderStatus);
        TextView orderPay = rootView.findViewById(R.id.orderPay);
        TextView spOrderDetail = rootView.findViewById(R.id.spOrderDetail);
        TextView spOrderPay = rootView.findViewById(R.id.spOrderPay);
        ImageView imageView = rootView.findViewById(R.id.spinnerDetail);
        Button orderStatusColor = rootView.findViewById(R.id.orderStatusColour);
        LinearLayout orderDetailLayout = rootView.findViewById(R.id.orderDetailLayout);


        imageView.setOnClickListener(view1 -> {
            if (orderDetailLayout.getVisibility() == View.VISIBLE)
                orderDetailLayout.setVisibility(View.GONE);
            else
                orderDetailLayout.setVisibility(View.VISIBLE);
        });

        String pay = response.getProductDetail().getSummaryPrice() + "TL";
        int month = Integer.parseInt(response.getMonth());

        String status = response.getProductState();


        if (status.equals("Yolda"))
            orderStatusColor.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        if (status.equals("Hazırlanıyor"))
            orderStatusColor.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        if (status.equals("Onay Bekliyor"))
            orderStatusColor.setBackgroundColor(mContext.getResources().getColor(R.color.red));



        String months = formatMonth(month, Locale.getDefault());

        dateOrderMonth.setText(months);
        dateOrderNumber.setText(response.getDate());
        orderMarkets.setText(response.getMarketName());
        orderDetails.setText(response.getProductDetail().getOrderDetail());
        orderStatus.setText(response.getProductState());
        orderPay.setText(pay);
        spOrderDetail.setText(response.getProductDetail().getOrderDetail());
        spOrderPay.setText(pay);


        return rootView;
    }

    public String formatMonth(int month, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("MMMM", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1);
        return formatter.format(calendar.getTime());
    }


}
