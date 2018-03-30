package dao.impls;

import DBUtil.DBUtil;
import dao.IBookDao;
import pojo.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IBookDaoImpl implements IBookDao {

    public String SQL = "";

    @Override
    public boolean addBook(String bookName, String author, float price) {
        SQL = "INSERT INTO book values (null, ?, ?, ?)";

        boolean result = false;

        try (

                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, author);
            preparedStatement.setFloat(3, price);
            if (preparedStatement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteBook(String bookName) throws SQLException, ClassNotFoundException {
        SQL = "DELETE FROM book WHERE bookname = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, bookName);

        boolean result = false;

        if (preparedStatement.executeUpdate() != 0) result = true;

        return result;
    }

    @Override
    public List<Book> queryAllBooks() throws SQLException, ClassNotFoundException {
        SQL = "SELECT * FROM book";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);


        ResultSet resultSet = preparedStatement.executeQuery();


        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setBookname(resultSet.getString("bookname"));
            book.setAuthor(resultSet.getString("author"));
            book.setPrice(Float.parseFloat(resultSet.getString("price")));
            books.add(book);
        }
        return books;
    }

    public int getId(String book) throws SQLException, ClassNotFoundException {
        SQL = "select id from book WHERE bookname = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, book);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }

        return id;
    }

    @Override
    public boolean changeBooks(String book, String author, float price) throws SQLException, ClassNotFoundException {

        int id = getId(book);
        SQL = "UPDATE book SET author = ?, price = ? WHERE id = ?";

        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, author);
        preparedStatement.setFloat(2, price);
        preparedStatement.setInt(3, id);

        System.out.println(id + book + "" + author + "" + price);

        boolean result = false;

        if (preparedStatement.executeUpdate() != 0) {
            result = true;
        }

        connection.close();
        preparedStatement.close();
        return result;
    }

    @Override
    public Book queryBook(String bookname) {
        SQL = "SELECT * FROM book WHERE bookname = ?";
        Book book = null;
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, bookname);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("dao " + bookname);
            while (resultSet.next()) {
                book = new Book();
                System.out.println(resultSet.getString("bookname"));
                book.setBookname(resultSet.getString("bookname"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getFloat("price"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }


}
