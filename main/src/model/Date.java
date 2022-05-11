package model;

public class Date
{
  //CREATING FIELDS/ATTRIBUTES FOR CLASS Date
  private int day;
  private int month;
  private int year;

  //3 ARGUMENTS CONSTRUCTOR
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  //SETTER FOR DAY
  public void setDay(int day)
  {
    this.day = day;
  }

  //SETTER FOR MONTH
  public void setMonth(int month)
  {
    this.month = month;
  }

  //SETTER FOR YEAR
  public void setYear(int year)
  {
    this.year = year;
  }

  //GETTER FOR DAY
  public int getDay()
  {
    return day;
  }

  //GETTER FOR MONTH
  public int getMonth()
  {
    return month;
  }

  //GETTER FOR YEAR
  public int getYear()
  {
    return year;
  }

  //TO STRING METHOD INCLUDING IDENTIFICATION TEXT FOR EACH FIELD
  public String toString()
  {
    if (dateCheck())
    {
      return day + "/" + month + "/" + year;
    }
    return "Error: Not a valid date!";
  }

  //COPY METHOD FOR MAINLY COMPOSITION
  public Date copy()
  {
    return new Date(day, month, year);
  }

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

  //!!!New method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  //CHECKING IF GIVEN YEAR IS A LEAP YEAR
  public boolean isLeapYear()
  {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

  //!!!New method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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

  //!!!New method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

  //!!!new method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  //IF DATE IS AFTER OTHER DATE METHOD WILL RETURN TRUE
  //IF DAY IS BEFORE OR EQUAL METHOD WILL RETURN FALSE
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

  //!!!NEW METHOD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  //IF DATE IS BEFORE OTHER DATE METHOD RETURN TRUE
  //IF DATE IS AFTER OR EQUAL TO OTHER DATE METHOD RETURN FALSE
  public boolean isBefore(Date other)
  {
    return !isAfter(other) && !equals(other);
  }
}

