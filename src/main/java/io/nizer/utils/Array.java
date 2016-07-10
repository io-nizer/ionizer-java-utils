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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class to work with {@code Collection} instances.
 *
 * @author J-F Beauchef
 * @since 0.0.1
 */
public final class Array {

  private Array() {
  }

  /**
   * Chunk.
   *
   * @param list
   * @param chunkSize
   * @param <E>
   * @return
   */
  public static <E> List<? extends List<E>> chunk(List<E> list, int chunkSize) {
    return new ArrayList<>(list.parallelStream()
        .collect(Collectors.groupingBy(e -> Math.floorDiv(list.indexOf(e), chunkSize))).values());
  }

  /**
   * Compact.
   *
   * @param collection
   * @param <E>
   * @return
   */
  public static <E> List<E> compact(Collection<E> collection) {
    return collection.parallelStream()
        .filter(e -> (e != null) && (!(e instanceof Optional) || ((Optional) e).isPresent()))
        .collect(Collectors.toList());
  }

  /**
   * Flatten.
   *
   * @param collection
   * @param <E>
   * @return
   */
  public static <E> List<E> flatten(Collection<? extends Collection<E>> collection) {
    return collection.parallelStream().flatMap(Collection::parallelStream)
        .collect(Collectors.toList());
  }

  /**
   * First.
   *
   * @param list
   * @param <E>
   * @return
   */
  public static <E> E first(List<E> list) {
    return list.isEmpty() ? null : list.get(0);
  }

  /**
   * Intersection.
   *
   * @param first
   * @param second
   * @param <E>
   * @return
   */
  public static <E> List<E> intersection(Collection<E> first, Collection<E> second) {
    return first.stream().filter(second::contains).collect(Collectors.toList());
  }

  /**
   * Last.
   *
   * @param list
   * @param <E>
   * @return
   */
  public static <E> E last(List<E> list) {
    return list.parallelStream().reduce((e1, e2) -> e2).orElse(null);
  }

}
