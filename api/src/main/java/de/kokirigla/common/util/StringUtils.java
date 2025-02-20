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
package de.kokirigla.common.util;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import org.jspecify.annotations.NullMarked;

import static de.kokirigla.common.examine.examiner.RecordLikeStringExaminer.simpleEscaping;

/**
 * Utilities for strings.
 *
 * @since 1.1.0
 */
@SuppressWarnings("unused")
@NullMarked
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Returns a string representation of the given {@link Examinable} object using a simple
     * escaping format.
     *
     * @param examinable the object to examine
     * @return the string representation of the examinable object
     * @since 1.1.0
     */
    public static String asString(final Examinable examinable) {
        return simpleEscaping().examine(examinable);
    }

    /**
     * Appends the specified suffix character to the end of the given string.
     *
     * @param string the original string
     * @param suffix the character to append
     * @return a new string consisting of the original string followed by the suffix character
     * @author Kyori
     * @since 1.1.0
     */
    public static String withSuffix(final String string, final char suffix) {
        return string + suffix;
    }

    /**
     * Wraps the given string with the specified character at both the beginning and the end.
     *
     * @param string the string to wrap
     * @param wrap   the character to use for wrapping
     * @return a new string with the wrap character appended to the beginning and the end of the
     *     original string
     * @author Kyori
     * @since 1.1.0
     */
    public static String wrapIn(final String string, final char wrap) {
        return wrap + string + wrap;
    }

    /**
     * Determines the maximum length among a stream of strings.
     *
     * <p>If the stream is empty, this method returns 0.</p>
     *
     * @param strings a stream of strings whose lengths are to be evaluated
     * @return the length of the longest string in the stream, or 0 if the stream is empty
     * @author Kyori
     * @since 1.1.0
     */
    public static int maxLength(final Stream<String> strings) {
        return strings.mapToInt(String::length).max().orElse(0);
    }

    /**
     * Repeats the given string a specified number of times.
     *
     * <p>If the {@code count} is 0, an empty string is returned.
     * If the {@code count} is 1, the original string is returned.</p>
     *
     * @param string the string to be repeated
     * @param count  the number of times to repeat the string
     * @return a new string consisting of the original string repeated {@code count} times
     * @author Kyori
     * @since 1.1.0
     */
    public static String repeat(final String string, final int count) {
        if (count == 0) {
            return "";
        } else if (count == 1) {
            return string;
        }
        return string.repeat(Math.max(0, count));
    }

    /**
     * Pads the given string on the right (end) with the specified padding character until it
     * reaches the minimum length.
     *
     * <p>If the original string's length is greater than or equal to {@code minLength},
     * the original string is returned unchanged. Otherwise, a new string is returned where the
     * original string is followed by enough padding characters to reach the desired length.</p>
     *
     * @param string    the original string to pad
     * @param minLength the desired minimum length of the resulting string
     * @param padding   the character to use for padding
     * @return the original string if its length is at least {@code minLength}, or a new padded
     *     string otherwise
     * @author Kyori
     * @since 1.1.0
     */
    public static String padEnd(final String string, final int minLength, final char padding) {
        return string.length() >= minLength ? string : String.format("%-" + minLength + "s",
            padding
        );
    }

}
