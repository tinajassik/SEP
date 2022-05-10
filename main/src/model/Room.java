package model;

/**
 * Room class.
 * Returns the room number, the room type, the room price, and the room status.
 * @author Dragos Cotaga
 * @version 1.1
 * @see Room
 * @since 2022-05-10
 */

// Method: getRoomNumber() - returns the room number of the room object being called on in the method call (Room room = new Room(1); room.getRoomNumber();)
public class Room {
    private String roomNumber, roomType;
    private boolean extraBed, available;
    private double price;


    // Constructor for the Room class. Initializes the room number, room type, extra bed, available, and price variables.
    public Room(String roomNumber, String roomType, boolean extraBed, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.extraBed = extraBed;
        this.price = price;
        this.available = true;
    }

    // Method: getRoomNumber() - returns the room number of the room object being called on in the method call (Room room = new Room(1); room.getRoomNumber();)
    public String getRoomNumber() {
        return roomNumber;
    }

    // Method: setRoomNumber() - sets the room number of the room object being called on in the method call (Room room = new Room(1); room.setRoomNumber(2);)
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Method: getRoomType() - returns the room type of the room object being called on in the method call (Room room = new Room(1); room.getRoomType();)
    public String getRoomType() {
        return roomType;
    }

    // Method: setRoomType() - sets the room type of the room object being called on in the method call (Room room = new Room(1); room.setRoomType("Single");)
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Method: isExtraBed() - returns the extra bed status of the room object being called on in the method call (Room room = new Room(1); room.isExtraBed();)
    public boolean isExtraBed() {
        return extraBed;
    }

    // Method: setExtraBed() - sets the extra bed status of the room object being called on in the method call (Room room = new Room(1); room.setExtraBed(true);)
    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    // Method: isAvailable() - returns true if the room is available, false if it is not.
    public boolean isAvailable() {
        return available;
    }

    // Method: setAvailable() - sets the room to available or not available.
    public void setAvailability(boolean available) {
        this.available = available;
    }

    // Method: getPrice() - returns the price of the room.
    public double getPrice() {
        return price;
    }

    // Method: setPrice() - sets the price of the room.
    public void setPrice(double price) {
        this.price = price;
    }

    // Method: GuestList() - returns the list of guests.
    public GuestList getAllGuest() {
        return null;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\nRoom Type: " + roomType + "\nExtra Bed: " + extraBed + "\nPrice: " + price;
    }
}



