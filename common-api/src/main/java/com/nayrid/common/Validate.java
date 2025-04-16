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

import java.util.Objects;
import java.util.function.Function;
import org.jetbrains.annotations.Contract;
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
    }

    /**
     * Validates that an object is non-null and returns it.
     *
     * @param object the object to check for
     * @param name   the name of the object
     * @param <T>    the object type
     * @return the object, guaranteed to be non-null
     * @throws NullPointerException if the object is null
     * @since 1.0.0
     */
    @Contract(value = "null, _ -> fail; !null, _ -> param1")
    public static <T> T nonNull(final @Nullable T object, final @Nullable String name) {
        return nonNull(object, name, (n) -> new NullPointerException(n == null ? "unnamed object" : n + " must not be be null"));
    }

    /**
     * Validates that an object is non-null and returns it.
     *
     * @param object            the object to check for
     * @param name              the name of the object
     * @param exceptionSupplier the supplier of the thrown exception if the {@code object} is null
     * @param <T>               the object type
     * @param <E>               the exception type
     * @return the object, guaranteed to be non-null
     * @throws E if the object is null
     * @since 1.0.0
     */
    @Contract(value = "null, _, _ -> fail; !null, _, _ -> param1")
    public static <T, E extends RuntimeException> T nonNull(final @Nullable T object, final @Nullable String name, final Function<@Nullable String, E> exceptionSupplier) throws E {
        Objects.requireNonNull(exceptionSupplier, "exceptionSupplier must not be null");

        if (object == null) {
            throw Objects.requireNonNull(exceptionSupplier.apply(name), "Exception supplied by exceptionSupplier must not be null");
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
     * @since 1.0.0
     * @deprecated since 1.0.0, use {@link #nonNull(Object, String)} instead.
     */
    @Deprecated(since = "1.0.0")
    @Contract(value = "null -> fail; !null -> param1")
    public static <T> T nonNull(final @Nullable T object) {
        return nonNull(object, null);
    }

}
