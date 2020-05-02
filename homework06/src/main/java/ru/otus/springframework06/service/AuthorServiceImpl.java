package ru.otus.springframework06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.springframework06.domain.Author;
import ru.otus.springframework06.exception.AuthorAlreadyExistsException;
import ru.otus.springframework06.exception.AuthorNotFoundException;
import ru.otus.springframework06.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final ShellService shellService;
    private final MessageService messageService;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, ShellService shellService, MessageService messageService) {
        this.authorRepository = authorRepository;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    public void insert() {
        Author author = shellService.authorInsert();
        if (author != null){
            try {
                authorRepository.insert(author);
                messageService.messagePrintOut("author.success.insert", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorAlreadyExistsException e) {
                messageService.messagePrintOut("author.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.insert");
        }
    }

    @Override
    public void update() {
        Author author = shellService.authorUpdate();
        if (author != null){
            try {
                authorRepository.update(author);
                messageService.messagePrintOut("author.success.update", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorNotFoundException e) {
                messageService.messagePrintOut("author.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.update");
        }
    }

    @Override
    public void delete() {
        Author author = shellService.authorDelete();
        if (author.getAuthorID() > 0){
            try {
                authorRepository.delete(author);
                messageService.messagePrintOut("author.success.delete", new Object[] {author.getAuthorID()});
            } catch (AuthorNotFoundException e) {
                messageService.messagePrintOut("author.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Author> list = authorRepository.findAll();
        shellService.authorListOutput(list);
    }
}
