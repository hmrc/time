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

package uk.gov.hmrc.time.workingdays

import org.joda.time.LocalDate
import org.scalatest.{WordSpecLike, Matchers}

class WorkingDaysPackageLocalDateWithHolidaysSpec extends WordSpecLike with Matchers {
  implicit val bankHolidays = BankHolidays.eventSet

  "LocalDateWithHoliday - isBankHoliday" should {
    "say that Christmas Day 2012 is a bank holiday" in {
      val christmasDay = new LocalDate(2012, 12, 25)
      christmasDay.isBankHoliday shouldBe true
    }
    "say that Christmas Eve 2012 is not a bank holiday" in {
      val christmasEve = new LocalDate(2012, 12, 24)
      christmasEve.isBankHoliday shouldBe false
    }
  }

  "LocalDateWithHoliday - isWeekendDay" should {
    "say that Friday, the 15th of November 2013 is not a weekend day" in {
      val friday = new LocalDate(2013, 11, 15)
      friday.isWeekendDay shouldBe false
    }
    "say that Saturday, the 16th of November 2013 is a weekend day" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.isWeekendDay shouldBe true
    }
    "say that Sunday, the 17th of November 2013 is a weekend day" in {
      val sunday = new LocalDate(2013, 11, 17)
      sunday.isWeekendDay shouldBe true
    }
  }

  "LocalDateWithHoliday - isWorkingDay" should {
    "say that Friday, the 15th of November 2013 is a working day" in {
      val workingDay = new LocalDate(2013, 11, 15)
      workingDay.isWorkingDay shouldBe true
    }
    "say that Christmas Day 2012 is not a working day" in {
      val christmas = new LocalDate(2012, 12, 25)
      christmas.isWorkingDay shouldBe false
    }
    "say that Saturday, the 16th of November 2013 is not a working day" in {
      val weekendDay = new LocalDate(2013, 11, 16)
      weekendDay.isWorkingDay shouldBe false
    }
  }

  "LocalDateWithHoliday - plusWorkingDays" should {
    "say that Christmas Day 2012 plus 2 working days is Friday, the 28th December 2012" in {
      val christmas = new LocalDate(2012, 12, 25)
      christmas.plusWorkingDays(2) shouldBe new LocalDate(2012, 12, 28)
    }
    "say that Christmas Day 2012 plus 3 working days is Monday, the 31st December 2012" in {
      val christmas = new LocalDate(2012, 12, 25)
      christmas.plusWorkingDays(3) shouldBe new LocalDate(2012, 12, 31)
    }
    "say that Friday, the 15th of November 2013 plus 2 working days is Tuesday, the 19th November 2013" in {
      val friday = new LocalDate(2013, 11, 15)
      friday.plusWorkingDays(2) shouldBe new LocalDate(2013, 11, 19)
    }
    "say that Saturday, the 16th of November 2013 plus 1 working day is Monday, the 18th November 2013" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.plusWorkingDays(1) shouldBe new LocalDate(2013, 11, 18)
    }
    "say that Saturday, the 16th of November 2013 plus 0 working days is the same date" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.plusWorkingDays(0) shouldBe new LocalDate(2013, 11, 16)
    }
    "say that Saturday, the 16th of November 2013 plus -2 working days is Thursday, the 14th of November 2013" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.plusWorkingDays(-2) shouldBe new LocalDate(2013, 11, 14)
    }
  }

  "LocalDateWithHoliday - minusWorkingDays" should {
    "say that Christmas Day 2012 minus 2 working days is Friday, the 21st of December 2012" in {
      val christmas = new LocalDate(2012, 12, 25)
      christmas.minusWorkingDays(2) shouldBe new LocalDate(2012, 12, 21)
    }
    "say that Saturday, the 29th of December 2012 minus 3 working days is Monday, the 24th of December 2012" in {
      val saturday = new LocalDate(2012, 12, 29)
      saturday.minusWorkingDays(3) shouldBe new LocalDate(2012, 12, 24)
    }
    "say that Tuesday, the 19th of November 2013 minus 1 working day is Monday, the 18th of November 2013" in {
      val tuesday = new LocalDate(2013, 11, 19)
      tuesday.minusWorkingDays(1) shouldBe new LocalDate(2013, 11, 18)
    }
    "say that Tuesday, the 19th of November 2013 minus 3 working days is Thursday, the 14th of November 2013" in {
      val tuesday = new LocalDate(2013, 11, 19)
      tuesday.minusWorkingDays(3) shouldBe new LocalDate(2013, 11, 14)
    }
    "say that Monday, the 18th of November 2013 minus 2 working days is Thursday, the 14th November 2013" in {
      val monday = new LocalDate(2013, 11, 18)
      monday.minusWorkingDays(2) shouldBe new LocalDate(2013, 11, 14)
    }
    "say that Saturday, the 16th of November 2013 minus 1 working day is Friday, the 15th November 2013" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.minusWorkingDays(1) shouldBe new LocalDate(2013, 11, 15)
    }
    "say that Saturday, the 16th of November 2013 minus 0 working days is the same date" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.minusWorkingDays(0) shouldBe new LocalDate(2013, 11, 16)
    }
    "say that Saturday, the 16th of November 2013 minus -2 working days is Tuesday, the 19th of November 2013" in {
      val saturday = new LocalDate(2013, 11, 16)
      saturday.minusWorkingDays(-2) shouldBe new LocalDate(2013, 11, 19)
    }
  }

  "LocalDateWithHoliday - rollBackWorkingDay" should {

    "say that Wednesday, the 20th of February 2013 is the previous working day for Thursday, the 21st of February 2013" in {
      val thursday = new LocalDate(2013, 2, 21)
      thursday.rollBackWorkingDay shouldBe new LocalDate(2013, 2, 20)
    }

    "say that Friday, the 8th of February 2013 is the previous working day for Sunday, the 10th of February 2013" in {
      val sunday = new LocalDate(2013, 2, 10)
      sunday.rollBackWorkingDay shouldBe new LocalDate(2013, 2, 8)
    }

    "say that Friday, the 8th of February 2013 is the previous working day for Monday, the 11th of February 2013" in {
      val sunday = new LocalDate(2013, 2, 11)
      sunday.rollBackWorkingDay shouldBe new LocalDate(2013, 2, 8)
    }

    "say that Monday, the 31st of December 2012 is the previous working day for Wednesday, the 2nd of January 2013" in {
      val date = new LocalDate(2013, 1, 2)
      date.rollBackWorkingDay shouldBe new LocalDate(2012, 12, 31)
    }

  }

  "LocalDateWithHoliday - rollForwardWorkingDay" should {

    "say that Wednesday, the 20th of February 2013 is the next working day for Tuesday, the 19th of February 2013" in {
      val tuesday = new LocalDate(2013, 2, 19)
      tuesday.rollForwardWorkingDay shouldBe new LocalDate(2013, 2, 20)
    }

    "say that Monday, the 11th of February 2013 is the next working day for Friday, the 8th of February 2013" in {
      val sunday = new LocalDate(2013, 2, 8)
      sunday.rollForwardWorkingDay shouldBe new LocalDate(2013, 2, 11)
    }

    "say that Wednesday, the 2nd of January 2013 is the next working day for Monday, the 31st of December 2012" in {
      val date = new LocalDate(2012, 12, 31)
      date.rollForwardWorkingDay shouldBe new LocalDate(2013, 1, 2)
    }

  }

}
