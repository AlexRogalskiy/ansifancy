package com.sensiblemetrics.ansifancy.model;

import static com.sensiblemetrics.ansifancy.model.Point.Orientation.COLLINEAR;
import static com.sensiblemetrics.ansifancy.model.Point.orientation;

public final class Segment {
    final Point a;
    final Point b;

    Segment(final Point a, final Point b) {
        this.a = a;
        this.b = b;
    }

    boolean intersects(final Segment other) {
        final Point.Orientation o1 = orientation(this.a, this.b, other.a);
        final Point.Orientation o2 = orientation(this.a, this.b, other.b);
        final Point.Orientation o3 = orientation(other.a, other.b, this.a);
        final Point.Orientation o4 = orientation(other.a, other.b, this.b);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        if (o1 == COLLINEAR && other.a.between(a, b))
            return true;

        if (o2 == COLLINEAR && other.b.between(a, b))
            return true;

        if (o3 == COLLINEAR && a.between(other.a, other.b))
            return true;

        if (o4 == COLLINEAR && b.between(other.a, other.b))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s %s]", a, b);
    }
}
