package model;
import java.io.Serializable;

/**
 * A class containing information about a Date object
 * @author Kevin Kluka
 * @version 1.3
 */
public class Date implements Serializable
{
  //CREATING FIELDS/ATTRIBUTES FOR CLASS Date
  private int day;
  private int month;
  private int year;

  //3 ARGUMENTS CONSTRUCTOR

  /**
   * Three- argument constructor.
   * @param day the
   * @param month
   * @param year
   */
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Returns the value of the day variable
   * @return the value of the day variable
   */

  //GETTER FOR DAY
  public int getDay()
  {
    return day;
  }

  /**
   * Returns the value of the month variable
   * @return the value of the month variable
   */

  //GETTER FOR MONTH
  public int getMonth()
  {
    return month;
  }

  /**
   * Returns the value of the year variable
   * @return the value of the year variable
   */

  //GETTER FOR YEAR
  public int getYear()
  {
    return year;
  }

  /**
   * Creates a String containing all information about a Date object
   * @return
   */

  //TO STRING METHOD INCLUDING IDENTIFICATION TEXT FOR EACH FIELD
  public String toString()
  {
    if (dateCheck())
    {
      return year + "-" + month + "-" + day ;
    }
    return "Error: Not a valid date!";
  }

  /**
   * Creates a copy of a Date object
   * @return Date object with the same values
   */

  //COPY METHOD FOR MAINLY COMPOSITION
  public Date copy()
  {
    return new Date(day, month, year);
  }

  /**
   * Compares a Date object to another object and determines whether they are same type and if their values are equal
   * @param obj the object that is compared to a Date object
   * @return true or false statement depending on the outcome of the comparison of the two objects
   */

  //EQUALS METHOD COMPARING ELEMENTS OF ATTRIBUTES
  public boolean equals(Object obj)
  {
    //CHECKING IF obj  IS A OBJECT OF DATE CLASS
    if (obj instanceof Date)
    {
      //CREATING VARIABLE WHICH HOLD ATTRIBUTES OF DATE CLASS
      //AS IN THIS PHASE WE KNOW THAT obj IS DATE CLASS OBJECT
      Date other = (Date) obj;//
      //COMPARTMENT OF EACH ATTRIBUTE WITH ATTRIBUTES OF obj
      return day == other.day && month == other.month && year == other.year;
    }
    //RETURN FALSE IF obj IS NOT INSTANCE OF DATE CLASS
    //RETURN FALSE IF obj DOES NOT HAVE SAME ATTRIBUTES VALUES AS OTHER
    return false;
  }

  /**
   * Checks if a given year is a leap year
   * @return true or false statement telling whether it is a leap year or not
   */

  //CHECKING IF GIVEN YEAR IS A LEAP YEAR
  public boolean isLeapYear()
  {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

  /**
   * Changes the date to the next day's date
   */
  //METHOD ADDING ONE DAY TO A DAY
  public void nextDay()
  {
    day++;
    //CHECKING WHICH MONTH IT IS SO WE CAN ADD CORRECTLY ONE EXTRA DAY
    //THIS CONDITION CHECKING FOR MOTHS WITH 31 DAY
    //EXCEPTION JANUARY
    if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10) && (day >= 32))
    {
      ++month;
      day -= 31;
    }
    //THIS CONDITION CHECKING FOR FEBRUARY WITH 28 DAYS
    //USING isLeapYear METHOD TO CHECK IF IT IS FEBRUARY WITH 28 OR 29 DAYS
    else if ((month == 2 && !isLeapYear()) && day >= 29)
    {
      ++month;
      day -= 28;
    }
    //THIS CONDITION CHECKING FOR FEBRUARY WITH 29 DAYS
    //USING isLeapYear METHOD TO CHECK IF IT IS FEBRUARY WITH 28 OR 29 DAYS
    else if ((month == 2 && isLeapYear()) && day >= 30)
    {
      ++month;
      day -= 29;
    }
    //THIS CONDITION CHECKING FOR MONTHS WITH 30 DAYS
    else if ((month == 4 || month == 6 || month == 9 || month == 11)
        && day >= 31)
    {
      ++month;
      day -= 30;
    }
    //THIS CONDITION IS CHECKING FOR DECEMBER
    //SO WE CAN ADD ONE MORE YEAR IF IT WILL GET PAST 31 DAYS
    else if (month >= 12 && day >= 32)
    {
      year += 1;
      month -= 11;
      day -= 31;
    }
  }

  /**
   * Checks if the date contains correct values
   * @return true statement if the date is correct and false if it is not
   */
  //CHECKING IF WRITTEN DATE IS CORRECT POSSIBLY EXISTING DATE
  public boolean dateCheck()
  {
    //IF YEAR IS MORE THAN 1901
    if (year > 1901)
    {
      //YEAR CANNOT HAVE MORE THAN 12 MONTHS
      if (month < 13)
      {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12)
        {
          //JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER CANNOT HAVE MORE THAN 31 DAYS
          return day < 32;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11)
        {
          //APRIL, JUNE, SEPTEMBER, NOVEMBER CANNOT HAVE MORE THAN 30 DAYS
          return day < 31;
        }
        else if (month == 2)
        {
          if (isLeapYear())
          {
            //FEBRUARY DURING LEAP YEAR CANNOT HAVE MORE THAN 29 DAYS
            return day < 30;
          }
          else
          {
            //FEBRUARY CANNOT HAVE MORE THAN 28 DAYS
            return day < 29;
          }
        }
      }
    }
    return false;
  }

  //IF DATE IS AFTER OTHER DATE METHOD WILL RETURN TRUE
  //IF DAY IS BEFORE OR EQUAL METHOD WILL RETURN FALSE

  /**
   * Checks if a date is chronologically after another date
   * @param other the date that is supposed to be chronologically first
   * @return true statement if the date is chronologically after the date in the other parameter and a false statement if it is before
   */
  public boolean isAfter(Date other)
  {
    if (!equals(other))
    {
      if (year >= other.year)
      {
        if (year == other.year)
        {
          if (month >= other.month)
          {
            if (month == other.month)
            {
              if (day > other.day)
              {
                return true;
              }
            }
            else
            {
              return true;
            }
          }
        }
        else
        {
          return true;
        }
      }
    }
    return false;
  }

  //IF DATE IS BEFORE OTHER DATE METHOD RETURN TRUE
  //IF DATE IS AFTER OR EQUAL TO OTHER DATE METHOD RETURN FALSE
/**
 * Checks if a date is chronologically before another date
 * @param other the date that is supposed to be chronologically second
 * @return true statement if the date is chronologically before the date in the other parameter and a false statement if it is after
 * */
  public boolean isBefore(Date other)
  {
    return !isAfter(other) && !equals(other);
  }
}

