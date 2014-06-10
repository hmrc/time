package uk.gov.hmrc.time.workingdays

import org.joda.time.LocalDate

object BankHolidays {

  val eventSet =
    BankHolidaySet(
      "england-and-wales",
      List(
        BankHoliday(title="New Year's Day", date = new LocalDate(2012, 1, 2)),
        BankHoliday(title="Easter Monday", date = new LocalDate(2012, 4, 9)),
        BankHoliday(title="Early May bank holiday", date = new LocalDate(2012, 5, 7)),
        BankHoliday(title="Spring bank holiday", date = new LocalDate(2012, 6 , 5)),
        BankHoliday(title="Queen’s Diamond Jubilee", date = new LocalDate(2012, 6 , 5)),
        BankHoliday(title="Summer bank holiday", date = new LocalDate(2012, 8, 27)),
        BankHoliday(title="Christmas Day", date = new LocalDate(2012, 12, 25)),
        BankHoliday(title="Boxing Day", date = new LocalDate(2012, 12, 26)),
        BankHoliday(title="New Year's Day", date = new LocalDate(2013, 1, 1))
      )
    )
}
