/*
 * Copyright 2014 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.time

import org.joda.time.{ DateTimeZone, DateTime, LocalDate }
import org.scalatest.{WordSpecLike, Matchers}

class TaxYearResolverSpec extends WordSpecLike with Matchers {

  object Resolver {

    def apply(currentTime: DateTime) = new TaxYearResolver {
      private[time] override val now = () => currentTime
    }

    def apply() = new TaxYearResolver {
      private[time] override val now = () => new DateTime(2013, 9, 24, 11, 39, 55, 222, DateTimeZone.forID("Europe/London"))
    }
  }

  "Requesting the tax year for a date" should {

    "Return 2012 when the date is 2013/4/5" in {
      Resolver().taxYearFor(new LocalDate(2013, 4, 5)) shouldBe 2012
    }

    "Return 2013 when the date is 2013/4/6" in {
      Resolver().taxYearFor(new LocalDate(2013, 4, 6)) shouldBe 2013
    }

    "Return 2013 when the date is 2013/9/24" in {
      Resolver().taxYearFor(new LocalDate(2013, 9, 24)) shouldBe 2013
    }

    "Return 2013 when the date is 2014/2/1" in {
      Resolver().taxYearFor(new LocalDate(2014, 2, 1)) shouldBe 2013
    }

    "Return 2013 when the date is 2014/4/5" in {
      Resolver().taxYearFor(new LocalDate(2014, 4, 5)) shouldBe 2013
    }

    "Return 2014 when the date is 2014/4/6" in {
      Resolver().taxYearFor(new LocalDate(2014, 4, 6)) shouldBe 2014
    }
  }

  "Requesting the currentTaxYear" should {

    "Return 2012 when the current UK time is 23:59:59.999 on 2013/4/5" in {
      val currentTime = new DateTime(2013, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2012
    }

    "Return 2013 when the current UK time is 00:00:00.000 on 2013/4/6" in {
      val currentTime = new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2013
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2013/9/24" in {
      val currentTime = new DateTime(2013, 9, 24, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2013
    }

    "Return 2013 when the current UK time is 11:06:23.323 on 2014/2/1" in {
      val currentTime = new DateTime(2014, 2, 1, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2013
    }

    "Return 2013 when the current UK time is 23:59:59.999 on 2014/4/5" in {
      val currentTime = new DateTime(2014, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2013
    }

    "Return 2014 when the current UK time is 00:00:00.000 on 2014/4/6" in {
      val currentTime = new DateTime(2014, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).currentTaxYear shouldBe 2014
    }
  }

  "Requesting the start of the current tax year" should {

    "Return 2012/4/6 when the current UK time is 23:59:59.999 on 2013/4/5" in {
      val currentTime = new DateTime(2013, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2012, 4, 6)
    }

    "Return 2013/4/6 when the current UK time is 00:00:00.000 on 2013/4/6" in {
      val currentTime = new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2013, 4, 6)
    }

    "Return 2013/4/6 when the current UK time is 11:06:23.323 on 2013/9/24" in {
      val currentTime = new DateTime(2013, 9, 24, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2013, 4, 6)
    }

    "Return 2013/4/6 when the current UK time is 11:06:23.323 on 2014/2/1" in {
      val currentTime = new DateTime(2014, 2, 1, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2013, 4, 6)
    }

    "Return 2013/4/6 when the current UK time is 23:59:59.999 on 2014/4/5" in {
      val currentTime = new DateTime(2014, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2013, 4, 6)
    }

    "Return 2014/4/6 when the current UK time is 00:00:00.000 on 2014/4/6" in {
      val currentTime = new DateTime(2014, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfCurrentTaxYear shouldBe new LocalDate(2014, 4, 6)
    }
  }

  "Requesting the start of a given tax year" should {

    "Return 2012/4/6 for 2012" in {
      Resolver().startOfTaxYear(2012) shouldBe new LocalDate(2012, 4, 6)
    }

  }

  "Requesting the end of the current tax year" should {

    "Return 2013/4/5 when the current UK time is 23:59:59.999 on 2013/4/5" in {
      val currentTime = new DateTime(2013, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2013, 4, 5)
    }

    "Return 2014/4/5 when the current UK time is 00:00:00.000 on 2013/4/6" in {
      val currentTime = new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2014, 4, 5)
    }

    "Return 2014/4/5 when the current UK time is 11:06:23.323 on 2013/9/24" in {
      val currentTime = new DateTime(2013, 9, 24, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2014, 4, 5)
    }

    "Return 2014/4/5 when the current UK time is 11:06:23.323 on 2014/2/1" in {
      val currentTime = new DateTime(2014, 2, 1, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2014, 4, 5)
    }

    "Return 2014/4/5 when the current UK time is 23:59:59.999 on 2014/4/5" in {
      val currentTime = new DateTime(2014, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2014, 4, 5)
    }

    "Return 2015/4/5 when the current UK time is 00:00:00.000 on 2014/4/6" in {
      val currentTime = new DateTime(2014, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).endOfCurrentTaxYear shouldBe new LocalDate(2015, 4, 5)
    }
  }

  "Requesting the start of the next tax year" should {

    "Return 2013/4/6 when the current UK time is 23:59:59.999 on 2013/4/5" in {
      val currentTime = new DateTime(2013, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2013, 4, 6)
    }

    "Return 2014/4/6 when the current UK time is 00:00:00.000 on 2013/4/6" in {
      val currentTime = new DateTime(2013, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2014, 4, 6)
    }

    "Return 2014/4/6 when the current UK time is 11:06:23.323 on 2013/9/24" in {
      val currentTime = new DateTime(2013, 9, 24, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2014, 4, 6)
    }

    "Return 2014/4/6 when the current UK time is 11:06:23.323 on 2014/2/1" in {
      val currentTime = new DateTime(2014, 2, 1, 11, 6, 23, 323, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2014, 4, 6)
    }

    "Return 2014/4/6 when the current UK time is 23:59:59.999 on 2014/4/5" in {
      val currentTime = new DateTime(2014, 4, 5, 23, 59, 59, 999, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2014, 4, 6)
    }

    "Return 2015/4/6 when the current UK time is 00:00:00.000 on 2014/4/6" in {
      val currentTime = new DateTime(2014, 4, 6, 0, 0, 0, 0, DateTimeZone.forID("Europe/London"))
      Resolver(currentTime).startOfNextTaxYear shouldBe new LocalDate(2015, 4, 6)
    }
  }
}
