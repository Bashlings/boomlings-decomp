package com.flurry.org.codehaus.jackson;

/* loaded from: classes.dex */
public interface SerializableString {
    char[] asQuotedChars();

    byte[] asQuotedUTF8();

    byte[] asUnquotedUTF8();

    int charLength();

    String getValue();
}
