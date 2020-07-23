package ru.otus.springframework16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework16.domain.Author;
import ru.otus.springframework16.exception.AuthorAlreadyExistsException;
import ru.otus.springframework16.exception.AuthorNotFoundException;
import ru.otus.springframework16.repository.AuthorRepository;

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
    @Transactional
    public void insert() {
        Author author = shellService.authorInsert();
        if (author != null){
            try {
                authorRepository.save(author);
                messageService.messagePrintOut("author.success.insert", new Object[] {author.getAuthorID(), author.getName()});
            } catch (Exception e) {
                messageService.messagePrintOut("author.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.insert");
        }
    }

    @Override
    @Transactional
    public void update() {
        Author author = shellService.authorUpdate();
        if (author != null){
            try {
                authorRepository.save(author);
                messageService.messagePrintOut("author.success.update", new Object[] {author.getAuthorID(), author.getName()});
            } catch (Exception e) {
                messageService.messagePrintOut("author.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.update");
        }
    }

    @Override
    @Transactional
    public void delete() {
        Author author = shellService.authorDelete();
        if (author.getAuthorID() > 0){
            try {
                authorRepository.delete(author);
                messageService.messagePrintOut("author.success.delete", new Object[] {author.getAuthorID()});
            } catch (Exception e) {
                messageService.messagePrintOut("author.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.delete");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        List<Author> list = authorRepository.findAll();
        shellService.authorListOutput(list);
    }
}
