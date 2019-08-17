/*
 * The MIT License
 *
 * Copyright 2019 SensibleMetrics Labs, Inc.
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
package com.sensiblemetrics.ansifancy.parser.iface;

import com.sensiblemetrics.ansifancy.utils.ValidationUtils;

import java.util.Objects;

/**
 * Parser interface declaration
 *
 * @param <T> type of input element to be converted from
 * @param <R> type of input element to be converted to
 * @author Alexander Rogalskiy
 * @version 1.1
 * @since 1.0
 */
@FunctionalInterface
public interface ParserIF<T, R> {

    /**
     * Returns parsed value {@code R} by input argument value {@code T}
     *
     * @param value - initial input argument value {@code T} to parse by
     * @return parsed value {@code R}
     */
    R parse(final T value);

    /**
     * Returns composed {@link ParserIF} function that first applies input {@link ParserIF}
     * function to its value, and then applies current {@link ParserIF} function to the result
     *
     * @param <V>    type of input element to be converted from
     * @param before - initial input {@link ParserIF} function to apply before current {@link ParserIF} function is applied
     * @return composed {@link ParserIF} function that first applies the {@link ParserIF} function and then applies current {@link ParserIF} function
     * @throws NullPointerException if before is null
     */
    default <V> ParserIF<V, R> compose(final ParserIF<? super V, ? extends T> before) {
        ValidationUtils.notNull(before);
        return (V v) -> parse(before.parse(v));
    }

    /**
     * Returns composed {@link ParserIF} function that first applies current {@link {@link ParserIF}} function to
     * its input, and then applies input {@link ParserIF} function to the result
     *
     * @param <V>   type of input element to be converted from
     * @param after - initial input {@link ParserIF} function to apply after current {@link ParserIF} function is applied
     * @return composed {@link ParserIF} function that first applies current {@link ParserIF} function and then
     * applies input {@link ParserIF} function
     * @throws NullPointerException if after is null
     */
    default <V> ParserIF<T, V> andThen(final ParserIF<? super R, ? extends V> after) {
        ValidationUtils.notNull(after);
        return (T t) -> after.parse(parse(t));
    }

    /**
     * Returns {@link ParserIF} function that always returns its value argument
     *
     * @param <T> type of input element to be converted from
     * @return {@link ParserIF} function that always returns its value argument
     */
    static <T> ParserIF<T, T> identity() {
        return t -> t;
    }
}
