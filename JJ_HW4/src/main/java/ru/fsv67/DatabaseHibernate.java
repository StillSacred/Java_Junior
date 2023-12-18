package ru.fsv67;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/*
 * 2. С помощью JPA(Hibernate) выполнить:
 * 2.1 Описать сущность Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 */
public class DatabaseHibernate {
    final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    Book book;

    /**
     * Метод создания и заполнения таблицы BOOKS
     */
    public void createTableBook() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Author author1 = new Author("Дэвид Томас");
            session.persist(author1);

            Author author2 = new Author("Роберт Мартин");
            session.persist(author2);

            Author author3 = new Author("Стив Макконнелл");
            session.persist(author3);

            Author author4 = new Author("Эрих Гамма");
            session.persist(author4);

            Author author5 = new Author("Эрик Фримен");
            session.persist(author5);

            Author author6 = new Author("Мартин Фаулер");
            session.persist(author6);

            Author author7 = new Author("Алан Купер");
            session.persist(author7);

            Author author8 = new Author("Дональд Кнут");
            session.persist(author8);

            Author author9 = new Author("Адитья Бхаргава");
            session.persist(author9);

            Author author10 = new Author("Томас Кормен");
            session.persist(author10);


            book = new Book("Программист-прагматик", author1);
            session.persist(book);

            book = new Book("Чистый код", author2);
            session.persist(book);

            book = new Book("Совершенный код", author3);
            session.persist(book);

            book = new Book("Паттерны объектно-ориентированного проектирования", author4);
            session.persist(book);

            book = new Book("Head First. Паттерны проектирования", author5);
            session.persist(book);

            book = new Book("Шаблоны корпоративных приложений", author6);
            session.persist(book);

            book = new Book("Идеальный программист", author2);
            session.persist(book);

            book = new Book("Психбольница в руках пациентов", author7);
            session.persist(book);

            book = new Book("Искусство программирования", author8);
            session.persist(book);

            book = new Book("Грокаем алгоритмы", author9);
            session.persist(book);

            book = new Book("Алгоритмы. Построение и анализ", author10);
            session.persist(book);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска в базе данных по автору
     *
     * @param searchAuthor строка (имя автора)
     */
    public void getBooksByAuthor(String searchAuthor) {
        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery(
                            "FROM Book WHERE author = (FROM Author WHERE nameAuthor = :name_author)", Book.class
                    ).setParameter("name_author", searchAuthor)
                    .getResultList();

            books.forEach(System.out::println);
        }
    }

    /**
     * Метод закрытия сессии
     */
    public void closedSession() {
        sessionFactory.close();
    }
}