package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002¨\u0006\u0005"}, d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "(Lkotlin/jvm/functions/Function0;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 8})
/* compiled from: Sequences.kt */
public final class StringsKt___StringsKt$asSequence$$inlined$Sequence$1 implements Sequence<Character> {
    final /* synthetic */ CharSequence receiver$0$inlined;

    public StringsKt___StringsKt$asSequence$$inlined$Sequence$1(CharSequence charSequence) {
        this.receiver$0$inlined = charSequence;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<Character> iterator() {
        return StringsKt.iterator(this.receiver$0$inlined);
    }
}
