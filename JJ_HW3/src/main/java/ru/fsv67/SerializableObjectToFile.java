package ru.fsv67;

import java.io.*;

/**
 * Класс для сериализации и десериализации объекта
 */
public class SerializableObjectToFile {
    /**
     * Метод записывающий объект в файл
     *
     * @param object записываемый объект
     * @param path   путь к файлу для записи
     * @param <T>    класс записываемого объекта
     */
    public <T> void writingObjectToFile(T object, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            objectOutput.writeObject(object);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Файл " + path + " не существует (" + e.getMessage() + ")");
            } else {
                System.out.println("Ошибка записи файла (" + e.getMessage() + ")");
            }
        }
    }

    /**
     * Метод чтения объекта из файла
     *
     * @param path путь к файлу для чтения
     */
    public void readingObjectFromFile(String path) {
        try (FileInputStream inputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            System.out.println(objectInputStream.readObject());
            File inputStreamFile = new File(path);
            inputStreamFile.delete();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Файл " + path + " не существует (" + e.getMessage() + ")");
            } else if (e instanceof ClassNotFoundException) {
                System.out.println("Класс считанный из файла " + path + " не найден (" + e.getMessage() + ")");
            } else {
                System.out.println("Ошибка чтения файла (" + e.getMessage() + ")");
            }
        }
    }
}