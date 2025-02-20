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
package de.kokirigla.common.examine.reflect;

/*
 * Adapted from kyori's long-abandoned reflection branch on examination - MIT license!
 * https://github.com/KyoriPowered/examination/tree/05bdd6b9a16f7bd95058f183d593011814bd5e45/reflection/src/main/java/net/kyori/examination/reflection
 */

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jspecify.annotations.NullMarked;

/**
 * An examinable property source which provides {@link ExaminableProperty properties} by
 * reflectively examining an object.
 *
 * @author kashike
 * @see <a
 *     href="https://github.com/KyoriPowered/examination/tree/05bdd6b9a16f7bd95058f183d593011814bd5e45/reflection/src/main/java/net/kyori/examination/reflection">reflection
 *     branch</a>
 * @since 1.1.0
 */
@NullMarked
public sealed interface ReflectiveExaminableProperties permits ReflectiveExaminablePropertiesImpl {

    /**
     * Creates an examinable property source from the fields in {@code object} annotated with
     * {@link Examine}.
     *
     * @param object the object to be examined
     * @return an examinable property source
     * @since 1.1.0
     */
    static ReflectiveExaminableProperties forFields(final Object object) {
        return ReflectiveExaminablePropertiesImpl.forFields(object);
    }

    /**
     * Gets a stream of examinable properties.
     *
     * @return a stream of examinable properties
     * @since 1.1.0
     */
    Stream<? extends ExaminableProperty> examinableProperties();

}
