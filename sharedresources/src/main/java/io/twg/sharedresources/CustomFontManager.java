package io.twg.sharedresources;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import java.util.HashMap;

public class CustomFontManager {

    private final static String CUSTOM_FONT_BASE_FILE_PATH = "fonts/Roboto-";

    public final static int FONT_TYPE_BLACK = 0;
    public final static int FONT_TYPE_BLACK_ITALIC = 1;
    public final static int FONT_TYPE_BOLD = 2;
    public final static int FONT_TYPE_BOLD_ITALIC = 3;
    public final static int FONT_TYPE_ITALIC = 4;
    public final static int FONT_TYPE_LIGHT = 5;
    public final static int FONT_TYPE_LIGHT_ITALIC = 6;
    public final static int FONT_TYPE_MEDIUM = 7;
    public final static int FONT_TYPE_MEDIUM_ITALIC = 8;
    public final static int FONT_TYPE_REGULAR = 9;
    public final static int FONT_TYPE_THIN = 10;
    public final static int FONT_TYPE_THIN_ITALIC = 11;

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
            case FONT_TYPE_BLACK:
                fontName = "Black";
                break;
            case FONT_TYPE_BLACK_ITALIC:
                fontName = "BlackItalic";
                break;
            case FONT_TYPE_BOLD:
                fontName = "Bold";
                break;
            case FONT_TYPE_BOLD_ITALIC:
                fontName = "BoldItalic";
                break;
            case FONT_TYPE_ITALIC:
                fontName = "Italic";
                break;
            case FONT_TYPE_LIGHT:
                fontName = "Light";
                break;
            case FONT_TYPE_LIGHT_ITALIC:
                fontName = "LightItalic";
                break;
            case FONT_TYPE_MEDIUM:
                fontName = "Medium";
                break;
            case FONT_TYPE_MEDIUM_ITALIC:
                fontName = "MediumItalic";
                break;
            case FONT_TYPE_REGULAR:
                fontName = "Regular";
                break;
            case FONT_TYPE_THIN:
                fontName = "Thin";
                break;
            case FONT_TYPE_THIN_ITALIC:
                fontName = "ThinItalic";
                break;
            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }
        return Typeface.createFromAsset(context.getAssets(), CUSTOM_FONT_BASE_FILE_PATH + fontName + ".ttf");
    }
}
