package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;

@Deprecated
/* loaded from: classes.dex */
public class JsonNodeDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.JsonNodeDeserializer {
    @Deprecated
    public static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    @Deprecated
    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return deserializeAny(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    @Deprecated
    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }
}
