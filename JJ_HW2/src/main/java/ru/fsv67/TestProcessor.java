package ru.fsv67;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestProcessor {
    /**
     * Данный метод находит все void методы без аргументов в классе, и запускает их.
     * Для запуска создается тестовый объект с помощью конструктора без аргументов.
     *
     * @param testClass получаемый класс для тестирования
     */
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \""
                    + testClass.getName() + "\" Не найден конструктор без аргументов");
        }
        Object testObject;
        try {
            testObject = declaredConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            checkTestMethod(method);
            if (method.isAnnotationPresent(BeforeEach.class) && !method.isAnnotationPresent(Skip.class)) {
                methods.addFirst(method);
            }
            if (method.isAnnotationPresent(AfterEach.class) && !method.isAnnotationPresent(Skip.class)) {
                methods.addLast(method);
            }
            if (method.isAnnotationPresent(MyTestAnnotation.class) && !method.isAnnotationPresent(Skip.class)) {

                methods.add(method);
            }
        }
        methods.sort((o1, o2) -> {
            if (o1.isAnnotationPresent(MyTestAnnotation.class) && o2.isAnnotationPresent(MyTestAnnotation.class)) {
                return o1.getAnnotation(MyTestAnnotation.class).order() - o2.getAnnotation(MyTestAnnotation.class).order();
            }
            return 0;
        });
        methods.forEach(it -> runMethod(it, testObject));
    }


    private static void checkTestMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Метод \""
                    + method.getName() + "\" должен быть void и не иметь аргументов");
        }
    }

    private static void runMethod(Method testMethod, Object testObject) {
        try {
            testMethod.invoke(testObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
        }
    }
}