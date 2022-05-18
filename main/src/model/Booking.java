package model;
import java.util.ArrayList;
import java.io.Serializable;

public class Booking implements Serializable
{
  //creating fields/attributes for class Booking
  private GuestList guests;
  private Room room;
  private DateInterval dates;
  private boolean checkIn = false;
  private boolean lateCheckIn = false;
  private boolean extraBed = false;

  //3 arguments constructor
  public Booking(GuestList guests, Room room, DateInterval dates)
  {
    this.guests=guests;
    this.room=room;
    this.dates=dates;
  }

  //getBookingGuest() method that returns the first person from the ArrayList(the person that made the booking)
  public Guest getBookingGuest()
  {
    return guests.getFirstGuest();
  }

  //getGuestList() method that returns the list of guests for a specific room
  public GuestList getGuests(){ return guests;}

  //getDateInterval() method that returns the date interval of the booking
  public DateInterval getDateInterval()
  {
    return dates;
  }

  public void addExtraBed() {
    extraBed = true;
  }
  //getBookedRoom() method that returns the booked room
  public Room getBookedRoom()
  {
    return room;
  }

  //isCheckIn() method returns if the guestes have checked in
  public boolean isCheckIn(){return this.checkIn;}

  //isLateCheckIn method returns if the guests will check in after 6 p.m.
  public boolean isLateCheckIn(){return this.lateCheckIn;}

  //checkedIn method is run when the guest checks in
  public void checkedIn(){this.checkIn = true;}

  //willCheckInLate() method is run when the guest lets the hotel know that they will arrive after  6 p.m.
  public void willCheckInLate(){this.lateCheckIn = true;}


  //toString() method used to convert string objects into a string
  public String toString()
  {
   String status ="";
   if (checkIn) status = "Checked In";
   else status = "NOT Checked In";

  return "Guest: " + getBookingGuest().getFirstName() + " " + getBookingGuest().getLastName() +
        "\n"  + getBookedRoom() + "\n" + getDateInterval() + "\n" + "Status: " + status;
  }

  //equals() method for checking if obj is the same object as Booking
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
