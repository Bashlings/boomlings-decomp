package com.google.ads;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

public class d {
  private c a = null;
  
  private long b = -1L;
  
  public void a(c paramc, int paramInt) {
    this.a = paramc;
    this.b = TimeUnit.MILLISECONDS.convert(paramInt, TimeUnit.SECONDS) + SystemClock.elapsedRealtime();
  }
  
  public boolean a() {
    return (this.a != null && SystemClock.elapsedRealtime() < this.b);
  }
  
  public c b() {
    return this.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */