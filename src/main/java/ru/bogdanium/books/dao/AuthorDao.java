package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(Integer id);

    boolean add(Author author);

    boolean update(Author author);

    boolean delete(Author author);
}
