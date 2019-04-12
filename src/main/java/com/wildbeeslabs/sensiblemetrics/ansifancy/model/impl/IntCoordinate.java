package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Default coordinate implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@Data
@EqualsAndHashCode
@ToString
public class IntCoordinate {

    /**
     * Default coordinate value
     */
    int value;
}
