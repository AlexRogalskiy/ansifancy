package com.sensiblemetrics.ansifancy.model.shapes.iface;

import java.util.Locale;

/**
 * Formats objects of type T.
 * A Formatter is both a Printer <i>and</i> a Parser for an object type.
 *
 * @param <T> the type of object this Formatter formats
 * @author Keith Donald
 * @since 3.0
 */
public interface Formatter<T> {

    String print(final T distance, final Locale locale);

    T parse(final String text, final Locale locale);
}
