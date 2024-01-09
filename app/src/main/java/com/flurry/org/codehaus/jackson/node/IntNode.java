package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class IntNode extends NumericNode {
    private static final IntNode[] CANONICALS = new IntNode[12];
    static final int MAX_CANONICAL = 10;
    static final int MIN_CANONICAL = -1;
    final int _value;

    static {
        for (int i = 0; i < 12; i++) {
            CANONICALS[i] = new IntNode(i - 1);
        }
    }

    public IntNode(int i) {
        this._value = i;
    }

    public static IntNode valueOf(int i) {
        return (i > MAX_CANONICAL || i < -1) ? new IntNode(i) : CANONICALS[i + 1];
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((IntNode) obj)._value == this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.INT;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return Integer.valueOf(this._value);
    }

    public int hashCode() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isInt() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isIntegralNumber() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
