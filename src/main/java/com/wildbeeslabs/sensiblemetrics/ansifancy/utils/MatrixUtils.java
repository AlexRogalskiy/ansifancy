package com.wildbeeslabs.sensiblemetrics.ansifancy.utils;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.Area;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.Position;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Matrix utilities implementation
 */
@Slf4j
@UtilityClass
public class MatrixUtils {

    public static <T> boolean equals(final T[][] m1, final T[][] m2, final Comparator<? super T> cmp) {
        Objects.requireNonNull(m1);
        Objects.requireNonNull(m1[0]);
        Objects.requireNonNull(m2);
        Objects.requireNonNull(m2[0]);

        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            return false;
        }
        for (int k = 0; k < m1.length; k++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (Objects.compare(m1[k][j], m2[k][j], cmp) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static <T> void updateColumn(final T[][] matrix, int col, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        checkBound(col, 0, matrix[0].length - 1);
        for (final T[] row : matrix) {
            row[col] = value;
        }
    }

    public static <T> void updateRow(final T[][] matrix, int row, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[row]);

        checkBound(row, 0, matrix.length - 1);
        for (int j = 0; j < matrix[row].length; j++) {
            matrix[row][j] = value;
        }
    }

    public static <T> void rotate(final T[][] matrix, int size) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        assert matrix.length == matrix[0].length : "Should be a square matrix";
        checkBound(size, 0, matrix.length - 1);

        for (int layer = 0; layer < size / 2; layer++) {
            int first = layer;
            int last = size - layer - 1;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                T top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }

    public static <T> void replaceBy(final T[][] matrix, final T value, final T defaultValue) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        boolean rowHasZero = false, colHasZero = false;
        for (final T row : matrix[0]) {
            if (Objects.equals(row, value)) {
                rowHasZero = true;
                break;
            }
        }
        for (final T[] col : matrix) {
            if (Objects.equals(col[0], value)) {
                colHasZero = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (Objects.equals(matrix[i][j], value)) {
                    matrix[i][0] = defaultValue;
                    matrix[0][j] = defaultValue;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (Objects.equals(matrix[i][0], defaultValue)) {
                updateRow(matrix, i, defaultValue);
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (Objects.equals(matrix[0][j], defaultValue)) {
                updateColumn(matrix, j, defaultValue);
            }
        }
        if (rowHasZero) {
            updateRow(matrix, 0, defaultValue);
        }
        if (colHasZero) {
            updateColumn(matrix, 0, defaultValue);
        }
    }

    public static <T> void shuffle(final T[][] matrix) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        int nRows = matrix.length;
        int nColumns = matrix[0].length;
        int num = nRows * nColumns;
        for (int i = 0; i < num; i++) {
            int j = i + RandomUtils.nextInt(0, num - i);
            if (i != j) {
                int row1 = i / nColumns;
                int column1 = (i - row1 * nColumns) % nColumns;
                T cell1 = matrix[row1][column1];

                int row2 = j / nColumns;
                int column2 = (j - row2 * nColumns) % nColumns;
                T cell2 = matrix[row2][column2];

                matrix[row1][column1] = cell2;
                matrix[row2][column2] = cell1;
            }
        }
    }

    public static <T> boolean exists(final T[][] matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (Objects.compare(matrix[row][col], value, cmp) == 0) {
                return true;
            } else if (Objects.compare(matrix[row][col], value, cmp) > 0) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static <T> Position find(final T[][] matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        final Position origin = Position.create(0, 0);
        final Position dest = Position.create(matrix.length - 1, matrix[0].length - 1);
        return find(matrix, origin, dest, value, cmp);
    }

    private static <T> Position find(final T[][] matrix, final Position origin, final Position dest, final T value, final Comparator<? super T> cmp) {
        if (!origin.inBounds(matrix) || !dest.inBounds(matrix)) {
            return null;
        }
        if (Objects.compare(matrix[origin.getRow().getValue()][origin.getColumn().getValue()], value, cmp) == 0) {
            return origin;
        } else if (!origin.isBefore(dest)) {
            return null;
        }

        final Position start = origin.clone();
        int diagDist = Math.min(dest.getRow().getValue() - origin.getRow().getValue(), dest.getColumn().getValue() - origin.getColumn().getValue());
        final Position end = Position.create(start.getRow().getValue() + diagDist, start.getColumn().getValue() + diagDist);
        final Position p = Position.create(0, 0);

        while (start.isBefore(end)) {
            p.average(start, end);
            if (Objects.compare(value, matrix[p.getRow().getValue()][p.getColumn().getValue()], cmp) > 0) {
                start.getRow().setValue(p.getRow().getValue() + 1);
                start.getColumn().setValue(p.getColumn().getValue() + 1);
            } else {
                end.getRow().setValue(p.getRow().getValue() - 1);
                end.getColumn().setValue(p.getColumn().getValue() - 1);
            }
        }
        return partitionAndSearch(matrix, origin, dest, start, value, cmp);
    }

    private static <T> Position partitionAndSearch(final T[][] matrix, final Position origin, final Position dest, final Position pivot, final T value, final Comparator<? super T> cmp) {
        final Position lowerLeftOrigin = Position.create(pivot.getRow().getValue(), origin.getColumn().getValue());
        final Position lowerLeftDest = Position.create(dest.getRow().getValue(), pivot.getColumn().getValue() - 1);
        final Position upperRightOrigin = Position.create(origin.getRow().getValue(), pivot.getColumn().getValue());
        final Position upperRightDest = Position.create(pivot.getRow().getValue() - 1, dest.getColumn().getValue());

        final Position lowerLeft = find(matrix, lowerLeftOrigin, lowerLeftDest, value, cmp);
        if (Objects.isNull(lowerLeft)) {
            return find(matrix, upperRightOrigin, upperRightDest, value, cmp);
        }
        return lowerLeft;
    }

    public static <T> Position search(final T[][] matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        int l = matrix.length - 1;
        int k = 0;
        while (l >= 0 && k <= matrix[0].length - 1) {
            if (Objects.compare(matrix[l][k], value, cmp) < 0) {
                k++;
            } else if (Objects.compare(matrix[l][k], value, cmp) > 0) {
                l--;
            } else {
                break;
            }
        }
        return (Objects.compare(matrix[l][k], value, cmp) == 0) ? Position.create(l, k) : null;
    }

    public static int[][] randomMatrix(int rows, int columns, int min, int max) {
        assert rows > 0 : "Should be greater than or equal zero";
        assert columns > 0 : "Should be greater than or equal zero";

        final int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = RandomUtils.nextInt(min, max);
            }
        }
        return matrix;
    }

    public static <T extends Serializable> T[][] fillMatrix(int rows, int columns, final Class<? extends T> clazz, final T defaultValue) {
        assert rows > 0 : "Should be greater than or equal zero";
        assert columns > 0 : "Should be greater than or equal zero";

        final T[][] matrix = newMatrix(clazz, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = SerializationUtils.clone(defaultValue);
            }
        }
        return matrix;
    }

