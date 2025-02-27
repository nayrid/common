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
package de.kokirigla.common;

import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Represents a builder of something.
 *
 * @param <T> the type being built
 * @since 1.1.0
 */
@NullMarked
public interface Builder<T> {

    /**
     * Configures {@code builder} using {@code consumer} and then builds.
     *
     * @param builder the builder
     * @param consumer the builder consume
     * @param <T> the type to be built
     * @param <B> the builder type
     * @return the built thing
     * @since 1.1.0
     */
    @Contract(mutates = "param1")
    static <T, B extends Builder<T>> T configureAndBuild(final B builder, final @Nullable Consumer<? super B> consumer) {
        if (consumer != null) {
            consumer.accept(builder);
        }
        return builder.build();
    }

    /**
     * Builds.
     *
     * @return the built thing
     * @since 1.1.0
     */
    @Contract(value = "-> new", pure = true)
    T build();

}
