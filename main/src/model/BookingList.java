package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Booking objects
 * @author Andreea Asimine
 * @version 1.1
 */
public class BookingList implements Serializable
{
  // ArrayList for storing the Booking objects
  private ArrayList<Booking> bookings;

  //Constructor for initializing the ArrayList

  /**
   * No- argument constructor initializing BookingList
   */
  public BookingList()
  {
    bookings=new ArrayList<Booking>();
  }

  /**
   * Searches for a Booking in the booking list based on its index
   * @param index the index of the booking that is supposed to be found
   * @return the Booking at the index
   */
  //getBooking() method that returns a booking from a specific index in the ArrayList
  public Booking getBooking(int index)
  {
    return bookings.get(index);
  }

  /**
   * Adds a new booking to the BookingList
   * @param booking the Booking that is supposed to be added
   */
  public void addBooking(Booking booking)
  {
    bookings.add(booking);
  }

  /**
   * Removes a booking from the BookingList
   * @param booking the Booking that is supposed to be deleted from the BookingList
   */
  public void deleteBooking(Booking booking)
  {
   bookings.remove(booking);
  }

  /**
   * Searches for all bookings based on the first and last name of the person who made the booking
   * @param firstName first name of the person who made the booking
   * @param lastName last name of the person who made the booking
   * @return all Bookings made by a person with a certain first and last name
   */
  //getBookingByFullName() method that returns an ArrayList with bookings made by the same person
  public ArrayList getBookingsByFullName(String firstName, String lastName)
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

  /**
   * @return the number of Booking objects in the list
   */
  // Return how many Booking objects are in the list
  public int size()
  {
    return bookings.size();
  }

  /**
   * @return a String containing information about all Booking objects in the BookingList array
   */

  //toString() method used to convert the ArrayList bookings into a string
  public String toString()
  {
    String s = "";

    for (int i = 0; i < bookings.size(); i++)
    {
      s +="Booking: ";
      Booking temp = bookings.get(i);
      s += temp +"\n";
    }
    return s;
  }

  /**
   * Compares two objects and determines whether they are of the same type and contain the same information
   * @param obj the  object that is compared to a BookingList object
   * @return a true or false statement depending on the outcome of the comparison of the two objects
   */

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
