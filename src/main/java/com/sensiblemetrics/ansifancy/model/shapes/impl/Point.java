package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Represents a geospatial point value.
 *
 * @author Mark Pollack
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Data
@EqualsAndHashCode
@ToString
public class Point implements Serializable {

    private static final long serialVersionUID = 3583151228933783558L;

    private final double x;
    private final double y;

    /**
     * Creates a {@link Point} from the given {@code x}, {@code y} coordinate.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a {@link Point} from the given {@link Point} coordinate.
     *
     * @param point must not be {@literal null}.
     */
    public Point(final Point point) {
        ValidationUtils.notNull(point, "Source point must not be null!");
        this.x = point.x;
        this.y = point.y;
    }
}
