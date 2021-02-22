package com.vaskevicius.android.banker.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.vaskevicius.android.banker.R;
import java.util.Random;

public class CommonUtils {

  public static int random(int min, int max) {
    Random random = new Random();
    if(min >= max)
      throw new RuntimeException("Minimal value is >= maximums!");

    return random.nextInt(max - min + 1) + min;
  }

  public static ProgressDialog showLoadingDialog(Context context) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.show();
    if (progressDialog.getWindow() != null) {
      progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    progressDialog.setContentView(R.layout.dialog_loading);
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    return progressDialog;
  }

}
