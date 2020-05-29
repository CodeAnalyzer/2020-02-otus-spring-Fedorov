package ru.otus.springframework08.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.springframework08.domain.Author;
import ru.otus.springframework08.repository.AuthorRepository;

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
                authorRepository.insert(author);
                messageService.messagePrintOut("author.success.insert", new Object[] {author.getName()});
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
        if (author.getAuthorID() != ""){
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
