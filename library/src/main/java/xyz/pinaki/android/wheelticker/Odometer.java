package xyz.pinaki.android.wheelticker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by pinaki on 6/8/16.
 */
public class Odometer extends TableLayout {
    private static final int DEFAULT_DIGIT_SIZE_DP = 30;
    private static final int DEFAULT_NUM_DIGITS = 2;
    private NumberSpinner num1, num10, num100, num1K, num10K, num100K, num1M;
    private int numDigits = DEFAULT_NUM_DIGITS;
    private int digitSize = DEFAULT_DIGIT_SIZE_DP;
    private int currentValue = 0;

    public Odometer(Context context, int numberSize) {
        super(context);
        digitSize = numberSize;
        init(context);
    }

    public Odometer(Context context) {
        this(context, DEFAULT_DIGIT_SIZE_DP);
    }

    public Odometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Odometer);
        numDigits = typedArray.getInteger(R.styleable.Odometer_num_digits, DEFAULT_NUM_DIGITS);
        digitSize = typedArray.getInteger(R.styleable.Odometer_digit_size, DEFAULT_DIGIT_SIZE_DP);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        TableRow tableRow = new TableRow(context);
        tableRow.setGravity(Gravity.CENTER);
        num1M = new NumberSpinner(context, digitSize);
        num100K = new NumberSpinner(context, digitSize);
        num10K = new NumberSpinner(context, digitSize);
        num1K = new NumberSpinner(context, digitSize);
        num100 = new NumberSpinner(context, digitSize);
        num10 = new NumberSpinner(context, digitSize);
        num1 = new NumberSpinner(context, digitSize);
        if (numDigits >= 7) {
            tableRow.addView(num1M);
        }
        if (numDigits >= 6) {
            tableRow.addView(num100K);
        }
        if (numDigits >= 5) {
            tableRow.addView(num10K);
        }
        if (numDigits >= 4) {
            tableRow.addView(num1K);
        }
        if (numDigits >= 3) {
            tableRow.addView(num100);
        }
        if (numDigits >= 2) {
            tableRow.addView(num10);
        }
        tableRow.addView(num1);
        // make everything disappear except the unit's place
        num1M.setAlpha(0.0f);
        num100K.setAlpha(0.0f);
        num10K.setAlpha(0.0f);
        num1K.setAlpha(0.0f);
        num100.setAlpha(0.0f);
        num10.setAlpha(0.0f);
        this.setGravity(Gravity.CENTER);
        this.addView(tableRow);
    }

    public void spinTo(int num) {
        if (num < 0 || num >= 10e6) {
            throw new RuntimeException("Num should be within 0 and 10M: " + num);
        }
        makeRelevantDigitsVisible(num);
        // separate out the integer into 1 digit numbers
        int dividend = num;
        num1.spinTo((dividend % 10));

        dividend = dividend / 10;
        num10.spinTo(dividend % 10);

        dividend = dividend / 10;
        num100.spinTo(dividend % 10);

        dividend = dividend / 10;
        num1K.spinTo(dividend % 10);

        dividend = dividend / 10;
        num10K.spinTo(dividend % 10);

        dividend = dividend / 10;
        num100K.spinTo(dividend % 10);

        dividend = dividend / 10;
        num1M.spinTo(dividend % 10);

        animateHigherOrderZeroesToInvisible(num);

        currentValue = num;
    }

    private void makeRelevantDigitsVisible(int num) {
        if (num >= 1000000) {
            num1M.setAlpha(1.0f);
        }
        if (num >= 100000) {
            num100K.setAlpha(1.0f);
        }
        if (num >= 10000) {
            num10K.setAlpha(1.0f);
        }
        if (num >= 1000) {
            num1K.setAlpha(1.0f);
        }
        if (num >= 100) {
            num100.setAlpha(1.0f);
        }
        if (num >= 10) {
            num10.setAlpha(1.0f);
        }
    }

    private void animateHigherOrderZeroesToInvisible(int num) {
        if (num < 1000000 && num1M.getAlpha() > 0.0) {
            animateToInvisible(num1M);
        }
        if (num < 100000 && num100K.getAlpha() > 0.0) {
            animateToInvisible(num100K);
        }
        if (num < 10000 && num10K.getAlpha() > 0.0) {
            animateToInvisible(num10K);
        }
        if (num < 1000 && num1K.getAlpha() > 0.0) {
            animateToInvisible(num1K);
        }
        if (num < 100 && num100.getAlpha() > 0.0) {
            animateToInvisible(num100);
        }
        if (num < 10 && num10.getAlpha() > 0.0) {
            animateToInvisible(num10);
        }
    }

    private static void animateToInvisible(View view) {
//        ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0).setDuration(2000).start();
        view.setAlpha(0);
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
