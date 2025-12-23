package ru.vsu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {
    private Injector injector;

    @BeforeEach
    void setUp() throws Exception {
        injector = new Injector(); 
    }

    @Test
    void testInjection() throws Exception {
        SomeBean sb = injector.inject(new SomeBean());
        assertNotNull(sb);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        sb.foo();

        System.out.flush();
        System.setOut(old);

        String output = baos.toString().replace("\r\n", "\n").replace("\r", "\n");
        assertEquals("A\nC\n", output);
    }

    @Test
    void testMissingProperty() throws Exception {
        Properties props = new Properties();

        Injector customInjector = new Injector(props);

        SomeBean sb = new SomeBean();

        assertThrows(IllegalArgumentException.class,
                () -> customInjector.inject(sb),
                "Должно бросаться исключение, если нет свойства для интерфейса");
    }
}