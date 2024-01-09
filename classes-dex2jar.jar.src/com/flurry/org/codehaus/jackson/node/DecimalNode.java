package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class DecimalNode extends NumericNode {
  protected final BigDecimal _value;
  
  public DecimalNode(BigDecimal paramBigDecimal) {
    this._value = paramBigDecimal;
  }
  
  public static DecimalNode valueOf(BigDecimal paramBigDecimal) {
    return new DecimalNode(paramBigDecimal);
  }
  
  public String asText() {
    return this._value.toString();
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject != null) {
      bool1 = bool2;
      if (paramObject.getClass() == getClass())
        bool1 = ((DecimalNode)paramObject)._value.equals(this._value); 
    } 
    return bool1;
  }
  
  public BigInteger getBigIntegerValue() {
    return this._value.toBigInteger();
  }
  
  public BigDecimal getDecimalValue() {
    return this._value;
  }
  
  public double getDoubleValue() {
    return this._value.doubleValue();
  }
  
  public int getIntValue() {
    return this._value.intValue();
  }
  
  public long getLongValue() {
    return this._value.longValue();
  }
  
  public JsonParser.NumberType getNumberType() {
    return JsonParser.NumberType.BIG_DECIMAL;
  }
  
  public Number getNumberValue() {
    return this._value;
  }
  
  public int hashCode() {
    return this._value.hashCode();
  }
  
  public boolean isBigDecimal() {
    return true;
  }
  
  public boolean isFloatingPointNumber() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(this._value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\DecimalNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */