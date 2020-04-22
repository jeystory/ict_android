package com.ict.ex72_recyclervlew;

import android.view.View;

public interface OnCustomerListener {
    public void onItemClick(CustomerAdapter.ViewHolder holder, View view, int position);
}
