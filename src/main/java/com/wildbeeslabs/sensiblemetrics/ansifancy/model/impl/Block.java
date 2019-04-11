package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.BlockIF;
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
public class Block implements BlockIF {

    /**
     * Block width
     */
    public int width;
    /**
     * Block height
     */
    public int height;
    /**
     * Array of block styles {@link StyleIF}
     */
    public StyleIF[][] matrix;

    /**
     * Default block constructor by input width / height parameters
     *
     * @param width  - initial input block width
     * @param height - initial input block height
     */
    public Block(int width, int height) {
        assert width > 0 : "Should be greater than zero";
        assert height > 0 : "Should be greater than zero";

        this.width = width;
        this.height = height;
        this.matrix = new StyleIF[this.height][this.width];
    }

    /**
     * Default block constructor by input array of {@link StyleIF}
     *
     * @param matrix - initial input array of {@link StyleIF}
     */
    public Block(final StyleIF[][] matrix) {
        this.matrix = Objects.requireNonNull(matrix);
        this.height = matrix.length;
        this.width = Objects.requireNonNull(matrix[0]).length;
    }

    public StyleIF getStyle(int i, int j) {
        checkBound(i, 0, this.height - 1);
        checkBound(j, 0, this.width - 1);
        return this.matrix[i][j];
    }

    public void setStyle(int i, int j, final StyleIF style) {
        checkBound(i, 0, this.height - 1);
        checkBound(j, 0, this.width - 1);
        this.matrix[i][j] = style;
    }

    /**
     * Returns updated {@link BlockIF} by input height / width offset
     *
     * @param heightOffset - initial input height offset
     * @param widthOffset  - initial input width offset
     * @return updated {@link BlockIF}
     */
    public BlockIF append(int heightOffset, int widthOffset) {
        assert heightOffset > 0 : "Should be greater than zero";
        assert widthOffset > 0 : "Should be greater than zero";

        final StyleIF temp[][] = new StyleIF[this.getHeight() + heightOffset][this.getWidth() + widthOffset];
        for (int i = 0; i < this.getHeight(); i++) {
            System.arraycopy(this.matrix[i], 0, temp[i], 0, this.getWidth());
        }
        return new Block(temp);
    }

    public String getColumn(int i) {
        final StringBuffer sb = new StringBuffer();
        for (int r = 0; r < this.height; r++) {
            sb.append(this.matrix[r][i]);
        }
        return sb.toString();
    }

    /**
     * Returns new {@link BlockIF} instance by input parameters
     *
     * @param height - initial input block height
     * @param width  - initial input bloock width
     * @param styles - initial input array of style points {@link StyleIF}
     * @return new {@link BlockIF} instance
     */
    public static BlockIF create(final int height, final int width, final StyleIF[][] styles) {
        return Block.builder()
            .height(height)
            .width(width)
            .matrix(styles)
            .build();
    }
}
