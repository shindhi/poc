package com.invillia.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(
            final HttpServletRequest req,
            final HttpServletResponse resp
    ) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("Hello World");

        final Iterator<String> it = req.getHeaderNames().asIterator();
        it.forEachRemaining(i -> {
            System.out.println(i + ": " + req.getHeader(i));
        });
    }

    @Override
    protected void doPost(
            final HttpServletRequest req,
            final HttpServletResponse resp
    ) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("do post");
    }
}
