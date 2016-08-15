package bg.softuni.contract;

public interface Service extends ProcessingData {

    String addNewGarbage(String command);

    String getStatusOfStation();
}
