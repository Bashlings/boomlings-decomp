package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class r implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    if (paramWebView instanceof AdWebView) {
      ((AdWebView)paramWebView).a();
      return;
    } 
    b.b("Trying to close WebView that isn't an AdWebView");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */