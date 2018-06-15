package br.edu.uniritter.monitors.converters;

import br.edu.uniritter.monitors.contracts.ThresholdRule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ThresholdRuleSerializer extends JsonSerializer<ThresholdRule> {
    @Override
    public void serialize(ThresholdRule rule, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(rule.toString());
    }
}
