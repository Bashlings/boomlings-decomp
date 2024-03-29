package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import com.flurry.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* loaded from: classes.dex */
public class JodaSerializers implements Provider {
    static final HashMap _serializers = new HashMap();

    /* loaded from: classes.dex */
    public final class DateMidnightSerializer extends JodaSerializer {
        public DateMidnightSerializer() {
            super(DateMidnight.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(DateMidnight dateMidnight, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (!serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeString(printLocalDate((ReadableInstant) dateMidnight));
                return;
            }
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(dateMidnight.year().get());
            jsonGenerator.writeNumber(dateMidnight.monthOfYear().get());
            jsonGenerator.writeNumber(dateMidnight.dayOfMonth().get());
            jsonGenerator.writeEndArray();
        }
    }

    /* loaded from: classes.dex */
    public final class DateTimeSerializer extends JodaSerializer {
        public DateTimeSerializer() {
            super(DateTime.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeNumber(dateTime.getMillis());
            } else {
                jsonGenerator.writeString(dateTime.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class JodaSerializer extends SerializerBase {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.dateTime();
        static final DateTimeFormatter _localDateFormat = ISODateTimeFormat.date();

        protected JodaSerializer(Class cls) {
            super(cls);
        }

        protected String printLocalDate(ReadableInstant readableInstant) {
            return _localDateFormat.print(readableInstant);
        }

        protected String printLocalDate(ReadablePartial readablePartial) {
            return _localDateFormat.print(readablePartial);
        }

        protected String printLocalDateTime(ReadablePartial readablePartial) {
            return _localDateTimeFormat.print(readablePartial);
        }
    }

    /* loaded from: classes.dex */
    public final class LocalDateSerializer extends JodaSerializer {
        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (!serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeString(printLocalDate((ReadablePartial) localDate));
                return;
            }
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(localDate.year().get());
            jsonGenerator.writeNumber(localDate.monthOfYear().get());
            jsonGenerator.writeNumber(localDate.dayOfMonth().get());
            jsonGenerator.writeEndArray();
        }
    }

    /* loaded from: classes.dex */
    public final class LocalDateTimeSerializer extends JodaSerializer {
        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (!serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeString(printLocalDateTime(localDateTime));
                return;
            }
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(localDateTime.year().get());
            jsonGenerator.writeNumber(localDateTime.monthOfYear().get());
            jsonGenerator.writeNumber(localDateTime.dayOfMonth().get());
            jsonGenerator.writeNumber(localDateTime.hourOfDay().get());
            jsonGenerator.writeNumber(localDateTime.minuteOfHour().get());
            jsonGenerator.writeNumber(localDateTime.secondOfMinute().get());
            jsonGenerator.writeNumber(localDateTime.millisOfSecond().get());
            jsonGenerator.writeEndArray();
        }
    }

    static {
        _serializers.put(DateTime.class, new DateTimeSerializer());
        _serializers.put(LocalDateTime.class, new LocalDateTimeSerializer());
        _serializers.put(LocalDate.class, new LocalDateSerializer());
        _serializers.put(DateMidnight.class, new DateMidnightSerializer());
        _serializers.put(Period.class, ToStringSerializer.instance);
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Provider
    public Collection provide() {
        return _serializers.entrySet();
    }
}
