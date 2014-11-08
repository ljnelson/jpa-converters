/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014 Edugility LLC.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * The original copy of this license is available at
 * http://www.opensource.org/license/mit-license.html.
 */
package com.edugility.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * An {@link AttributeConverter} that converts a {@link Class} to and
 * from its {@linkplain Class#getName() <code>String</code>
 * representation}.
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see AttributeConverter
 *
 * @see Class#toString()
 */
@Converter
public class ClassToStringConverter implements AttributeConverter<Class<?>, String> {

  /**
   * Creates a new {@link ClassToStringConverter}.
   */
  public ClassToStringConverter() {
    super();
  }

  /**
   * Calls the {@link Class#getName()} method on the supplied {@link
   * Class} and returns its result.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @param cls the {@link Class} whose {@link String} representation
   * is desired; may be {@code null} in which case {@code null} will
   * be returned
   *
   * @return the result of calling the {@link Class#getName()} method
   * on the supplied {@link Class}, or {@code null}
   */
  @Override
  public String convertToDatabaseColumn(final Class<?> cls) {
    if (cls == null) {
      return null;
    }
    return cls.getName();
  }

  /**
   * Calls the {@link #loadClass(String)} method passing it the
   * supplied {@code className} and returns its result.
   *
   * @param className the {@linkplain Class#getName() name} of the
   * {@link Class} to load; may be {@code null}
   *
   * @return a {@link Class} corresponding to the supplied {@code
   * className}, or {@code null}
   *
   * @see #loadClass(String)
   */
  @Override
  public Class<?> convertToEntityAttribute(final String className) {
    try {
      return this.loadClass(className);
    } catch (final ClassNotFoundException cnfe) {
      throw new IllegalArgumentException(className, cnfe);
    }
  }

  /**
   * Loads a {@link Class} {@linkplain Class#getName() named} by the
   * supplied {@code name} and returns it.
   *
   * <p>This method may return {@code null}.</p>
   *
   * <p>This implementation first checks to see if the supplied {@code
   * name} is {@code null}.  If it is, then {@code null} is returned.
   * Otherwise, the {@link Class#forName(String, boolean,
   * ClassLoader)} method is called with the supplied {@code name},
   * {@code true} and the result of calling the {@link
   * Thread#getContextClassLoader()} method on the {@linkplain
   * Thread#currentThread() current <code>Thread</code>} as arguments,
   * and its result is returned.</p>
   *
   * @param name the {@linkplain Class#getName() name} of the {@link
   * Class} to load; may be {@code null} in which case {@code null}
   * will be returned
   *
   * @return a {@link Class} corresponding to the supplied {@code
   * name}, or {@code null}
   *
   * @exception ClassNotFoundException if the {@link
   * Class#forName(String, boolean, ClassLoader)} method threw a
   * {@link ClassNotFoundException}
   *
   * @see Class#forName(String, boolean, ClassLoader)
   *
   * @see Thread#getContextClassLoader()
   */
  protected Class<?> loadClass(final String name) throws ClassNotFoundException {
    final Class<?> returnValue;
    if (name == null) {
      returnValue = null;
    } else {
      returnValue = Class.forName(name, true, Thread.currentThread().getContextClassLoader());
    }
    return returnValue;
  }

}
