package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;

class ValidatingGrammarGenerator$LitS {
  public final Schema actual;
  
  public ValidatingGrammarGenerator$LitS(Schema paramSchema) {
    this.actual = paramSchema;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject instanceof ValidatingGrammarGenerator$LitS && this.actual == ((ValidatingGrammarGenerator$LitS)paramObject).actual)
      bool = true; 
    return bool;
  }
  
  public int hashCode() {
    return this.actual.hashCode();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\ValidatingGrammarGenerator$LitS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */