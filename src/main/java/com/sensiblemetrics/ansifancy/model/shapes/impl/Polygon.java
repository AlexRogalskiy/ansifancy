package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.model.shapes.iface.Shape;
import com.sensiblemetrics.ansifancy.utils.StringUtils;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;

import java.util.*;

/**
 * Simple value object to represent a {@link Polygon}.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Data
public class Polygon implements Iterable<Point>, Shape {

    private static final long serialVersionUID = -2705040068154648988L;

    private final List<Point> points;

    /**
     * Creates a new {@link Polygon} for the given Points.
     *
     * @param x      must not be {@literal null}.
     * @param y      must not be {@literal null}.
     * @param z      must not be {@literal null}.
     * @param others
     */
    public Polygon(final Point x, final Point y, final Point z, final Point... others) {
        ValidationUtils.notNull(x, "X coordinate must not be null!");
        ValidationUtils.notNull(y, "Y coordinate must not be null!");
        ValidationUtils.notNull(z, "Z coordinate must not be null!");
        ValidationUtils.notNull(others, "Others must not be null!");

        final List<Point> points = new ArrayList<>(3 + others.length);
        points.addAll(Arrays.asList(x, y, z));
        points.addAll(Arrays.asList(others));
        this.points = Collections.unmodifiableList(points);
    }

    /**
     * Creates a new {@link Polygon} for the given Points.
     *
     * @param points must not be {@literal null}.
     */
    public Polygon(final List<? extends Point> points) {
        ValidationUtils.notNull(points, "Points must not be null!");
        final List<Point> pointsToSet = new ArrayList<>(points.size());
        for (final Point point : points) {
            ValidationUtils.notNull(point, "Single Point in Polygon must not be null!");
            pointsToSet.add(point);
        }
        this.points = Collections.unmodifiableList(pointsToSet);
    }

    /**
     * Returns all {@link Point}s the {@link Polygon} is made of.
     *
     * @return
     */
    public List<Point> getPoints() {
        return this.points;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Point> iterator() {
        return this.points.iterator();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Polygon: [%s]", StringUtils.collectionToCommaDelimitedString(points));
    }
}
