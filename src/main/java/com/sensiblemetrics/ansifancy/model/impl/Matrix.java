package com.sensiblemetrics.ansifancy.model.impl;

import com.sensiblemetrics.ansifancy.model.iface.MatrixIF;
import com.sensiblemetrics.ansifancy.model.iface.StyleIF;
import com.sensiblemetrics.ansifancy.utils.MatrixUtils;
import lombok.*;

/**
 * Default {@link MatrixIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Matrix implements MatrixIF<StyleIF> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -4063160670905573268L;

    /**
     * Array of styles {@link StyleIF}
     */
    public final StyleIF[][] matrix;

    /**
     * Default matrix constructor by input height / width parameters
     *
     * @param height - initial input matrix height
     * @param width  - initial input matrix width
     */
    public Matrix(final int height, final int width) {
        this.matrix = new StyleIF[height][width];
    }

    /**
     * Returns {@link StyleIF} by input row / column parameters
     *
     * @param i - initial input row value
     * @param j - initial input column value
     * @return {@link StyleIF}
     */
    @Override
    public StyleIF get(int i, int j) {
        MatrixUtils.checkBound(i, 0, this.height() - 1);
        MatrixUtils.checkBound(j, 0, this.width() - 1);
        return this.matrix[i][j];
    }

    /**
     * Updates {@link StyleIF} by input row / column parameters
     *
     * @param i     - initial input row value
     * @param j     - initial input column value
     * @param style - initial input {@link StyleIF} instance
     */
    @Override
    public void set(int i, int j, final StyleIF style) {
        MatrixUtils.checkBound(i, 0, this.height() - 1);
        MatrixUtils.checkBound(j, 0, this.width() - 1);
        this.matrix[i][j] = style;
    }

    /**
     * Returns copy of {@link StyleIF} by input height / width offset
     *
     * @return copy of {@link StyleIF}
     */
    public Matrix copy() {
        return this.create(this.copy(this.height(), this.width()));
    }

    /**
     * Returns matrix copy of {@link StyleIF} by input height / width offset
     *
     * @param hOffset - initial input height offset
     * @param wOffset - initial input width offset
     * @return matrix copy of {@link StyleIF}
     */
    protected StyleIF[][] copy(int hOffset, int wOffset) {
        MatrixUtils.checkBound(hOffset, 0, this.height());
        MatrixUtils.checkBound(wOffset, 0, this.width());

        final StyleIF temp[][] = new StyleIF[hOffset][wOffset];
        for (int i = 0; i < hOffset; i++) {
            System.arraycopy(this.matrix[i], 0, temp[i], 0, wOffset);
        }
        return temp;
    }

    /**
     * Returns array of {@link StyleIF} by input row index
     *
     * @param i - initial input row index
     * @return array of {@link StyleIF}
     */
    @Override
    public StyleIF[] getRow(int i) {
        MatrixUtils.checkBound(i, 0, this.height());
        return this.matrix[i];
    }

    /**
     * Returns array of {@link StyleIF} by input row index
     *
     * @param j - initial input row index
     * @return array of {@link StyleIF}
     */
    @Override
    public StyleIF[] getColumn(int j) {
        MatrixUtils.checkBound(j, 0, this.width());
        final StyleIF[] result = new StyleIF[this.height()];
        for (int r = 0; r < this.height(); r++) {
            result[r] = this.matrix[r][j];
        }
        return result;
    }

    /**
     * Returns new {@link Matrix} instance by input parameters
     *
     * @param matrix - initial input array of {@link StyleIF}
     * @return new {@link Matrix} instance
     */
    public static Matrix create(@NonNull final StyleIF[][] matrix) {
        return Matrix.builder()
            .matrix(matrix)
            .build();
    }

    /**
     * Returns {@link Matrix} width
     *
     * @return width of current {@link Matrix}
     */
    @Override
    public int width() {
        return this.matrix[0].length;
    }

    /**
     * Returns {@link Matrix} height
     *
     * @return height of current {@link Matrix}
     */
    @Override
    public int height() {
        return this.matrix.length;
    }
}
