package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class JsonParser implements Versioned, Closeable {
    private static final int MAX_BYTE_I = 127;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected JsonToken _currToken;
    protected int _features;
    protected JsonToken _lastClearedToken;

    /* loaded from: classes.dex */
    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true);
        
        final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        public static int collectDefaults() {
            Feature[] values;
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (getMask() & i) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    /* loaded from: classes.dex */
    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParser(int i) {
        this._features = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonParseException _constructError(String str) {
        return new JsonParseException(str, getCurrentLocation());
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return false;
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public abstract void close();

    public JsonParser configure(Feature feature, boolean z) {
        if (z) {
            enableFeature(feature);
        } else {
            disableFeature(feature);
        }
        return this;
    }

    public JsonParser disable(Feature feature) {
        this._features &= feature.getMask() ^ (-1);
        return this;
    }

    public void disableFeature(Feature feature) {
        disable(feature);
    }

    public JsonParser enable(Feature feature) {
        this._features |= feature.getMask();
        return this;
    }

    public void enableFeature(Feature feature) {
        enable(feature);
    }

    public abstract BigInteger getBigIntegerValue();

    public byte[] getBinaryValue() {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    public boolean getBooleanValue() {
        if (getCurrentToken() == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (getCurrentToken() == JsonToken.VALUE_FALSE) {
            return false;
        }
        throw new JsonParseException("Current token (" + this._currToken + ") not of boolean type", getCurrentLocation());
    }

    public byte getByteValue() {
        int intValue = getIntValue();
        if (intValue < MIN_BYTE_I || intValue > MAX_BYTE_I) {
            throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
        }
        return (byte) intValue;
    }

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName();

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public abstract BigDecimal getDecimalValue();

    public abstract double getDoubleValue();

    public Object getEmbeddedObject() {
        return null;
    }

    public abstract float getFloatValue();

    public Object getInputSource() {
        return null;
    }

    public abstract int getIntValue();

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public abstract long getLongValue();

    public abstract NumberType getNumberType();

    public abstract Number getNumberValue();

    public abstract JsonStreamContext getParsingContext();

    public short getShortValue() {
        int intValue = getIntValue();
        if (intValue < MIN_SHORT_I || intValue > MAX_SHORT_I) {
            throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
        }
        return (short) intValue;
    }

    public abstract String getText();

    public abstract char[] getTextCharacters();

    public abstract int getTextLength();

    public abstract int getTextOffset();

    public abstract JsonLocation getTokenLocation();

    public boolean getValueAsBoolean() {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean z) {
        return z;
    }

    public double getValueAsDouble() {
        return getValueAsDouble(0.0d);
    }

    public double getValueAsDouble(double d) {
        return d;
    }

    public int getValueAsInt() {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i) {
        return i;
    }

    public long getValueAsLong() {
        return getValueAsInt(0);
    }

    public long getValueAsLong(long j) {
        return j;
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public abstract boolean isClosed();

    public boolean isEnabled(Feature feature) {
        return (this._features & feature.getMask()) != 0;
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public final boolean isFeatureEnabled(Feature feature) {
        return isEnabled(feature);
    }

    public Boolean nextBooleanValue() {
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public boolean nextFieldName(SerializableString serializableString) {
        return nextToken() == JsonToken.FIELD_NAME && serializableString.getValue().equals(getCurrentName());
    }

    public int nextIntValue(int i) {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
    }

    public long nextLongValue(long j) {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
    }

    public String nextTextValue() {
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    public abstract JsonToken nextToken();

    public JsonToken nextValue() {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    public Object readValueAs(TypeReference typeReference) {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValue(this, typeReference);
    }

    public Object readValueAs(Class cls) {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValue(this, cls);
    }

    public JsonNode readValueAsTree() {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        }
        return codec.readTree(this);
    }

    public Iterator readValuesAs(TypeReference typeReference) {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, typeReference);
    }

    public Iterator readValuesAs(Class cls) {
        ObjectCodec codec = getCodec();
        if (codec == null) {
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        }
        return codec.readValues(this, cls);
    }

    public int releaseBuffered(OutputStream outputStream) {
        return -1;
    }

    public int releaseBuffered(Writer writer) {
        return -1;
    }

    public abstract void setCodec(ObjectCodec objectCodec);

    public void setFeature(Feature feature, boolean z) {
        configure(feature, z);
    }

    public void setSchema(FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Parser of type " + getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }

    public abstract JsonParser skipChildren();

    @Override // com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return Version.unknownVersion();
    }
}
