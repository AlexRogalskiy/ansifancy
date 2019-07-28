package com.sensiblemetrics.ansifancy.model;

import java.util.ArrayList;
import java.util.List;

import static com.sensiblemetrics.ansifancy.model.Point.Orientation.COLLINEAR;
import static com.sensiblemetrics.ansifancy.model.Point.orientation;
import static java.util.Comparator.comparingDouble;

/**
 * @see <a href="https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/">Article</a>
 * @see <a href="https://bit.ly/2xm22oj">Stack Overflow</a>
 */
public final class Polygon {
    final List<Point> points;

    public Polygon(List<Point> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException(
                "Need at least three points, got " + points.size());
        }

        this.points = new ArrayList<>(points);
        this.points.sort(comparingDouble(a -> Math.atan2(a.y, a.x)));
    }

    boolean convex() {
        if (points.size() < 4)
            return true;

        boolean sign = false;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double dx1 =
                points.get((i + 2) % n).x - points.get((i + 1) % n).x;
            double dy1 =
                points.get((i + 2) % n).y - points.get((i + 1) % n).y;
            double dx2 =
                points.get(i).x - points.get((i + 1) % n).x;
            double dy2 =
                points.get(i).y - points.get((i + 1) % n).y;
            double zCrossProduct = dx1 * dy2 - dy1 * dx2;

            if (i == 0)
                sign = zCrossProduct > 0;
            else if (sign != (zCrossProduct > 0))
                return false;
        }

        return true;
    }

    boolean contains(Point p) {
        Point extreme = new Point(Integer.MAX_VALUE, p.y);
        Segment pToExtreme = new Segment(p, extreme);

        // Count intersections of the above line with sides of polygon
        int count = 0;
        int i = 0;

        do {
            int next = (i + 1) % points.size();

            Segment iToNext = new Segment(points.get(i), points.get(next));
            if (iToNext.intersects(pToExtreme)) {
                if (orientation(points.get(i), p, points.get(next)) == COLLINEAR)
                    return p.between(points.get(i), points.get(next));

                ++count;
            }

            i = next;
        } while (i != 0);

        return count % 2 != 0;
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
