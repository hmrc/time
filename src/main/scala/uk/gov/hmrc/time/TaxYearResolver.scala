package uk.gov.hmrc.time

import org.joda.time.{Interval, LocalDate, DateTimeZone, DateTime}

private[time] trait TaxYearResolver {

  private[time] val now: () => DateTime

  private val ukTime = DateTimeZone.forID("Europe/London")

  def taxYearFor(dateToResolve: LocalDate): Int = {
    val year = dateToResolve.year.get

    if (dateToResolve.isBefore(new LocalDate(year, 4, 6)))
      year - 1
    else
      year
  }

  def fallsInThisTaxYear(currentDate: LocalDate): Boolean = {
    val earliestDateForCurrentTaxYear = new LocalDate(taxYearFor(currentDate), 4, 6)
    earliestDateForCurrentTaxYear.isBefore(currentDate) || earliestDateForCurrentTaxYear.isEqual(currentDate)
  }

  def currentTaxYear: Int = taxYearFor(new LocalDate(now(), ukTime))

  def currentTaxYearYearsRange = currentTaxYear to currentTaxYear + 1

  def startOfTaxYear(year: Int) = new LocalDate(year, 4, 6)

  def startOfCurrentTaxYear = startOfTaxYear(currentTaxYear)

  def endOfCurrentTaxYear = new LocalDate(currentTaxYear + 1, 4, 5)

  def startOfNextTaxYear = new LocalDate(currentTaxYear + 1, 4, 6)

  def taxYearInterval = new Interval(startOfCurrentTaxYear.toDateTimeAtStartOfDay(ukTime),
    startOfNextTaxYear.toDateTimeAtStartOfDay(ukTime))
}

object TaxYearResolver extends TaxYearResolver {
  override private[time] val now = () => DateTimeUtils.now
}