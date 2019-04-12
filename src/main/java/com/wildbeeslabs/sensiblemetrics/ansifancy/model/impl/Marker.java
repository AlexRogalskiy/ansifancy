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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.*;
import lombok.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Default {@link MarkerIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Marker implements MarkerIF<IntCoordinate> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -1737463437481603468L;

    /**
     * Default marker position
     */
    private final PositionIF<IntCoordinate> position;
    /**
     * Default symbol meta data
     */
    private final MetaDataIF metaData;
    /**
     * Default collection of styles
     */
    private final Collection<StyleIF> styles;

    /**
     * Returns updated {@link Marker} marker by input array of styles {@link StyleIF}
     *
     * @param styles - initial input array of styles {@link StyleIF}
     * @return updated {@link Marker} marker
     */
    public MarkerIF add(final StyleIF... styles) {
        Arrays.asList(Optional.ofNullable(styles).orElse(new StyleIF[0])).stream().forEach(getStyles()::add);
        return this;
    }

    /**
     * Returns new {@link Marker} instance by input parameters
     *
     * @param position - initial input marker position {@link PositionIF}
     * @param metaData - initial input meta data {@link MetaDataIF}
     * @param styles   - initial array of styles {@link StyleIF}
     * @return new {@link Marker} instance
     */
    public static MarkerIF create(@NonNull final PositionIF<IntCoordinate> position, @NonNull final MetaDataIF metaData, final StyleIF... styles) {
        return Marker.builder()
            .position(position)
            .metaData(metaData)
            .styles(Arrays.asList(Optional.ofNullable(styles).orElse(new StyleIF[0])))
            .build();
    }

    /**
     * Clones current {@link BlockIF}
     *
     * @return new copy of current {@link BlockIF}
     */
    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CloneDoesntCallSuperClone"})
    public MarkerIF clone() {
        return create(this.position, this.metaData, this.styles.toArray(new StyleIF[this.styles.size()]));
    }
}
