package br.edu.uniritter.monitors.constant;

public enum Rule {
    EQUAL() {
        public Boolean compare(Long value, Long threshold) { return value.equals(threshold); }
    },
    GREATER_THAN() {
        public Boolean compare(Long value, Long threshold) {
            return value > threshold;
        }
    },
    GREATER_THAN_EQUAL() {
        public Boolean compare(Long value, Long threshold) { return value >= threshold; }
    },
    LESS_THAN() {
        public Boolean compare(Long value, Long threshold) {
            return value < threshold;
        }
    },
    LESS_THAN_EQUAL() {
        public Boolean compare(Long value, Long threshold) { return value <= threshold; }
    },
    NOT_EQUAL() {
        public Boolean compare(Long value, Long threshold) { return !value.equals(threshold); }
    };

    public abstract Boolean compare(Long value, Long threshold);
}
