public class Furniture implements Marker {

    private String type;
    private double metersTo;
    private double metersFrom;

    public double getMetersTo() {
        return metersTo;
    }

    public String getType() {
        return type;
    }

    public double getMetersFrom() {
        return metersFrom;
    }

    public Furniture(String type, double metersTo) {
        this.type = type;
        this.metersTo = metersTo;
        if (this.metersTo <= 0) {
            System.out.println("The footage of furniture must be positive.");
            System.exit(1);
        }
    }

    public Furniture(String type, double metersFrom, double metersTo) {
        this.type = type;
        this.metersFrom = metersFrom;
        this.metersTo = metersTo;
        if (this.metersFrom <= 0) {
            System.out.println("The footage of furniture must be positive.");
            System.exit(1);
        }
        if (this.metersTo <= 0) {
            System.out.println("The footage of furniture must be positive.");
            System.exit(1);
        }
    }
}
