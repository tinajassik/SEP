package model;

public class DateInterval
{
  //CREATING FIELDS FOR CLASS DateInterval
  private Date arrivalDate;
  private Date departureDate;

  //2 ARGUMENTS CONSTRUCTOR
  public DateInterval(Date arrivalDate, Date departureDate)
  {
    this.arrivalDate = arrivalDate.copy();
    this.departureDate = departureDate.copy();
  }

  //SETTER FOR ARRIVAL DATE
  public void setArrivalDate(Date arrivalDate)
  {
    this.arrivalDate = arrivalDate.copy();
  }

  //SETTER FOR DEPARTURE DATE
  public void setDepartureDate(Date departureDate)
  {
    this.departureDate = departureDate.copy();
  }

  //GETTER FOR ARRIVAL DATE
  public Date getArrivalDate()
  {
    return arrivalDate.copy();
  }

  //GETTER FOR DEPARTURE DATE
  public Date getDepartureDate()
  {
    return departureDate.copy();
  }

  //!!!New method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  //ARRIVAL DATE HAS TO BE BEFORE DEPARTURE DATE
  public boolean compareDatesContinuity()
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

  //COUNTING NUMBER OF DAYS SINCE ARRIVAL DATE TO DEPARTURE DATE
  public int getNumberOfNights()
  {
    //IF ARRIVAL DATES IS SOONER THAN DEPARTURE DATE
    if (compareDatesContinuity())
    {
      //COUNTING VARIABLE
      int countDays = 0;
      //MAKING COPY OF ARRIVAL DATE SO WE CAN USE IT
      Date copyArrival = arrivalDate.copy();
      //WHILE COPY OF ARRIVAL DATE IS NOT SAME AS DEPARTURE DATE WE ARE COUNTING UP DAYS
      while (!(copyArrival.equals(departureDate.copy())))
      {
        copyArrival.nextDay();
        countDays++;
      }
      //RETURN COUNT
      return countDays;
    }
    //ERROR WHEN ARRIVAL DATE IS AFTER DEPARTURE DATE
    else
    {
      return -1;
    }
  }

  //RETURNING TO STRING METHOD WITH ARRIVAL DATE AND DEPARTURE DATE
  public String toString()
  {
    return "Arrival date: " + arrivalDate.copy() + ", Departure date:"
        + departureDate.copy();
  }

  //CHECKING IF DateInterval CLASS IS SAME/EQUAL AS obj DateInterval CLASS
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
