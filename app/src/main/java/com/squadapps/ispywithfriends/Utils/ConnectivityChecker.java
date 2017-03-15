package com.squadapps.ispywithfriends.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by leona on 9/19/2015.
 */
public class ConnectivityChecker {

    public ConnectivityChecker() {
        //Empty Constructor
    }

    public boolean isInternetConnected(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
