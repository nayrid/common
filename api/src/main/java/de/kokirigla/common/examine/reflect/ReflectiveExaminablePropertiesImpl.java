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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;

/**
 * Implementation for {@link ReflectiveExaminableProperties}.
 *
 * @author kashike
 * @see <a
 *     href="https://github.com/KyoriPowered/examination/tree/05bdd6b9a16f7bd95058f183d593011814bd5e45/reflection/src/main/java/net/kyori/examination/reflection">reflection
 *     branch</a>
 * @since 1.1.0
 */
@NullUnmarked
final class ReflectiveExaminablePropertiesImpl implements ReflectiveExaminableProperties {

    private final List<Supplier<ExaminableProperty>> properties;

    private ReflectiveExaminablePropertiesImpl(final List<Supplier<ExaminableProperty>> properties) {
        this.properties = properties;
    }

    public static ReflectiveExaminablePropertiesImpl forFields(final Object object) {
        final List<Supplier<ExaminableProperty>> properties = new ArrayList<>();
        for (final Field field : object.getClass().getDeclaredFields()) {
            final @org.jetbrains.annotations.Nullable Examine examine = field.getAnnotation(Examine.class);
            if (examine != null) {
                field.setAccessible(true);

                final String name = name(field, examine);
                final MethodHandle handle;
                try {
                    handle = MethodHandles.lookup().unreflectGetter(field);
                } catch (final IllegalAccessException e) {
                    throw new ReflectiveExaminationException(e);
                }
                properties.add(() -> {
                    final Object value;
                    try {
                        value = handle.invoke(object);
                    } catch (final Throwable e) {
                        throw new ReflectiveExaminationException(e);
                    }
                    return ExaminableProperty.of(name, value);
                });
            }
        }
        return new ReflectiveExaminablePropertiesImpl(properties);
    }

    private static String name(final Field field, final Examine examine) {
        String name = examine.name();
        if (name.isEmpty()) {
            name = field.getName();
        }
        return name;
    }

    @Override
    public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
        return this.properties.stream().map(Supplier::get).filter(Objects::nonNull);
    }

}
