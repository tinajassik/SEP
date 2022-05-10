package model;

import java.util.ArrayList;

public class BookingList
{
  //creating fields/attributes for class Guest
  private ArrayList<Booking> bookings;

  //1 argument constructor
  public BookingList(ArrayList<Booking> bookings)
  {
    this.bookings=new ArrayList<Booking>();
  }

  //getBooking() method that returns a booking from a specific index in the ArrayList
  public Booking getBooking(int index)
  {
    return bookings.get(index);
  }

  //addBooking() method that adds a new booking to the ArrayList
  public void addBooking(Booking booking)
  {
    bookings.add(booking);
  }

  //getBookingByFullName() method that returns an ArrayList with bookings made by the same person
  public ArrayList getBookingByFullName(String firstName, String lastName)
  {
     ArrayList bookingsByFullName = new ArrayList<Booking>();

    for (int i = 0; i < bookings.size(); i++)
    {
      if(bookings.get(i).getBookingGuest().getFirstName().equals(firstName)
      && bookings.get(i).getBookingGuest().getLastName().equals(lastName))
        bookingsByFullName.add(bookings.get(i));
    }

    return bookingsByFullName;
  }

  //toString() method used to convert the ArrayList bookings into a string
  public String toString()
  {
    String s = "";

    for (int i = 0; i < bookings.size(); i++)
    {
      s+=bookings.get(i) + "n/";
    }
    return s;
  }

  //equals() method for checking if obj is the same object as BookingList
  public boolean equals(Object obj)
  {
    if(!(obj instanceof BookingList)) //check if obj is the same type
    {
      return false;
    }
    BookingList other = (BookingList) obj; //cast obj to this type

    //check if the fields are the same
    return bookings.equals(other.bookings);
  }



}
