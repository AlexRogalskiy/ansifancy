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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Position;
import lombok.*;

import java.util.Objects;

import static com.wildbeeslabs.sensiblemetrics.ansifancy.utils.NumberUtils.toInt;

/**
 * Default position implementation {@link Position}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class DefaultPosition implements Position {

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

    public DefaultPosition division(int rowOffset, int colOffset) {
        if (0 == rowOffset || 0 == colOffset) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, rowOffset={%s}, colOffset={%s}", rowOffset, colOffset));
        }
        setRow(getRow() / rowOffset);
        setColumn(getColumn() / colOffset);
        return this;
    }

    public DefaultPosition multiply(int rowOffset, int colOffset) {
        setRow(getRow() * rowOffset);
        setColumn(getColumn() * colOffset);
        return this;
    }

    public DefaultPosition shift(int rowOffset, int colOffset) {
        setRow(getRow() + rowOffset);
        setColumn(getColumn() + colOffset);
        return this;
    }

    public DefaultPosition negate() {
        setRow(-getRow());
        setColumn(-getColumn());
        return this;
    }

    public double length() {
        return Math.sqrt(getRow() * getRow() + getColumn() * getColumn());
    }

    public DefaultPosition normalize() {
        double length = this.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        setRow(toInt(getRow() / length));
        setColumn(toInt(getColumn() / length));
        return this;
    }

    public DefaultPosition rotate(double angle) {
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

    private void rotateX(double angle) {
        int vy = getRow(), vz = getDepth();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setRow(toInt(vy * cosVal - vz * sinVal));
        setDepth(toInt(vy * sinVal + vz * cosVal));
    }

    private void rotateY(double angle) {
        int vx = getColumn(), vz = getDepth();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setColumn(toInt(vx * cosVal + vz * sinVal));
        setDepth(toInt(-vx * sinVal + vz * cosVal));
    }

    private void rotateZ(double angle) {
        int vx = getColumn(), vy = getRow();
        double cosVal = Math.cos(angle), sinVal = Math.sin(angle);
        setColumn(toInt(vx * cosVal - vy * sinVal));
        setRow(toInt(vx * sinVal + vy * cosVal));
    }

    public DefaultPosition vector(final Position position) {
        Objects.requireNonNull(position, "Position should not be null");
        int vx = getColumn() * position.getDepth() - getDepth() * position.getRow();
        int vy = getDepth() * position.getColumn() - getColumn() * position.getDepth();
        int vz = getColumn() * position.getRow() - getRow() * position.getColumn();
        return new DefaultPosition(vx, vy, vz);
    }

    public int scalar(final Position position) {
        Objects.requireNonNull(position, "Position should not be null");
        return getColumn() * position.getColumn() + getRow() * position.getRow() + getDepth() * position.getDepth();
    }

    public double distace(final Position position) {
        Objects.requireNonNull(position, "Position should not be null");
        int resX = (getColumn() - position.getColumn()) * (getColumn() - position.getColumn());
        int resY = (getRow() - position.getRow()) * (getRow() - position.getRow());
        int resZ = (getDepth() - position.getDepth()) * (getDepth() - position.getDepth());
        int res = resX + resY + resZ;
        return (res > 0 ? Math.sqrt(res) : 0);
    }

    public double angle(final Position position) {
        Objects.requireNonNull(position, "Position should not be null");
        double length = this.length() * position.length();
        if (0 == length) {
            throw new IllegalArgumentException(String.format("ERROR: should not be equal to zero, length={%s}", length));
        }
        return Math.acos(this.scalar(position) / length);
    }

    /**
     * Returns new {@link DefaultPosition} instance by input offset parameters
     *
     * @param rowOffset - initial input row offset
     * @param colOffset - initial input column offset
     * @return new {@link DefaultPosition} instance
     */
    public DefaultPosition offset(int rowOffset, int colOffset) {
        return new DefaultPosition(getRow() + rowOffset, getColumn() + colOffset, getDepth());
    }

    /**
     * Returns new {@link DefaultPosition} instance by input parameters
     *
     * @param row    - initial input row position
     * @param column - initial input column position
     * @return new {@link DefaultPosition} instance
     */
    public static DefaultPosition getPosition(int row, int column) {
        return DefaultPosition.builder()
            .row(row)
            .column(column)
            .build();
    }
}
