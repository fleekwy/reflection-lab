package ru.vsu;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Injector for dependency injection via reflection and properties.
 */
public class Injector {
    private final Properties properties;

    public Injector() throws IOException {
        this(loadDefaultProperties());
    }

    public Injector(Properties properties) {
        this.properties = properties;
    }

    private static Properties loadDefaultProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream is = Injector.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new IOException("Properties file not found");
            }
            props.load(is);
        }
        return props;
    }

    public <T> T inject(T obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = properties.getProperty(interfaceName);
                if (implClassName == null) {
                    throw new IllegalArgumentException("No implementation for " + interfaceName);
                }
                Class<?> implClass = Class.forName(implClassName);
                Object implInstance = implClass.getDeclaredConstructor().newInstance();
                field.setAccessible(true);
                field.set(obj, implInstance);
            }
        }
        return obj;
    }
}