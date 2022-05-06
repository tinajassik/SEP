package model;

public class Date
{
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getDay()
  {
    return day;
  }

  public int getMonth()
  {
    return month;
  }

  public int getYear()
  {
    return year;
  }

  public String toString()
  {
    return "day: " + day + ", month: " + month + ", year: " + year;
  }

  public Date copy()
  {
    return new Date(day, month, year);
  }

  public boolean equals(Object obj)
  {
    if (obj instanceof Date)
    {
      Date other = (Date) obj;
      return day == other.day && month == other.month && year == other.year;
    }
    return false;
  }

  //New method!!! using in getNumberOfNights method in DateInterval
  public boolean isLeapYear()
  {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

  //New method!!! using in getNumberOfNights method in DateInterval
  public void nextDay()
  {
    day++;
    if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10) && (day >= 32))
    {
      ++month;
      day -= 31;
    }
    else if ((month == 2 && !isLeapYear()) && day >= 29)
    {
      ++month;
      day -= 28;
    }
    else if ((month == 2 && isLeapYear()) && day >= 30)
    {
      ++month;
      day -= 29;
    }
    else if ((month == 4 || month == 6 || month == 9 || month == 11)
        && day >= 31)
    {
      ++month;
      day -= 30;
    }
    else if (month >= 12 && day >= 32)
    {
      year += 1;
      month -= 11;
      day -= 31;
    }
  }
}
