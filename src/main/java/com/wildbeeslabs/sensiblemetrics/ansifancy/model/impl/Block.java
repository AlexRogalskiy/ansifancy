package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.BlockIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.StyleIF;
import lombok.*;

import java.util.Objects;

import static com.wildbeeslabs.sensiblemetrics.ansifancy.utils.MatrixUtils.checkBound;

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
    private final AreaIF<IntCoordinate> area;

    /**
     * Array of block styles {@link StyleIF}
     */
    public final StyleIF[][] matrix;

    /**
     * Default block constructor by input width / height parameters
     *
     * @param area - initial input {@link AreaIF}
     */
    public Block(final AreaIF<IntCoordinate> area) {
        this.area = Objects.requireNonNull(area);
        this.matrix = new StyleIF[this.area.height()][this.area.width()];
    }

    /**
     * Returns {@link StyleIF} by input row / column parameters
     *
     * @param i - initial input row value
     * @param j - initial input column value
     * @return {@link StyleIF}
     */
    public StyleIF getStyle(int i, int j) {
        checkBound(i, 0, this.area.height() - 1);
        checkBound(j, 0, this.area.width() - 1);
        return this.matrix[i][j];
    }

    /**
     * Updates {@link StyleIF} by input row / column parameters
     *
     * @param i     - initial input row value
     * @param j     - initial input column value
     * @param style - initial input {@link StyleIF} instance
     */
    public void setStyle(int i, int j, final StyleIF style) {
        checkBound(i, 0, this.area.height() - 1);
        checkBound(j, 0, this.area.width() - 1);
        this.matrix[i][j] = style;
    }

    /**
     * Returns copy of {@link StyleIF} by input height / width offset
     *
     * @return copy of {@link StyleIF}
     */
    protected StyleIF[][] copy() {
        return this.copy(this.area.height(), this.area.width());
    }

    /**
     * Returns matrix copy of {@link StyleIF} by input height / width offset
     *
     * @param hOffset - initial input height offset
     * @param wOffset - initial input width offset
     * @return matrix copy of {@link StyleIF}
     */
    protected StyleIF[][] copy(int hOffset, int wOffset) {
        checkBound(hOffset, 0, this.area.height());
        checkBound(wOffset, 0, this.area.width());

        final StyleIF temp[][] = new StyleIF[hOffset][wOffset];
        for (int i = 0; i < hOffset; i++) {
            System.arraycopy(this.matrix[i], 0, temp[i], 0, wOffset);
        }
        return temp;
    }

    /**
     * Returns {@link String} representation of a column by input index
     *
     * @param i - initial input column index
     * @return {@link String} representation of a column
     */
    public String getColumn(int i) {
        checkBound(i, 0, this.area.width());

        final StringBuffer sb = new StringBuffer();
        for (int r = 0; r < this.area.height(); r++) {
            sb.append(this.matrix[r][i]);
        }
        return sb.toString();
    }

    /**
     * Returns new {@link BlockIF} instance by input parameters
     *
     * @param area - initial input {@link AreaIF}
     * @return new {@link BlockIF} instance
     */
    public static Block create(@NonNull final AreaIF<IntCoordinate> area) {
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
            .area(this.area)
            .matrix(this.copy())
            .build();
    }
}
