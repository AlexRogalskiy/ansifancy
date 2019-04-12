package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.*;

/**
 * Default {@link AreaIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Area implements AreaIF<Integer> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 8105392752473811836L;

    /**
     * Default top right {@link PositionIF}
     */
    private PositionIF<Integer> topRight;
    /**
     * Default bottom left {@link PositionIF}
     */
    private PositionIF<Integer> bottomLeft;

    public boolean isOverlap(final AreaIF<Integer> other) {
        if (getTopRight().getRow() < other.getBottomLeft().getRow() || getBottomLeft().getRow() > other.getTopRight().getRow()) {
            return false;
        }
        if (getTopRight().getColumn() < other.getBottomLeft().getColumn() || getBottomLeft().getColumn() > other.getTopRight().getColumn()) {
            return false;
        }
        return true;
    }

    @Override
    public int width() {
        return (getTopRight().getColumn() - getBottomLeft().getColumn());
    }

    @Override
    public int height() {
        return (getTopRight().getRow() - getTopRight().getRow());
    }

    /**
     * Returns new {@link AreaIF} instance by input parameters
     *
     * @param topRight   - initial input top right {@link PositionIF}
     * @param bottomLeft - initial input bottom left {@link PositionIF}
     * @return new {@link AreaIF} instance
     */
    public static AreaIF<Integer> create(final PositionIF<Integer> topRight, final PositionIF<Integer> bottomLeft) {
        return Area.builder()
            .topRight(topRight)
            .bottomLeft(bottomLeft)
            .build();
    }

    /**
     * Returns new {@link AreaIF} instance by input parameters
     *
     * @param row  - initial input row position
     * @param col  - initial input column position
     * @param size - initial input area size
     * @return new {@link AreaIF} instance
     */
    public static AreaIF<Integer> create(final Integer row, final Integer col, final Integer size) {
        return Area.builder()
            .topRight(Position.create(row, col + size))
            .bottomLeft(Position.create(row + size, col))
            .build();
    }
}
