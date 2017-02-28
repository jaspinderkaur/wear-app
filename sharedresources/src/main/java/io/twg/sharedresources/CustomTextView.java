package io.twg.sharedresources;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        onInitTypeface(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitTypeface(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitTypeface(context);
    }

    private void onInitTypeface(Context context) {
        Typeface customTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/PoiretOne-Regular.ttf");
        setTypeface(customTypeFace);
    }
}
