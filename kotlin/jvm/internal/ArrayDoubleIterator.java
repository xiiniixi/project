package kotlin.jvm.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.DoubleIterator;
import org.jetbrains.annotations.NotNull;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayDoubleIterator;", "Lkotlin/collections/DoubleIterator;", "array", "", "([D)V", FirebaseAnalytics.Param.INDEX, "", "hasNext", "", "nextDouble", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 8})
/* compiled from: ArrayIterators.kt */
public final class ArrayDoubleIterator extends DoubleIterator {
    private final double[] array;
    private int index;

    public ArrayDoubleIterator(@NotNull double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "array");
        this.array = dArr;
    }

    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.DoubleIterator
    public double nextDouble() {
        try {
            double[] dArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return dArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
