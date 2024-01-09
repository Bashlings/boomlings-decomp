package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

final class ExternalTypeHandler$ExtTypedProperty {
  private final SettableBeanProperty _property;
  
  private final String _typePropertyName;
  
  public ExternalTypeHandler$ExtTypedProperty(SettableBeanProperty paramSettableBeanProperty, String paramString) {
    this._property = paramSettableBeanProperty;
    this._typePropertyName = paramString;
  }
  
  public SettableBeanProperty getProperty() {
    return this._property;
  }
  
  public String getTypePropertyName() {
    return this._typePropertyName;
  }
  
  public boolean hasTypePropertyName(String paramString) {
    return paramString.equals(this._typePropertyName);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\ExternalTypeHandler$ExtTypedProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */