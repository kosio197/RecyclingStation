package bg.softuni.repository;

import java.util.Collections;
import java.util.List;

import bg.softuni.contract.Repository;
import bg.softuni.framework.annotation.Component;
import bg.softuni.model.Garbage;

@Component
public class RepositoryImpl implements Repository {

    private List<Garbage> innerCollection;

    @Override
    public void addGarbage(Garbage garbage) {
        this.innerCollection.add(garbage);
    }

    @Override
    public void removeGarbage(Garbage garbage) {
        this.innerCollection.remove(garbage);
    }

    @Override
    public Iterable<Garbage> getAllGarbage() {

        return Collections.unmodifiableList(this.innerCollection);
    }

}
