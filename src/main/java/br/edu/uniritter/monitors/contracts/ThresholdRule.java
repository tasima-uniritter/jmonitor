package br.edu.uniritter.monitors.contracts;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

class ThresholdRuleSerializer extends JsonSerializer<ThresholdRule> {
    @Override
    public void serialize(ThresholdRule value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.toString());
    }
}

@JsonSerialize(using = ThresholdRuleSerializer.class)
public abstract class ThresholdRule {
    public abstract Boolean compare(Long value, Long threshold);

    public String toString() {
        return UPPER_CAMEL.to(UPPER_UNDERSCORE, this.getClass().getSimpleName());
    }
}
