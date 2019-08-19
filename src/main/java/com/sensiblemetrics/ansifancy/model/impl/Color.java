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
package com.sensiblemetrics.ansifancy.model.impl;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Predicate;

import static com.sensiblemetrics.ansifancy.utils.NumberUtils.inRange;

/**
 * Default color implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Color implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -8240773927637633603L;

    /**
     * Default color brightness factor
     */
    private static final double DEFAULT_COLOR_FACTOR = 0.7;

    /**
     * Default color components channel names
     */
    private static final String DEFAULT_COLOR_APLHA_CHANNEL_NAME = "Alpha";
    private static final String DEFAULT_COLOR_RED_CHANNEL_NAME = "Red";
    private static final String DEFAULT_COLOR_GREEN_CHANNEL_NAME = "Green";
    private static final String DEFAULT_COLOR_BLUE_CHANNEL_NAME = "Blue";
    /**
     * Default RGB min color bound
     */
    public static final int DEFAULT_RGB_MIN_BOUND = 0;
    /**
     * Default RGB max color bound
     */
    public static final int DEFAULT_RGB_MAX_BOUND = 0xFF;
    /**
     * Default HSB min color bound
     */
    public static final float DEFAULT_HSB_MIN_BOUND = 0x00;
    /**
     * Default HSB max color bound
     */
    public static final float DEFAULT_HSB_MAX_BOUND = 1.0f;

    /**
     * Default RGB color namespace values
     */
    public final static Color WHITE = of(DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MAX_BOUND);
    public final static Color BLACK = of(DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MIN_BOUND);
    public final static Color PINK = of(DEFAULT_RGB_MAX_BOUND, 175, 175);
    public final static Color ORANGE = of(DEFAULT_RGB_MAX_BOUND, 200, DEFAULT_RGB_MIN_BOUND);
    public final static Color GRAY = of(DEFAULT_RGB_MAX_BOUND / 2, DEFAULT_RGB_MAX_BOUND / 2, DEFAULT_RGB_MAX_BOUND / 2);
    public final static Color LIGHT_GRAY = of(192, 192, 192);
    public final static Color DARK_GRAY = of(64, 64, 64);
    public final static Color RED = of(DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MIN_BOUND);
    public final static Color LIGHT_RED = of(DEFAULT_RGB_MAX_BOUND, 51, DEFAULT_RGB_MIN_BOUND);
    public final static Color YELLOW = of(DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MIN_BOUND);
    public final static Color LIGHT_YELLOW = of(DEFAULT_RGB_MAX_BOUND, 153, 51);
    public final static Color GREEN = of(DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MIN_BOUND);
    public final static Color LIGHT_GREEN = of(51, 204, 51);
    public final static Color MAGENTA = of(204, DEFAULT_RGB_MIN_BOUND, 204);
    public final static Color LIGHT_MAGENTA = of(DEFAULT_RGB_MAX_BOUND, DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MAX_BOUND);
    public final static Color CYAN = of(DEFAULT_RGB_MIN_BOUND, 153, DEFAULT_RGB_MAX_BOUND);
    public final static Color LIGHT_CYAN = of(DEFAULT_RGB_MIN_BOUND, 204, DEFAULT_RGB_MAX_BOUND);
    public final static Color BLUE = of(DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MAX_BOUND);
    public final static Color LIGHT_BLUE = of(26, 140, DEFAULT_RGB_MAX_BOUND);

    /**
     * Default predicate to check RGB color namespace component value
     *
     * @param value - initial input color component to be checked
     * @return true - if color component is in range, false - otherwise
     */
    private static final Predicate<Float> DEFAULT_RGB_COLOR_CHECK = value -> inRange(value, DEFAULT_RGB_MIN_BOUND, DEFAULT_RGB_MAX_BOUND);

    /**
     * Default predicate to check HSB color namespace component value
     *
     * @param value - initial input color component to be checked
     * @return true - if color component is in range, false - otherwise
     */
    private static final Predicate<Float> DEFAULT_HSB_COLOR_CHECK = value -> inRange(value, DEFAULT_HSB_MIN_BOUND, DEFAULT_HSB_MAX_BOUND);

    /**
     * Default transparency color type
     */
    enum TransparencyColorType {
        OPAQUE(1),
        BITMASK(2),
        TRANSLUCENT(3);

        /**
         * Default transparency type value
         */
        private int value;

        /**
         * Default transparency color constructor with input type
         *
         * @param value - initial input transparency color type
         */
        TransparencyColorType(int value) {
            this.value = value;
        }
    }

    /**
     * Default composite color value
     */
    private int value;

    /**
     * Default RGB namespace color value
     */
    private float rgbValues[];

    /**
     * Default HSB namespace color value
     */
    private float hsbValues[];

    /**
     * Default color alpha channel
     */
    private float alpha;

    /**
     * Checks RBG namespace color components supplied for validity
     * Throws an {@link IllegalArgumentException} if the value is out of
     * range.
     *
     * @param red   - initial input RGB red component
     * @param green - initial input RGB green component
     * @param blue  - initial input RGB blue component
     * @param alpha - initial input RGB alpha component
     **/
    public static void testRGBColor(final int red, final int green, final int blue, final int alpha) {
        testColor(red, green, blue, alpha, DEFAULT_RGB_COLOR_CHECK);
    }

    /**
     * Checks HSB namespace color components supplied for validity
     * Throws an <code>IllegalArgumentException</code> if the value is out
     * of range.
     *
     * @param red   - initial input HSB red component
     * @param green - initial input HSB green component
     * @param blue  - initial input HSB blue component
     * @param alpha - initial input HSB alpha component
     **/
    public static void testHSBColor(final float red, final float green, final float blue, final float alpha) {
        testColor(red, green, blue, alpha, DEFAULT_HSB_COLOR_CHECK);
    }

    /**
     * Checks color of input color components supplied for validity
     * Throws an {@link IllegalArgumentException} if the value is out of
     * range.
     *
     * @param red            - initial input red component of the color
     * @param green          - initial input green component of the color
     * @param blue           - initial input blue component of the color
     * @param alpha          - initial input alpha component of the color
     * @param colorPredicate - initial input color predicate {@link Predicate}
     **/
    private static void testColor(final float red, final float green, final float blue, final float alpha, final Predicate<Float> colorPredicate) {
        final StringBuilder errorMessageBuilder = new StringBuilder();
        boolean hasRangeError = testColorComponent(red, DEFAULT_COLOR_APLHA_CHANNEL_NAME, errorMessageBuilder, colorPredicate) &&
            testColorComponent(red, DEFAULT_COLOR_RED_CHANNEL_NAME, errorMessageBuilder, colorPredicate) &&
            testColorComponent(red, DEFAULT_COLOR_GREEN_CHANNEL_NAME, errorMessageBuilder, colorPredicate) &&
            testColorComponent(red, DEFAULT_COLOR_BLUE_CHANNEL_NAME, errorMessageBuilder, colorPredicate);
        if (hasRangeError) {
            throw new IllegalArgumentException(String.format("ERROR: invalid namespace color range: {%s}", errorMessageBuilder.toString()));
        }
    }

    /**
     * Returns binary flag based on input value to check, error message {@link String}, message builder {@link StringBuilder} and color range predicate {@link Predicate}
     *
     * @param value               - initial input value to check
     * @param errorMessage        - initial input error message {@link String}
     * @param errorMessageBuilder - initial input error message builder {@link StringBuilder}
     * @param colorRangePredicate - initial input color range predicate {@link Predicate}
     * @return true - if input value matches predicate, false - otherwise
     */
    private static boolean testColorComponent(final float value, final String errorMessage, final StringBuilder errorMessageBuilder, final Predicate<Float> colorRangePredicate) {
        if (colorRangePredicate.negate().test(value)) {
            errorMessageBuilder.append(errorMessage);
            return true;
        }
        return false;
    }

    /**
     * Default {@link Color} constructor with input RGB color namespace parameters (red, green, and blue values in range (0 - 255) and alpha by default to 255)
     *
     * @param red   - initial input RGB red component
     * @param green - initial input RGB green component
     * @param blue  - initial input RGB blue component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  or <code>b</code> are outside of the range
     *                                  0 to 255, inclusive
     */
    public Color(final int red, final int green, final int blue) {
        this(red, green, blue, DEFAULT_RGB_MAX_BOUND);
    }

    /**
     * Default {@link Color} constructor with input RGB color namespace parameters
     *
     * @param red   - initial input red component
     * @param green - initial input green component
     * @param blue  - initial input blue component
     * @param alpha - initial input alpha component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>,
     *                                  <code>b</code> or <code>a</code> are outside of the range
     *                                  0 to 255, inclusive
     */
    public Color(final int red, final int green, final int blue, final int alpha) {
        this.value = (
            (alpha & DEFAULT_RGB_MAX_BOUND) << 24) |
            ((red & DEFAULT_RGB_MAX_BOUND) << 16) |
            ((green & DEFAULT_RGB_MAX_BOUND) << 8) |
            ((blue & DEFAULT_RGB_MAX_BOUND) << 0);
        this.testRGBColor(red, green, blue, alpha);
    }

    /**
     * Default {@link Color} constructor with input RGB color namespace argument (red channel in bits 16-23, the green channel
     * in bits 8-15, and the blue channel in bits 0-7 and alpha channel (by default))
     *
     * @param rgb - initial input RGB color namespace composite value
     */
    public Color(final int rgb) {
        this.value = 0xFF000000 | rgb;
    }

    /**
     * Default {@link Color} constructor with input RGB color namespace composite argument (red channel in bits 16-23, the green channel
     * in bits 8-15, and the blue channel in bits 0-7 and alpha channel (by default))
     *
     * @param rgb       - initial input RGB color namespace composite value
     * @param withAlpha - <code>true</code> if the alpha bits are valid;
     *                  <code>false</code> otherwise
     */
    public Color(final int rgb, final boolean withAlpha) {
        this.value = (withAlpha) ? rgb : (0xFF000000 | rgb);
    }

    /**
     * Default {@link Color} constructor with input HSB color namespace arguments (with specified red, green, and blue values in range (0.0 - 1.0) and alpha by default to 1.0)
     *
     * @param red   - initial input red component
     * @param green - initial input green component
     * @param blue  - initial input blue component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  or <code>b</code> are outside of the range
     *                                  0.0 to 1.0, inclusive
     */
    public Color(final float red, final float green, final float blue) {
        this((int) (red * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2),
            (int) (green * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2),
            (int) (blue * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2));
        this.testHSBColor(red, green, blue, DEFAULT_HSB_MAX_BOUND);
        this.hsbValues = new float[3];
        this.hsbValues[0] = red;
        this.hsbValues[1] = green;
        this.hsbValues[2] = blue;
        this.alpha = DEFAULT_HSB_MAX_BOUND;
    }

    /**
     * Default {@link Color} constructor with input HSB namespace color arguments (with specified red, green, and blue values in range (0.0 - 1.0) and alpha by default to 1.0)
     *
     * @param red   - initial input red component
     * @param green - initial input green component
     * @param blue  - initial input blue component
     * @param alpha - initial input alpha component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  <code>b</code> or <code>a</code> are outside of the range
     *                                  0.0 to 1.0, inclusive
     */
    public Color(final float red, final float green, final float blue, final float alpha) {
        this((int) (red * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2),
            (int) (green * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2),
            (int) (blue * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2),
            (int) (alpha * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2));
        this.hsbValues = new float[3];
        this.hsbValues[0] = red;
        this.hsbValues[1] = green;
        this.hsbValues[2] = blue;
        this.alpha = alpha;
    }

    /**
     * Returns red component in RGB color namespace
     *
     * @return red component
     */
    public int getRedComponent() {
        return (this.getRGBColor() >> 16) & DEFAULT_RGB_MAX_BOUND;
    }

    /**
     * Returns green component in RGB color namespace
     *
     * @return green component
     */
    public int getGreenComponent() {
        return (this.getRGBColor() >> 8) & DEFAULT_RGB_MAX_BOUND;
    }

    /**
     * Returns blue component in RGB color namespace
     *
     * @return blue component
     */
    public int getBlueComponent() {
        return (this.getRGBColor() >> 0) & DEFAULT_RGB_MAX_BOUND;
    }

    /**
     * Returns alpha component in RGB color namespace
     *
     * @return alpha component
     */
    public int getAlphaComponent() {
        return (this.getRGBColor() >> 24) & DEFAULT_RGB_MAX_BOUND;
    }

    /**
     * Returns composite color in RGB color namespace
     *
     * @return the RGB value of the color in the default sRGB
     * <code>ColorModel</code>.
     */
    public int getRGBColor() {
        return this.value;
    }

    /**
     * Creates new {@link Color} instance that is a brighter version of current {@link Color} instance
     *
     * @return new {@link Color} instance
     */
    public Color brighter() {
        int red = this.getRedComponent();
        int green = this.getGreenComponent();
        int blue = this.getBlueComponent();
        int alpha = this.getAlphaComponent();

        int i = (int) (DEFAULT_HSB_MAX_BOUND / (DEFAULT_HSB_MAX_BOUND - DEFAULT_COLOR_FACTOR));
        if (red == 0 && green == 0 && blue == 0) {
            return new Color(i, i, i, alpha);
        }
        if (red > 0 && red < i) red = i;
        if (green > 0 && green < i) green = i;
        if (blue > 0 && blue < i) blue = i;

        return new Color(
            Math.min((int) (red / DEFAULT_COLOR_FACTOR), DEFAULT_RGB_MAX_BOUND),
            Math.min((int) (green / DEFAULT_COLOR_FACTOR), DEFAULT_RGB_MAX_BOUND),
            Math.min((int) (blue / DEFAULT_COLOR_FACTOR), DEFAULT_RGB_MAX_BOUND),
            alpha);
    }

    /**
     * Creates new {@link Color} instance that is a darker version of current {@link Color} instance
     *
     * @return new {@link Color} instance
     */
    public Color darker() {
        return new Color(
            Math.max((this.getRedComponent() * DEFAULT_RGB_MAX_BOUND), DEFAULT_RGB_MIN_BOUND),
            Math.max((this.getGreenComponent() * DEFAULT_RGB_MAX_BOUND), DEFAULT_RGB_MIN_BOUND),
            Math.max((this.getBlueComponent() * DEFAULT_RGB_MAX_BOUND), DEFAULT_RGB_MIN_BOUND),
            getAlphaComponent());
    }

    public static double[] normalize(final Color color, final int value) {
        final double[] result = new double[3];
        result[0] = color.getRedComponent() / 255.0;
        result[1] = color.getGreenComponent() / 255.0;
        result[2] = color.getBlueComponent() / 255.0;
        return result;
    }

    /**
     * Returns new {@link Color} instance to represent octal and hexadecimal numbers
     *
     * @param namespace - initial input color namespace
     * @return new {@link Color} instance
     * @throws NumberFormatException if the specified string cannot
     *                               be interpreted as a decimal,
     *                               octal, or hexadecimal integer.
     */
    public static Color decode(final String namespace) throws NumberFormatException {
        int i = Integer.decode(namespace).intValue();
        return new Color(
            (i >> 16) & DEFAULT_RGB_MAX_BOUND,
            (i >> 8) & DEFAULT_RGB_MAX_BOUND,
            i & DEFAULT_RGB_MAX_BOUND);
    }

    /**
     * Returns new {@link Color} instance by input color namespace
     *
     * @param namespace - initial input color namespace
     * @return new {@link Color} instance
     */
    public static Color getColor(final String namespace) {
        return getColor(namespace, null);
    }

    /**
     * Returns new {@link Color} instance by input color namespace and default {@link Color} instance
     *
     * @param namespace - initial input color namespace
     * @param value     - initial input {@link Color} value
     * @return new {@link Color} instance
     */
    public static Color getColor(final String namespace, final Color value) {
        final Integer intval = Integer.getInteger(namespace);
        if (Objects.isNull(intval)) return value;
        int i = intval.intValue();
        return new Color(
            (i >> 16) & DEFAULT_RGB_MAX_BOUND,
            (i >> 8) & DEFAULT_RGB_MAX_BOUND,
            i & DEFAULT_RGB_MAX_BOUND);
    }

    /**
     * Returns new {@link Color} instance by input color namespace and default color composite value
     *
     * @param namespace - initial input color namespace
     * @param value     - initial input composite color value
     * @return new {@link Color} instance
     */
    public static Color getColor(final String namespace, final int value) {
        final Integer intval = Integer.getInteger(namespace);
        int i = (Objects.nonNull(intval)) ? intval.intValue() : value;
        return new Color(
            (i >> 16) & DEFAULT_RGB_MAX_BOUND,
            (i >> 8) & DEFAULT_RGB_MAX_BOUND,
            (i >> 0) & DEFAULT_RGB_MAX_BOUND);
    }

    /**
     * Converts from HSB to RGB color namespace by input color components (hue, saturation, brightness)
     *
     * @param hue        - initial input hue component of the color
     * @param saturation - initial input saturation component of the color
     * @param brightness - initial input brightness component of the color
     * @return the RGB value of the color by indicated hue, saturation, and brightness components
     */
    public static int convertFromHSBToRGB(final float hue, final float saturation, final float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
        } else {
            float h = (hue - (float) Math.floor(hue)) * 6.0f;
            float f = h - (float) java.lang.Math.floor(h);
            float p = brightness * (DEFAULT_HSB_MAX_BOUND - saturation);
            float q = brightness * (DEFAULT_HSB_MAX_BOUND - saturation * f);
            float t = brightness * (DEFAULT_HSB_MAX_BOUND - (saturation * (DEFAULT_HSB_MAX_BOUND - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (t * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
                case 1:
                    r = (int) (q * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
                case 2:
                    r = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (t * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
                case 3:
                    r = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (q * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
                case 4:
                    r = (int) (t * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
                case 5:
                    r = (int) (brightness * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    g = (int) (p * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    b = (int) (q * DEFAULT_RGB_MAX_BOUND + DEFAULT_HSB_MAX_BOUND / 2);
                    break;
            }
        }
        return 0xFF000000 | (r << 16) | (g << 8) | (b << 0);
    }

    /**
     * Converts from RGB to HSB color namespace by input color components (red, green, blue)
     *
     * @param red   - initial input red component of the color
     * @param green - initial input green component of the color
     * @param blue  - initial input blue component of the color
     * @return an array of three elements containing the hue, saturation and brightness of the color
     */
    public static float[] convertFromRGBToHSB(final int red, final int green, final int blue) {
        float hue, saturation, brightness;
        float[] hsbvals = new float[3];
        int cmax = (red > green) ? red : green;
        if (blue > cmax) cmax = blue;
        int cmin = (red < green) ? red : green;
        if (blue < cmin) cmin = blue;

        brightness = ((float) cmax) / DEFAULT_RGB_MAX_BOUND;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - red)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - green)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - blue)) / ((float) (cmax - cmin));
            if (red == cmax)
                hue = bluec - greenc;
            else if (green == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + DEFAULT_HSB_MAX_BOUND;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    /**
     * Returns new {@link Color} instance based on specified values of HSB color namespace model
     *
     * @param h - initial input hue component of the color
     * @param s - initial input saturation component of the color
     * @param b - initial input brightness component of the color
     * @return a <code>Color</code> object with the specified hue, saturation, and brightness.
     */
    public static Color getHSBColor(final float h, final float s, final float b) {
        return new Color(convertFromHSBToRGB(h, s, b));
    }

    /**
     * Returns <code>float</code> array containing the color and alpha components of current {@link Color} instance
     *
     * @return <code>float</code> array of color components
     */
    public float[] getRGBAComponents() {
        final float[] f = new float[4];
        if (Objects.isNull(this.rgbValues)) {
            f[0] = ((float) this.getRedComponent()) / DEFAULT_RGB_MAX_BOUND;
            f[1] = ((float) this.getGreenComponent()) / DEFAULT_RGB_MAX_BOUND;
            f[2] = ((float) this.getBlueComponent()) / DEFAULT_RGB_MAX_BOUND;
            f[3] = ((float) this.getAlphaComponent()) / DEFAULT_RGB_MAX_BOUND;
        } else {
            f[0] = this.rgbValues[0];
            f[1] = this.rgbValues[1];
            f[2] = this.rgbValues[2];
            f[3] = this.alpha;
        }
        return f;
    }

    /**
     * Returns <code>float</code> array containing the color components of current {@link Color} instance
     *
     * @return <code>float</code> array of color components
     */
    public float[] getRGBComponents() {
        final float[] f = new float[3];
        if (Objects.isNull(this.rgbValues)) {
            f[0] = ((float) this.getRedComponent()) / DEFAULT_RGB_MAX_BOUND;
            f[1] = ((float) this.getGreenComponent()) / DEFAULT_RGB_MAX_BOUND;
            f[2] = ((float) this.getBlueComponent()) / DEFAULT_RGB_MAX_BOUND;
        } else {
            f[0] = this.rgbValues[0];
            f[1] = this.rgbValues[1];
            f[2] = this.rgbValues[2];
        }
        return f;
    }

    /**
     * Returns <code>float</code> array containing the color and alpha components of current {@link Color} instance
     *
     * @return <code>float</code> array of color components
     */
    public float[] getRGBAColorComponents() {
        if (Objects.isNull(this.rgbValues)) {
            return this.getRGBAComponents();
        }
        final float[] f = new float[4];
        int n = this.rgbValues.length;
        for (int i = 0; i < n; i++) {
            f[i] = this.rgbValues[i];
        }
        f[n] = this.alpha;
        return f;
    }

    /**
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code>, in the
     * <code>ColorSpace</code> of the <code>Color</code>.
     *
     * @return <code>float</code> array of color components
     */
    public float[] getRGBColorComponents() {
        if (Objects.isNull(this.rgbValues)) {
            return this.getRGBComponents();
        }
        final float[] f = new float[3];
        int n = this.rgbValues.length;
        for (int i = 0; i < n; i++) {
            f[i] = this.rgbValues[i];
        }
        return f;
    }

    /**
     * Returns {@link TransparencyColorType} type for current <code>Color</code> instance
     *
     * @return {@link TransparencyColorType} type
     */
    public TransparencyColorType getTransparencyComponent() {
        int alpha = this.getAlphaComponent();
        if (Objects.equals(DEFAULT_RGB_MAX_BOUND, alpha)) {
            return TransparencyColorType.OPAQUE;
        } else if (Objects.equals(DEFAULT_RGB_MIN_BOUND, alpha)) {
            return TransparencyColorType.BITMASK;
        }
        return TransparencyColorType.TRANSLUCENT;
    }

    @NotNull
    public static final Color of(final int red, final int green, final int blue) {
        return new Color(red, green, blue);
    }
}
