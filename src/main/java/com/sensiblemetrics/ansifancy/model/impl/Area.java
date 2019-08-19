package com.sensiblemetrics.ansifancy.model.impl;

import com.sensiblemetrics.ansifancy.calculation.OperationFactory;
import com.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.sensiblemetrics.ansifancy.model.iface.PositionIF;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Default {@link AreaIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
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
        if (OperationFactory.INT_COORDINATE_COMPARATOR.compare(getTopRight().getRow(), other.getBottomLeft().getRow()) < 0 || OperationFactory.INT_COORDINATE_COMPARATOR.compare(getBottomLeft().getRow(), other.getTopRight().getRow()) > 0) {
            return false;
        }
        if (OperationFactory.INT_COORDINATE_COMPARATOR.compare(getTopRight().getColumn(), other.getBottomLeft().getColumn()) < 0 || OperationFactory.INT_COORDINATE_COMPARATOR.compare(getBottomLeft().getColumn(), other.getTopRight().getColumn()) > 0) {
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
        return OperationFactory.SUBTRACT.apply(getTopRight().getColumn(), getBottomLeft().getColumn());
    }

    /**
     * Returns height of current {@link AreaIF}
     *
     * @return height of current {@link AreaIF}
     */
    @Override
    public int height() {
        return OperationFactory.SUBTRACT.apply(getTopRight().getRow(), getTopRight().getRow());
    }

    /**
     * Returns binary flag by input {@link PositionIF} value
     *
     * @param p - initial input {@link PositionIF} value
     * @return true - if current {@link Area} contains {@link PositionIF}, false - otherwise
     */
    public boolean contains(final PositionIF<IntCoordinate> p) {
        return p.getColumn().getValue() >= this.getBottomLeft().getColumn().getValue()
            && p.getColumn().getValue() <= this.getTopRight().getColumn().getValue()
            && p.getRow().getValue() <= this.getBottomLeft().getRow().getValue()
            && p.getColumn().getValue() >= this.getTopRight().getRow().getValue();
    }

    public boolean isEmpty() {
        return (this.width() == 0) || (this.height() == 0);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * Returns copy of {@link AreaIF}
     *
     * @return copy of {@link AreaIF}
     */
    public Area copy() {
        return create(this.getTopRight(), this.getBottomLeft());
    }

    /**
     * Returns centroid {@link PositionIF} of current {@link AreaIF}
     *
     * @return centroid {@link PositionIF} of current {@link AreaIF}
     */
    public Position centroid() {
        return Position.create(OperationFactory.ADD.apply(this.getBottomLeft().getColumn(), this.getTopRight().getColumn()) / 2,
            OperationFactory.ADD.apply(this.getTopRight().getRow(), this.getBottomLeft().getRow()) / 2,
            OperationFactory.ADD.apply(this.getBottomLeft().getDepth(), this.getTopRight().getDepth()) / 2);
    }

    /**
     * Returns new {@link AreaIF} instance by input parameters
     *
     * @param topRight   - initial input top right {@link PositionIF}
     * @param bottomLeft - initial input bottom left {@link PositionIF}
     * @return new {@link AreaIF} instance
     */
    @NotNull
    public static Area create(@NonNull final PositionIF<IntCoordinate> topRight, @NonNull final PositionIF<IntCoordinate> bottomLeft) {
        return Area
            .builder()
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
        ValidationUtils.isTrue(size >= 0, "Should be greater than or equal zero");
        return create(Position.create(row, col + size), Position.create(row + size, col));
    }

    @NotNull
    public PositionIF<IntCoordinate> getCenter() {
        return Position
            .builder()
            .row(IntCoordinate.of(OperationFactory.SUBTRACT.apply(this.topRight.getRow(), this.bottomLeft.getRow()) / 2))
            .column(IntCoordinate.of(OperationFactory.SUBTRACT.apply(this.topRight.getColumn(), this.bottomLeft.getColumn()) / 2))
            .build();
    }
}
