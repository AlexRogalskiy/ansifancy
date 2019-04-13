package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.*;

import java.util.Objects;

import static com.wildbeeslabs.sensiblemetrics.ansifancy.operation.OperationFactory.*;

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
        if (DEFAULT_COORDINATE_CMP.compare(getTopRight().getRow(), other.getBottomLeft().getRow()) < 0 || DEFAULT_COORDINATE_CMP.compare(getBottomLeft().getRow(), other.getTopRight().getRow()) > 0) {
            return false;
        }
        if (DEFAULT_COORDINATE_CMP.compare(getTopRight().getColumn(), other.getBottomLeft().getColumn()) < 0 || DEFAULT_COORDINATE_CMP.compare(getBottomLeft().getColumn(), other.getTopRight().getColumn()) > 0) {
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
        return SUBTRACT.apply(getTopRight().getColumn(), getBottomLeft().getColumn());
    }

    /**
     * Returns height of current {@link AreaIF}
     *
     * @return height of current {@link AreaIF}
     */
    @Override
    public int height() {
        return SUBTRACT.apply(getTopRight().getRow(), getTopRight().getRow());
    }

    /**
     * Returns centroid {@link PositionIF} of current {@link AreaIF}
     *
     * @return centroid {@link PositionIF} of current {@link AreaIF}
     */
    public PositionIF<IntCoordinate> centroid() {
        return Position.create(ADD.apply(this.getBottomLeft().getColumn(), this.getTopRight().getColumn()) / 2,
            ADD.apply(this.getTopRight().getRow(), this.getBottomLeft().getRow()) / 2,
            ADD.apply(this.getBottomLeft().getDepth(), this.getTopRight().getDepth()) / 2);
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
        assert size >= 0 : "Should be greater than or equal zero";
        return create(Position.create(row, col + size), Position.create(row + size, col));
    }
}
