package bg.softuni.framework.object.contract;


public interface AnnotationScanner {
    int scan(String srcFolder, String... packagesToScan);
    Class<?> getAnnotationType();
    ObjectDefinitionRegistry getRegistry();
}
