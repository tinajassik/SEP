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
    return "day=" + day + ", month=" + month + ", year=" + year;
  }

  public Date copy()
  {
    return new Date(day,month,year);
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
}
