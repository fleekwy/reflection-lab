package ru.vsu;

import java.io.IOException;

/**
 * Entry point to demonstrate injection.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Injector injector = new Injector();
            SomeBean sb = injector.inject(new SomeBean());
            sb.foo(); // Should print "AC"
        } catch (IOException e) {
            System.err.println("Failed to load properties: " + e.getMessage());
        }
    }
}