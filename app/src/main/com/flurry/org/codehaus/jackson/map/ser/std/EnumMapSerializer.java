package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
/* loaded from: classes.dex */
public class EnumMapSerializer extends ContainerSerializerBase implements ResolvableSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected JsonSerializer _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, enumValues, typeSerializer, beanProperty, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._valueSerializer = jsonSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._property, this._valueSerializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                Enum[] enumArr = (Enum[]) constructType.getRawClass().getEnumConstants();
                for (Enum r7 : enumArr) {
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(constructType2.getRawClass(), this._property);
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(r7), findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
                createSchemaNode.put("properties", objectNode);
            }
        }
        return createSchemaNode;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        if (this._staticTyping && this._valueSerializer == null) {
            this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(EnumMap enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    protected void serializeContents(EnumMap enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer;
        Class<?> cls;
        JsonSerializer jsonSerializer2;
        Class<?> cls2 = null;
        if (this._valueSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, this._valueSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        EnumValues enumValues2 = enumValues;
        JsonSerializer jsonSerializer3 = null;
        for (Map.Entry entry : enumMap.entrySet()) {
            Enum r1 = (Enum) entry.getKey();
            EnumValues enumValues3 = enumValues2 == null ? ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property))).getEnumValues() : enumValues2;
            jsonGenerator.writeFieldName(enumValues3.serializedValueFor(r1));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                cls = cls2;
                jsonSerializer2 = jsonSerializer3;
            } else {
                Class<?> cls3 = value.getClass();
                if (cls3 == cls2) {
                    cls3 = cls2;
                    jsonSerializer = jsonSerializer3;
                } else {
                    jsonSerializer3 = serializerProvider.findValueSerializer(cls3, this._property);
                    jsonSerializer = jsonSerializer3;
                }
                try {
                    jsonSerializer3.serialize(value, jsonGenerator, serializerProvider);
                    cls = cls3;
                    jsonSerializer2 = jsonSerializer;
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                    cls = cls3;
                    jsonSerializer2 = jsonSerializer;
                }
            }
            enumValues2 = enumValues3;
            cls2 = cls;
            jsonSerializer3 = jsonSerializer2;
        }
    }

    protected void serializeContentsUsing(EnumMap enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer jsonSerializer) {
        EnumValues enumValues = this._keyEnums;
        EnumValues enumValues2 = enumValues;
        for (Map.Entry entry : enumMap.entrySet()) {
            Enum r1 = (Enum) entry.getKey();
            if (enumValues2 == null) {
                enumValues2 = ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(r1.getDeclaringClass(), this._property))).getEnumValues();
            }
            jsonGenerator.writeFieldName(enumValues2.serializedValueFor(r1));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, ((Enum) entry.getKey()).name());
                }
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(EnumMap enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }
}
