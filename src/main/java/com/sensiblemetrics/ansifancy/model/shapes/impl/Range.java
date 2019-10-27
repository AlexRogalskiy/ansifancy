package com.sensiblemetrics.ansifancy.model.shapes.impl;

import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.*;

import java.util.Optional;

/**
 * Simple value object to work with ranges and boundaries.
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Range<T extends Comparable<T>> {

    private final static Range<?> UNBOUNDED = Range.of(Bound.unbounded(), Bound.UNBOUNDED);

    /**
     * The lower bound of the range.
     */
    private final @NonNull Bound<T> lowerBound;

    /**
     * The upper bound of the range.
     */
    private final @NonNull Bound<T> upperBound;

    /**
     * Creates a new {@link Range} with the given lower and upper bound. Treats the given values as inclusive bounds. Use
     * {@link #Range(Comparable, Comparable, boolean, boolean)} to configure different bound behavior.
     *
     * @param lowerBound can be {@literal null} in case upperBound is not {@literal null}.
     * @param upperBound can be {@literal null} in case lowerBound is not {@literal null}.
     * @see Range#of(Bound, Bound)
     */
    public Range(final T lowerBound, final T upperBound) {
        this(lowerBound, upperBound, true, true);
    }

    /**
     * Creates a new {@link Range} with the given lower and upper bound as well as the given inclusive/exclusive
     * semantics.
     *
     * @param lowerBound     can be {@literal null}.
     * @param upperBound     can be {@literal null}.
     * @param lowerInclusive
     * @param upperInclusive
     */
    public Range(final T lowerBound, final T upperBound, final boolean lowerInclusive, final boolean upperInclusive) {
        this.lowerBound = lowerBound == null ? Bound.unbounded()
            : lowerInclusive ? Bound.inclusive(lowerBound) : Bound.exclusive(lowerBound);

        this.upperBound = upperBound == null ? Bound.unbounded()
            : upperInclusive ? Bound.inclusive(upperBound) : Bound.exclusive(upperBound);
    }

    /**
     * Returns an unbounded {@link Range}.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Range<T> unbounded() {
        return (Range<T>) UNBOUNDED;
    }

    /**
     * Create a {@link RangeBuilder} given the lower {@link Bound}.
     *
     * @param lower must not be {@literal null}.
     * @return
     * @since 2.0
     */
    public static <T extends Comparable<T>> RangeBuilder<T> from(final Bound<T> lower) {
        ValidationUtils.notNull(lower, "Lower bound must not be null!");
        return new RangeBuilder<>(lower);
    }

    /**
     * Creates a new {@link Range} with the given lower and upper bound.
     *
     * @param lowerBound must not be {@literal null}.
     * @param upperBound must not be {@literal null}.
     * @since 2.0
     */
    public static <T extends Comparable<T>> Range<T> of(final Bound<T> lowerBound, final Bound<T> upperBound) {
        return new Range<>(lowerBound, upperBound);
    }

    /**
     * @return
     */
    public boolean isLowerInclusive() {
        return this.lowerBound.isInclusive();
    }

    /**
     * @return
     */
    public boolean isUpperInclusive() {
        return this.upperBound.isInclusive();
    }

    /**
     * Returns whether the {@link Range} contains the given value.
     *
     * @param value must not be {@literal null}.
     * @return
     */
    public boolean contains(final T value) {
        ValidationUtils.notNull(value, "Reference value must not be null!");

        boolean greaterThanLowerBound = this.lowerBound.getValue()
            .map(it -> this.lowerBound.isInclusive() ? it.compareTo(value) <= 0 : it.compareTo(value) < 0)
            .orElse(true);

        boolean lessThanUpperBound = this.upperBound.getValue()
            .map(it -> this.upperBound.isInclusive() ? it.compareTo(value) >= 0 : it.compareTo(value) > 0)
            .orElse(true);

        return greaterThanLowerBound && lessThanUpperBound;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s-%s", this.lowerBound.toPrefixString(), this.upperBound.toSuffixString());
    }

    /**
     * Value object representing a boundary. A boundary can either be {@link #unbounded() unbounded},
     * {@link #inclusive(Comparable) including its value} or {@link #exclusive(Comparable) its value}.
     *
     * @author Mark Paluch
     * @soundtrack Mohamed Ragab - Excelsior Sessions (March 2017)
     * @since 2.0
     */
    @Value
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Bound<T extends Comparable<T>> {
        @SuppressWarnings({"rawtypes", "unchecked"}) //
        private static final Bound<?> UNBOUNDED = new Bound(Optional.empty(), true);

        private final Optional<T> value;
        private final boolean inclusive;

        /**
         * Creates an unbounded {@link Bound}.
         */
        @SuppressWarnings("unchecked")
        public static <T extends Comparable<T>> Bound<T> unbounded() {
            return (Bound<T>) UNBOUNDED;
        }

        /**
         * Returns whether this boundary is bounded.
         *
         * @return
         */
        public boolean isBounded() {
            return this.value.isPresent();
        }

        /**
         * Creates a boundary including {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static <T extends Comparable<T>> Bound<T> inclusive(final T value) {
            ValidationUtils.notNull(value, "Value must not be null!");
            return new Bound<>(Optional.of(value), true);
        }

        /**
         * Creates a boundary including {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Integer> inclusive(final int value) {
            return inclusive((Integer) value);
        }

        /**
         * Creates a boundary including {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Long> inclusive(final long value) {
            return inclusive((Long) value);
        }

        /**
         * Creates a boundary including {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Float> inclusive(final float value) {
            return inclusive((Float) value);
        }

        /**
         * Creates a boundary including {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Double> inclusive(final double value) {
            return inclusive((Double) value);
        }

        /**
         * Creates a boundary excluding {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static <T extends Comparable<T>> Bound<T> exclusive(final T value) {
            ValidationUtils.notNull(value, "Value must not be null!");
            return new Bound<>(Optional.of(value), false);
        }

        /**
         * Creates a boundary excluding {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Integer> exclusive(final int value) {
            return exclusive((Integer) value);
        }

        /**
         * Creates a boundary excluding {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Long> exclusive(final long value) {
            return exclusive((Long) value);
        }

        /**
         * Creates a boundary excluding {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Float> exclusive(final float value) {
            return exclusive((Float) value);
        }

        /**
         * Creates a boundary excluding {@code value}.
         *
         * @param value must not be {@literal null}.
         * @return
         */
        public static Bound<Double> exclusive(final double value) {
            return exclusive((Double) value);
        }

        private String toPrefixString() {
            return getValue() //
                .map(Object::toString) //
                .map(it -> isInclusive() ? "[".concat(it) : "(".concat(it)) //
                .orElse("unbounded");
        }

        private String toSuffixString() {
            return getValue() //
                .map(Object::toString) //
                .map(it -> isInclusive() ? it.concat("]") : it.concat(")")) //
                .orElse("unbounded");
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return this.value.map(Object::toString).orElse("unbounded");
        }

    }

    /**
     * Builder for {@link Range} allowing to specify the upper boundary.
     *
     * @author Mark Paluch
     * @soundtrack Aly and Fila - Future Sound Of Egypt 493
     * @since 2.0
     */
    @Getter
    @RequiredArgsConstructor
    public static class RangeBuilder<T extends Comparable<T>> {
        private final Bound<T> lower;

        /**
         * Create a {@link Range} given the upper {@link Bound}.
         *
         * @param upper must not be {@literal null}.
         * @return
         */
        public Range<T> to(final Bound<T> upper) {
            ValidationUtils.notNull(upper, "Upper bound must not be null!");
            return new Range<>(this.lower, upper);
        }
    }
}
