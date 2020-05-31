package ru.otus.springframework09.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework09.domain.Genre;
import ru.otus.springframework09.repository.GenreRepository;

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
    @Transactional
    public void insert() {
        Genre genre = shellService.genreInsert();
        if (genre != null){
            try {
                genreRepository.save(genre);
                messageService.messagePrintOut("genre.success.insert", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (Exception e) {
                messageService.messagePrintOut("genre.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.insert");
        }
    }

    @Override
    @Transactional
    public void update() {
        Genre genre = shellService.genreUpdate();
        if (genre != null){
            try{
                genreRepository.save(genre);
                messageService.messagePrintOut("genre.success.update", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (Exception e) {
                messageService.messagePrintOut("genre.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.update");
        }
    }

    @Override
    @Transactional
    public void delete() {
        Genre genre = shellService.genreDelete();
        if (genre.getGenreID() > 0){
            try {
                genreRepository.delete(genre);
                messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
            } catch (Exception e) {
                messageService.messagePrintOut("genre.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.delete");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        List<Genre> list = genreRepository.findAll();
        shellService.genreListOutput(list);
    }

}
