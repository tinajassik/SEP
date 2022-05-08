package model;

/**
 * Room class.
 *
 * @author Dragos Cotaga
 * @version 1.0
 * @see Room
 * @since 1.0
 */


public class Room {
    private String roomNumber, roomType;
    private boolean extraBed, available;
    private double price;

    public Room(String roomNumber, String roomType, boolean extraBed, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.extraBed = extraBed;
        this.price = price;
        this.available = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public GuestList getAllGuest() {
        return null;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\nRoom Type: " + roomType + "\nExtra Bed: " + extraBed + "\nPrice: " + price;
    }
}



