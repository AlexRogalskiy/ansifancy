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
package com.wildbeeslabs.sensiblemetrics.ansifancy.config;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Symbol;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.DefaultPoint;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.DefaultSymbol;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Fancy configuration
 */
@Data
@EqualsAndHashCode
@ToString
public class AnsiFancyConfiguration {

    /**
     * Default symbols map {@link Map}
     */
    private final Map<CharSequence, Symbol> DEFAULT_SYMBOL_MAP = new HashMap<>();

    public AnsiFancyConfiguration() {
        initialize();
    }

    private void initialize() {
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.getPoint("b", "0")).styles(null).build());
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("u").view("underline").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("blk").view("blink").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("rev").view("reverse").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("blnk").view("blank").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("b").view("bold").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("b").view("bold").build()));
        DEFAULT_SYMBOL_MAP.put("b", DefaultSymbol.builder().metaData(null).point(DefaultPoint.builder().code("b").view("bold").build()));


        public static final AnsiSequence RESET = new AnsiSequence("0");
        public static final AnsiSequence BOLD = new AnsiSequence("1");
        public static final AnsiSequence DIM = new AnsiSequence("2");
        public static final AnsiSequence UNDERLINE = new AnsiSequence("4");
        public static final AnsiSequence BLINK = new AnsiSequence("5");
        public static final AnsiSequence REVERSE = new AnsiSequence("7");
        public static final AnsiSequence BLANK = new AnsiSequence("8");
        public static final AnsiSequence OVERSTRIKE = new AnsiSequence("9");

        public static final AnsiSequence BLACK = new AnsiSequence("30");
        public static final AnsiSequence BLACK_BG = new AnsiSequence("40");
        public static final AnsiSequence RED = new AnsiSequence("31");
        public static final AnsiSequence RED_BG = new AnsiSequence("41");
        public static final AnsiSequence GREEN = new AnsiSequence("32");
        public static final AnsiSequence GREEN_BG = new AnsiSequence("42");
        public static final AnsiSequence YELLOW = new AnsiSequence("33");
        public static final AnsiSequence YELLOW_BG = new AnsiSequence("43");
        public static final AnsiSequence BLUE = new AnsiSequence("34");
        public static final AnsiSequence BLUE_BG = new AnsiSequence("44");
        public static final AnsiSequence MAGENTA = new AnsiSequence("35");
        public static final AnsiSequence MAGENTA_BG = new AnsiSequence("45");
        public static final AnsiSequence CYAN = new AnsiSequence("36");
        public static final AnsiSequence CYAN_BG = new AnsiSequence("46");
        public static final AnsiSequence WHITE = new AnsiSequence("37");
        public static final AnsiSequence WHITE_BG = new AnsiSequence("47");

        registeredClasses.put("bold", AnsiClass.withName("b").add(BOLD));
        registeredClasses.put("dim", AnsiClass.withName("dim").add(DIM));
        registeredClasses.put("u", AnsiClass.withName("u").add(UNDERLINE));
        registeredClasses.put("underline", AnsiClass.withName("underline").add(UNDERLINE));
        registeredClasses.put("blink", AnsiClass.withName("blink").add(BLINK));
        registeredClasses.put("reverse", AnsiClass.withName("reverse").add(REVERSE));
        registeredClasses.put("blank", AnsiClass.withName("blank").add(BLANK));
        registeredClasses.put("overstrike", AnsiClass.withName("overstrike").add(OVERSTRIKE));
        registeredClasses.put("reset", AnsiClass.withName("reset").add(RESET));
        registeredClasses.put("black", AnsiClass.withName("black").add(BLACK));
        registeredClasses.put("blackBg", AnsiClass.withName("blackBg").add(BLACK_BG));
        registeredClasses.put("red", AnsiClass.withName("red").add(RED));
        registeredClasses.put("redBg", AnsiClass.withName("redBg").add(RED_BG));
        registeredClasses.put("green", AnsiClass.withName("green").add(GREEN));
        registeredClasses.put("greenBg", AnsiClass.withName("greenBg").add(GREEN_BG));
        registeredClasses.put("yellow", AnsiClass.withName("yellow").add(YELLOW));
        registeredClasses.put("yellowBg", AnsiClass.withName("yellowBg").add(YELLOW_BG));
        registeredClasses.put("blue", AnsiClass.withName("blue").add(BLUE));
        registeredClasses.put("blueBg", AnsiClass.withName("blueBg").add(BLUE_BG));
        registeredClasses.put("magenta", AnsiClass.withName("magenta").add(MAGENTA));
        registeredClasses.put("magentaBg", AnsiClass.withName("blueBg").add(MAGENTA_BG));
        registeredClasses.put("cyan", AnsiClass.withName("cyan").add(CYAN));
        registeredClasses.put("cyanBg", AnsiClass.withName("cyan").add(CYAN_BG));
        registeredClasses.put("white", AnsiClass.withName("white").add(WHITE));
        registeredClasses.put("whiteBg", AnsiClass.withName("whiteBg").add(WHITE_BG));
    }

    public Set<String> getClassNames() {
        return Collections.unmodifiableSet(registeredClasses.keySet());
    }

    public AnsiScapeContext add(AnsiClass ansiClass) {
        if (registeredClasses.containsKey(ansiClass.getName())) {
            throw asciiClassAlreadyDefined(ansiClass.getName());
        }
        registeredClasses.put(ansiClass.getName(), ansiClass);
        return this;
    }

    public AnsiClass get(String className) {
        return registeredClasses.get(className);
    }
}
