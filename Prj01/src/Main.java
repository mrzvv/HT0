public class Main {
    public static void main(String[] args) throws IlluminanceTooMuchException, SpaceUsageTooMuchException, IlluminanceTooLittleException {
    Building b1 = new Building("Building1");
    b1.addRoom("Room1", 200.5, 2);
    b1.getRoom("Room1").add(new LightBulb(200));
    b1.getRoom("Room1").add(new LightBulb(400));
    b1.getRoom("Room1").add(new Furniture("Table", 5, 7));
    b1.getRoom("Room1").add(new Furniture("Arm Chair", 1));
    b1.addRoom("Room2", 500, 3);
    b1.describe();
    }
}
