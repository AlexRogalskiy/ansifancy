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
package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.exception.FormatException;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MarkerSequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.StyleIF;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Fancy {@link MarkerSequence} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class FancyString implements MarkerSequence {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -6957211742749459460L;

    /**
     * Default {@link String} value
     */
    private final CharSequence value;

    /**
     * Default {@link List} collection of position arguments {@link Object}
     */
    private final List<Object> positionArguments = new ArrayList<>();

    /**
     * Default {@link Map} collection of name arguments {@link Object}
     */
    private final Map<CharSequence, Object> nameArguments = new HashMap<>();

    /**
     * Default {@link List} collection of styles {@link StyleIF}
     */
    private final List<StyleIF> styles = new ArrayList<>();

    /**
     * Default {@link FancyString} constructor with input {@link String} value
     *
     * @param value - initial input {@link String} value
     */
    public FancyString(final CharSequence value) {
        this.value = value;
    }

    @Override
    public MarkerSequence styles(final StyleIF... styles) {
        this.setStyles(Arrays.asList(Optional.ofNullable(styles).orElse(new Style[0])));
        return this;
    }

    public static MarkerSequence string(final CharSequence value) {
        return new FancyString(value);
    }

    public static MarkerSequence string(final CharSequence value, final Object... args) {
        final FancyString fancyString = new FancyString(value);
        if (Objects.nonNull(args)) {
            fancyString.setPositionArguments(Arrays.asList(args));
        }
        return fancyString;
    }

    public static MarkerSequence string(final CharSequence value, final Map<String, Object> args) {
        return string(value).args(args);
    }

    public static MarkerSequence file(final String strPath) {
        return string(readFromFile(strPath));
    }

    public static MarkerSequence file(final String strPath, final Charset encoding) {
        return string(readFromFile(strPath, encoding));
    }

    public static MarkerSequence file(final String strPath, final Object... args) {
        return string(readFromFile(strPath), args);
    }

    public static MarkerSequence file(final String strPath, final Charset encoding, final Object... args) {
        return string(readFromFile(strPath, encoding), args);
    }

    public static MarkerSequence file(final String strPath, final Map<String, Object> args) {
        return string(readFromFile(strPath), args);
    }

    public static MarkerSequence file(final String strPath, final Charset encoding, final Map<String, Object> args) {
        return string(readFromFile(strPath, encoding), args);
    }

    public MarkerSequence string(final CharSequence argName, final Object object) {
        if (Objects.nonNull(argName)) {
            this.getNameArguments().putIfAbsent(argName, object);
        }
        return this;
    }

    @Override
    public <C extends CharSequence> MarkerSequence args(final Map<C, Object> args) {
        Optional.ofNullable(args)
            .orElseGet(Collections::emptyMap)
            .entrySet()
            .forEach(entry -> string(entry.getKey(), entry.getValue()));
        return this;
    }

    public MarkerSequence args(final Object... args) {
        Objects.requireNonNull(args);
        if (args.length % 2 == 1)
            throw FormatException.invalidNumberOfArguments(args.length);
        for (int i = 0; i < args.length; i += 2) {
            this.getNameArguments().putIfAbsent(String.valueOf(args[i]), args[i + 1]);
        }
        return this;
    }

    private static String readFromFile(final CharSequence sourcePath, final Charset encoding) {
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get(String.valueOf(sourcePath)));
            return new String(encodedBytes, encoding);
        } catch (IOException e) {
            throw FormatException.ioException(sourcePath, e.getMessage());
        }
    }

    private static String readFromFile(final CharSequence strPath) {
        return readFromFile(strPath, StandardCharsets.UTF_8);
    }

    public void setPositionArguments(final Collection<? extends Object> arguments) {
        this.getPositionArguments().clear();
        Optional.ofNullable(arguments)
            .orElseGet(Collections::emptyList)
            .forEach(this::addPositionArgument);
    }

    public void addPositionArgument(final Object argument) {
        this.getPositionArguments().add(argument);
    }

    public void setStyles(final Collection<? extends StyleIF> styles) {
        this.getStyles().clear();
        Optional.ofNullable(styles)
            .orElseGet(Collections::emptyList)
            .forEach(this::addStyle);
    }

    public void addStyle(final StyleIF style) {
        if (Objects.nonNull(style)) {
            this.getStyles().add(style);
        }
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
