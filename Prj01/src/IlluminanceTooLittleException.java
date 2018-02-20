public class IlluminanceTooLittleException extends Exception {
    public IlluminanceTooLittleException () {
        System.out.println("Minimal allowed lux value is 300.");
    }
}
