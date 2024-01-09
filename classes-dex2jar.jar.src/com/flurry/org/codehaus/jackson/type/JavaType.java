package com.flurry.org.codehaus.jackson.type;

import java.lang.reflect.Modifier;

public abstract class JavaType {
  protected final Class _class;
  
  protected final int _hashCode;
  
  protected Object _typeHandler;
  
  protected Object _valueHandler;
  
  protected JavaType(Class paramClass, int paramInt) {
    this._class = paramClass;
    this._hashCode = paramClass.getName().hashCode() + paramInt;
    this._valueHandler = null;
    this._typeHandler = null;
  }
  
  protected void _assertSubclass(Class<?> paramClass1, Class paramClass2) {
    if (!this._class.isAssignableFrom(paramClass1))
      throw new IllegalArgumentException("Class " + paramClass1.getName() + " is not assignable to " + this._class.getName()); 
  }
  
  protected abstract JavaType _narrow(Class paramClass);
  
  protected JavaType _widen(Class paramClass) {
    return _narrow(paramClass);
  }
  
  public JavaType containedType(int paramInt) {
    return null;
  }
  
  public int containedTypeCount() {
    return 0;
  }
  
  public String containedTypeName(int paramInt) {
    return null;
  }
  
  public abstract boolean equals(Object paramObject);
  
  public JavaType forcedNarrowBy(Class paramClass) {
    if (paramClass == this._class)
      return this; 
    JavaType javaType = _narrow(paramClass);
    null = javaType;
    if (this._valueHandler != javaType.getValueHandler())
      null = javaType.withValueHandler(this._valueHandler); 
    javaType = null;
    if (this._typeHandler != null.getTypeHandler())
      javaType = null.withTypeHandler(this._typeHandler); 
    return javaType;
  }
  
  public JavaType getContentType() {
    return null;
  }
  
  public String getErasedSignature() {
    StringBuilder stringBuilder = new StringBuilder(40);
    getErasedSignature(stringBuilder);
    return stringBuilder.toString();
  }
  
  public abstract StringBuilder getErasedSignature(StringBuilder paramStringBuilder);
  
  public String getGenericSignature() {
    StringBuilder stringBuilder = new StringBuilder(40);
    getGenericSignature(stringBuilder);
    return stringBuilder.toString();
  }
  
  public abstract StringBuilder getGenericSignature(StringBuilder paramStringBuilder);
  
  public JavaType getKeyType() {
    return null;
  }
  
  public final Class getRawClass() {
    return this._class;
  }
  
  public Object getTypeHandler() {
    return this._typeHandler;
  }
  
  public Object getValueHandler() {
    return this._valueHandler;
  }
  
  public boolean hasGenericTypes() {
    return (containedTypeCount() > 0);
  }
  
  public final boolean hasRawClass(Class paramClass) {
    return (this._class == paramClass);
  }
  
  public final int hashCode() {
    return this._hashCode;
  }
  
  public boolean isAbstract() {
    return Modifier.isAbstract(this._class.getModifiers());
  }
  
  public boolean isArrayType() {
    return false;
  }
  
  public boolean isCollectionLikeType() {
    return false;
  }
  
  public boolean isConcrete() {
    boolean bool = true;
    if ((this._class.getModifiers() & 0x600) != 0 && !this._class.isPrimitive())
      bool = false; 
    return bool;
  }
  
  public abstract boolean isContainerType();
  
  public final boolean isEnumType() {
    return this._class.isEnum();
  }
  
  public final boolean isFinal() {
    return Modifier.isFinal(this._class.getModifiers());
  }
  
  public final boolean isInterface() {
    return this._class.isInterface();
  }
  
  public boolean isMapLikeType() {
    return false;
  }
  
  public final boolean isPrimitive() {
    return this._class.isPrimitive();
  }
  
  public boolean isThrowable() {
    return Throwable.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowBy(Class paramClass) {
    if (paramClass == this._class)
      return this; 
    _assertSubclass(paramClass, this._class);
    JavaType javaType = _narrow(paramClass);
    null = javaType;
    if (this._valueHandler != javaType.getValueHandler())
      null = javaType.withValueHandler(this._valueHandler); 
    javaType = null;
    if (this._typeHandler != null.getTypeHandler())
      javaType = null.withTypeHandler(this._typeHandler); 
    return javaType;
  }
  
  public abstract JavaType narrowContentsBy(Class paramClass);
  
  @Deprecated
  public void setValueHandler(Object paramObject) {
    if (paramObject != null && this._valueHandler != null)
      throw new IllegalStateException("Trying to reset value handler for type [" + toString() + "]; old handler of type " + this._valueHandler.getClass().getName() + ", new handler of type " + paramObject.getClass().getName()); 
    this._valueHandler = paramObject;
  }
  
  public abstract String toCanonical();
  
  public abstract String toString();
  
  public JavaType widenBy(Class paramClass) {
    if (paramClass == this._class)
      return this; 
    _assertSubclass(this._class, paramClass);
    return _widen(paramClass);
  }
  
  public abstract JavaType widenContentsBy(Class paramClass);
  
  public abstract JavaType withContentTypeHandler(Object paramObject);
  
  public JavaType withContentValueHandler(Object paramObject) {
    getContentType().setValueHandler(paramObject);
    return this;
  }
  
  public abstract JavaType withTypeHandler(Object paramObject);
  
  public JavaType withValueHandler(Object paramObject) {
    setValueHandler(paramObject);
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\type\JavaType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */