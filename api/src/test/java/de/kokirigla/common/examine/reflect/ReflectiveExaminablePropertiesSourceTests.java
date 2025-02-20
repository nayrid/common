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

import de.kokirigla.common.examine.AbstractExaminable;
import java.util.List;
import net.kyori.examination.ExaminableProperty;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NullMarked
class ReflectiveExaminablePropertiesSourceTests {

    @Test
    void test() {
        final TestExaminable te = new TestExaminable("kashike", 0);
        @SuppressWarnings("unchecked")
        final List<ExaminableProperty> properties = (List<ExaminableProperty>) te.examinableProperties().toList();

        assertEquals(2, properties.size());
        assertEquals("aName", properties.getFirst().name());
        assertEquals("age", properties.getLast().name());
    }

    static class TestExaminable extends AbstractExaminable {
        private final @Examine(name = "aName") String name;
        private final @Examine int age;
        private final char thisWillNotBeExamined = 'd';

        TestExaminable(final String name, final int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String examinableName() {
            return "TestExaminable";
        }

    }
}
