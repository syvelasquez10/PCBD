package co.edu.uniandes.andar.services;

import co.edu.uniandes.andar.PersistenceManager;
import co.edu.uniandes.andar.converter.BookingConverter;
import co.edu.uniandes.andar.models.BookingDTO;
import co.edu.uniandes.andar.models.BookingEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
public class BookingService {

    @PersistenceContext(unitName = "AplicacionMundialPU")
    EntityManager entityManager;
    
    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    public BookingDTO createBook(BookingDTO dto) {
        
        BookingEntity bE = BookingConverter.basicDTO2Entity(dto);
         try {
            entityManager.getTransaction().begin();
            entityManager.persist(bE);
            entityManager.getTransaction().commit();
            entityManager.refresh(bE);
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            bE = null;
        } finally {
        	entityManager.clear();
        	entityManager.close();
        }
        return BookingConverter.basicEntity2DTO(bE);
    }

    @GET
    public List<BookingDTO> getBooks() {
        Query q = entityManager.createQuery("select u from BookEntity u");
        return BookingConverter.listEntity2DTO(q.getResultList());
    }

    @GET
    @Path("{id: \\d+}")
    public BookingDTO getBook(@PathParam("id") Long id) {
        return BookingConverter.basicEntity2DTO(entityManager.find(BookingEntity.class, id));
    }

    @PUT
    @Path("{id: \\d+}")
    public BookingDTO updateBook(@PathParam("id") Long id, BookingDTO dto) {
        BookingEntity entity =null; 
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.merge(BookingConverter.basicDTO2Entity(dto));
            entityManager.getTransaction().commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
        	entityManager.clear();
        	entityManager.close();
        }
        return BookingConverter.basicEntity2DTO(entity);
    }

    @DELETE
    @Path("{id: \\d+}")
    public BookingDTO deleteBook(@PathParam("id") Long id) {
        BookingEntity entity =null; 
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.find(BookingEntity.class, id);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
        	entityManager.clear();
        	entityManager.close();
        }
       return BookingConverter.basicEntity2DTO(entity);
    }
    
    @OPTIONS
    public Response cors(@javax.ws.rs.core.Context HttpHeaders requestHeaders) {
        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "AUTHORIZATION, content-type, accept").build();
    }
}
