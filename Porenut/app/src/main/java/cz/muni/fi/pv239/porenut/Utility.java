package cz.muni.fi.pv239.porenut;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }
}
