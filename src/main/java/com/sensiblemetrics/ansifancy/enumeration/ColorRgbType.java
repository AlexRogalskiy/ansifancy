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
package com.sensiblemetrics.ansifancy.enumeration;

import com.sensiblemetrics.ansifancy.converter.ColorConverter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.sensiblemetrics.ansifancy.exception.UnsupportedColorRgbTypeException.throwUnsupportedColorRgbType;

/**
 * Default color RGB type {@link Enum}
 */
@Getter
@RequiredArgsConstructor
public enum ColorRgbType {
    TRANSPARENT(new ColorConverter(0, 0, 0, 0d)),
    ALICEBLUE(new ColorConverter(240, 248, 255, 1d)),
    ANTIQUEWHITE(new ColorConverter(250, 235, 215, 1d)),
    AQUA(new ColorConverter(0, 255, 255, 1d)),
    AQUAMARINE(new ColorConverter(127, 255, 212, 1d)),
    AZURE(new ColorConverter(240, 255, 255, 1d)),
    BEIGE(new ColorConverter(245, 245, 220, 1d)),
    BISQUE(new ColorConverter(255, 228, 196, 1d)),
    BLACK(new ColorConverter(0, 0, 0, 1d)),
    BLANCHEDALMOND(new ColorConverter(255, 235, 205, 1d)),
    BLUE(new ColorConverter(0, 0, 255, 1d)),
    BLUEVIOLET(new ColorConverter(138, 43, 226, 1d)),
    BROWN(new ColorConverter(165, 42, 42, 1d)),
    BURLYWOOD(new ColorConverter(222, 184, 135, 1d)),
    CADETBLUE(new ColorConverter(95, 158, 160, 1d)),
    CHARTREUSE(new ColorConverter(127, 255, 0, 1d)),
    CHOCOLATE(new ColorConverter(210, 105, 30, 1d)),
    CORAL(new ColorConverter(255, 127, 80, 1d)),
    CORNFLOWERBLUE(new ColorConverter(100, 149, 237, 1d)),
    CORNSILK(new ColorConverter(255, 248, 220, 1d)),
    CRIMSON(new ColorConverter(220, 20, 60, 1d)),
    CYAN(new ColorConverter(0, 255, 255, 1d)),
    DARKBLUE(new ColorConverter(0, 0, 139, 1d)),
    DARKCYAN(new ColorConverter(0, 139, 139, 1d)),
    DARKGOLDENROD(new ColorConverter(184, 134, 11, 1d)),
    DARKGRAY(new ColorConverter(169, 169, 169, 1d)),
    DARKGREEN(new ColorConverter(0, 100, 0, 1d)),
    DARKGREY(new ColorConverter(169, 169, 169, 1d)),
    DARKKHAKI(new ColorConverter(189, 183, 107, 1d)),
    DARKMAGENTA(new ColorConverter(139, 0, 139, 1d)),
    DARKOLIVEGREEN(new ColorConverter(85, 107, 47, 1d)),
    DARKORANGE(new ColorConverter(255, 140, 0, 1d)),
    DARKORCHID(new ColorConverter(153, 50, 204, 1d)),
    DARKRED(new ColorConverter(139, 0, 0, 1d)),
    DARKSALMON(new ColorConverter(233, 150, 122, 1d)),
    DARKSEAGREEN(new ColorConverter(143, 188, 143, 1d)),
    DARKSLATEBLUE(new ColorConverter(72, 61, 139, 1d)),
    DARKSLATEGRAY(new ColorConverter(47, 79, 79, 1d)),
    DARKSLATEGREY(new ColorConverter(47, 79, 79, 1d)),
    DARKTURQUOISE(new ColorConverter(0, 206, 209, 1d)),
    DARKVIOLET(new ColorConverter(148, 0, 211, 1d)),
    DEEPPINK(new ColorConverter(255, 20, 147, 1d)),
    DEEPSKYBLUE(new ColorConverter(0, 191, 255, 1d)),
    DIMGRAY(new ColorConverter(105, 105, 105, 1d)),
    DIMGREY(new ColorConverter(105, 105, 105, 1d)),
    DODGERBLUE(new ColorConverter(30, 144, 255, 1d)),
    FIREBRICK(new ColorConverter(178, 34, 34, 1d)),
    FLORALWHITE(new ColorConverter(255, 250, 240, 1d)),
    FORESTGREEN(new ColorConverter(34, 139, 34, 1d)),
    FUCHSIA(new ColorConverter(255, 0, 255, 1d)),
    GAINSBORO(new ColorConverter(220, 220, 220, 1d)),
    GHOSTWHITE(new ColorConverter(248, 248, 255, 1d)),
    GOLD(new ColorConverter(255, 215, 0, 1d)),
    GOLDENROD(new ColorConverter(218, 165, 32, 1d)),
    GRAY(new ColorConverter(128, 128, 128, 1d)),
    GREY(new ColorConverter(128, 128, 128, 1d)),
    GREEN(new ColorConverter(0, 128, 0, 1d)),
    GREENYELLOW(new ColorConverter(173, 255, 47, 1d)),
    HONEYDEW(new ColorConverter(240, 255, 240, 1d)),
    HOTPINK(new ColorConverter(255, 105, 180, 1d)),
    INDIANRED(new ColorConverter(205, 92, 92, 1d)),
    INDIGO(new ColorConverter(75, 0, 130, 1d)),
    IVORY(new ColorConverter(255, 255, 240, 1d)),
    KHAKI(new ColorConverter(240, 230, 140, 1d)),
    LAVENDER(new ColorConverter(230, 230, 250, 1d)),
    LAVENDERBLUSH(new ColorConverter(255, 240, 245, 1d)),
    LAWNGREEN(new ColorConverter(124, 252, 0, 1d)),
    LEMONCHIFFON(new ColorConverter(255, 250, 205, 1d)),
    LIGHTBLUE(new ColorConverter(173, 216, 230, 1d)),
    LIGHTCORAL(new ColorConverter(240, 128, 128, 1d)),
    LIGHTCYAN(new ColorConverter(224, 255, 255, 1d)),
    LIGHTGOLDENRODYELLOW(new ColorConverter(250, 250, 210, 1d)),
    LIGHTGRAY(new ColorConverter(211, 211, 211, 1d)),
    LIGHTGREEN(new ColorConverter(144, 238, 144, 1d)),
    LIGHTGREY(new ColorConverter(211, 211, 211, 1d)),
    LIGHTPINK(new ColorConverter(255, 182, 193, 1d)),
    LIGHTSALMON(new ColorConverter(255, 160, 122, 1d)),
    LIGHTSEAGREEN(new ColorConverter(32, 178, 170, 1d)),
    LIGHTSKYBLUE(new ColorConverter(135, 206, 250, 1d)),
    LIGHTSLATEGRAY(new ColorConverter(119, 136, 153, 1d)),
    LIGHTSLATEGREY(new ColorConverter(119, 136, 153, 1d)),
    LIGHTSTEELBLUE(new ColorConverter(176, 196, 222, 1d)),
    LIGHTYELLOW(new ColorConverter(255, 255, 224, 1d)),
    LIME(new ColorConverter(0, 255, 0, 1d)),
    LIMEGREEN(new ColorConverter(50, 205, 50, 1d)),
    LINEN(new ColorConverter(250, 240, 230, 1d)),
    MAGENTA(new ColorConverter(255, 0, 255, 1d)),
    MAROON(new ColorConverter(128, 0, 0, 1d)),
    MEDIUMAQUAMARINE(new ColorConverter(102, 205, 170, 1d)),
    MEDIUMBLUE(new ColorConverter(0, 0, 205, 1d)),
    MEDIUMORCHID(new ColorConverter(186, 85, 211, 1d)),
    MEDIUMPURPLE(new ColorConverter(147, 112, 219, 1d)),
    MEDIUMSEAGREEN(new ColorConverter(60, 179, 113, 1d)),
    MEDIUMSLATEBLUE(new ColorConverter(123, 104, 238, 1d)),
    MEDIUMSPRINGGREEN(new ColorConverter(0, 250, 154, 1d)),
    MEDIUMTURQUOISE(new ColorConverter(72, 209, 204, 1d)),
    MEDIUMVIOLETRED(new ColorConverter(199, 21, 133, 1d)),
    MIDNIGHTBLUE(new ColorConverter(25, 25, 112, 1d)),
    MINTCREAM(new ColorConverter(245, 255, 250, 1d)),
    MISTYROSE(new ColorConverter(255, 228, 225, 1d)),
    MOCCASIN(new ColorConverter(255, 228, 181, 1d)),
    NAVAJOWHITE(new ColorConverter(255, 222, 173, 1d)),
    NAVY(new ColorConverter(0, 0, 128, 1d)),
    OLDLACE(new ColorConverter(253, 245, 230, 1d)),
    OLIVE(new ColorConverter(128, 128, 0, 1d)),
    OLIVEDRAB(new ColorConverter(107, 142, 35, 1d)),
    ORANGE(new ColorConverter(255, 165, 0, 1d)),
    ORANGERED(new ColorConverter(255, 69, 0, 1d)),
    ORCHID(new ColorConverter(218, 112, 214, 1d)),
    PALEGOLDENROD(new ColorConverter(238, 232, 170, 1d)),
    PALEGREEN(new ColorConverter(152, 251, 152, 1d)),
    PALETURQUOISE(new ColorConverter(175, 238, 238, 1d)),
    PALEVIOLETRED(new ColorConverter(219, 112, 147, 1d)),
    PAPAYAWHIP(new ColorConverter(255, 239, 213, 1d)),
    PEACHPUFF(new ColorConverter(255, 218, 185, 1d)),
    PERU(new ColorConverter(205, 133, 63, 1d)),
    PINK(new ColorConverter(255, 192, 203, 1d)),
    PLUM(new ColorConverter(221, 160, 221, 1d)),
    POWDERBLUE(new ColorConverter(176, 224, 230, 1d)),
    PURPLE(new ColorConverter(128, 0, 128, 1d)),
    REBECCAPURPLE(new ColorConverter(102, 51, 153, 1d)),
    RED(new ColorConverter(255, 0, 0, 1d)),
    ROSYBROWN(new ColorConverter(188, 143, 143, 1d)),
    ROYALBLUE(new ColorConverter(65, 105, 225, 1d)),
    SADDLEBROWN(new ColorConverter(139, 69, 19, 1d)),
    SALMON(new ColorConverter(250, 128, 114, 1d)),
    SANDYBROWN(new ColorConverter(244, 164, 96, 1d)),
    SEAGREEN(new ColorConverter(46, 139, 87, 1d)),
    SEASHELL(new ColorConverter(255, 245, 238, 1d)),
    SIENNA(new ColorConverter(160, 82, 45, 1d)),
    SILVER(new ColorConverter(192, 192, 192, 1d)),
    SKYBLUE(new ColorConverter(135, 206, 235, 1d)),
    SLATEBLUE(new ColorConverter(106, 90, 205, 1d)),
    SLATEGRAY(new ColorConverter(112, 128, 144, 1d)),
    SLATEGREY(new ColorConverter(112, 128, 144, 1d)),
    SNOW(new ColorConverter(255, 250, 250, 1d)),
    SPRINGGREEN(new ColorConverter(0, 255, 127, 1d)),
    STEELBLUE(new ColorConverter(70, 130, 180, 1d)),
    TAN(new ColorConverter(210, 180, 140, 1d)),
    TEAL(new ColorConverter(0, 128, 128, 1d)),
    THISTLE(new ColorConverter(216, 191, 216, 1d)),
    TOMATO(new ColorConverter(255, 99, 71, 1d)),
    TURQUOISE(new ColorConverter(64, 224, 208, 1d)),
    VIOLET(new ColorConverter(238, 130, 238, 1d)),
    WHEAT(new ColorConverter(245, 222, 179, 1d)),
    WHITE(new ColorConverter(255, 255, 255, 1d)),
    WHITESMOKE(new ColorConverter(245, 245, 245, 1d)),
    YELLOW(new ColorConverter(255, 255, 0, 1d)),
    YELLOWGREEN(new ColorConverter(154, 205, 50, 1d));

    /**
     * Default {@link ColorConverter} instance
     */
    private final ColorConverter colorValue;

    @NonNull
    public static ColorRgbType getColorRgbType(final String value) {
        return Arrays.stream(values())
            .filter(type -> type.name().equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> throwUnsupportedColorRgbType(value));
    }
}
