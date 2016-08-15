package bg.softuni.framework.object.component;

import java.io.File;
import java.util.regex.Pattern;

import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.object.contract.AnnotationScanner;
import bg.softuni.framework.object.contract.ObjectDefinitionRegistry;

public class ComponentScanner implements AnnotationScanner {
    private ObjectDefinitionRegistry registry;

    public ComponentScanner(ObjectDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int scan(String srcFolder, String... packagesToScan) {
        int startCount = registry.getObjectCount();

        for (int i = 0; i < packagesToScan.length; i++) {
            File folder = new File(getPath(srcFolder, packagesToScan[i]));

            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    scan(srcFolder, getPackage(srcFolder, file.getPath()));
                }

                if (!file.isFile() || !file.getName().endsWith(".java")) {
                    continue;
                }

                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                Class<?> currClass;
                try {
                    currClass = Class.forName(packagesToScan[i] + "." + className);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Error while parsing for component annotations.");
                }

                if (!currClass.isAnnotationPresent(Component.class)) {
                    continue;
                }

                Component component = currClass.getAnnotation(Component.class);
                registry.register(new ComponentDefinition(component.name(), currClass));
            }
        }

        return registry.getObjectCount() - startCount;
    }

    private String getPath(String srcFolder, String packageName) {
        return srcFolder + packageName.replaceAll(Pattern.quote("."), "/");
    }

    private String getPackage(String srcFolder, String path) {
        return path.replaceFirst(Pattern.quote(srcFolder), "").replaceAll("/", ".");
    }

    @Override
    public Class<?> getAnnotationType() {
        return Component.class;
    }

    @Override
    public ObjectDefinitionRegistry getRegistry() {
        return registry;
    }
}
