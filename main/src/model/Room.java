package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A class representing a room with a room number, a room type, a room price, and a room status.
 *
 * @author Dragos Cotaga
 * @version 1.1
 */

public class Room implements Serializable
{
  private String roomNumber, roomType;
  private double price;

  /**
   * Three-argument constructor.
   *
   * @param roomNumber the room number
   * @param roomType   the room type
   * @param price      the room price
   */
  public Room(String roomNumber, String roomType, double price)
  {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.price = price;
  }

  /**
   * Sets the room type and price based on the room number.
   *
   * @param roomNumber is number of the room
   */
  public Room(String roomNumber)
  {
    this.roomNumber = roomNumber;

    switch (roomNumber)
    {
      case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10":
        roomType = "Single Room";
        price = 129;
        break;
      case "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32":
        roomType = "Double Room - King Size Bed";
        price = 169;
        break;
      case "33", "34", "35", "36", "37":
        roomType = "Double Room - Twin Size Bed";
        price = 169;
        break;
      case "38", "39", "40":
        roomType = "Single Suite";
        price = 259;
        break;
      case "41":
        roomType = "Double Suite";
        price = 339;
        break;
      case "42":
        roomType = "Triple Suite";
        price = 399;
        break;
      default:
        break;
    }
  }

  /**
   * Gets the room number.
   *
   * @return the room number
   */
  public String getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * Sets the room number.
   *
   * @param roomNumber the number that the room number will be set to
   */
  public void setRoomNumber(String roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  /**
   * Gets the room type.
   *
   * @return the room type
   */
  public String getRoomType()
  {
    return roomType;
  }

  /**
   * Gets price of the room.
   *
   * @return the price of the room
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * Returns a string representation of the room.
   *
   * @return a string representation of the room
   */
  @Override public String toString()
  {
    return "Room Number: " + roomNumber + "\n" + "Room Type: " + roomType + "\n"
        + "Price: " + price;
  }

  /**
   * Compares the Room objects with other given object.
   *
   * @param o the object to compare
   * @return true if given object is instance of Room class and is equal to Room object, else returns false
   */
  public boolean equals(Object o)
  {
    if (!(o instanceof Room))
      return false;

    Room other = (Room) o;
    return roomNumber.equals(other.roomNumber);
  }
}



