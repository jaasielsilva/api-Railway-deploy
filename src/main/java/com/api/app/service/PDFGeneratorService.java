package com.api.app.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.api.app.model.User;
import com.api.app.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {

    private final UserRepository userRepository;

    public PDFGeneratorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void export(HttpServletResponse response) throws IOException {
        // Consulta os usuários no banco de dados
        List<User> users = userRepository.findAll(); 

        // Criando o documento PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        
        // Título
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("Usernames List", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        
        // Adicionando o título no documento
        document.add(title);
        
        // Adicionando um espaçamento entre o título e a lista de usernames
        document.add(Chunk.NEWLINE);

        // Adicionando os usernames no documento
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        
        for (User user : users) {
            Paragraph paragraph = new Paragraph(user.getUsername(), fontParagraph);
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph);
        }

        // Fechando o documento
        document.close();
    }
}
