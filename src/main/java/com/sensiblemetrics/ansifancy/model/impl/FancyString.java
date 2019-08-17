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
package com.sensiblemetrics.ansifancy.model.impl;

import com.sensiblemetrics.ansifancy.exception.FormatException;
import com.sensiblemetrics.ansifancy.model.iface.MarkerSequence;
import com.sensiblemetrics.ansifancy.model.iface.StyleIF;
import com.sensiblemetrics.ansifancy.stream.FancyOutputStream;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
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
@Slf4j
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
    private final List<Object> positionedArguments = new ArrayList<>();

    /**
     * Default {@link Map} collection of name arguments {@link Object}
     */
    private final Map<CharSequence, Object> namedArguments = new HashMap<>();

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
            this.getNamedArguments().putIfAbsent(argName, object);
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
        ValidationUtils.notNull(args, "Arguments should not be null");
        if (args.length % 2 == 1)
            throw FormatException.throwInvalidNumOfArguments(args.length);
        for (int i = 0; i < args.length; i += 2) {
            this.getNamedArguments().putIfAbsent(String.valueOf(args[i]), args[i + 1]);
        }
        return this;
    }

    private static String readFromFile(final CharSequence sourcePath, final Charset encoding) {
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get(String.valueOf(sourcePath)));
            return new String(encodedBytes, encoding);
        } catch (IOException e) {
            throw FormatException.throwIOError(sourcePath, e);
        }
    }

    private static String readFromFile(final CharSequence strPath) {
        return readFromFile(strPath, StandardCharsets.UTF_8);
    }

    public void setPositionArguments(final Collection<? extends Object> arguments) {
        this.getPositionedArguments().clear();
        Optional.ofNullable(arguments)
            .orElseGet(Collections::emptyList)
            .forEach(this::addPositionArgument);
    }

    public void addPositionArgument(final Object argument) {
        this.getPositionedArguments().add(argument);
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

    private CharSequence chew(final CharSequence str) {
        ValidationUtils.notNull(str, "Char sequence should not be null");
        final ByteArrayOutputStream buff = new ByteArrayOutputStream();
        final FancyOutputStream out = new FancyOutputStream(buff);
        try {
            out.write(str.toString().getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("ERROR: cannot write output stream, message={%s}", e.getMessage());
            throw new RuntimeException(e);
        }
        return String.valueOf(buff.toByteArray());
    }

    @Override
    public char charAt(final int index) {
        return getValue().charAt(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return getValue().subSequence(start, end);
    }

    @Override
    public int length() {
        return getValue().length();
    }
}
