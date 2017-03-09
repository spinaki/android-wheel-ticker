package xyz.pinaki.android.wheelticker;

import android.content.Context;

/**
 * Created by pinaki on 6/13/16.
 */
public class NumberSpinner extends VerticalSpinner {
    private static final String[] NUMBERS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public NumberSpinner(Context context, int textSizeInDP) {
        super(context, NUMBERS, textSizeInDP);
    }


    public void spinTo(int num) {
        if (num < 0 || num > 9) {
            throw new RuntimeException("Num should be within 0 and 9: " + num);
        }
        spinToIndex(num);
    }
}
