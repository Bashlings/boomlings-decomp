package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;

public abstract class BeanDeserializerModifier {
  public JsonDeserializer modifyDeserializer(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, JsonDeserializer paramJsonDeserializer) {
    return paramJsonDeserializer;
  }
  
  public BeanDeserializerBuilder updateBuilder(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder) {
    return paramBeanDeserializerBuilder;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\BeanDeserializerModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */