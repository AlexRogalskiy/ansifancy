package com.sensiblemetrics.ansifancy.model.shapes;

import com.sensiblemetrics.ansifancy.model.shapes.iface.Metric;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Commonly used {@link Metric}s.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since 1.8
 */
@Getter
@RequiredArgsConstructor
public enum Metrics implements Metric {
    KILOMETERS(6378.137, "km"),
    MILES(3963.191, "mi"),
    NEUTRAL(1, "");

    private final double multiplier;
    private final String abbreviation;
}
