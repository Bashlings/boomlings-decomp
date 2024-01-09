package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public class StringDeserializer extends StdScalarDeserializer {
  public StringDeserializer() {
    super(String.class);
  }
  
  public String deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING)
      return paramJsonParser.getText(); 
    if (jsonToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
      null = paramJsonParser.getEmbeddedObject();
      return (null == null) ? null : ((null instanceof byte[]) ? Base64Variants.getDefaultVariant().encode((byte[])null, false) : null.toString());
    } 
    if (jsonToken.isScalarValue())
      return paramJsonParser.getText(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  public String deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return deserialize(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StringDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */