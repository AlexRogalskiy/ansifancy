package com.wildbeeslabs.sensiblemetrics.ansifancy.stream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

/**
 * Fancy print writer which supports automatic ANSI color rendering via {@link FancyWriter}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @author <a href="http://hiramchirino.com">Hiram Chirino</a>
 * @since 1.1
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

    //
    // Need to prevent partial output from being written while formatting or we will get rendering exceptions
    //

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
