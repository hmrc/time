/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.time

import org.joda.time._

trait DateTimeUtils {
  def now: DateTime = DateTime.now.withZone(DateTimeZone.UTC)

  def daysBetween(start: LocalDate, end: LocalDate): Int =
    Days.daysBetween(start.toDateTimeAtStartOfDay(DateTimeZone.UTC), end.toDateTimeAtStartOfDay(DateTimeZone.UTC)).getDays

  def isEqualOrAfter(date:LocalDate, laterDate:LocalDate):Boolean = date.isEqual(laterDate) || date.isBefore(laterDate)
}

object DateTimeUtils extends DateTimeUtils
