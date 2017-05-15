package com.booboomx.tvshow.Utils;

import android.content.Context;
import android.content.Intent;

import com.booboomx.tvshow.MainActivity;

/**
 * Created by booboomx on 17/5/15.
 */

public class JumpUtils {


    public static void go2Main(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }



}
