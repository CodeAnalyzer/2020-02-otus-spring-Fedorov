package ru.otus.springframework05.service;

import org.springframework.stereotype.Service;
import ru.otus.springframework05.dao.AuthorDao;
import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.exception.AuthorAlreadyExistsException;
import ru.otus.springframework05.exception.AuthorNotFoundException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final ShellService shellService;

    public AuthorServiceImpl(AuthorDao authorDao, ShellService shellService) {
        this.authorDao = authorDao;
        this.shellService = shellService;
    }

    @Override
    public void insert() {
        Author author = shellService.authorInsert();
        if (author != null){
            try {
                authorDao.insert(author);
                shellService.messagePrintOut("author.success.insert", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorAlreadyExistsException e) {
                shellService.messagePrintOut("author.error.insert", e.getMessage());
            }
        }
        else{
            shellService.messagePrintOut("author.error.insert");
        }
    }

    @Override
    public void update() {
        Author author = shellService.authorUpdate();
        if (author != null){
            try {
                authorDao.update(author);
                shellService.messagePrintOut("author.success.update", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorNotFoundException e) {
                shellService.messagePrintOut("author.error.update", e.getMessage());
            }
        }
        else{
            shellService.messagePrintOut("author.error.update");
        }
    }

    @Override
    public void delete() {
        Author author = shellService.authorDelete();
        if (author.getAuthorID() > 0){
            try {
                authorDao.delete(author);
                shellService.messagePrintOut("author.success.delete", new Object[] {author.getAuthorID()});
            } catch (AuthorNotFoundException e) {
                shellService.messagePrintOut("author.error.delete", e.getMessage());
            }
        }
        else{
            shellService.messagePrintOut("author.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Author> list = authorDao.findAll();
        shellService.authorListOutput(list);
    }
}
