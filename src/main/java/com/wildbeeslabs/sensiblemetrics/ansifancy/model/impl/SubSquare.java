package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PositionIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.SubSquareIF;
import lombok.*;

/**
 * Default {@link SubSquareIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class SubSquare implements SubSquareIF {

    private PositionIF position;
    private int size;

    /**
     * Returns new {@link SubSquareIF} instance by input parameters
     *
     * @param row  - initial input row index
     * @param col  - initial input column index
     * @param size - initial input block size
     * @return new {@link SubSquareIF} instance
     */
    public static SubSquareIF create(final int row, final int col, final int size) {
        return SubSquare.builder()
            .position(Position.builder().row(row).column(col).build())
            .size(size)
            .build();
    }
}
