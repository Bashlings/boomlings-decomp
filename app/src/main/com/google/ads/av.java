package com.google.ads;

import com.google.ads.mediation.MediationInterstitialAdapter;

/* loaded from: classes.dex */
class av implements Runnable {
    final /* synthetic */ MediationInterstitialAdapter a;
    final /* synthetic */ h b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public av(h hVar, MediationInterstitialAdapter mediationInterstitialAdapter) {
        this.b = hVar;
        this.a = mediationInterstitialAdapter;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.e();
        } catch (Throwable th) {
            com.google.ads.util.b.b("Error while telling adapter (" + this.b.h() + ") ad to show interstitial: ", th);
        }
    }
}
