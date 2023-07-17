package model;

import java.util.Objects;

public class Room  implements IRoom {

    private String roomNumber;
    private double price;
    private RoomType roomType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getRoomNumber().equals(room.getRoomNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber());
    }

    public Room(String roomNumber, double price, RoomType roomType) {
        super();
        this.roomNumber =  roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public final String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public final Double getRoomPrice() {
        return price;
    }

    @Override
    public final RoomType getRoomType() {
        return roomType;
    }

    @Override
    public final Boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
