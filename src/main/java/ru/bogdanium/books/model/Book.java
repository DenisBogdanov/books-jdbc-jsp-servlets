package ru.bogdanium.books.model;

public class Book {
    private Integer id;
    private String title;
    private Author author;
    private Integer authorId;

    public static class Builder {
        private String title;

        private Integer id = null;
        private Author author = null;
        private Integer authorId = null;

        public Builder(String title) {
            this.title = title;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder author(Author author) {
            this.author = author;
            return this;
        }

        public Builder authorId(Integer authorId) {
            this.authorId = authorId;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.authorId = builder.authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
