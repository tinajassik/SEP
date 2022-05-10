//package model;
//import java.util.ArrayList;
//
//public class Booking
//{
//  //creating fields/attributes for class Booking
//  private GuestList guests;
//  private Room room;
//  private DateInterval dates;
//
//  //3 arguments constructor
//  public Booking(GuestList guests, RoomList rooms, DateInterval dates)
//  {
//    this.guests=guests;
//    this.room=room;
//    this.dates=dates;
//  }
//
//  //getBookingGuest() method that returns the first person from the ArrayList(the person that made the booking)
//  public Guest getBookingGuest()
//  {
//    return guests.getFirstGuest();
//  }
//
//  //getDateInterval() method that returns the date interval of the booking
//  public DateInterval getDateInterval()
//  {
//    return dates;
//  }
//
//  //getBookedRoom() method that returns the booked room
//  public Room getBookedRoom()
//  {
//    return room;
//  }
//
//  //toString() method used to convert string objects into a string
//  public String toString()
//  {
//    String s="";
//    s+= "Guests: " + "/n" + guests.toString();
//    s+= room.toString() + "/n";
//    s+= dates.toString() + "/n";
//    return s;
//  }
//
//  //equals() method for checking if obj is the same object as Booking
//  public boolean equals(Object obj)
//  {
//    if (!(obj instanceof Booking)) //check if obj is the same type
//    {
//      return false;
//    }
//    Booking other = (Booking) obj; //cast obj to this type
//
//    //check if the fields are the same
//    return guests.equals(other.guests) && room.equals(other.room)
//        && dates.equals(other.dates);
//  }
//}
