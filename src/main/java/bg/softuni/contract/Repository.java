package bg.softuni.contract;

import bg.softuni.model.Garbage;

public interface Repository {

    void addGarbage(Garbage garbage);

    void removeGarbage(Garbage garbage);

    Iterable<Garbage> getAllGarbage();
}
