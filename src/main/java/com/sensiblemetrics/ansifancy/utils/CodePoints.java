package com.sensiblemetrics.ansifancy.utils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * Maps ordinal values to corresponding Unicode code points in a
 * {@link java.nio.charset.Charset}.
 */
public class CodePoints {
    private static final Map<Charset, CodePoints> ENCODABLES = new HashMap<>();

    private final List<CodePointRange> ranges;

    public CodePoints() {
        this.ranges = new ArrayList<>();
    }

    /**
     * @param index index to look up
     * @return this code point set's {@code index}'th code point
     * @throws IndexOutOfBoundsException if there is no such code point
     */
    public int at(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("illegal negative index: " + index);

        int min = 0;
        int max = ranges.size() - 1;

        while (min <= max) {
            int midpoint = min + ((max - min) / 2);
            CodePointRange current = ranges.get(midpoint);

            if (index >= current.previousCount && index < current.previousCount + current.size())
                return current.low + index - current.previousCount;
            else if (index < current.previousCount)
                max = midpoint - 1;
            else
                min = midpoint + 1;
        }
        throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    /**
     * @return how many code points are in this code point set
     */
    public int size() {
        if (ranges.isEmpty())
            return 0;

        CodePointRange last = ranges.get(ranges.size() - 1);
        return last.previousCount + last.size();
    }

    /**
     * @param codePoint a code point
     * @return whether this code point set contains the given code point
     */
    public boolean contains(int codePoint) {
        return ranges.stream().anyMatch(r -> r.contains(codePoint));
    }

    /**
     * Gives a set of the code points in the given charset.
     *
     * @param c a charset
     * @return the set of code points in the charset
     */
    public static CodePoints forCharset(Charset c) {
        if (ENCODABLES.containsKey(c))
            return ENCODABLES.get(c);

        final CodePoints points = load(c);
        ENCODABLES.put(c, points);
        return points;
    }

    private static CodePoints load(Charset c) {
        if (!c.canEncode()) {
            throw new IllegalArgumentException("Charset " + c.name() + " does not support encoding");
        }
        return encodableCodePoints(c.newEncoder());
    }

    void add(final CodePointRange range) {
        this.ranges.add(range);
    }

    private static CodePoints encodableCodePoints(final CharsetEncoder encoder) {
        final CodePoints points = new CodePoints();
        int start = 0;
        boolean inRange = false;
        int current = 0;
        int previousCount = 0;
        int[] buffer = new int[1];

        for (; current <= Character.MAX_CODE_POINT; ++current) {
            encoder.reset();
            buffer[0] = current;

            String s = new String(buffer, 0, 1);
            if (encoder.canEncode(s)) {
                if (!inRange) {
                    inRange = true;
                    start = current;
                }
            } else if (inRange) {
                inRange = false;
                CodePointRange range = new CodePointRange(start, current - 1, previousCount);
                points.add(range);
                previousCount += range.size();
            }
        }

        if (inRange)
            points.add(new CodePointRange(start, current - 1, previousCount));

        return points;
    }

    public static class CodePointRange {
        final int low;
        final int high;
        final int previousCount;

        CodePointRange(int low, int high, int previousCount) {
            if (low > high)
                throw new IllegalArgumentException(format("%d > %d", low, high));

            this.low = low;
            this.high = high;
            this.previousCount = previousCount;
        }

        boolean contains(int codePoint) {
            return codePoint >= low && codePoint <= high;
        }

        int size() {
            return high - low + 1;
        }
    }

    public boolean codePointInRange(int codePoint) {
        return codePoint >= 0 && codePoint < Character.MIN_SURROGATE;
    }
}
