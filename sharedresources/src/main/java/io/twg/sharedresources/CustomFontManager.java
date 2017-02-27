package io.twg.sharedresources;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.HashMap;

public class CustomFontManager {

    public final static int FONT_POIRET = 0;
    public final static int FONT_LATO = 1;

    private static HashMap<Integer, Typeface> fontCache = new HashMap<>();

    public static Typeface obtainTypeface(@NonNull Context context,
                                          @NonNull int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = fontCache.get(typefaceValue);
        if (typeface == null) {
            try {
                typeface = createTypeface(context, typefaceValue);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(typefaceValue, typeface);
        }
        return typeface;
    }

    /**
     * Create typeface from assets.
     *
     * @param context       The Context the widget is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue The value of "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private static Typeface createTypeface(@NonNull Context context,
                                           @NonNull int typefaceValue) throws IllegalArgumentException {
        String fontName;
        switch (typefaceValue) {
            case FONT_POIRET:
                fontName = "PoiretOne-Regular";
                break;
            case FONT_LATO:
                fontName = "Lato-Regular";
                break;
            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
    }
}
