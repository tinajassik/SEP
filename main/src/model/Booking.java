package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class containing all information about a Booking object.
 *
 * @author Andreea Asimine, Aleksandra Adamczak
 * @version 1.2
 */

public class Booking implements Serializable
{
  //creating fields/attributes for class Booking
  private GuestList guests;
  private Room room;
  private DateInterval dates;
  private boolean checkIn;
  private boolean lateCheckIn;
  private boolean extraBed;

  /**
   * Three-arguments constructor.
   *
   * @param guests The guest list for a particular room
   * @param room   The booked room
   * @param dates  The dates of arrival and departure
   */
  public Booking(GuestList guests, Room room, DateInterval dates)
  {
    this.guests = guests;
    this.room = room;
    this.dates = dates;
    checkIn = false;
    extraBed = false;
    lateCheckIn = false;
  }

  /**
   * The method returns the first guest of the booking.
   *
   * @return the first person from the ArrayList(the person that made the booking)
   */
  public Guest getBookingGuest()
  {
    return guests.getFirstGuest();
  }

  /**
   * The method returns true if extra bed was desired or false if it is not.
   *
   * @return boolean true if extra bed is set or false it is not
   */
  public boolean hasExtraBed()
  {
    return extraBed;
  }

  /**
   * The method returns all the guests from the booking.
   *
   * @return the list of guests.txt from the booking
   */
  public GuestList getGuests()
  {
    return guests;
  }

  /**
   * The method returns arrival and departure dates.
   *
   * @return the date interval of the booking
   */
  public DateInterval getDateInterval()
  {
    return dates;
  }

  /**
   * The method sets extra bed to true if extra bed is needed.
   */
  public void addExtraBed()
  {
    extraBed = true;
  }

  /**
   * The method returns the room from the booking.
   *
   * @return the room from the booking
   */
  public Room getBookedRoom()
  {
    return room;
  }

  /**
   * The method sets the room to the booking.
   *
   * @param room which is being set as the room in the booking
   */
  public void setBookedRoom(Room room)
  {
    this.room = room;
  }

  /**
   * The method returns if the guests have a checked in or not.
   *
   * @return boolean true or false
   */
  public boolean isCheckIn()
  {
    return this.checkIn;
  }

  /**
   * The method returns true if the guests will check in after 6 p.m.
   *
   * @return boolean true or false
   */
  public boolean isLateCheckIn()
  {
    return this.lateCheckIn;
  }

  /**
   * The method set extra bed option to the false if the guest does not need extra bed.
   */
  public void removeExtraBed()
  {
    extraBed = false;
  }

  /**
   * The method sets checkIn to true.
   */
  public void checkedIn()
  {
    checkIn = true;
  }

  /**
   * The method sets lateCheckIn to true.
   */
  public void willCheckInLate()
  {
    this.lateCheckIn = true;
  }

  /**
   * The method sets lateCheckIn to false.
   */
  public void willNotCheckInLate()
  {
    lateCheckIn = false;
  }

  /**
   * The method returns a String representation of the Booking
   *
   * @return a String containing information about Booking Objects
   */
  public String toString()
  {
    String status = "";
    String bed = "";
    String late = "";
    if (checkIn)
      status = "Checked In";
    else
      status = "NOT Checked In";

    if (extraBed)
      bed = " Yes";
    else
      bed = " No";

    if (lateCheckIn)
      late = "Will Check-In after 18:00";

    return "Guest: " + getBookingGuest().getFirstName() + " "
        + getBookingGuest().getLastName() + "\n" + getBookedRoom() + "\n"
        + getDateInterval() + "\n" + "Status: " + status + "\n" + "Extra bed: "
        + bed + "\n" + late;
  }

  /**
   * The method compares object to the booking object.
   *
   * @param obj the object that is being compared
   * @return if the compared objects are the instance of Booking class and containing same information then returns true and if not it returns false
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
