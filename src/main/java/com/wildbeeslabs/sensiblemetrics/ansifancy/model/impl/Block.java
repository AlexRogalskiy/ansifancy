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
public class Block implements BlockIF<Integer> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 1382952184639687107L;

    /**
     * Block {@link Area} position
     */
    private AreaIF<Integer> area;

    /**
     * Array of block styles {@link StyleIF}
     */
    public StyleIF[][] matrix;

    /**
     * Default block constructor by input width / height parameters
     *
     * @param area - initial input {@link AreaIF}
     */
    public Block(final AreaIF<Integer> area) {
        this.area = Objects.requireNonNull(area);
        this.matrix = new StyleIF[this.area.height()][this.area.width()];
    }

    public StyleIF getStyle(int i, int j) {
        checkBound(i, 0, this.area.height() - 1);
        checkBound(j, 0, this.area.width() - 1);
        return this.matrix[i][j];
    }

    public void setStyle(int i, int j, final StyleIF style) {
        checkBound(i, 0, this.area.height() - 1);
        checkBound(j, 0, this.area.width() - 1);
        this.matrix[i][j] = style;
    }

    /**
     * Returns copy of {@link StyleIF} by input height / width offset
     *
     * @param heightOffset - initial input height offset
     * @param widthOffset  - initial input width offset
     * @return copy of {@link StyleIF}
     */
    protected StyleIF[][] copy(int heightOffset, int widthOffset) {
        assert heightOffset > 0 : "Should be greater than zero";
        assert widthOffset > 0 : "Should be greater than zero";

        final StyleIF temp[][] = new StyleIF[heightOffset][widthOffset];
        for (int i = 0; i < this.area.height(); i++) {
            System.arraycopy(this.matrix[i], 0, temp[i], 0, this.area.width());
        }
        return temp;
    }

    public String getColumn(int i) {
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
    public static BlockIF<Integer> create(final AreaIF<Integer> area) {
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
    public static BlockIF<Integer> create(final PositionIF<Integer> topRight, final PositionIF<Integer> bottomLeft) {
        return Block.builder()
            .area(Area.create(topRight, bottomLeft))
            .build();
    }
}
