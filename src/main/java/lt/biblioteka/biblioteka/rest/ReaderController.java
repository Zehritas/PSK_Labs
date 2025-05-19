package lt.biblioteka.biblioteka.rest;

import lt.biblioteka.biblioteka.persistence.ReadersDAO;
import lt.biblioteka.biblioteka.entities.Reader;
import lt.biblioteka.biblioteka.rest.dtos.ReaderDto;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;
import java.util.List;

@ApplicationScoped
@Path("/readers")
public class ReaderController {

    @Inject
    @Setter @Getter
    private ReadersDAO readersDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Reader> all = readersDAO.loadAll();

        List<ReaderDto> dtos = all.stream().map(reader -> {
            ReaderDto dto = new ReaderDto();
            dto.setId(reader.getId());
            dto.setName(reader.getName());
            return dto;
        }).collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Reader reader = readersDAO.findOne(id);
        if (reader == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ReaderDto dto = new ReaderDto();
        dto.setId(reader.getId());
        dto.setName(reader.getName());

        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ReaderDto readerDto) {
        Reader reader = new Reader();
        reader.setName(readerDto.getName());
        readersDAO.persist(reader);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Long readerId, ReaderDto readerDto) {
        try {
            Reader existingReader = readersDAO.findOne(readerId);
            if (existingReader == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            existingReader.setName(readerDto.getName());
            readersDAO.update(existingReader);
            return Response.ok().build();

        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
