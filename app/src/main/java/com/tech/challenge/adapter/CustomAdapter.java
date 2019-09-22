package com.tech.challenge.adapter;

import android.content.Context;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Responsible to manage CustomAdapter
 *
 * @author bilal
 * @version 1.0.0
 */

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

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mLayoutInflater.inflate(R.layout.each_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        /**
         * TODO : Code Review overClass
         */

        Response response = mListResponse.get(i);

        holder.spinnerDetail.setOnClickListener(view1 -> {
            if (holder.orderDetailLayout.getVisibility() == View.VISIBLE)
                holder.orderDetailLayout.setVisibility(View.GONE);
            else
                holder.orderDetailLayout.setVisibility(View.VISIBLE);
        });

        String pay = response.getProductDetail().getSummaryPrice() + "TL";
        int month = Integer.parseInt(response.getMonth());

        String status = response.getProductState();


        /**
         * TODO : Code Review
         */
        if (status.equals("Yolda"))
            holder.orderStatusColour.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        if (status.equals("Hazırlanıyor"))
            holder.orderStatusColour.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        if (status.equals("Onay Bekliyor"))
            holder.orderStatusColour.setBackgroundColor(mContext.getResources().getColor(R.color.red));


        String months = formatMonth(month, Locale.getDefault());

        holder.orderDayMonth.setText(months);
        holder.orderDayNumber.setText(response.getDate());
        holder.orderMarkets.setText(response.getMarketName());
        holder.orderDetail.setText(response.getProductDetail().getOrderDetail());
        holder.orderStatus.setText(response.getProductState());
        holder.orderPay.setText(pay);
        holder.spOrderDetail.setText(response.getProductDetail().getOrderDetail());
        holder.spOrderPay.setText(pay);


        return view;
    }


    private String formatMonth(int month, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("MMMM", locale);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month - 1);
        return formatter.format(calendar.getTime());
    }


    static class ViewHolder {
        @BindView(R.id.orderDayNumber)
        TextView orderDayNumber;
        @BindView(R.id.orderDayMonth)
        TextView orderDayMonth;
        @BindView(R.id.llLeft)
        LinearLayout llLeft;
        @BindView(R.id.orderMarkets)
        TextView orderMarkets;
        @BindView(R.id.orderDetail)
        TextView orderDetail;
        @BindView(R.id.orderStatusColour)
        Button orderStatusColour;
        @BindView(R.id.orderStatus)
        TextView orderStatus;
        @BindView(R.id.spinnerDetail)
        ImageView spinnerDetail;
        @BindView(R.id.orderPay)
        TextView orderPay;
        @BindView(R.id.spOrderDetail)
        TextView spOrderDetail;
        @BindView(R.id.spOrderPay)
        TextView spOrderPay;
        @BindView(R.id.orderDetailLayout)
        LinearLayout orderDetailLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
