package model;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class containing all information about a Booking object
 * @author Andreea Asimine Aleksandra Adamczak
 * @version 1.2
 */

public class Booking implements Serializable
{
  //creating fields/attributes for class Booking
  private GuestList guests;
  private Room room;
  private DateInterval dates;
  private boolean checkIn = false;
  private boolean lateCheckIn = false;
  private boolean extraBed = false;

  /** 3 arguments constructor
   * @param guests the guest list for a particular room
   * @param room the booked room
   * @param dates the dates of arrival and departure
   */
  public Booking(GuestList guests, Room room, DateInterval dates)
  {
    this.guests=guests;
    this.room=room;
    this.dates=dates;
  }

  /**
   * @return the first person from the ArrayList(the person that made the booking)
   */
  public Guest getBookingGuest()
  {
    return guests.getFirstGuest();
  }

  /**
   * @return method that returns the list of guests for a specific room
   */
  public GuestList getGuests(){ return guests;}

  /**
   * @return method that returns the date interval of the booking
   */
  public DateInterval getDateInterval()
  {
    return dates;
  }

  public void addExtraBed() {
    extraBed = true;
  }
  /**
   * @return method that returns the booked room
   */
  public Room getBookedRoom()
  {
    return room;
  }

  /**
   * @return method returns if the guests have checked in
   */
  public boolean isCheckIn(){return this.checkIn;}

  /**
   * @return method returns if the guests will check in after 6 p.m.
   */
  public boolean isLateCheckIn(){return this.lateCheckIn;}

  /**
   * method is run when the guest checks in
   */
  public void checkedIn(){this.checkIn = true;}

  /**
   * method is run when the guest lets the hotel know that they will arrive after  6 p.m.
   */
  public void willCheckInLate(){this.lateCheckIn = true;}

  /**
   * @return method used to convert string objects into a string
   */
  public String toString()
  {
   String status ="";
   if (checkIn) status = "Checked In";
   else status = "NOT Checked In";

  return "Guest: " + getBookingGuest().getFirstName() + " " + getBookingGuest().getLastName() +
        "\n"  + getBookedRoom() + "\n" + getDateInterval() + "\n" + "Status: " + status;
  }

  /**
   * @param obj the object that is being compared
   * @return method returns if the compared objects are the bookings containing same information
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Booking)) //check if obj is the same type
    {
      return false;
    }
    Booking other = (Booking) obj; //cast obj to this type

    //check if the fields are the same
    return guests.equals(other.guests) && room.equals(other.room)
        && dates.equals(other.dates);
  }
}
