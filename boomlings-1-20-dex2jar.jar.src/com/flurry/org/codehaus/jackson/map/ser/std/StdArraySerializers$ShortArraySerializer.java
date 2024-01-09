package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$ShortArraySerializer extends StdArraySerializers$ArraySerializerBase {
  public StdArraySerializers$ShortArraySerializer() {
    this((TypeSerializer)null);
  }
  
  public StdArraySerializers$ShortArraySerializer(TypeSerializer paramTypeSerializer) {
    super(short[].class, paramTypeSerializer, (BeanProperty)null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new StdArraySerializers$ShortArraySerializer(paramTypeSerializer);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", (JsonNode)createSchemaNode("integer"));
    return (JsonNode)objectNode;
  }
  
  public void serializeContents(short[] paramArrayOfshort, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    byte b = 0;
    int i = paramArrayOfshort.length;
    while (b < i) {
      paramJsonGenerator.writeNumber(paramArrayOfshort[b]);
      b++;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$ShortArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */