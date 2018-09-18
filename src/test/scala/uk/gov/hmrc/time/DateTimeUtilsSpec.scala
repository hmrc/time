/*
 * Copyright 2018 HM Revenue & Customs
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

import uk.gov.hmrc.time.DateTimeUtils._
import org.joda.time.LocalDate
import org.scalatest.{WordSpecLike, Matchers}

class DateTimeUtilsSpec extends WordSpecLike with Matchers {

  "isEqualOrAfter" should {
    "return true if first date is after second date" in {
      val firstDate = new LocalDate()
      val secondDate = firstDate.plusDays(2)
      isEqualOrAfter(firstDate, secondDate) shouldBe true
    }

    "return true if first date is equal to second date" in {
      val firstDate = new LocalDate()
      val secondDate = firstDate
      isEqualOrAfter(firstDate, secondDate) shouldBe true
    }

    "return false if first date is before second date" in {
      val firstDate = new LocalDate()
      val secondDate = firstDate.minusDays(2)
      isEqualOrAfter(firstDate, secondDate) shouldBe false
    }
  }
}
