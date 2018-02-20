public class IlluminanceTooMuchException extends Exception {
    public IlluminanceTooMuchException() {
        System.out.println("The limit of maximum illumination (4,000 lux) has been exceeded.");
    }
}
