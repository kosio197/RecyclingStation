package bg.softuni.framework.object.contract;

public interface ObjectContainer {
    <T> T getInstance(Class<T> t);
}
