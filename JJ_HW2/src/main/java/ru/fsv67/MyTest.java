package ru.fsv67;

public class MyTest {

    @MyTestAnnotation(order = 5)
    public static void thirdTest() {
        System.out.println("Third test запущен");
    }

    @MyTestAnnotation(order = -2)
    public static void firstTest() {
        System.out.println("First test запущен");
    }

    @MyTestAnnotation(order = 10)
    @Skip
    public static void skipTest() {
        System.out.println("Skip test запущен");
    }

    @MyTestAnnotation
    public static void secondTest() {
        System.out.println("Second test запущен");
    }

    @BeforeEach
    public static void beforeEachTest() {
        System.out.println("before each test запущен");
    }

    @AfterEach
    public static void afterEachTest() {
        System.out.println("after each test запущен");
    }

    @BeforeEach
    public static void beforeEachTestTwo() {
        System.out.println("before each test two запущен");
    }
}