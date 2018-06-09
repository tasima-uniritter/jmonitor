package br.edu.uniritter.monitors.constant;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Metric {
    @SerializedName("memory-usage")
    MEMORY_USAGE("memory-usage");

    private String metric;

    public String toString() {
        return this.metric;
    }
}
