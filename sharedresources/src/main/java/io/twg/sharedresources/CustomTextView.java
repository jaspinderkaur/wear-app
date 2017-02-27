package io.twg.sharedresources;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        onInitTypeface(context, null, CustomFontManager.FONT_POIRET);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitTypeface(context, attrs, CustomFontManager.FONT_POIRET);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitTypeface(context, attrs, CustomFontManager.FONT_POIRET);
    }

    private void onInitTypeface(Context context, AttributeSet attrs, int defStyle) {
        int typefaceValue = CustomFontManager.FONT_POIRET;
        if (attrs != null) {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyle, 0);
            typefaceValue = values.getInt(R.styleable.CustomTextView_font, CustomFontManager.FONT_POIRET);
            values.recycle();
        }
        Typeface customTypeFace = CustomFontManager.obtainTypeface(context, typefaceValue);
        setTypeface(customTypeFace);
    }
}
