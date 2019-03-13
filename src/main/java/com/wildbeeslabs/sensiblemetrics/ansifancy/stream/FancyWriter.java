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
package com.wildbeeslabs.sensiblemetrics.ansifancy.stream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

/**
 * Fancy print writer which supports automatic ANSI color rendering via {@link FancyWriter}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public class FancyWriter extends PrintWriter {

    public FancyWriter(final OutputStream out) {
        super(out);
    }

    public FancyWriter(final OutputStream out, final boolean autoFlush) {
        super(out, autoFlush);
    }

    public FancyWriter(final Writer out) {
        super(out);
    }

    public FancyWriter(final Writer out, final boolean autoFlush) {
        super(out, autoFlush);
    }

    @Override
    public void write(final String s) {
        if (test(s)) {
            super.write(render(s));
        } else {
            super.write(s);
        }
    }

    @Override
    public PrintWriter format(final String format, final Object... args) {
        print(String.format(format, args));
        return this;
    }

    @Override
    public PrintWriter format(final Locale l, final String format, final Object... args) {
        print(String.format(l, format, args));
        return this;
    }
}
