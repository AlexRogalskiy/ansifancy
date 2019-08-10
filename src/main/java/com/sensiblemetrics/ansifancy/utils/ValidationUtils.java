/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.ansifancy.utils;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.net.URL;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Validation utilities implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-07
 */
@UtilityClass
public class ValidationUtils {

    /**
     * Ensures that a string passed as a parameter to the calling method is not empty.
     *
     * @param value string value
     * @throws IllegalArgumentException if {@code value} is empty
     */
    public static void checkNotEmptyString(final String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("An empty string passed as parameter");
        }
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not empty.
     *
     * @param value        string value
     * @param errorMessage the exception message to use if the check fails; will be converted to a string using
     *                     {@link String#valueOf(Object)}
     * @throws IllegalArgumentException if {@code value} is empty
     */
    public static void checkNotEmptyString(final String value, final String errorMessage) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * <p>Checks if the value matches the regular expression.</p>
     *
     * @param value  The value validation is being performed on.
     * @param regexp The regular expression.
     * @return true if matches the regular expression.
     */
    public static boolean matchRegexp(final String value, final String regexp) {
        if (org.apache.commons.lang3.StringUtils.isBlank(regexp)) {
            return false;
        }
        return Pattern.matches(regexp, value);
    }

    public static <T extends Comparable<? super T>> Predicate<T> inRange(final T min, final T max) {
        return c -> {
            if (min == null && max == null)
                return true;
            if (min == null)
                return c.compareTo(max) <= 0;
            if (max == null)
                return c.compareTo(min) >= 0;
            return c.compareTo(min) >= 0 && c.compareTo(max) <= 0;
        };
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final byte value, final byte min, final byte max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final int value, final int min, final int max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final float value, final float min, final float max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final short value, final short min, final short max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final long value, final long min, final long max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(final double value, final double min, final double max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param index The value validation is being performed on.
     * @param size  The minimum value of the range.
     * @param desc  The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static int checkElementIndex(final int index, final int size, final String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("ERROR: index = {%s} out of bounds = {%s}, message = {%s}", index, size, desc));
        }
        return index;
    }

    /**
     * <p>Checks if the value's length is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max   The maximum length.
     * @return true if the value's length is less than the specified maximum.
     */
    public static boolean maxLength(final String value, int max) {
        return (value.length() <= max);
    }

    /**
     * Validates that the array contains no null elements
     *
     * @param objects the array to test
     */
    public static void noNullElements(final Object[] objects) {
        noNullElements(objects, "Array must not contain any null objects");
    }

    /**
     * Validates that the array contains no null elements
     *
     * @param objects the array to test
     * @param msg     message to output if validation fails
     */
    public static void noNullElements(final Object[] objects, final String msg) {
        for (final Object obj : objects) {
            if (Objects.isNull(obj)) {
                throw new IllegalArgumentException(msg);
            }
        }
    }

    public static <K, V> void checkEntryNotNull(final K key, final V value) {
        if (Objects.isNull(key)) {
            throw new NullPointerException(String.format("ERROR: null key in entry with value = {%s}", value));
        } else if (Objects.isNull(value)) {
            throw new NullPointerException(String.format("ERROR: null value in entry with key = {%s}", key));
        }
    }

    public static BigInteger checkPositiveOrZero(final BigInteger value, final String name) {
        if (value.signum() < 0) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative but was: {%s}", name, value));
        }
        return value;
    }

