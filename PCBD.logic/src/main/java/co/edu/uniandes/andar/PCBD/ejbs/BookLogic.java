package co.edu.uniandes.andar.PCBD.ejbs;

import co.edu.uniandes.andar.PCBD.api.IBookLogic;
import co.edu.uniandes.andar.PCBD.converters.BookConverter;
import co.edu.uniandes.andar.PCBD.dtos.BookDTO;
import co.edu.uniandes.andar.PCBD.entities.BookEntity;
import co.edu.uniandes.andar.PCBD.persistence.BookPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookLogic implements IBookLogic {

    @Inject 
    private BookPersistence persistence;

    public List<BookDTO> getBooks() {
        return BookConverter.listEntity2DTO(persistence.findAll());
    }

    public BookDTO getBook(Long id) {
        return BookConverter.basicEntity2DTO(persistence.find(id));
    }

    public BookDTO createBook(BookDTO dto) {
        BookEntity entity = BookConverter.basicDTO2Entity(dto);
        persistence.create(entity);
        return BookConverter.basicEntity2DTO(entity);
    }

    public BookDTO updateBook(BookDTO dto) {
        BookEntity entity = persistence.update(BookConverter.basicDTO2Entity(dto));
        return BookConverter.basicEntity2DTO(entity);
    }

    public void deleteBook(Long id) {
        persistence.delete(id);
    }
}
