package com.example.yossy.orderfoodserver.Common;

import com.example.yossy.orderfoodserver.Model.User;

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Pesanan Diproses";
        else if (status.equals("1"))
            return "Pesanan Diantar";
        else
            return "Bayar";
    }
}