    public static double checkPositiveOrZero(double value, final String name) {
        if (!(value >= 0)) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative or zero but was: {%s}", name, value));
        }
        return value;
    }

    public static int checkPositiveOrZero(int value, final String name) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative but was: {%s}", name, value));
        }
        return value;
    }

    public static long checkPositiveOrZero(long value, final String name) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative but was: {%s}", name, value));
        }
        return value;
    }

    public static double checkPositive(double value, final String name) {
        if (!(value > 0)) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative but was: {%s}", name, value));
        }
        return value;
    }

    public static void checkPositive(int value, String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative or zero but was: {%s}", name, value));
        }
    }

    public static BigInteger checkPositive(final BigInteger value, final String name) {
        if (value.signum() <= 0) {
            throw new IllegalArgumentException(String.format("ERROR: {%s} cannot be negative or zero but was: {%s}", name, value));
        }
        return value;
    }

    /**
     * <p>Checks if the value's adjusted length is less than or equal to the max.</p>
     *
     * @param value         The value validation is being performed on.
     * @param max           The maximum length.
     * @param lineEndLength The length to use for line endings.
     * @return true if the value's length is less than the specified maximum.
     */
    public static boolean maxLength(final String value, int max, int lineEndLength) {
        int adjustAmount = adjustForLineEnding(value, lineEndLength);
        return ((value.length() + adjustAmount) <= max);
    }

    /**
     * <p>Checks if the value's length is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum length.
     * @return true if the value's length is more than the specified minimum.
     */
    public static boolean minLength(final String value, int min) {
        return (value.length() >= min);
    }

    /**
     * <p>Checks if the value's adjusted length is greater than or equal to the min.</p>
     *
     * @param value         The value validation is being performed on.
     * @param min           The minimum length.
     * @param lineEndLength The length to use for line endings.
     * @return true if the value's length is more than the specified minimum.
     */
    public static boolean minLength(final String value, int min, int lineEndLength) {
        int adjustAmount = adjustForLineEnding(value, lineEndLength);
        return ((value.length() + adjustAmount) >= min);
    }

    /**
     * Calculate an adjustment amount for line endings.
     * <p>
     * See Bug 37962 for the rational behind this.
     *
     * @param value         The value validation is being performed on.
     * @param lineEndLength The length to use for line endings.
     * @return the adjustment amount.
     */
    private static int adjustForLineEnding(final String value, int lineEndLength) {
        int nCount = 0;
        int rCount = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '\n') {
                nCount++;
            }
            if (value.charAt(i) == '\r') {
                rCount++;
            }
        }
        return ((nCount * lineEndLength) - (rCount + nCount));
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(int value, int min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(final long value, final long min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(final double value, final double min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min   The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(float value, float min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max   The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(final int value, final int max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max   The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(final long value, final long max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max   The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(final double value, final double max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max   The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(final float value, final float max) {
        return (value <= max);
    }

    /**
     * Validates that the object is not null
     *
     * @param obj object to test
     */
    public static void notNull(final Object obj) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException("Object must not be null");
        }
    }

    /**
     * Validates that the object is not null
     *
     * @param obj object to test
     * @param msg message to output if validation fails
     */
    public static void notNull(final Object obj, final String msg) {
        if (Objects.nonNull(obj)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Validates that the value is true
     *
     * @param val object to test
     */
    public static void isTrue(final boolean val) {
        if (!val) {
            throw new IllegalArgumentException("Must be true");
        }
    }

    /**
     * Validates that the value is true
     *
     * @param val object to test
     * @param msg message to output if validation fails
     */
    public static void isTrue(final boolean val, final String msg) {
        if (!val) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Validates that the value is false
     *
     * @param val object to test
     */
    public static void isFalse(final boolean val) {
        if (val) {
            throw new IllegalArgumentException("Must be false");
        }
    }

    /**
     * Validates that the value is false
     *
     * @param val object to test
     * @param msg message to output if validation fails
     */
    public static void isFalse(final boolean val, final String msg) {
        if (val) {
            throw new IllegalArgumentException(msg);
        }
    }


    /**
     * Validates that the string is not empty
     *
     * @param string the string to test
     */
    public static void notEmpty(final String string) {
        if (org.apache.commons.lang3.StringUtils.isBlank(string)) {
            throw new IllegalArgumentException("String must not be empty");
        }
    }

    /**
     * Validates that the string is not empty
     *
     * @param string the string to test
     * @param msg    message to output if validation fails
     */
    public static void notEmpty(final String string, final String msg) {
        if (org.apache.commons.lang3.StringUtils.isBlank(string)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Cause a failure.
     *
     * @param msg message to output.
     */
    public static void fail(final String msg) {
        throw new IllegalArgumentException(msg);
    }

    /**
     * Helper method that requires the value argument to be 0.
     *
     * @param value - initial input value to check
     * @throws IndexOutOfBoundsException
     */
    public static void checkZero(final int value) throws IndexOutOfBoundsException {
        if (value != 0) {
            throw new IllegalArgumentException("ERROR: invalid value={%s}, supported only 0");
        }
    }

    /**
     * Helper method that checks lower/upper bounds
     *
     * @param lowerBound - initial input lower bound
     * @param upperBound - initial input upper bound
     * @throws IllegalStateException if bounds are invalid
     */
    public void checkBounds(final int lowerBound, final int upperBound) throws IllegalStateException {
        if ((lowerBound < 0) || (upperBound < 0 || lowerBound > upperBound)) {
            throw new IllegalArgumentException("ERROR: invalid lower={%s}, upper={%s} bounds");
        }
    }

    public static void checkOffsetAndCount(final long arrayLength, final long offset, final long count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean isValidUrl(final String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidEmail(final String email) {
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(emailRegex);
        if (Objects.isNull(email)) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isNumber(final String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
