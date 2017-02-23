package io.twg.sharedresources;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoTextView extends TextView {

    public RobotoTextView(Context context) {
        super(context);
        onInitTypeface(context, null, CustomFontManager.FONT_TYPE_MEDIUM);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitTypeface(context, attrs, CustomFontManager.FONT_TYPE_MEDIUM);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitTypeface(context, attrs, CustomFontManager.FONT_TYPE_MEDIUM);
    }

    private void onInitTypeface(Context context, AttributeSet attrs, int defStyle) {
        int typefaceValue = CustomFontManager.FONT_TYPE_MEDIUM;
        if (attrs != null) {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.RobotoTextView, defStyle, 0);
            typefaceValue = values.getInt(R.styleable.RobotoTextView_typeface, CustomFontManager.FONT_TYPE_MEDIUM);
            values.recycle();
        }
        Typeface customTypeFace = CustomFontManager.obtainTypeface(context, typefaceValue);
        setTypeface(customTypeFace);
    }
}
