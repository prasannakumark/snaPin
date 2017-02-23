package com.snapin.utility;


import android.app.ProgressDialog;
import android.content.Context;



public class Alert {

    private static Alert INSTANCE;
    public static final String EMAIL = "email";

    public static Alert getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Alert();
        return INSTANCE;
    }
    /*
    * This method is made for show an alert dialog when tickets are loading in map level
    *  @param view:LayoutView Id
    *  @param dialogTitle:title of alert
    *  @param isVisible:boolean make true or false
            */
    public ProgressDialog callLoadingAlert(Context context, String dialogTitle) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setMessage(dialogTitle);
        return dialog;
    }

}