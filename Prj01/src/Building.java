import java.util.ArrayList;

public class Building {
    private String buildingName;

    public Building(String name) {
        this.buildingName = name;
    }

    ArrayList<Room> rooms = new ArrayList<>();

    public void addRoom(String roomName, double roomMeters, int windowsInRoom) throws IlluminanceTooMuchException {
        Room r = new Room(roomName, roomMeters, windowsInRoom);
        rooms.add(r);
    }

    public Room getRoom(String searchName) {
        for (Room room : rooms) {
            if ((room.getRoomName()).equals(searchName)) {
                return room;
            }
        }
        return null;
    }

    public void describe() {
        System.out.println(buildingName);
        for (Room room : rooms) {
            System.out.println("\t" + room.getRoomName());
            System.out.println("\t\tIllumination = " + room.luxCounter + " lux (" + room.getWindows() + " windows x700 lux, " + room.bulbs.size() + " light bulb(s))");
            room.showLightBulbs();
            if (room.furSpaceCounter != 0) {
                System.out.println("\t\tRoom space = " + room.getRoomMeters() + " square meters; " + room.furSpaceCounter + " square meters equipped; " +
                        (room.getRoomMeters() - room.furSpaceCounter) + " square meters or " + Math.rint(100.0 * ((room.getRoomMeters() - room.furSpaceCounter) * 100 / room.getRoomMeters())) / 100.0 + "% available");
            } else {
                System.out.println("\t\tRoom space = " + room.getRoomMeters() + " square meters; " + room.furSpaceCounter + " square meters equipped; " +
                        room.getRoomMeters() + " square meters or 100% available");
            }
            System.out.println("\t\tFurniture: ");
            room.showFurniture();
        }
    }
}
