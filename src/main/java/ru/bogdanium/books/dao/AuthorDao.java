package ru.bogdanium.books.dao;

import ru.bogdanium.books.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author findById(Integer id);

    boolean add(Author author);
}
