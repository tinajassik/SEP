package model;

public class DateInterval
{
  private Date arrivalDate;
  private Date departureDate;

  public DateInterval(Date arrivalDate, Date departureDate)
  {
    this.arrivalDate = arrivalDate.copy();
    this.departureDate = departureDate.copy();
  }

  public void setArrivalDate(Date arrivalDate)
  {
    this.arrivalDate = arrivalDate.copy();
  }

  public void setDepartureDate(Date departureDate)
  {
    this.departureDate = departureDate.copy();
  }

  public Date getArrivalDate()
  {
    return arrivalDate.copy();
  }

  public Date getDepartureDate()
  {
    return departureDate.copy();
  }

  //New method!!! compare if arrival date is smaller than departed day
  public boolean compareDateContinuity()
  {
    if (arrivalDate.copy().getYear() < departureDate.copy().getYear())
    {
      return true;
    }
    else if (arrivalDate.copy().getYear() > departureDate.copy().getYear())
    {
      return false;
    }
    else if (arrivalDate.copy().getMonth() < departureDate.copy().getMonth())
    {
      return true;
    }
    else if (arrivalDate.copy().getMonth() > departureDate.copy().getMonth())
    {
      return false;
    }
    else if (arrivalDate.copy().getDay() < departureDate.copy().getDay())
    {
      return true;
    }
    else if (arrivalDate.copy().getDay() > departureDate.copy().getDay())
    {
      return false;
    }
    return false;
  }

  public int getNumberOfNights()
  {
    if (compareDateContinuity())
    {
      int countDays = 0;
      Date copyArrival = arrivalDate.copy();
      while (!(copyArrival.equals(departureDate.copy())))
      {
        copyArrival.nextDay();
        countDays++;
      }
      return countDays;
    }
    else
    {
      return -1;
    }
  }

  public String toString()
  {
    return "Arrival date: " + arrivalDate.copy() + ", Departure date:"
        + departureDate.copy();
  }

  public boolean equals(Object obj)
  {
    if (obj instanceof DateInterval)
    {
      DateInterval other = (DateInterval) obj;
      return arrivalDate.copy() == other.arrivalDate.copy()
          && departureDate.copy() == other.departureDate.copy();
    }
    return false;
  }
}
