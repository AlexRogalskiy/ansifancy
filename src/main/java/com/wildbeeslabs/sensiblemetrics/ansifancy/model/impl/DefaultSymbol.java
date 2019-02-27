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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MetaData;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Style;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Symbol;
import lombok.*;

import java.util.List;

/**
 * Default symbol implementation {@link Symbol}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class DefaultSymbol implements Symbol {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -8851145060884769651L;

    private MetaData metaData;
    private Point point;
    private List<Style> styles;

    @Override
    public MetaData getMetaData() {
        return null;
    }

    @Override
    public <P extends Point> P getPoint() {
        return null;
    }

    @Override
    public <S extends Iterable<? extends Style>> S getStyles() {
        return null;
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
