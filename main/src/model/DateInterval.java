package model;
import java.io.Serializable;

public class DateInterval implements Serializable
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

  //RETURNING TO STRING METHOD WITH ARRIVAL DATE AND DEPARTURE DATE
  public String toString()
  {
    if (compareDatesContinuity())
    {
      return "Arrival date: " + arrivalDate.copy() + "\n" + "Departure date:"
          + departureDate.copy();
    }
    return "Error: Arrival date is not before departure date or they are equal!";
  }

  //CHECKING IF DateInterval CLASS IS SAME/EQUAL AS obj DateInterval CLASS
  public boolean equals(Object obj)
  {
    if (obj instanceof DateInterval)
    {
      DateInterval other = (DateInterval) obj;
      return arrivalDate.equals(other.arrivalDate)
          && departureDate.equals(other.departureDate);
    }
    return false;
  }


  //ARRIVAL DATE HAS TO BE BEFORE DEPARTURE DATE
  public boolean compareDatesContinuity()
  {
    if (arrivalDate.copy().isBefore(departureDate.copy()))
    {
      return true;
    }
    return false;
  }

  //COUNTING NUMBER OF DAYS SINCE ARRIVAL DATE TO DEPARTURE DATE
  public int getNumberOfNights()
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

  //CHECKING IF OTHER DateInterval DATE OVERLAPS WITH DateInterval DATE
  public boolean isAvailableDate(DateInterval other)
  {
    //IF ARRIVAL DATE IS AFTER OTHER DEPARTURE DATE
    //OR DEPARTURE IS BEFORE OTHER ARRIVAL DATE RETURN TRUE
    return arrivalDate.copy().isAfter(other.departureDate.copy())
        || departureDate.copy().isBefore(other.arrivalDate.copy());
  }
}
