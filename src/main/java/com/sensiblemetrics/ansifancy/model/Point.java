package com.sensiblemetrics.ansifancy.model;

import static com.sensiblemetrics.ansifancy.model.Point.Orientation.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @see <a href="https://www.geeksforgeeks.org/program-check-three-points-collinear">Article</a>
 * @see <a href="https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon">Article</a>
 */
public final class Point {
    enum Orientation {
        COLLINEAR,
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    final double x;
    final double y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    boolean collinearWith(final Point a, final Point b) {
        return COLLINEAR == orientation(a, this, b);
    }

    public boolean between(final Point a, final Point b) {
        return collinearWith(a, b)
            && this.x <= max(a.x, b.x) && x >= min(a.x, b.x)
            && this.y <= max(a.y, b.y) && y >= min(a.y, b.y);
    }

    public static Orientation orientation(final Point p, final Point q, final Point r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0)
            return COLLINEAR;
        return val > 0 ? CLOCKWISE : COUNTERCLOCKWISE;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
