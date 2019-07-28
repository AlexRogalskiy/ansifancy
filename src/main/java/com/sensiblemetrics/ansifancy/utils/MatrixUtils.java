package com.sensiblemetrics.ansifancy.utils;

import com.sensiblemetrics.ansifancy.model.iface.AreaIF;
import com.sensiblemetrics.ansifancy.model.iface.MatrixIF;
import com.sensiblemetrics.ansifancy.model.impl.Area;
import com.sensiblemetrics.ansifancy.model.impl.Position;
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

import static com.sensiblemetrics.ansifancy.calculation.OperationFactory.SUBTRACT;

/**
 * Matrix utilities implementation
 */
@Slf4j
@UtilityClass
public class MatrixUtils {

    public static <T> boolean equals(final MatrixIF<T> matrix1, final MatrixIF<T> matrix2, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix1);
        Objects.requireNonNull(matrix1.getRow(0));
        Objects.requireNonNull(matrix2);
        Objects.requireNonNull(matrix2.getRow(0));

        if (matrix1.height() != matrix2.height() || matrix1.width() != matrix2.width()) {
            return false;
        }
        for (int k = 0; k < matrix1.height(); k++) {
            for (int j = 0; j < matrix1.width(); j++) {
                if (Objects.compare(matrix1.get(k, j), matrix2.get(k, j), cmp) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static <T> void updateColumn(final MatrixIF<T> matrix, int col, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        checkBound(col, 0, matrix.width() - 1);
        for (int i = 0; i < matrix.height(); i++) {
            matrix.set(i, col, value);
        }
    }

    public static <T> void updateRow(final MatrixIF<T> matrix, int row, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(row));

        checkBound(row, 0, matrix.height() - 1);
        for (int j = 0; j < matrix.getRow(row).length; j++) {
            matrix.set(row, j, value);
        }
    }

    public static <T> void rotate(final MatrixIF<T> matrix, int size) {
        ValidationUtils.notNull(matrix, "Matrix should not be null");
        ValidationUtils.notNull(matrix.getRow(0), "Matrix row should not be null");

        ValidationUtils.isTrue(matrix.height() == matrix.width(), "Should be a square matrix");
        checkBound(size, 0, matrix.height() - 1);

        for (int layer = 0; layer < size / 2; layer++) {
            int first = layer;
            int last = size - layer - 1;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                T top = matrix.get(first, i);
                matrix.set(first, i, matrix.get(last - offset, first));
                matrix.set(last - offset, first, matrix.get(last, last - offset));
                matrix.set(last, last - offset, matrix.get(i, last));
                matrix.set(i, last, top);
            }
        }
    }

    public static <T> void replaceBy(final MatrixIF<T> matrix, final T value, final T defaultValue) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        boolean rowHasZero = false, colHasZero = false;
        for (final T row : matrix.getRow(0)) {
            if (Objects.equals(row, value)) {
                rowHasZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.height(); i++) {
            if (Objects.equals(matrix.get(i, 0), value)) {
                colHasZero = true;
                break;
            }
        }
        for (int i = 1; i < matrix.height(); i++) {
            for (int j = 1; j < matrix.width(); j++) {
                if (Objects.equals(matrix.get(i, j), value)) {
                    matrix.set(i, 0, defaultValue);
                    matrix.set(0, j, defaultValue);
                }
            }
        }
        for (int i = 1; i < matrix.height(); i++) {
            if (Objects.equals(matrix.get(i, 0), defaultValue)) {
                updateRow(matrix, i, defaultValue);
            }
        }
        for (int j = 1; j < matrix.width(); j++) {
            if (Objects.equals(matrix.get(0, j), defaultValue)) {
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

    public static <T> void shuffle(final MatrixIF<T> matrix) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        int nRows = matrix.height();
        int nColumns = matrix.width();
        int num = nRows * nColumns;
        for (int i = 0; i < num; i++) {
            int j = i + RandomUtils.nextInt(0, num - i);
            if (i != j) {
                int row1 = i / nColumns;
                int column1 = (i - row1 * nColumns) % nColumns;
                T cell1 = matrix.get(row1, column1);

                int row2 = j / nColumns;
                int column2 = (j - row2 * nColumns) % nColumns;
                T cell2 = matrix.get(row2, column2);

                matrix.set(row1, column1, cell2);
                matrix.set(row2, column2, cell1);
            }
        }
    }

    public static <T> boolean exists(final MatrixIF<T> matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        int row = 0;
        int col = matrix.width() - 1;
        while (row < matrix.height() && col >= 0) {
            if (Objects.compare(matrix.get(row, col), value, cmp) == 0) {
                return true;
            } else if (Objects.compare(matrix.get(row, col), value, cmp) > 0) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static <T> Position find(final MatrixIF<T> matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        final Position origin = Position.create(0, 0);
        final Position dest = Position.create(matrix.height() - 1, matrix.width() - 1);
        return find(matrix, origin, dest, value, cmp);
    }

    private static <T> Position find(final MatrixIF<T> matrix, final Position origin, final Position dest, final T value, final Comparator<? super T> cmp) {
        if (!origin.inBounds(matrix) || !dest.inBounds(matrix)) {
            return null;
        }
        if (Objects.compare(matrix.get(origin.getRow().getValue(), origin.getColumn().getValue()), value, cmp) == 0) {
            return origin;
        } else if (!origin.isBefore(dest)) {
            return null;
        }

        final Position start = origin.clone();
        int diagDist = Math.min(SUBTRACT.apply(dest.getRow(), origin.getRow()), SUBTRACT.apply(dest.getColumn(), origin.getColumn()));
        final Position end = Position.create(start.getRow().getValue() + diagDist, start.getColumn().getValue() + diagDist);

        while (start.isBefore(end)) {
            Position p = Position.middle(start, end);
            if (Objects.compare(value, matrix.get(p.getRow().getValue(), p.getColumn().getValue()), cmp) > 0) {
                start.getRow().setValue(p.getRow().getValue() + 1);
                start.getColumn().setValue(p.getColumn().getValue() + 1);
            } else {
                end.getRow().setValue(p.getRow().getValue() - 1);
                end.getColumn().setValue(p.getColumn().getValue() - 1);
            }
        }
        return partitionAndSearch(matrix, origin, dest, start, value, cmp);
    }

    private static <T> Position partitionAndSearch(final MatrixIF<T> matrix, final Position origin, final Position dest, final Position pivot, final T value, final Comparator<? super T> cmp) {
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

    public static <T> Position search(final MatrixIF<T> matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        int l = matrix.height() - 1;
        int k = 0;
        while (l >= 0 && k <= matrix.width() - 1) {
            if (Objects.compare(matrix.get(l, k), value, cmp) < 0) {
                k++;
            } else if (Objects.compare(matrix.get(l, k), value, cmp) > 0) {
                l--;
            } else {
                break;
            }
        }
        return (Objects.compare(matrix.get(l, k), value, cmp) == 0) ? Position.create(l, k) : null;
    }

    public static int[][] randomMatrix(int rows, int columns, int min, int max) {
        ValidationUtils.isTrue(rows > 0, "Should be greater than or equal zero");
        ValidationUtils.isTrue(columns > 0, "Should be greater than or equal zero");

        final int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = RandomUtils.nextInt(min, max);
            }
        }
        return matrix;
    }

    public static <T extends Serializable> T[][] fillMatrix(int rows, int columns, final Class<? extends T> clazz, final T defaultValue) {
        ValidationUtils.isTrue(rows > 0, "Should be greater than or equal zero");
        ValidationUtils.isTrue(columns > 0, "Should be greater than or equal zero");

        final T[][] matrix = newMatrix(clazz, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = SerializationUtils.clone(defaultValue);
            }
        }
        return matrix;
    }

    public static <T> boolean fill(final MatrixIF<T> matrix, int row, int column, final T value) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        if (Objects.equals(value, matrix.get(row, column))) {
            return false;
        }
        return fill(matrix, row, column, matrix.get(row, column), value);
    }

    private static <T> boolean fill(final MatrixIF<T> matrix, int row, int column, final T oldValue, final T newValue) {
        checkBound(row, 0, matrix.height() - 1);
        checkBound(column, 0, matrix.width() - 1);

        if (Objects.equals(oldValue, matrix.get(row, column))) {
            matrix.set(row, column, newValue);
            fill(matrix, row - 1, column, oldValue, newValue);
            fill(matrix, row + 1, column, oldValue, newValue);
            fill(matrix, row, column - 1, oldValue, newValue);
            fill(matrix, row, column + 1, oldValue, newValue);
        }
        return true;
    }

    public static <T> AreaIF findSquare(final MatrixIF<T> matrix, final T value, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        for (int i = matrix.height(); i >= 1; i--) {
            final AreaIF square = findSquareWithSize(matrix, value, i, cmp);
            if (Objects.nonNull(square)) {
                return square;
            }
        }
        return null;
    }

    private static <T> AreaIF findSquareWithSize(final MatrixIF<T> matrix, final T value, int squareSize, final Comparator<? super T> cmp) {
        int count = matrix.height() - squareSize + 1;
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(matrix, value, row, col, squareSize, cmp)) {
                    return Area.create(row, col, squareSize);
                }
            }
        }
        return null;
    }

    private static <T> boolean isSquare(final MatrixIF<T> matrix, final T value, int row, int col, int size, final Comparator<? super T> cmp) {
        for (int j = 0; j < size; j++) {
            if (Objects.compare(matrix.get(row, col + j), value, cmp) == 0) {
                return false;
            }
            if (Objects.compare(matrix.get(row + size - 1, col + j), value, cmp) == 0) {
                return false;
            }
        }
        for (int i = 1; i < size - 1; i++) {
            if (Objects.compare(matrix.get(row + i, col), value, cmp) == 0) {
                return false;
            }
            if (Objects.compare(matrix.get(row + i, col + size - 1), value, cmp) == 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean checkDiagonal(final MatrixIF<T> matrix, boolean isMainDiagonal, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        int row = 0, column = isMainDiagonal ? 0 : matrix.width() - 1;
        int direction = isMainDiagonal ? 1 : -1;
        final T first = matrix.get(0, column);
        for (int i = 0; i < matrix.height(); i++) {
            if (Objects.compare(matrix.get(i, column), first, cmp) != 0) {
                return false;
            }
            column += direction;
        }
        return true;
    }

    public static <T> List<Integer> computeAreaSize(final MatrixIF<T> matrix, final T emptyValue, final Comparator<? super T> cmp) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(matrix.getRow(0));

        boolean[][] visited = new boolean[matrix.height()][matrix.width()];
        final List<Integer> areaSize = new ArrayList<>();
        for (int r = 0; r < matrix.height(); r++) {
            for (int c = 0; c < matrix.width(); c++) {
                int size = computeArea(matrix, visited, r, c, emptyValue, cmp);
                if (size > 0) {
                    areaSize.add(size);
                }
            }
        }
        return areaSize;
    }

    private static <T> int computeArea(final MatrixIF<T> matrix, boolean[][] visited, int row, int col, final T emptyValue, final Comparator<? super T> cmp) {
        if (row < 0 || col < 0 || row >= matrix.height() || col >= matrix.width() || visited[row][col] || Objects.compare(matrix.get(row, col), emptyValue, cmp) == 0) {
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
        ValidationUtils.isTrue(size >= 0, "Should be greater than or equal zero");
        return type.cast(Array.newInstance(type.getComponentType(), size));
//        return (T[]) Array.newInstance(type, size);
    }

    public static <T> T[][] newMatrix(@NonNull final Class<? extends T> type, int rows, int columns) {
        ValidationUtils.isTrue(rows > 0, "Should be greater than or equal zero");
        ValidationUtils.isTrue(columns > 0, "Should be greater than or equal zero");

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
        ValidationUtils.isTrue(index >= lowerBound && index <= upperBound, String.format("Should be in range [{%s},{%s}]", lowerBound, upperBound));
    }
}
