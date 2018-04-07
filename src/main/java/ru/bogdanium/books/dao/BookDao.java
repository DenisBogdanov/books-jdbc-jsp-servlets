package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;
import ru.bogdanium.books.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    List<Book> findByAuthor(Author author);

    Book findById(Integer id);

    boolean add(Book book);

    boolean update(Book book);

    boolean delete(Book book);
}
