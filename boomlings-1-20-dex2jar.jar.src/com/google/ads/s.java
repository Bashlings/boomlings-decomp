package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class s implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    if (paramWebView instanceof AdWebView) {
      ((AdWebView)paramWebView).setCustomClose("1".equals(paramHashMap.get("custom_close")));
      return;
    } 
    b.b("Trying to set a custom close icon on a WebView that isn't an AdWebView");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */