package com.sensiblemetrics.ansifancy.model.shapes;

import com.sensiblemetrics.ansifancy.model.shapes.iface.Converter;
import com.sensiblemetrics.ansifancy.model.shapes.iface.Formatter;
import com.sensiblemetrics.ansifancy.model.shapes.iface.Metric;
import com.sensiblemetrics.ansifancy.model.shapes.impl.Distance;
import com.sensiblemetrics.ansifancy.utils.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Converter to create {@link Distance} instances from {@link String} representations. The supported format is a decimal
 * followed by whitespace and a metric abbreviation. We currently support the following abbreviations
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 */
public enum DistanceFormatter implements Converter<String, Distance>, Formatter<Distance> {
    INSTANCE;

    private static final Map<String, Metric> SUPPORTED_METRICS;
    private static final String INVALID_DISTANCE = "Expected double amount optionally followed by a metrics abbreviation (%s) but got '%s'!";

    static {
        final Map<String, Metric> metrics = new LinkedHashMap<>();
        for (final Metric metric : Metrics.values()) {
            metrics.put(metric.getAbbreviation(), metric);
            metrics.put(metric.toString().toLowerCase(Locale.US), metric);
        }

        SUPPORTED_METRICS = Collections.unmodifiableMap(metrics);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public final Distance convert(final String source) {
        return source == null ? null : doConvert(source.trim().toLowerCase(Locale.US));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
     */
    @Override
    public String print(final Distance distance, final Locale locale) {
        return distance == null ? null : String.format("%s%s", distance.getValue(), distance.getUnit().toLowerCase(locale));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
     */
    @Override
    public Distance parse(final String text, final Locale locale) {
        return doConvert(text.trim().toLowerCase(locale));
    }

    /**
     * Converts the given {@link String} source into a distance. Expects the source to reflect the {@link Metric} as held
     * in the {@link #SUPPORTED_METRICS} map.
     *
     * @param source must not be {@literal null}.
     * @return
     */
    private static Distance doConvert(final String source) {
        for (final Entry<String, Metric> metric : SUPPORTED_METRICS.entrySet()) {
            if (source.endsWith(metric.getKey())) {
                return fromString(source, metric);
            }
        }

        try {
            return new Distance(Double.parseDouble(source));
        } catch (NumberFormatException o_O) {
            throw new IllegalArgumentException(String.format(INVALID_DISTANCE, StringUtils.collectionToCommaDelimitedString(SUPPORTED_METRICS.keySet()), source));
        }
    }

    /**
     * Creates a {@link Distance} from the given source String and the {@link Metric} detected.
     *
     * @param source the raw source {@link String}, must not be {@literal null} or empty.
     * @param metric the {@link Metric} detected keyed by the keyword it was detected for, must not be {@literal null}.
     * @return
     */
    private static Distance fromString(final String source, final Entry<String, Metric> metric) {
        final String amountString = source.substring(0, source.indexOf(metric.getKey()));

        try {
            return new Distance(Double.parseDouble(amountString), metric.getValue());
        } catch (NumberFormatException o_O) {
            throw new IllegalArgumentException(String.format(INVALID_DISTANCE, StringUtils.collectionToCommaDelimitedString(SUPPORTED_METRICS.keySet()), source));
        }
    }
}
