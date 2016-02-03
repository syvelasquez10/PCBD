package co.edu.uniandes.andar.converter;


import co.edu.uniandes.andar.models.BookingDTO;
import co.edu.uniandes.andar.models.BookingEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class BookingConverter {

    public static BookingDTO basicEntity2DTO(BookingEntity entity) {
        if (entity != null) {
            BookingDTO dto = new BookingDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dto.setTimeStamp(entity.getTimeDay());
            return dto;
        } else {
            return null;
        }
    }

    public static BookingEntity basicDTO2Entity(BookingDTO dto) {
        if (dto != null) {
            BookingEntity entity = new BookingEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setTimeDay(dto.getTimeStamp());
            return entity;
        } else {
            return null;
        }
    }

    public static List<BookingDTO> listEntity2DTO(List<BookingEntity> entities) {
        List<BookingDTO> dtos = new ArrayList<BookingDTO>();
        if (entities != null) {
            for (BookingEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<BookingEntity> listDTO2Entity(List<BookingDTO> dtos) {
        List<BookingEntity> entities = new ArrayList<BookingEntity>();
        if (dtos != null) {
            for (BookingDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
