package com.saad.baitalkhairat.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

public class CustomAutoComplete extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {

    public CustomAutoComplete(Context context) {
        super(context);
    }

    public CustomAutoComplete(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public CustomAutoComplete(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isPopupShowing()) {
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputManager.hideSoftInputFromWindow(findFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS)) {
                return true;
            }
        }

        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
//        Drawable dropdownIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_drop_down_black_24dp);
//        if (dropdownIcon != null) {
//            right = dropdownIcon;
//            right.mutate().setAlpha(150);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            super.setCompoundDrawablesRelativeWithIntrinsicBounds(left, top, right, bottom);
//        } else {
//            super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
//        }

    }

}
