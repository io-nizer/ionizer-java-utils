/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Ionizer
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

package io.nizer.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tests for {@code io.nizer.utils.CollectionUtils}.
 *
 * @author J-F Beauchef
 * @since 0.0.1
 */
@RunWith(JUnitPlatform.class)
public class ArrayTest {

  @Test public void givenCollectionOfTenElements_whenChunkingWithThree_thenGetFourChunksBack() {
    List<String> elements =
        IntStream.range(0, 10).boxed().map(i -> Integer.toString(i)).collect(Collectors.toList());
    List<? extends List<String>> result = Array.chunk(elements, 3);
    Assertions.assertTrue(result.size() == 4);
    Assertions.assertTrue(result.parallelStream().reduce((a, b) -> b).orElse(null).size() == 1);
  }

  @Test public void givenCollectionOfTenElements_whenLast_thenGetLastElement() {
    List<String> elements =
        IntStream.range(0, 10).boxed().map(i -> Integer.toString(i)).collect(Collectors.toList());
    String result = Array.last(elements);
    Assertions.assertEquals("9", result);
  }

}
