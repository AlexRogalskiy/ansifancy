package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.model.shapes.iface.Shape;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a geospatial box value
 *
 * @author Mark Pollack
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Data
@EqualsAndHashCode
@ToString
public class Box implements Shape {

    private static final long serialVersionUID = 8198095179084040711L;

    private final Point first;
    private final Point second;

    /**
     * Creates a new Box spanning from the given first to the second {@link Point}.
     *
     * @param first  must not be {@literal null}.
     * @param second must not be {@literal null}.
     */
    public Box(final Point first, final Point second) {
        ValidationUtils.notNull(first, "First point must not be null!");
        ValidationUtils.notNull(second, "Second point must not be null!");

        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new Box from the given {@code first} to the {@code second} point represented as the {@literal double[]}.
     *
     * @param first  must not be {@literal null} and contain exactly 2 doubles.
     * @param second must not be {@literal null} and contain exactly 2 doubles.
     */
    public Box(final double[] first, final double[] second) {
        ValidationUtils.isTrue(first.length == 2, "Point array has to have 2 elements!");
        ValidationUtils.isTrue(second.length == 2, "Point array has to have 2 elements!");

        this.first = new Point(first[0], first[1]);
        this.second = new Point(second[0], second[1]);
    }
}
