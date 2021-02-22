package com.vaskevicius.android.banker.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.vaskevicius.android.banker.databinding.ViewPersonalImageViewBinding;
import com.vaskevicius.android.banker.utils.CommonUtils;

public class PersonalImageView extends LinearLayout {

  ViewPersonalImageViewBinding binding;

  public PersonalImageView(Context context) {
    super(context);
    init();
  }

  public PersonalImageView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PersonalImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public PersonalImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    binding = ViewPersonalImageViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
  }

  public void setRandomBackgroundColor(int min, int max) {
    // min= minimal color value.
    // max = maximum color value.
    // Min value cannot be >= Max!!!
    final int base = 255;
    int randomColor = Color
        .rgb(base - CommonUtils.random(min, max), base - CommonUtils.random(min, max),
            base - CommonUtils.random(min, max));
    binding.container.getBackground().setColorFilter(randomColor, PorterDuff.Mode.SRC_ATOP);
  }

  public void setBackgroundColor(@ColorInt int color) {
    binding.container.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
  }

  public void setName(String name) {
    binding.nameLetter.setText(name.trim().substring(0, 1));
  }
}
