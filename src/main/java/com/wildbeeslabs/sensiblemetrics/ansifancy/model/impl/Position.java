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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.*;

import java.util.Objects;

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
public class Position implements PositionIF {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 4745754960240147629L;

    /**
     * Default row position
     */
    private int row;
    /**
     * Default column position
     */
    private int column;
    /**
     * Default depth position
     */
    private int depth;

    /**
     * Returns updated {@link PositionIF} instance by input position scale parameters division
     *
     * @param rowScale - initial input row scale
     * @param colScale - initial input column scale
     * @return updated {@link PositionIF} instance
     */
    public Position divide(int rowScale, int colScale) {
        if (0 == rowScale || 0 == colScale) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, rowOffset={%s}, colOffset={%s}", rowScale, colScale));
        }
        setRow(getRow() / rowScale);
        setColumn(getColumn() / colScale);
        return this;
    }

    /**
     * Returns updated {@link PositionIF} instance by input position scale parameters multiplication
     *
     * @param rowScale - initial input row scale
     * @param colScale - initial input column scale
     * @return updated {@link PositionIF} instance
     */
    public PositionIF multiply(int rowScale, int colScale) {
        setRow(getRow() * rowScale);
        setColumn(getColumn() * colScale);
        return this;
    }

    /**
     * Returns updated {@link PositionIF} instance by input position offset parameters shift
     *
     * @param rowOffset - initial input row offset
     * @param colOffset - initial input column offset
     * @return updated {@link PositionIF} instance
     */
    public PositionIF shift(int rowOffset, int colOffset) {
        setRow(getRow() + rowOffset);
        setColumn(getColumn() + colOffset);
        return this;
    }

    /**
     * Returns updated {@link PositionIF} instance by current position parameters inversion
     *
     * @return updated {@link PositionIF} instance
     */
    public PositionIF negate() {
        setRow(-getRow());
        setColumn(-getColumn());
        return this;
    }

    /**
     * Returns {@link PositionIF} vector length by current position parameters
     *
     * @return position vector length
     */
    public double length() {
        return Math.sqrt(getRow() * getRow() + getColumn() * getColumn());
    }

    /**
     * Returns updated {@link PositionIF} instance by normalizing current position parameters
     *
     * @return updated {@link PositionIF} instance
     */
    public PositionIF normalize() {
        double length = this.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        setRow(toInt(getRow() / length));
        setColumn(toInt(getColumn() / length));
        return this;
    }

    /**
     * Returns updated {@link PositionIF} instance by current position parameters rotation on input angle
     *
     * @param angle - initial input angle to rotate by
     * @return updated {@link PositionIF} instance
     */
    public PositionIF rotate(double angle) {
        double length = this.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        double phi = Math.acos(getDepth() / length);
        double theta = (getColumn() > 0) ? Math.atan2(getRow(), getColumn()) : (getColumn() < 0) ? (Math.PI + Math.atan2(getRow(), getColumn())) : (getColumn() == 0 && getRow() >= 0) ? Math.PI / 2 : 3 * Math.PI / 2;
        //
        int vx = getColumn(), vy = getRow(), vz = getDepth();
        double cosVal = Math.cos(theta), sinVal = Math.sin(theta);
        setColumn(toInt(vx * cosVal + vy * sinVal));
        setRow(toInt(-vx * sinVal + vy * cosVal));
        //
        vx = getColumn();
        cosVal = Math.cos(phi);
        sinVal = Math.sin(phi);
        setColumn(toInt(vx * cosVal - vz * sinVal));
        setDepth(toInt(vx * sinVal + vz * cosVal));
        this.rotateX(angle);
        this.rotateZ(theta);
        this.rotateY(phi);
        return this;
    }

    /**
     * Rotates current position on input angle {@code axis-Ox}
     *
     * @param angle - initial input angle to rotate by
     */
    private void rotateX(double angle) {
        int vy = getRow(), vz = getDepth();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setRow(toInt(vy * cosVal - vz * sinVal));
        setDepth(toInt(vy * sinVal + vz * cosVal));
    }

    /**
     * Rotates current position on input angle {@code axis-Oy}
     *
     * @param angle - initial input angle value to rotate by
     */
    private void rotateY(double angle) {
        int vx = getColumn(), vz = getDepth();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setColumn(toInt(vx * cosVal + vz * sinVal));
        setDepth(toInt(-vx * sinVal + vz * cosVal));
    }

    /**
     * Rotates current position on input angle {@code axis-Oz}
     *
     * @param angle - initial input angle value to rotate by
     */
    private void rotateZ(double angle) {
        int vx = getColumn(), vy = getRow();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setColumn(toInt(vx * cosVal - vy * sinVal));
        setRow(toInt(vx * sinVal + vy * cosVal));
    }

    /**
     * Returns new {@link PositionIF} instance of vectors product by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return updated {@link Position} instance
     */
    public PositionIF vector(final PositionIF position) {
        Objects.requireNonNull(position, "Position should not be null");
        int vx = getColumn() * position.getDepth() - getDepth() * position.getRow();
        int vy = getDepth() * position.getColumn() - getColumn() * position.getDepth();
        int vz = getColumn() * position.getRow() - getRow() * position.getColumn();
        return Position.create(vx, vy, vz);
    }

    /**
     * Returns scalar product of position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return updated {@link Position} instance
     */
    public int scalar(final PositionIF position) {
        Objects.requireNonNull(position, "Position should not be null");
        return getColumn() * position.getColumn() + getRow() * position.getRow() + getDepth() * position.getDepth();
    }

    /**
     * Returns euclid distance of position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return euclid distance
     */
    public double distance(final PositionIF position) {
        Objects.requireNonNull(position, "Position should not be null");
        int resX = (getColumn() - position.getColumn()) * (getColumn() - position.getColumn());
        int resY = (getRow() - position.getRow()) * (getRow() - position.getRow());
        int resZ = (getDepth() - position.getDepth()) * (getDepth() - position.getDepth());
        int res = resX + resY + resZ;
        return (res > 0 ? Math.sqrt(res) : 0);
    }

    /**
     * Returns angle between position vectors by input {@link PositionIF} argument
     *
     * @param position - initial input {@link PositionIF} value to calculate by
     * @return angle value
     */
    public double angle(final PositionIF position) {
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
    public PositionIF offset(int rowOffset, int colOffset) {
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
    public PositionIF offset(int rowOffset, int colOffset, int depthOffset) {
        return Position.create(this.getRow() + rowOffset, this.getColumn() + colOffset, this.getDepth() + depthOffset);
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
            .row(row)
            .column(column)
            .depth(depth)
            .build();
    }

    /**
     * Checks current {@link Position} is in bounds of {@code T} input area
     *
     * @param <T>
     * @param area - initial input {@code T} area
     * @return true - if current position within bounds, false - otherwise
     */
    public <T> boolean inBounds(final T[][] area) {
        if (Objects.isNull(area) || Objects.isNull(area[0])) {
            return false;
        }
        return (this.getRow() >= 0 && this.getColumn() >= 0 && this.getRow() < area.length && this.getColumn() < area[0].length);
    }

    /**
     * Checks if current {@link Position} is before input position {@link Position}
     *
     * @param position - initial input {@link Position} to check by
     * @return true - if current position if before input position, false - otherwise
     */
    public boolean isBefore(final PositionIF position) {
        if (Objects.isNull(position)) {
            return false;
        }
        return (this.getRow() <= position.getRow() && this.getColumn() <= position.getColumn());
    }

    /**
     * Sets average {@link Position} coordinate by input min / max positions
     *
     * @param minPosition - initial input minimum {@link Position}
     * @param maxPosition - initial input maximum {@link Position}
     */
    public void average(final PositionIF minPosition, final PositionIF maxPosition) {
        if (Objects.isNull(minPosition) || Objects.isNull(maxPosition)) {
            return;
        }
        this.setRow((minPosition.getRow() + maxPosition.getRow()) / 2);
        this.setColumn((minPosition.getColumn() + maxPosition.getColumn()) / 2);
        this.setDepth((minPosition.getDepth() + maxPosition.getDepth()) / 2);
    }

    /**
     * Clones current {@link Position}
     *
     * @return new copy of current {@link Position}
     */
    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CloneDoesntCallSuperClone"})
    public Position clone() {
        return Position.create(this.getRow(), this.getColumn(), this.getDepth());
    }
}
