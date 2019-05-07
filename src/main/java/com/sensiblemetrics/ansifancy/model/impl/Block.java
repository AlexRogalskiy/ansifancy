package com.sensiblemetrics.ansifancy.model.impl;

import com.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.sensiblemetrics.ansifancy.model.iface.BlockIF;
import com.sensiblemetrics.ansifancy.model.iface.PositionIF;
import com.sensiblemetrics.ansifancy.model.iface.StyleIF;
import lombok.*;

import java.util.Objects;

/**
 * Default {@link BlockIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Block implements BlockIF<IntCoordinate> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 1382952184639687107L;

    /**
     * Block {@link Area} position
     */
    private final Area area;

    /**
     * Array of block styles {@link StyleIF}
     */
    public final Matrix matrix;

    /**
     * Default block constructor by input width / height parameters
     *
     * @param area - initial input {@link AreaIF}
     */
    public Block(final Area area) {
        this.area = Objects.requireNonNull(area);
        this.matrix = new Matrix(this.area.height(), this.area.width());
    }

    /**
     * Returns {@link StyleIF} by input row / column parameters
     *
     * @param i - initial input row value
     * @param j - initial input column value
     * @return {@link StyleIF}
     */
    public StyleIF getStyle(int i, int j) {
        return this.matrix.get(i, j);
    }

    /**
     * Updates {@link StyleIF} by input row / column parameters
     *
     * @param i     - initial input row value
     * @param j     - initial input column value
     * @param style - initial input {@link StyleIF} instance
     */
    public void set(int i, int j, final StyleIF style) {
        this.matrix.set(i, j, style);
    }

    /**
     * Returns array of {@link StyleIF} by row index
     *
     * @param i - initial input row index
     * @return array of {@link StyleIF}
     */
    public StyleIF[] getRow(int i) {
        return this.matrix.getRow(i);
    }

    /**
     * Returns array of {@link StyleIF} by column index
     *
     * @param i - initial input column index
     * @return array of {@link StyleIF}
     */
    public StyleIF[] getColumn(int i) {
        return this.matrix.getColumn(i);
    }

    /**
     * Returns new {@link BlockIF} instance by input parameters
     *
     * @param area - initial input {@link AreaIF}
     * @return new {@link BlockIF} instance
     */
    public static Block create(@NonNull final Area area) {
        return Block.builder()
            .area(area)
            .build();
    }

    /**
     * Returns new {@link BlockIF} instance by input parameters
     *
     * @param topRight   - initial input top right {@link PositionIF}
     * @param bottomLeft - initial input bottom left {@link PositionIF}
     * @return new {@link BlockIF} instance
     */
    public static Block create(@NonNull final PositionIF<IntCoordinate> topRight, @NonNull final PositionIF<IntCoordinate> bottomLeft) {
        return create(Area.create(topRight, bottomLeft));
    }

    /**
     * Clones current {@link BlockIF}
     *
     * @return new copy of current {@link BlockIF}
     */
    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CloneDoesntCallSuperClone"})
    public Block clone() {
        return Block.builder()
            .area(this.area.copy())
            .matrix(this.matrix.copy())
            .build();
    }
}
