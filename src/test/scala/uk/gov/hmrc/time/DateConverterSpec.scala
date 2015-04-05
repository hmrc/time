/*
 * Copyright 2015 HM Revenue & Customs
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

import org.joda.time.{ LocalDate, DateTimeZone, DateTime }
import org.scalatest.{WordSpecLike, Matchers}

class DateConverterSpec extends WordSpecLike with Matchers {

  import DateConverter._

  "parseToLong" should {
    "return a long representation in UTC of date" in {
      parseToLong("2010-05-03") shouldBe new DateTime(2010, 5, 3, 0, 0, DateTimeZone.UTC).getMillis
    }
  }

  "parseToDateTime" should {
    "return a DateTime instance  in UTC of date" in {
      parseToDateTime("2010-05-03") shouldBe new DateTime(2010, 5, 3, 0, 0, DateTimeZone.UTC)
    }
  }

  "parseToLocalDate" should {
    "return a localDate" in {
      parseToLocalDate("2010-05-03") shouldBe new LocalDate(2010, 5, 3)
    }

    "throw exception if the year is not made up of four digits" in {
      intercept[IllegalArgumentException] {
        println(parseToLocalDate("10-05-03").toString)
      }
    }
  }

  "formatToString with DateTime" should {
    "return a String format of the date" in {
      formatToString(new DateTime(2010, 5, 3, 0, 0, DateTimeZone.UTC)) shouldBe "2010-05-03"
    }
  }

  "formatToString with Long" should {
    "return a String format of the date" in {
      formatToString(new DateTime(2010, 5, 3, 0, 0, DateTimeZone.UTC).getMillis) shouldBe "2010-05-03"
    }
  }

}
