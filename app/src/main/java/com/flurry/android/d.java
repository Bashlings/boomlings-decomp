package com.flurry.android;

import android.content.Context;

/* loaded from: classes.dex */
public final class d extends an {
    private /* synthetic */ Context a;
    private /* synthetic */ String b;
    private /* synthetic */ bo c;

    public d(bo boVar, Context context, String str) {
        this.c = boVar;
        this.a = context;
        this.b = str;
    }

    @Override // com.flurry.android.an
    public final void a() {
        this.c.c(this.a, this.b);
    }
}
