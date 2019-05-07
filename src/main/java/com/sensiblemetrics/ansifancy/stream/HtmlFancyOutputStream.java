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
package com.sensiblemetrics.ansifancy.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Html fancy output stream writer with ANSI color rendering support {@link FancyOutputStream}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public class HtmlFancyOutputStream extends FancyOutputStream {

    private final List<String> closingAttributes = new ArrayList<>();
    private boolean concealOn = false;

    @Override
    public void close() throws IOException {
        closeAttributes();
        super.close();
    }

    private static final String[] ANSI_COLOR_MAP = {"black", "red", "green", "yellow", "blue", "magenta", "cyan", "white",};

    private static final byte[] BYTES_QUOT = "&quot;".getBytes();
    private static final byte[] BYTES_AMP = "&amp;".getBytes();
    private static final byte[] BYTES_LT = "&lt;".getBytes();
    private static final byte[] BYTES_GT = "&gt;".getBytes();

    public HtmlFancyOutputStream(final OutputStream os) {
        super(os);
    }

    private void write(final String s) throws IOException {
        this.out.write(s.getBytes());
    }

    private void writeAttribute(final String s) throws IOException {
        write("<" + s + ">");
        this.closingAttributes.add(0, s.split(" ", 2)[0]);
    }

    private void closeAttributes() throws IOException {
        for (String attr : closingAttributes) {
            write("</" + attr + ">");
        }
        this.closingAttributes.clear();
    }

    public void write(int data) throws IOException {
        switch (data) {
            case 34: // "
                this.out.write(BYTES_QUOT);
                break;
            case 38: // &
                this.out.write(BYTES_AMP);
                break;
            case 60: // <
                this.out.write(BYTES_LT);
                break;
            case 62: // >
                this.out.write(BYTES_GT);
                break;
            default:
                super.write(data);
        }
    }

    public void writeLine(byte[] buf, int offset, int len) throws IOException {
        write(buf, offset, len);
        closeAttributes();
    }

    @Override
    protected void processSetAttribute(int attribute) throws IOException {
        switch (attribute) {
            case ATTRIBUTE_CONCEAL_ON:
                write("\u001B[8m");
                concealOn = true;
                break;
            case ATTRIBUTE_INTENSITY_BOLD:
                writeAttribute("b");
                break;
            case ATTRIBUTE_INTENSITY_NORMAL:
                closeAttributes();
                break;
            case ATTRIBUTE_UNDERLINE:
                writeAttribute("u");
                break;
            case ATTRIBUTE_UNDERLINE_OFF:
                closeAttributes();
                break;
            case ATTRIBUTE_NEGATIVE_ON:
                break;
            case ATTRIBUTE_NEGATIVE_OFF:
                break;
            default:
                break;
        }
    }

    @Override
    protected void processAttributeRest() throws IOException {
        if (concealOn) {
            write("\u001B[0m");
            concealOn = false;
        }
        closeAttributes();
    }

    @Override
    protected void processSetForegroundColor(int color, boolean bright) throws IOException {
        writeAttribute("span style=\"color: " + ANSI_COLOR_MAP[color] + ";\"");
    }

    @Override
    protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
        writeAttribute("span style=\"background-color: " + ANSI_COLOR_MAP[color] + ";\"");
    }
}
