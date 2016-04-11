/*
 * Copyright 2016 HM Revenue & Customs
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

import org.joda.time.{DateTime, DateTimeZone, LocalDate, MonthDay}

trait CurrentTaxYear {

  val ukTime: DateTimeZone = DateTimeZone.forID("Europe/London")
  val startOfTaxYear = new MonthDay(4, 6)

  def firstDayOfTaxYear(year: Int) = startOfTaxYear.toLocalDate(year)

  def now: () => DateTime

  def forDate(when: LocalDate): TaxYear = {
    if (when isBefore firstDayOfTaxYear(when.getYear))
      TaxYear(when.getYear - 1)
    else
      TaxYear(when.getYear)
  }

  def current: TaxYear = forDate(new LocalDate(now(), ukTime))
}

trait TaxYearResolver extends CurrentTaxYear {

  @deprecated
  def taxYearFor(dateToResolve: LocalDate): Int = forDate(dateToResolve).startYear

  def fallsInThisTaxYear(when: LocalDate): Boolean = current.contains(when)

  @Deprecated
  def currentTaxYear: Int = current.startYear

  def currentTaxYearYearsRange = current.yearRange

  @deprecated
  def startOfTaxYear(year: Int) = TaxYear(year).starts

  @deprecated
  def endOfTaxYear(year: Int) = TaxYear(year).finishes

  def endOfLastTaxYear = current.previous.finishes

  def startOfCurrentTaxYear = current.starts

  def endOfCurrentTaxYear = current.finishes

  def startOfNextTaxYear = current.next.starts

  def taxYearInterval = current.interval
}

object TaxYearResolver extends TaxYearResolver {
  override def now = () => DateTimeUtils.now
}
