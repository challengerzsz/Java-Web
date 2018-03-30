package dao;

import pojo.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDao {
    boolean addBook(String bookName, String author, float price);

    boolean deleteBook(String bookName) throws SQLException, ClassNotFoundException;

    List<Book> queryAllBooks() throws SQLException, ClassNotFoundException;

    boolean changeBooks(String book, String author, float price) throws SQLException, ClassNotFoundException;

    Book queryBook(String bookname);
}
