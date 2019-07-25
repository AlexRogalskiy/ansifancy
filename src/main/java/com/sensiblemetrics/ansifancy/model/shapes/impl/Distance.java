package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.model.shapes.Metrics;
import com.sensiblemetrics.ansifancy.model.shapes.iface.Metric;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Value;

import java.io.Serializable;

/**
 * Value object to represent distances in a given metric.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Value
public class Distance implements Serializable, Comparable<Distance> {

    private static final long serialVersionUID = 2460886201934027744L;

    /**
     * The distance value in the current {@link Metric}.
     */
    private final double value;

    /**
     * The {@link Metric} of the {@link Distance}.
     */
    private final Metric metric;

    /**
     * Creates a new {@link Distance} with a neutral metric. This means the provided value needs to be in normalized form.
     *
     * @param value
     */
    public Distance(double value) {
        this(value, Metrics.NEUTRAL);
    }

    /**
     * Creates a new {@link Distance} with the given {@link Metric}.
     *
     * @param value
     * @param metric must not be {@literal null}.
     */
    public Distance(double value, Metric metric) {
        ValidationUtils.notNull(metric, "Metric must not be null!");

        this.value = value;
        this.metric = metric;
    }

    /**
     * Creates a {@link Range} between the given {@link Distance}.
     *
     * @param min can be {@literal null}.
     * @param max can be {@literal null}.
     * @return will never be {@literal null}.
     */
    public static Range<Distance> between(Distance min, Distance max) {
        return Range.from(Range.Bound.inclusive(min)).to(Range.Bound.inclusive(max));
    }

    /**
     * Creates a new {@link Range} by creating minimum and maximum {@link Distance} from the given values.
     *
     * @param minValue
     * @param minMetric can be {@literal null}.
     * @param maxValue
     * @param maxMetric can be {@literal null}.
     * @return
     */
    public static Range<Distance> between(double minValue, Metric minMetric, double maxValue, Metric maxMetric) {
        return between(new Distance(minValue, minMetric), new Distance(maxValue, maxMetric));
    }

    /**
     * Returns the normalized value regarding the underlying {@link Metric}.
     *
     * @return
     */
    public double getNormalizedValue() {
        return value / metric.getMultiplier();
    }

    /**
     * Returns a {@link String} representation of the unit the distance is in.
     *
     * @return the unit
     * @see Metric#getAbbreviation()
     */
    public String getUnit() {
        return this.metric.getAbbreviation();
    }

    /**
     * Adds the given distance to the current one. The resulting {@link Distance} will be in the same metric as the
     * current one.
     *
     * @param other must not be {@literal null}.
     * @return
     */
    public Distance add(final Distance other) {
        ValidationUtils.notNull(other, "Distance to add must not be null!");
        double newNormalizedValue = getNormalizedValue() + other.getNormalizedValue();
        return new Distance(newNormalizedValue * this.metric.getMultiplier(), this.metric);
    }

    /**
     * Adds the given {@link Distance} to the current one and forces the result to be in a given {@link Metric}.
     *
     * @param other  must not be {@literal null}.
     * @param metric must not be {@literal null}.
     * @return
     */
    public Distance add(final Distance other, final Metric metric) {
        ValidationUtils.notNull(other, "Distance to must not be null!");
        ValidationUtils.notNull(metric, "Result metric must not be null!");

        final double newLeft = getNormalizedValue() * metric.getMultiplier();
        final double newRight = other.getNormalizedValue() * metric.getMultiplier();
        return new Distance(newLeft + newRight, metric);
    }

    /**
     * Returns a new {@link Distance} in the given {@link Metric}. This means that the returned instance will have the
     * same normalized value as the original instance.
     *
     * @param metric must not be {@literal null}.
     * @return
     */
    public Distance in(final Metric metric) {
        ValidationUtils.notNull(metric, "Metric must not be null!");
        return this.metric.equals(metric) ? this : new Distance(getNormalizedValue() * metric.getMultiplier(), metric);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Distance that) {
        if (that == null) {
            return 1;
        }
        final double difference = this.getNormalizedValue() - that.getNormalizedValue();
        return difference == 0 ? 0 : difference > 0 ? 1 : -1;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.value);
        if (this.metric != Metrics.NEUTRAL) {
            builder.append(" ").append(this.metric.toString());
        }
        return builder.toString();
    }
}
