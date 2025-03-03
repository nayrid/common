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
package com.nayrid.common.examine.reflect;

import com.nayrid.common.examine.AbstractExaminable;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NullMarked
class ReflectiveExaminablePropertiesSourceTests {

    @Test
    void testClassWithParent() {
        final TestExaminable te = new TestExaminable("kashike", 0);
        @SuppressWarnings("unchecked")
        final List<ExaminableProperty> properties = (List<ExaminableProperty>) te.examinableProperties().toList();

        assertEquals(4, properties.size());
        assertEquals("pi", properties.get(0).name());
        assertEquals("aName", properties.get(1).name());
        assertEquals("aName", properties.get(2).name());
        assertEquals("age", properties.get(3).name());
    }

    @Test
    void testRecord() {
        final TestRecord testRecord = new TestRecord("test record");
        @SuppressWarnings("unchecked")
        final List<ExaminableProperty> propertes = (List<ExaminableProperty>) testRecord.examinableProperties().toList();

        assertEquals(1, propertes.size());
        assertEquals("name", propertes.getFirst().name());
    }

    record TestRecord(@Examine String name) implements Examinable {

        @Override
        public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
            return ReflectiveExaminableProperties.forFields(this).examinableProperties();
        }

    }

    static abstract class AbstractTestExaminable extends AbstractExaminable {

        private final @Examine double pi = Math.PI;
        private final @Examine(name = "aName") String nameButNot = "foo"; // same name as the child classes field is okay

    }

    static final class TestExaminable extends AbstractTestExaminable {
        private final @Examine(name = "aName") String name;
        private final @Examine int age;
        private final char thisWillNotBeExamined = 'd';

        TestExaminable(final String name, final int age) {
            super();
            this.name = name;
            this.age = age;
        }

        @Override
        public String examinableName() {
            return "TestExaminable";
        }

    }
}
