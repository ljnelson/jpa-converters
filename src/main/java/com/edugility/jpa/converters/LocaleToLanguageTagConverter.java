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

import java.util.Locale;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * An {@link AttributeConverter} that converts a {@link Locale} to and
 * from its {@linkplain Locale#toLanguageTag() language tag
 * representation}.
 *
 * @author <a href="http://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see AttributeConverter
 *
 * @see Locale
 */
@Converter
public class LocaleToLanguageTagConverter implements AttributeConverter<Locale, String> {

  /**
   * Creates a new {@link LocaleToLanguageTagConverter}.
   */
  public LocaleToLanguageTagConverter() {
    super();
  }

  /**
   * Calls the {@link Locale#toLanguageTag()} method on the supplied
   * {@link Locale} and returns its result.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @param locale the {@link Locale} to convert; if {@code null},
   * then {@code null} will be returned
   *
   * @return the {@linkplain Locale#toLanguageTag() language tag
   * representation} of the supplied {@link Locale}, or {@code null}
   *
   * @see Locale#toLanguageTag()
   */
  @Override
  public String convertToDatabaseColumn(final Locale locale) {
    if (locale == null) {
      return null;
    }
    return locale.toLanguageTag();
  }

  /**
   * Passes the supplied {@code languageTag} to the {@link
   * Locale#forLanguageTag(String)} method and returns its result.
   *
   * <p>This method may return {@code null}.</p>
   *
   * @param languageTag a <a
   * href="http://tools.ietf.org/html/bcp47">BCP 47</a>-compliant
   * language tag suitable for passing to the {@link
   * Locale#forLanguageTag(String)} method; may be {@code null} in
   * which case {@code null} will be returned
   *
   * @return a {@link Locale} corresponding to the supplied {@code
   * languageTag}, or {@code null}
   */
  @Override
  public Locale convertToEntityAttribute(final String languageTag) {
    if (languageTag == null) {
      return null;
    }
    return Locale.forLanguageTag(languageTag);
  }

}
