package com.google.android.gms.measurement.internal;

final class zzfo implements Runnable {
    private final /* synthetic */ zzq zzpf;
    private final /* synthetic */ zzfk zzph;

    zzfo(zzfk zzfk, zzq zzq) {
        this.zzph = zzfk;
        this.zzpf = zzq;
    }

    public final void run() {
        zzfk.zza(this.zzph).zzjq();
        zzfk.zza(this.zzph).zzf(this.zzpf);
    }
}
