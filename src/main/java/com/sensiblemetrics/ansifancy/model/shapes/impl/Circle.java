package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.model.shapes.Metrics;
import com.sensiblemetrics.ansifancy.model.shapes.iface.Shape;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a geospatial circle value
 *
 * @author Mark Pollack
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Data
@EqualsAndHashCode
@ToString
public class Circle implements Shape {

    private static final long serialVersionUID = 5215611530535947924L;

    private final Point center;
    private final Distance radius;

    /**
     * Creates a new {@link Circle} from the given {@link Point} and radius.
     *
     * @param center must not be {@literal null}.
     * @param radius must not be {@literal null} and it's value greater or equal to zero.
     */
    public Circle(final Point center, final Distance radius) {
        ValidationUtils.notNull(center, "Center point must not be null!");
        ValidationUtils.notNull(radius, "Radius must not be null!");
        ValidationUtils.isTrue(radius.getValue() >= 0, "Radius must not be negative!");

        this.center = center;
        this.radius = radius;
    }

    /**
     * Creates a new {@link Circle} from the given {@link Point} and radius.
     *
     * @param center   must not be {@literal null}.
     * @param radius's value must be greater or equal to zero.
     */
    public Circle(final Point center, final double radius) {
        this(center, new Distance(radius));
    }

    /**
     * Creates a new {@link Circle} from the given coordinates and radius as {@link Distance} with a {@link Metrics#NEUTRAL}.
     *
     * @param centerX
     * @param centerY
     * @param radius  must be greater or equal to zero.
     */
    public Circle(final double centerX, final double centerY, final double radius) {
        this(new Point(centerX, centerY), new Distance(radius));
    }
}
