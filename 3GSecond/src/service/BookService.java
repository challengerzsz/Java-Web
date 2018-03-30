package service;

import com.alibaba.fastjson.JSONObject;
import dao.impls.IBookDaoImpl;
import pojo.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    private IBookDaoImpl userDao = new IBookDaoImpl();

    public void addBook(HttpServletRequest request, HttpServletResponse response)  {
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        boolean result = userDao.addBook(bookName, author, price);

        if (result) try {
            try {
                request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    public void deleteBookByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");

        boolean result = false;
        try {
            result = userDao.deleteBook(bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (result) req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, resp);
    }

    public void queryBooks(HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("text/json");

//        PrintWriter printWriter = null;
//        try {
//            printWriter = resp.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<Book> books = null;
        try {
            books = userDao.queryAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("books", books);

        try {
            req.getRequestDispatcher("WEB-INF/jsp/showBooks.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        JSONObject jsonObject = new JSONObject();

//        jsonObject.put("books", books);
//        printWriter.println(jsonObject);
//        printWriter.close();
    }

    public void changeBook(HttpServletRequest req, HttpServletResponse resp) {

        String book = req.getParameter("bookName");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));


        boolean result = false;
        try {
            result = userDao.changeBooks(book, author, price);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (result) try {
            req.getRequestDispatcher("showBooks").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void queryBook(HttpServletRequest req, HttpServletResponse resp) {
        String bookname = req.getParameter("bookName");
        System.out.println(bookname);
        Book book = userDao.queryBook(bookname);

        req.setAttribute("book", book);

        try {
            req.getRequestDispatcher("/WEB-INF/jsp/showBook.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
