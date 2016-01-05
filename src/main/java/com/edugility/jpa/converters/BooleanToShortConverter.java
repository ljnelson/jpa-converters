/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014-2016 Edugility LLC.
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
 * An {@link AttributeConverter} that converts a {@link Boolean} to
 * and from a {@link Short} representation.
 *
 * <p>This {@link AttributeConverter} is used mostly to map the Java
 * {@link Boolean} type to a database type of {@code TINYINT},
 * following the <a
 * href="https://docs.oracle.com/javase/1.5.0/docs/guide/jdbc/getstart/mapping.html#1007274">recommened
 * JDBC type mapping of {@code TINYINT} to {@code short}</a>.</p>
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see AttributeConverter
 */
@Converter
public class BooleanToShortConverter implements AttributeConverter<Boolean, Short> {

  /**
   * Creates a new {@link BooleanToShortConverter}.
   */
  public BooleanToShortConverter() {
    super();
  }

  /**
   * Returns a {@link Short} representing the supplied {@link
   * Boolean}.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @param b the {@link Boolean} to convert; may be {@code null} in
   * which case {@code null} will be returned
   *
   * @return {@code null} if {@code b} is {@code null}, {@link
   * Short#valueOf(short) Short.valueOf((short)1)} if {@code b} is
   * {@linkplain Boolean#equals(Object) equal to} {@link
   * Boolean#TRUE}, and {@link Short#valueOf(short)
   * Short.valueOf((short)0)} in all other cases
   */
  @Override
  public Short convertToDatabaseColumn(final Boolean b) {
    final Short returnValue;
    if (b == null) {
      returnValue = null;
    } else if (Boolean.TRUE.equals(b)) {
      returnValue = Short.valueOf((short)1);
    } else {
      returnValue = Short.valueOf((short)0);
    }
    return returnValue;
  }

  /**
   * Returns a {@code Boolean} representing the supplied {@link
   * Short}.
   *
   * <p>This method may return {@code null}.</p>
   *
   * <p>If the supplied {@link Short} is {@code null}, then {@code
   * null} will be returned.</p>
   *
   * <p>If the supplied {@link Short}'s {@linkplain Short#shortValue()
   * value} is greater than {@code 0}, then {@link Boolean#TRUE} will
   * be returned.</p>
   *
   * <p>In all other cases, {@link Boolean#FALSE} will be
   * returned.</p>
   *
   * @param s the {@link Short} to convert; may be {@code null} in
   * which case {@code null} will be returned
   *
   * @return {@code null} if {@code s} is {@code null}, 
   * {@link Boolean#TRUE} if {@code s}'s {@linkplain Short#shortValue()
   * value} is greater than {@code 0}, or {@link Boolean#FALSE} in all
   * other cases className}
   */
  @Override
  public Boolean convertToEntityAttribute(final Short s) {
    final Boolean returnValue;
    if (s == null) {
      returnValue = null;
    } else if (s.shortValue() > 0) {
      returnValue = Boolean.TRUE;
    } else {
      returnValue = Boolean.FALSE;
    }
    return returnValue;
  }

}
