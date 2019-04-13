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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.MatrixIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.*;

import java.util.Objects;

import static com.wildbeeslabs.sensiblemetrics.ansifancy.operation.OperationFactory.*;
import static com.wildbeeslabs.sensiblemetrics.ansifancy.utils.NumberUtils.toInt;

/**
 * Default {@link PositionIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Position implements PositionIF<IntCoordinate> {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 4745754960240147629L;

    /**
     * Default row position
     */
    private IntCoordinate row;
    /**
     * Default column position
     */
    private IntCoordinate column;
    /**
     * Default depth position
     */
    private IntCoordinate depth;

    /**
     * Updates {@link PositionIF} coordinates by input parametes {@link IntCoordinate}
     *
     * @param row    - initial input row {@link IntCoordinate}
     * @param column - initial input column {@link IntCoordinate}
     * @param depth  - initial input depth {@link IntCoordinate}
     */
    public void setCoordinates(final IntCoordinate row, final IntCoordinate column, final IntCoordinate depth) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }

    /**
     * Returns updated {@link PositionIF} instance by input position scale parameters division
     *
     * @param rowScale - initial input row scale
     * @param colScale - initial input column scale
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T divide(int rowScale, int colScale) {
        if (0 == rowScale || 0 == colScale) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, rowOffset={%s}, colOffset={%s}", rowScale, colScale));
        }
        getRow().setValue(getRow().getValue() / rowScale);
        getColumn().setValue(getColumn().getValue() / colScale);
        return (T) this;
    }

    /**
     * Returns updated {@link PositionIF} instance by input position scale parameters multiplication
     *
     * @param rowScale - initial input row scale
     * @param colScale - initial input column scale
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T multiply(int rowScale, int colScale) {
        getRow().setValue(getRow().getValue() * rowScale);
        getColumn().setValue(getColumn().getValue() * colScale);
        return (T) this;
    }

    /**
     * Returns updated {@link PositionIF} instance by input position offset parameters shift
     *
     * @param rowOffset - initial input row offset
     * @param colOffset - initial input column offset
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T shift(int rowOffset, int colOffset) {
        getRow().setValue(getRow().getValue() + rowOffset);
        getColumn().setValue(getColumn().getValue() + colOffset);
        return (T) this;
    }

    /**
     * Returns updated {@link PositionIF} instance by current position parameters inversion
     *
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T negate() {
        getRow().setValue(-getRow().getValue());
        getColumn().setValue(-getColumn().getValue());
        return (T) this;
    }

    /**
     * Returns {@link PositionIF} vector length by current position parameters
     *
     * @return position vector length
     */
    public double length() {
        return Math.sqrt(MULTIPLY.apply(getRow(), getRow()) + MULTIPLY.apply(getColumn(), getColumn()));
    }

    /**
     * Returns updated {@link PositionIF} instance by normalizing current position parameters
     *
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T normalize() {
        double length = this.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        getRow().setValue(toInt(getRow().getValue() / length));
        getColumn().setValue(toInt(getColumn().getValue() / length));
        return (T) this;
    }

    /**
     * Returns updated {@link PositionIF} instance by current position parameters rotation on input angle
     *
     * @param angle - initial input angle to rotate by
     * @return updated {@link PositionIF} instance
     */
    public <T extends Position> T rotate(double angle) {
        double length = this.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        double phi = Math.acos(getDepth().getValue() / length);
        double theta = (getColumn().getValue() > 0)
            ? Math.atan2(getRow().getValue(), getColumn().getValue())
            : (getColumn().getValue() < 0)
            ? (Math.PI + Math.atan2(getRow().getValue(), getColumn().getValue()))
            : (getColumn().getValue() == 0 && getRow().getValue() >= 0) ? Math.PI / 2 : 3 * Math.PI / 2;

        int vx = getColumn().getValue(), vy = getRow().getValue(), vz = getDepth().getValue();
        double cosVal = Math.cos(theta), sinVal = Math.sin(theta);
        getColumn().setValue(toInt(vx * cosVal + vy * sinVal));
        getRow().setValue(toInt(-vx * sinVal + vy * cosVal));

        vx = getColumn().getValue();
        cosVal = Math.cos(phi);
        sinVal = Math.sin(phi);
        getColumn().setValue(toInt(vx * cosVal - vz * sinVal));
        getDepth().setValue(toInt(vx * sinVal + vz * cosVal));
        this.rotateX(angle);
        this.rotateZ(theta);
        this.rotateY(phi);
        return (T) this;
    }

    /**
     * Rotates current position on input angle {@code axis-Ox}
     *
     * @param angle - initial input angle to rotate by
     */
    private void rotateX(double angle) {
        int vy = getRow().getValue(), vz = getDepth().getValue();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        getRow().setValue(toInt(vy * cosVal - vz * sinVal));
        getDepth().setValue(toInt(vy * sinVal + vz * cosVal));
    }

    /**
     * Rotates current position on input angle {@code axis-Oy}
     *
     * @param angle - initial input angle value to rotate by
     */
    private void rotateY(double angle) {
        int vx = getColumn().getValue(), vz = getDepth().getValue();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        getColumn().setValue(toInt(vx * cosVal + vz * sinVal));
        getDepth().setValue(toInt(-vx * sinVal + vz * cosVal));
    }

    /**
     * Rotates current position on input angle {@code axis-Oz}
     *
     * @param angle - initial input angle value to rotate by
     */
    private void rotateZ(double angle) {
        int vx = getColumn().getValue(), vy = getRow().getValue();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        getColumn().setValue(toInt(vx * cosVal - vy * sinVal));
        getRow().setValue(toInt(vx * sinVal + vy * cosVal));
    }

    /**
     * Returns new {@link PositionIF} instance of vectors product by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return updated {@link Position} instance
     */
    public <T extends Position> T vector(final T position) {
        Objects.requireNonNull(position, "Position should not be null");
        int vx = MULTIPLY.apply(getColumn(), position.getDepth()) - MULTIPLY.apply(getDepth(), position.getRow());
        int vy = MULTIPLY.apply(getDepth(), position.getColumn()) - MULTIPLY.apply(getColumn(), position.getDepth());
        int vz = MULTIPLY.apply(getColumn(), position.getRow()) - MULTIPLY.apply(getRow(), position.getColumn());
        return (T) Position.create(vx, vy, vz);
    }

    /**
     * Returns scalar product of position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return updated {@link Position} instance
     */
    public <T extends Position> int scalar(final T position) {
        Objects.requireNonNull(position, "Position should not be null");
        return MULTIPLY.apply(getColumn(), position.getColumn())
            + MULTIPLY.apply(getRow(), position.getRow())
            + MULTIPLY.apply(getDepth(), position.getDepth());
    }

    /**
     * Returns euclid distance of position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return euclid distance
     */
    public <T extends Position> double distance(final T position) {
        Objects.requireNonNull(position, "Position should not be null");
        int res = SUBTRACT.apply(getColumn(), position.getColumn()) * 2
            + SUBTRACT.apply(getRow(), position.getRow()) * 2
            + SUBTRACT.apply(getDepth(), position.getDepth()) * 2;
        return (res > 0 ? Math.sqrt(res) : 0);
    }

    /**
     * Returns angle between position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return angle value
     */
    public <T extends Position> double angle(final T position) {
        Objects.requireNonNull(position, "Position should not be null");
        double length = this.length() * position.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        return Math.acos(this.scalar(position) / length);
    }

    /**
     * Returns new {@link PositionIF} instance by input position offset parameters
     *
     * @param rowOffset - initial input row offset
     * @param colOffset - initial input column offset
     * @return new {@link PositionIF} instance
     */
    public PositionIF<IntCoordinate> offset(int rowOffset, int colOffset) {
        return offset(rowOffset, colOffset, 0);
    }

    /**
     * Returns new {@link PositionIF} instance by input position offset parameters
     *
     * @param rowOffset   - initial input row offset
     * @param colOffset   - initial input column offset
     * @param depthOffset - initial input depth offset
     * @return new {@link PositionIF} instance
     */
    public <T extends Position> T offset(int rowOffset, int colOffset, int depthOffset) {
        return (T) Position.create(this.getRow().getValue() + rowOffset, this.getColumn().getValue() + colOffset, this.getDepth().getValue() + depthOffset);
    }

    /**
     * Sets average {@link Position} coordinate by input min / max positions
     *
     * @param minPosition - initial input minimum {@link Position}
     * @param maxPosition - initial input maximum {@link Position}
     */
    public static <T extends Position> T middle(final T minPosition, final T maxPosition) {
        if (Objects.isNull(minPosition) || Objects.isNull(maxPosition)) return null;
        return (T) create(ADD.apply(minPosition.getRow(), maxPosition.getRow()) / 2,
            ADD.apply(minPosition.getColumn(), maxPosition.getColumn()) / 2,
            ADD.apply(minPosition.getDepth(), maxPosition.getDepth()) / 2);
    }

    /**
     * Swaps {@link Position} coordinates
     *
     * @param <T>   type of position coordinate
     * @param first - initial input {@link Position} to swap by
     * @param last  - initial input {@link Position} to swap with
     */
    public static <T extends Position> void swap(final T first, final T last) {
        final IntCoordinate tempX = first.getColumn(), tempY = first.getRow(), tempZ = first.getDepth();
        first.setCoordinates(last.getRow(), last.getColumn(), last.getDepth());
        last.setCoordinates(tempX, tempY, tempZ);
    }

    /**
     * Returns new {@link PositionIF} instance by input position parameters
     *
     * @param row    - initial input row position
     * @param column - initial input column position
     * @return new {@link PositionIF} instance
     */
    public static Position create(int row, int column) {
        return create(row, column, 0);
    }

    /**
     * Returns new {@link PositionIF} instance by input position parameters
     *
     * @param row    - initial input row position
     * @param column - initial input column position
     * @param depth  - initial input depth position
     * @return new {@link PositionIF} instance
     */
    public static Position create(int row, int column, int depth) {
        return Position.builder()
            .row(IntCoordinate.of(row))
            .column(IntCoordinate.of(column))
            .depth(IntCoordinate.of(depth))
            .build();
    }

    /**
     * Checks current {@link Position} is in bounds of {@code T} input area
     *
     * @param <T>
     * @param area - initial input {@code T} area
     * @return true - if current position within bounds, false - otherwise
     */
    public <T> boolean inBounds(final MatrixIF<T> area) {
        if (Objects.isNull(area) || Objects.isNull(area.getRow(0))) return false;
        return (this.getRow().getValue() >= 0 && this.getColumn().getValue() >= 0
            && this.getRow().getValue() < area.height()
            && this.getColumn().getValue() < area.width());
    }

    /**
     * Checks if current {@link Position} is before input position {@link Position}
     *
     * @param position - initial input {@link Position} to check by
     * @return true - if current position if before input position, false - otherwise
     */
    public boolean isBefore(final PositionIF<IntCoordinate> position) {
        if (Objects.isNull(position)) return false;
        return DEFAULT_COORDINATE_CMP.compare(this.getRow(), position.getRow()) <= 0
            && DEFAULT_COORDINATE_CMP.compare(this.getColumn(), position.getColumn()) <= 0;
    }

    /**
     * Clones current {@link Position}
     *
     * @return new copy of current {@link Position}
     */
    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CloneDoesntCallSuperClone"})
    public Position clone() {
        return Position.builder()
            .row(this.getRow())
            .column(this.getColumn())
            .depth(this.getDepth())
            .build();
    }
}
