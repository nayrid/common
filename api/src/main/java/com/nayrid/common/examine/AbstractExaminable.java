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
package com.nayrid.common.examine;

import com.nayrid.common.examine.reflect.ReflectiveExaminableProperties;
import com.nayrid.common.util.StringUtils;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import org.jspecify.annotations.NullMarked;

/**
 * An abstract implementation of {@link Examinable}.
 *
 * @since 1.1.0
 */
@NullMarked
public abstract class AbstractExaminable implements Examinable {

    @Override
    public abstract String examinableName();

    @Override
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return ReflectiveExaminableProperties.forFields(this).examinableProperties();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public final <R> R examine(final Examiner<R> examiner) {
        return Examinable.super.examine(examiner);
    }

    @Override
    public final String toString() {
        return StringUtils.asString(this);
    }

}
