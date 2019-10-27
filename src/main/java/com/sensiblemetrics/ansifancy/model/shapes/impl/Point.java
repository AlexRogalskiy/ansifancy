package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Represents a geospatial point value.
 */
@Data
public class Point implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 3583151228933783558L;

    private final double x;
    private final double y;

    /**
     * Creates a {@link Point} from the given {@code x}, {@code y} coordinate.
     *
     * @param x
     * @param y
     */
    public Point(final double x, final double y) {
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
