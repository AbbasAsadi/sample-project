package com.baseProject.android.util;

import com.baseProject.android.data.remote.model.responseModel.refrralorder.ReferralAllOrders;

public class DateComparator implements java.util.Comparator<ReferralAllOrders> {
    @Override
    public int compare(ReferralAllOrders order1, ReferralAllOrders order2) {
        return Double.compare(order2.getReferralDate(), order1.getReferralDate());
    }
}

