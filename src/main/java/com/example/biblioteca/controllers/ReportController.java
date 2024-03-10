package com.example.biblioteca.controllers;

import com.example.biblioteca.models.Book;
import com.example.biblioteca.services.BookService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class ReportController {
    @Autowired
    private BookService service;
    @GetMapping("/create")
    public void reportBooks(HttpServletResponse response) throws JRException, IOException {
        List<Book> list = service.getAllLastBooks();
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/Last_Books.jasper");
        Map<String, Object> params = new HashMap<>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream,params,dataSource);
        response.setContentType("application/x-pdf");
        response.addHeader("Content-disposition", "filename=ReporteEmpresa.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
