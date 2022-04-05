package br.com.crud.resource;

import br.com.crud.controller.PessoaController;
import br.com.crud.entity.Pessoa;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    private PessoaController pessoaController;

    @GET
    public List<Pessoa> findAll() {
        return Pessoa.listAll();
    }
    
    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id) {
    	Pessoa pessoaEntity = pessoaController.carregaPessoa(id);

        if (pessoaEntity == null) {
            return Response.ok("Pessoa não foi encontrada").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        return Response.ok(pessoaEntity).build();
    }
    

    @POST
    @Transactional
    public Response create(Pessoa pessoa) {
        Pessoa.persist(pessoa);
        return Response.ok(pessoa).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Pessoa pessoa) {
        Pessoa pessoaEntity = pessoaController.update(id, pessoa);
        if  (pessoaEntity == null) {
        	throw new WebApplicationException("Pessoa com id " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        return Response.ok(pessoaEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Pessoa pessoaEntity = pessoaController.carregaPessoa(id);

        if (pessoaEntity == null) {
            throw new WebApplicationException("Pessoa com id " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        pessoaEntity.delete();
        return Response.status(204).build();
    }
}