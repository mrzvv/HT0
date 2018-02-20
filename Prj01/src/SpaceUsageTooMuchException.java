public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException () {
        System.out.println("The limit of maximum space usage (70% of room space) has been exceeded.");
    }
}
