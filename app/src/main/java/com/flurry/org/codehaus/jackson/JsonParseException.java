package com.flurry.org.codehaus.jackson;

/* loaded from: classes.dex */
public class JsonParseException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    public JsonParseException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    public JsonParseException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }
}
