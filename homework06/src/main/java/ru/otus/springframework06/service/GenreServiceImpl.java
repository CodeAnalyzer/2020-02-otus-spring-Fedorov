package ru.otus.springframework06.service;

import org.springframework.stereotype.Service;
import ru.otus.springframework06.domain.Genre;
import ru.otus.springframework06.exception.GenreAlreadyExistsException;
import ru.otus.springframework06.exception.GenreNotFoundException;
import ru.otus.springframework06.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final ShellService shellService;
    private final MessageService messageService;

    public GenreServiceImpl(GenreRepository genreRepository, ShellService shellService, MessageService messageService) {
        this.genreRepository = genreRepository;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    public void insert() {
        Genre genre = shellService.genreInsert();
        if (genre != null){
            try {
                genreRepository.insert(genre);
                messageService.messagePrintOut("genre.success.insert", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (GenreAlreadyExistsException e) {
                messageService.messagePrintOut("genre.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.insert");
        }
    }

    @Override
    public void update() {
        Genre genre = shellService.genreUpdate();
        if (genre != null){
            try{
                genreRepository.update(genre);
                messageService.messagePrintOut("genre.success.update", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.update");
        }
    }

    @Override
    public void delete() {
        Genre genre = shellService.genreDelete();
        if (genre.getGenreID() > 0){
            try {
                genreRepository.delete(genre);
                messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
            } catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Genre> list = genreRepository.findAll();
        shellService.genreListOutput(list);
    }

}