    public static <T> boolean fill(final T[][] matrix, int row, int column, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        if (Objects.equals(value, matrix[row][column])) {
            return false;
        }
        return fill(matrix, row, column, matrix[row][column], value);
    }

    private static <T> boolean fill(final T[][] matrix, int row, int column, final T oldValue, final T newValue) {
        checkBound(row, 0, matrix.length - 1);
        checkBound(column, 0, matrix[0].length - 1);

        if (Objects.equals(oldValue, matrix[row][column])) {
            matrix[row][column] = newValue;
            fill(matrix, row - 1, column, oldValue, newValue);
            fill(matrix, row + 1, column, oldValue, newValue);
            fill(matrix, row, column - 1, oldValue, newValue);
            fill(matrix, row, column + 1, oldValue, newValue);
        }
        return true;
    }

    public static <T> AreaIF findSquare(final T[][] matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        for (int i = matrix.length; i >= 1; i--) {
            final AreaIF square = findSquareWithSize(matrix, value, i, cmp);
            if (Objects.nonNull(square)) {
                return square;
            }
        }
        return null;
    }

    private static <T> AreaIF findSquareWithSize(final T[][] matrix, final T value, int squareSize, final Comparator<? super T> cmp) {
        int count = matrix.length - squareSize + 1;
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(matrix, value, row, col, squareSize, cmp)) {
                    return Area.create(row, col, squareSize);
                }
            }
        }
        return null;
    }

    private static <T> boolean isSquare(final T[][] matrix, final T value, int row, int col, int size, final Comparator<? super T> cmp) {
        for (int j = 0; j < size; j++) {
            if (Objects.compare(matrix[row][col + j], value, cmp) == 0) {
                return false;
            }
            if (Objects.compare(matrix[row + size - 1][col + j], value, cmp) == 0) {
                return false;
            }
        }
        for (int i = 1; i < size - 1; i++) {
            if (Objects.compare(matrix[row + i][col], value, cmp) == 0) {
                return false;
            }
            if (Objects.compare(matrix[row + i][col + size - 1], value, cmp) == 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean checkDiagonal(final T[][] matrix, boolean isMainDiagonal, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        int row = 0, column = isMainDiagonal ? 0 : matrix[0].length - 1;
        int direction = isMainDiagonal ? 1 : -1;
        final T first = matrix[0][column];
        for (final T[] ignored : matrix) {
            if (Objects.compare(matrix[row][column], first, cmp) != 0) {
                return false;
            }
            row++;
            column += direction;
        }
        return true;
    }

    public static <T> List<Integer> computeAreaSize(final T[][] matrix, final T emptyValue, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix[0]);

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        final List<Integer> areaSize = new ArrayList<>();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int size = computeArea(matrix, visited, r, c, emptyValue, cmp);
                if (size > 0) {
                    areaSize.add(size);
                }
            }
        }
        return areaSize;
    }

    private static <T> int computeArea(final T[][] matrix, boolean[][] visited, int row, int col, final T emptyValue, final Comparator<? super T> cmp) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || visited[row][col] || Objects.compare(matrix[row][col], emptyValue, cmp) == 0) {
            return 0;
        }
        int size = 1;
        visited[row][col] = true;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                size += computeArea(matrix, visited, row + dr, col + dc, emptyValue, cmp);
            }
        }
        return size;
    }

    public static <T> T[] newArray(@NonNull final Class<? extends T[]> type, int size) {
        assert size >= 0 : "Should be greater than or equal zero";
        return type.cast(Array.newInstance(type.getComponentType(), size));
//        return (T[]) Array.newInstance(type, size);
    }

    public static <T> T[][] newMatrix(@NonNull final Class<? extends T> type, int rows, int columns) {
        assert rows > 0 : "Should be greater than or equal zero";
        assert columns > 0 : "Should be greater than or equal zero";

        return (T[][]) Array.newInstance(type, rows, columns);
    }

    public static <T> T getInstance(@NonNull final Class<? extends T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            log.error("ERROR: cannot initialize class instance={}, message={}", clazz, ex.getMessage());
        } catch (NoSuchMethodException ex) {
            log.error("ERROR: cannot execute method of class instance={}, message={}", clazz, ex.getMessage());
        } catch (InvocationTargetException ex) {
            log.error("ERROR: cannot get class instance={}, message={}", clazz, ex.getMessage());
        }
        return null;
    }

    /**
     * Checks index bounds by lower / upper bounds
     *
     * @param index      - initial input index to check by
     * @param lowerBound - initial input lower bound
     * @param upperBound - initial input upper bound
     */
    public static void checkBound(int index, int lowerBound, int upperBound) {
        assert (index >= lowerBound && index <= upperBound) : String.format("Should be in range [{%s},{%s}]", lowerBound, upperBound);
    }
}
