package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.map.ser.impl.UnwrappingBeanPropertyWriter;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

/* loaded from: classes.dex */
public class BeanPropertyWriter implements BeanProperty {
    protected final Method _accessorMethod;
    protected final JavaType _cfgSerializationType;
    protected final Annotations _contextAnnotations;
    protected final JavaType _declaredType;
    protected PropertySerializerMap _dynamicSerializers;
    protected final Field _field;
    protected Class[] _includeInViews;
    protected HashMap _internalSettings;
    protected final AnnotatedMember _member;
    protected final SerializedString _name;
    protected JavaType _nonTrivialBaseType;
    protected final JsonSerializer _serializer;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected TypeSerializer _typeSerializer;

    public BeanPropertyWriter(AnnotatedMember annotatedMember, Annotations annotations, SerializedString serializedString, JavaType javaType, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, Method method, Field field, boolean z, Object obj) {
        this._member = annotatedMember;
        this._contextAnnotations = annotations;
        this._name = serializedString;
        this._declaredType = javaType;
        this._serializer = jsonSerializer;
        this._dynamicSerializers = jsonSerializer == null ? PropertySerializerMap.emptyMap() : null;
        this._typeSerializer = typeSerializer;
        this._cfgSerializationType = javaType2;
        this._accessorMethod = method;
        this._field = field;
        this._suppressNulls = z;
        this._suppressableValue = obj;
    }

    public BeanPropertyWriter(AnnotatedMember annotatedMember, Annotations annotations, String str, JavaType javaType, JsonSerializer jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, Method method, Field field, boolean z, Object obj) {
        this(annotatedMember, annotations, new SerializedString(str), javaType, jsonSerializer, typeSerializer, javaType2, method, field, z, obj);
    }

    public BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        this(beanPropertyWriter, beanPropertyWriter._serializer);
    }

    public BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, JsonSerializer jsonSerializer) {
        this._serializer = jsonSerializer;
        this._member = beanPropertyWriter._member;
        this._contextAnnotations = beanPropertyWriter._contextAnnotations;
        this._declaredType = beanPropertyWriter._declaredType;
        this._accessorMethod = beanPropertyWriter._accessorMethod;
        this._field = beanPropertyWriter._field;
        if (beanPropertyWriter._internalSettings != null) {
            this._internalSettings = new HashMap(beanPropertyWriter._internalSettings);
        }
        this._name = beanPropertyWriter._name;
        this._cfgSerializationType = beanPropertyWriter._cfgSerializationType;
        this._dynamicSerializers = beanPropertyWriter._dynamicSerializers;
        this._suppressNulls = beanPropertyWriter._suppressNulls;
        this._suppressableValue = beanPropertyWriter._suppressableValue;
        this._includeInViews = beanPropertyWriter._includeInViews;
        this._typeSerializer = beanPropertyWriter._typeSerializer;
        this._nonTrivialBaseType = beanPropertyWriter._nonTrivialBaseType;
    }

    protected JsonSerializer _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class cls, SerializerProvider serializerProvider) {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = this._nonTrivialBaseType != null ? propertySerializerMap.findAndAddSerializer(serializerProvider.constructSpecializedType(this._nonTrivialBaseType, cls), serializerProvider, this) : propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    public void _reportSelfReference(Object obj) {
        throw new JsonMappingException("Direct self-reference leading to cycle");
    }

    public final Object get(Object obj) {
        return this._accessorMethod != null ? this._accessorMethod.invoke(obj, new Object[0]) : this._field.get(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public Annotation getAnnotation(Class cls) {
        return this._member.getAnnotation(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public Annotation getContextAnnotation(Class cls) {
        return this._contextAnnotations.get(cls);
    }

    public Type getGenericPropertyType() {
        return this._accessorMethod != null ? this._accessorMethod.getGenericReturnType() : this._field.getGenericType();
    }

    public Object getInternalSetting(Object obj) {
        if (this._internalSettings == null) {
            return null;
        }
        return this._internalSettings.get(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public AnnotatedMember getMember() {
        return this._member;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty, com.flurry.org.codehaus.jackson.map.util.Named
    public String getName() {
        return this._name.getValue();
    }

    public Class getPropertyType() {
        return this._accessorMethod != null ? this._accessorMethod.getReturnType() : this._field.getType();
    }

    public Class getRawSerializationType() {
        if (this._cfgSerializationType == null) {
            return null;
        }
        return this._cfgSerializationType.getRawClass();
    }

    public JavaType getSerializationType() {
        return this._cfgSerializationType;
    }

    public SerializedString getSerializedName() {
        return this._name;
    }

    public JsonSerializer getSerializer() {
        return this._serializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public JavaType getType() {
        return this._declaredType;
    }

    public Class[] getViews() {
        return this._includeInViews;
    }

    public boolean hasSerializer() {
        return this._serializer != null;
    }

    public Object removeInternalSetting(Object obj) {
        if (this._internalSettings != null) {
            Object remove = this._internalSettings.remove(obj);
            if (this._internalSettings.size() == 0) {
                this._internalSettings = null;
                return remove;
            }
            return remove;
        }
        return null;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Class<?> cls;
        PropertySerializerMap propertySerializerMap;
        Object obj2 = get(obj);
        if (obj2 == null) {
            if (this._suppressNulls) {
                return;
            }
            jsonGenerator.writeFieldName(this._name);
            serializerProvider.defaultSerializeNull(jsonGenerator);
            return;
        }
        if (obj2 == obj) {
            _reportSelfReference(obj);
        }
        if (this._suppressableValue == null || !this._suppressableValue.equals(obj2)) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null && (jsonSerializer = (propertySerializerMap = this._dynamicSerializers).serializerFor((cls = obj2.getClass()))) == null) {
                jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
            }
            jsonGenerator.writeFieldName(this._name);
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
            } else {
                jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
            }
        }
    }

    public Object setInternalSetting(Object obj, Object obj2) {
        if (this._internalSettings == null) {
            this._internalSettings = new HashMap();
        }
        return this._internalSettings.put(obj, obj2);
    }

    public void setNonTrivialBaseType(JavaType javaType) {
        this._nonTrivialBaseType = javaType;
    }

    public void setViews(Class[] clsArr) {
        this._includeInViews = clsArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("property '").append(getName()).append("' (");
        if (this._accessorMethod != null) {
            sb.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
        } else {
            sb.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        }
        if (this._serializer == null) {
            sb.append(", no static serializer");
        } else {
            sb.append(", static serializer of type " + this._serializer.getClass().getName());
        }
        sb.append(')');
        return sb.toString();
    }

    public BeanPropertyWriter unwrappingWriter() {
        return new UnwrappingBeanPropertyWriter(this);
    }

    public BeanPropertyWriter withSerializer(JsonSerializer jsonSerializer) {
        if (getClass() != BeanPropertyWriter.class) {
            throw new IllegalStateException("BeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!");
        }
        return new BeanPropertyWriter(this, jsonSerializer);
    }
}
