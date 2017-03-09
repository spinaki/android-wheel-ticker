package xyz.pinaki.android.wheelticker;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by pinaki on 6/9/16.
 */
/* package */ class VerticalSpinner extends ScrollView {
    private static final int SPIN_ANIMATION_TIME_MILLISEC = 1000;
    private static final int DEFAULT_TEXT_SIZE_DP = 30;

    private int itemHeight = 0;
    private int currentIndex = 0;
    private int textSize = DEFAULT_TEXT_SIZE_DP;
    private LinearLayout textViewList;

    public VerticalSpinner(Context context, String[] items, int textSize) {
        super(context);
        init(context, items, textSize);
    }

    public VerticalSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null, DEFAULT_TEXT_SIZE_DP);
    }

    public VerticalSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, null, DEFAULT_TEXT_SIZE_DP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), itemHeight);
//        Log.i("onMeasure", "getMeasuredWidth: " + getMeasuredWidth());
//        Log.i("onMeasure", "getMeasuredHeight: " + getMeasuredHeight());
    }

    private void init(Context context, String[] items, int textSize) {
        this.textSize = textSize;
        this.setVerticalScrollBarEnabled(false);
        textViewList = new LinearLayout(context);
        textViewList.setOrientation(LinearLayout.VERTICAL);
        textViewList.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(textViewList);
        addItems(items, context);
    }

    private void addItems(String[] items, Context context) {
        for (String item : items) {
            textViewList.addView(createView(item, context));
        }
        smoothScrollTo(0, 0);
    }

    private TextView createView(String item, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setSingleLine(true);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        textView.setText(item);
        textView.setGravity(Gravity.CENTER);
        if (itemHeight == 0) {
            itemHeight = getViewMeasuredHeight(textView);
//            Log.i("createView", "itemHeight: " + itemHeight);
        }
        return textView;
    }

    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }

    public void spinToIndex(int newIndex) {
        int delta = newIndex - currentIndex;
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "scrollY",  getScrollY() + delta * itemHeight);
        objectAnimator.setDuration(SPIN_ANIMATION_TIME_MILLISEC);
        objectAnimator.start();
        currentIndex = newIndex;
    }

    public String getCurrentText() {
        TextView tv = (TextView) textViewList.getChildAt(currentIndex);
        return tv.getText().toString();
    }
}
