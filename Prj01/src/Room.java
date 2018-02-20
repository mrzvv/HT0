import java.util.ArrayList;

public class Room {
    private double roomMeters;
    private int windows;
    final static int WINDOWSLUX = 700;
    final static int MINLUX = 300;
    final static int MAXLUX = 4000;
    int luxCounter = 0;
    private String roomName;

    public double getRoomMeters() {
        return roomMeters;
    }

    public int getWindows() {
        return windows;
    }

    public String getRoomName() {
        return roomName;
    }

    public Room(String roomName, double meters, int windows) throws IlluminanceTooMuchException {
        this.roomName = roomName;
        this.roomMeters = meters;
        this.windows = windows;
        luxCounter = windows * WINDOWSLUX;
        if (luxCounter > MAXLUX) {
            throw new IlluminanceTooMuchException();
        }
        if (this.roomMeters <= 0) {
            System.out.println("Room footage must be positive.");
            System.exit(1);
        }
        if (this.windows < 0) {
            System.out.println("The quantity of windows can't be negative.");
            System.exit(1);
        }
    }

    int furSpaceCounter = 0;
    ArrayList<Furniture> furniture = new ArrayList<>();
    ArrayList<LightBulb> bulbs = new ArrayList<>();

    public <T extends Marker> void add(T obj) throws IlluminanceTooMuchException, SpaceUsageTooMuchException, IlluminanceTooLittleException {
        if (obj.getClass() == Furniture.class) {
            double freeSpace = (roomMeters * 0.7);
            Furniture f = (Furniture) obj;
            furniture.add(f);
            furSpaceCounter += f.getMetersTo();
            if ((double) furSpaceCounter > freeSpace) {
                throw new SpaceUsageTooMuchException();
            }
        } else if (obj.getClass() == LightBulb.class) {
            LightBulb lb = (LightBulb) obj;
            bulbs.add(lb);
            luxCounter += lb.getLux();
            if (luxCounter > MAXLUX) {
                throw new IlluminanceTooMuchException();
            }
            if (luxCounter < MINLUX) {
                throw new IlluminanceTooLittleException();
            }
        }
    }

    public void showLightBulbs() {
        for (LightBulb lights : bulbs) {
            System.out.println("\t\t\tLight bulb: " + lights.getLux());
        }
    }

    public void showFurniture() {
        if (furniture.size() == 0) {
            System.out.println("\t\t\tThere's no furniture in this room.");
        } else for (Furniture fur : furniture) {
            if (fur.getMetersFrom() != 0) {
                System.out.println("\t\t\t" + fur.getType() + ", equips " + fur.getMetersFrom() + "-" + fur.getMetersTo() + " square meter(s)");
            } else {
                System.out.println("\t\t\t" + fur.getType() + ", equips " + fur.getMetersTo() + " square meter(s)");
            }
        }
    }
}

