public class LightBulb implements Marker {

    private int lux;

    public int getLux() {
        return lux;
    }

    public LightBulb(int lux) {
        this.lux = lux;
        if (this.lux <= 0) {
            System.out.println("The luminosity of a light bulb must be positive.");
            System.exit(1);
        }
    }
}
