package com.flurry.org.codehaus.jackson;

public final class Base64Variants {
  public static final Base64Variant MIME = new Base64Variant("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
  
  public static final Base64Variant MIME_NO_LINEFEEDS = new Base64Variant(MIME, "MIME-NO-LINEFEEDS", 2147483647);
  
  public static final Base64Variant MODIFIED_FOR_URL;
  
  public static final Base64Variant PEM = new Base64Variant(MIME, "PEM", true, '=', 64);
  
  static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  
  static {
    StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
    stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
    MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", stringBuffer.toString(), false, false, 2147483647);
  }
  
  public static Base64Variant getDefaultVariant() {
    return MIME_NO_LINEFEEDS;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\Base64Variants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */