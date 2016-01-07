package demo.kolorob.kolorobdemoversion.utils;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import java.util.HashMap;

public class TypefaceSpan extends MetricAffectingSpan {

    /*Cache to save loaded fonts*/
    private static HashMap<String, Typeface> typeFaceCache = new HashMap<String, Typeface>(12);
    private Typeface mTypeface;

    public TypefaceSpan(Typeface typeface) {
        mTypeface = typeface;
        //we dont need to put it in cache
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
    }

}
