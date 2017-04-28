package xyz.pinaki.android.wheelticker;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Created by pinaki on 4/28/17.
 */

public abstract class OdometerAdapter {
    private DataSetObservable dataSetObservable = new DataSetObservable();

    public void notifyDataSetChanged() {
        dataSetObservable.notifyChanged();
    }

    public void registerObserver(DataSetObserver dataSetObserver) {
        dataSetObservable.registerObserver(dataSetObserver);
    }

    public void unregisterObserver(DataSetObserver dataSetObserver) {
        dataSetObservable.unregisterObserver(dataSetObserver);
    }

    public final void notifyDataSetInvalidated() {
        dataSetObservable.notifyInvalidated();
    }

    public abstract int getNumber();
}
