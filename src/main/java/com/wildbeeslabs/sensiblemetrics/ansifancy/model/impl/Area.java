package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.*;

import java.util.Objects;

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
public class Area implements AreaIF<IntCoordinate> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 8105392752473811836L;

    /**
     * Default top right {@link PositionIF}
     */
    private final PositionIF<IntCoordinate> topRight;
    /**
     * Default bottom left {@link PositionIF}
     */
    private final PositionIF<IntCoordinate> bottomLeft;

    /**
     * Checks if input {@link AreaIF} overlaps with current {@link AreaIF}
     *
     * @param other - initial input {@link AreaIF} to check by
     * @return true - if input {@link AreaIF} overlaps with current {@link AreaIF}, false - otherwise
     */
    public boolean isOverlap(final AreaIF<IntCoordinate> other) {
        if (Objects.isNull(other)) return false;
        if (getTopRight().getRow().getValue() < other.getBottomLeft().getRow().getValue() || getBottomLeft().getRow().getValue() > other.getTopRight().getRow().getValue()) {
            return false;
        }
        if (getTopRight().getColumn().getValue() < other.getBottomLeft().getColumn().getValue() || getBottomLeft().getColumn().getValue() > other.getTopRight().getColumn().getValue()) {
            return false;
        }
        return true;
    }

    /**
     * Returns width of current {@link AreaIF}
     *
     * @return width of current {@link AreaIF}
     */
    @Override
    public int width() {
        return (getTopRight().getColumn().getValue() - getBottomLeft().getColumn().getValue());
    }

    /**
     * Returns height of current {@link AreaIF}
     *
     * @return height of current {@link AreaIF}
     */
    @Override
    public int height() {
        return (getTopRight().getRow().getValue() - getTopRight().getRow().getValue());
    }

    /**
     * Returns new {@link AreaIF} instance by input parameters
     *
     * @param topRight   - initial input top right {@link PositionIF}
     * @param bottomLeft - initial input bottom left {@link PositionIF}
     * @return new {@link AreaIF} instance
     */
    public static AreaIF<IntCoordinate> create(@NonNull final PositionIF<IntCoordinate> topRight, @NonNull final PositionIF<IntCoordinate> bottomLeft) {
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
    public static AreaIF<IntCoordinate> create(final int row, final int col, final int size) {
        return create(Position.create(row, col + size), Position.create(row + size, col));
    }
}
