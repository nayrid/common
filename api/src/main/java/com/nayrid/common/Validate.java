/*
 * MIT License
 *
 * Copyright (c) 2025 nayrid
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
package com.nayrid.common;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Contains methods to assist in checking for illegal method parameters.
 *
 * @since 1.0.0
 */
@NullMarked
public final class Validate {

    private Validate() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    /**
     * Validates that an object is non-null and returns it.
     *
     * @param object the object to check for
     * @param name   the name of the object
     * @param <T>    the object type
     * @return the object, guaranteed to be non-null
     * @throws IllegalStateException if the object is null
     * @since 1.0.0
     */
    public static <T> T nonNull(final @Nullable T object, final @Nullable String name) {
        if (object == null) {
            throw new IllegalStateException(name == null ? "unnamed object" : name + " must not be be null");
        } else {
            return object;
        }
    }

    /**
     * Validates that an object is non-null and returns it.
     *
     * @param object the object to check for
     * @param <T>    the object type
     * @return the object, guaranteed to be non-null
     * @throws NullPointerException if the object is null
     * @deprecated since 1.0.0, use {@link #nonNull(Object, String)} instead.
     * @since 1.0.0
     */
    @Deprecated(since = "1.0.0")
    public static <T> T nonNull(final @Nullable T object) {
        return nonNull(object, null);
    }

}
