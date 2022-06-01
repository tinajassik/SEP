package model;
import java.io.Serializable;

/**
 * A class containing information about guest's arrival and departure
 * @author Kevin Kluka
 * @version 1.2
 */
public class DateInterval implements Serializable
{
  //CREATING FIELDS FOR CLASS DateInterval
  private Date arrivalDate;
  private Date departureDate;

  //2 ARGUMENTS CONSTRUCTOR

  /**
   * 2-argument constructor initializing the DateInterval object
   * @param arrivalDate
   * @param departureDate
   */
  public DateInterval(Date arrivalDate, Date departureDate)
  {
    this.arrivalDate = arrivalDate.copy();
    this.departureDate = departureDate.copy();
  }



  /**
   * Sets guest's arrival date
   * @param arrivalDate the date that the arrival date will be set to
   */
  //SETTER FOR ARRIVAL DATE
  public void setArrivalDate(Date arrivalDate)
  {
    this.arrivalDate = arrivalDate.copy();
  }

  /**
   * Sets guest's departure date
   * @param departureDate the date that the departure date will be set to
   */

  //SETTER FOR DEPARTURE DATE
  public void setDepartureDate(Date departureDate)
  {
    this.departureDate = departureDate.copy();
  }

  /**
   * Returns guest's arrival date
   * @return the guest's arrival date
   */

  //GETTER FOR ARRIVAL DATE
  public Date getArrivalDate()
  {
    return arrivalDate.copy();
  }

  /**
   * Returns guest's departure date
   * @return the guest's departure date
   */

  //GETTER FOR DEPARTURE DATE
  public Date getDepartureDate()
  {
    return departureDate.copy();
  }

  /**
   * Returns a string representation of the DateInterval
   * @return a string representation od the DateInterval
   */
  //RETURNING TO STRING METHOD WITH ARRIVAL DATE AND DEPARTURE DATE
  public String toString()
  {
    if (compareDatesContinuity())
    {
      return "Arrival date: " + arrivalDate.copy() + "\n" + "Departure date: "
          + departureDate.copy();
    }
    return "Error: Arrival date is not before departure date or they are equal!";
  }

  /**
   * Compares arrival and departure dates of two guests.
   * @param obj the object to compare with
   * @return true if given object is equal to this date interval
   */
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

  /**
   * Checks if the arrival date is before the departure date
   * @return true if the arrival date is before the departure date
   */
  public boolean compareDatesContinuity()
  {
    if (arrivalDate.copy().isBefore(departureDate.copy()))
    {
      return true;
    }
    return false;
  }

  /**
   * Returns number of nights between guest's arrival and departure.
   * @return number of nights between guest's arrival and departure
   */

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

  /**
   * Checks if two booking periods overlap each other
   * @param other the date interval to compare with
   * @return true if the bookings do not overlap each other
   */
  //CHECKING IF OTHER DateInterval DATE OVERLAPS WITH DateInterval DATE
  public boolean isAvailableDate(DateInterval other)
  {
    //IF ARRIVAL DATE IS AFTER OTHER DEPARTURE DATE
    //OR DEPARTURE IS BEFORE OTHER ARRIVAL DATE RETURN TRUE
    return arrivalDate.copy().isAfter(other.departureDate.copy())
        || departureDate.copy().isBefore(other.arrivalDate.copy());
  }
}
