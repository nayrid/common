/*
 * MIT License
 *
 * Copyright (c) 2025 kokiriglade
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.kokirigla.common.examine.examiner;

import de.kokirigla.common.util.StringUtils;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import net.kyori.examination.AbstractExaminer;
import net.kyori.examination.Examiner;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * {@link Examiner} that outputs a more stylized string similar to that of
 * {@link Record#toString()}.
 *
 * <p>The string is <strong>not</strong> guaranteed to be equal to the output of {@link Record#toString()}, just <strong>very</strong> similar.</p>
 *
 * @author Kyori
 * @see <a
 *     href="https://github.com/KyoriPowered/examination/blob/da10a790106751351ae714524d9fcf6ef28e5659/string/src/main/java/net/kyori/examination/string/StringExaminer.java">StringExaminer</a>
 * @since 1.1.0
 */
@NullMarked
public class RecordLikeStringExaminer extends AbstractExaminer<String> {

    private static final Function<String, String> DEFAULT_ESCAPER = string -> string.replace("\"",
            "\\\""
        )
        .replace("\\", "\\\\")
        .replace("\b", "\\b")
        .replace("\f", "\\f")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t");

    private static final Collector<CharSequence, ?, String> COMMA_CURLY = Collectors.joining(", ",
        "{",
        "}"
    );
    private static final Collector<CharSequence, ?, String> COMMA_SQUARE = Collectors.joining(", ",
        "[",
        "]"
    );
    private final Function<String, String> escaper;

    /**
     * Creates a new {@link RecordLikeStringExaminer}.
     *
     * @param escaper the escaper
     * @since 1.1.0
     */
    public RecordLikeStringExaminer(final Function<String, String> escaper) {
        this.escaper = escaper;
    }

    /**
     * Gets a {@link RecordLikeStringExaminer} that escapes simply.
     *
     * @return a string examiner
     * @since 1.1.0
     */
    public static RecordLikeStringExaminer simpleEscaping() {
        return Instances.SIMPLE_ESCAPING;
    }

    @Override
    protected <E> String array(final E[] array, final Stream<String> elements) {
        return elements.collect(COMMA_CURLY);
    }

    @Override
    protected <E> String collection(final Collection<E> collection, final Stream<String> elements) {
        return elements.collect(COMMA_CURLY);
    }

    @Override
    protected String examinable(final String name,
                                final Stream<Map.Entry<String, String>> properties) {
        return name + properties.map(property -> property.getKey() + "=" + property.getValue())
            .collect(COMMA_SQUARE);
    }

    @Override
    protected <K, V> String map(final Map<K, V> map,
                                final Stream<Map.Entry<String, String>> entries) {
        return entries.map(entry -> entry.getKey() + "=" + entry.getValue()).collect(COMMA_CURLY);
    }

    @Override
    protected String nil() {
        return "null";
    }

    @Override
    protected String scalar(final Object value) {
        return String.valueOf(value);
    }

    @Override
    public String examine(final boolean value) {
        return String.valueOf(value);
    }

    @Override
    public String examine(final byte value) {
        return String.valueOf(value) + 'b';
    }

    @Override
    public String examine(final char value) {
        return String.valueOf(value);
    }

    @Override
    public String examine(final double value) {
        return String.valueOf(value) + 'd';
    }

    @Override
    public String examine(final float value) {
        return String.valueOf(value) + 'f';
    }

    @Override
    public String examine(final int value) {
        return String.valueOf(value);
    }

    @Override
    public String examine(final long value) {
        return String.valueOf(value) + 'L';
    }

    @Override
    public String examine(final short value) {
        return String.valueOf(value) + 's';
    }

    @Override
    protected <T> String stream(final Stream<T> stream) {
        return stream.map(this::examine).collect(COMMA_CURLY);
    }

    @Override
    protected String stream(final DoubleStream stream) {
        return stream.mapToObj(this::examine).collect(COMMA_CURLY);
    }

    @Override
    protected String stream(final IntStream stream) {
        return stream.mapToObj(this::examine).collect(COMMA_CURLY);
    }

    @Override
    protected String stream(final LongStream stream) {
        return stream.mapToObj(this::examine).collect(COMMA_CURLY);
    }

    @Override
    public String examine(final @Nullable String value) {
        if (value == null) {
            return this.nil();
        }
        return StringUtils.wrapIn(this.escaper.apply(value), '\"');
    }

    @Override
    protected String array(final int length, final IntFunction<String> value) {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0;
             i < length;
             i++) {
            sb.append(value.apply(i));
            if (i + 1 < length) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private static final class Instances {

        static final RecordLikeStringExaminer SIMPLE_ESCAPING = new RecordLikeStringExaminer(
            DEFAULT_ESCAPER);

    }

}
