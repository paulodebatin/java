package br.com.crud.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.crud.controller.ProdutoController;
import br.com.crud.entity.Produto;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    private ProdutoController produtoController;

    @GET
    public List<Produto> findAll() {
        return Produto.listAll();
    }
    
    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id) {
    	Produto produtoEntity = produtoController.carregaProduto(id);

        if (produtoEntity == null) {
            return Response.ok("Produto não foi encontrada").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        return Response.ok(produtoEntity).build();
    }
    

    @POST
    @Transactional
    public Response create(Produto produto) {
        Produto.persist(produto);
        return Response.ok(produto).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Produto produto) {
        Produto produtoEntity = produtoController.update(id, produto);
        if  (produtoEntity == null) {
        	throw new WebApplicationException("Produto com id " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        return Response.ok(produtoEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Produto produtoEntity = produtoController.carregaProduto(id);

        if (produtoEntity == null) {
            throw new WebApplicationException("Produto com id " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        produtoEntity.delete();
        return Response.status(204).build();
    }
}