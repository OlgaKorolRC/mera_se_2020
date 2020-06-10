package VostrikovaE.Lesson8;

public class JsonDesirializeException extends Exception {
    private Integer id;
    private String Message;

    public JsonDesirializeException(String Message){
        super(Message);
    }
}
