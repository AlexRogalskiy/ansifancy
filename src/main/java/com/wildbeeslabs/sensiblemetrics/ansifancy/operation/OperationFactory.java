package com.wildbeeslabs.sensiblemetrics.ansifancy.operation;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.IntCoordinate;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.Objects;

/**
 * Operation factory implementation
 */
@UtilityClass
public class OperationFactory {

    /**
     * Default {@link MathOperation} functions
     */
    public static final MathOperation<IntCoordinate> ADD = (a, b) -> a.getValue() + b.getValue();
    public static final MathOperation<IntCoordinate> SUBTRACT = (a, b) -> a.getValue() - b.getValue();
    public static final MathOperation<IntCoordinate> MULTIPLY = (a, b) -> a.getValue() * b.getValue();
    public static final MathOperation<IntCoordinate> DIVIDE = (a, b) -> a.getValue() / b.getValue();
    /**
     * Default math {@link Comparator} instance
     */
    public static final Comparator<IntCoordinate> DEFAULT_COORDINATE_CMP = new MathComparator<>();

    /**
     * Default math apply declaration
     *
     * @param <T> type of math operand
     */
    @FunctionalInterface
    public interface MathOperation<T> {

        int apply(final T a, final T b);
    }

    /**
     * Default math {@link Comparator} implementation
     *
     * @param <T> type of comparable value
     */
    public static class MathComparator<T extends Comparable<? super T>> implements Comparator<T> {

        /**
         * Returns numeric result of arguments comparison:
         * "-1" - first argument is greater than the last one
         * "1" - last argument is greater than the first one
         * "0" - arguments are equal
         *
         * @param first - initial first argument
         * @param last  - initial last argument
         * @return numeric result of two entries comparison
         */
        @Override
        public int compare(final T first, final T last) {
            return this.compareTo(first, last);
        }

        /**
         * Returns numeric result of null-safe object arguments {@link T} comparison
         *
         * @param <T>
         * @param first - initial first argument {@link T}
         * @param last  - initial last argument {@link T}
         * @return a negative integer, zero, or a positive integer if the first
         * argument is less than, equal to, or greater than the second
         */
        public <T extends Comparable<? super T>> int compareTo(final T first, final T last) {
            final boolean f1, f2;
            return (f1 = Objects.isNull(first)) ^ (f2 = Objects.isNull(last)) ? f1 ? -1 : 1 : f1 && f2 ? 0 : first.compareTo(last);
        }
    }
}
