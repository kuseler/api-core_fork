package com.educoach.teachers;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.Objects;

@Path("/api/v1/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "teachers", description = "Teacher operations")
public class TeacherResourceV1 {

    private final TeacherService teacherService;

    public TeacherResourceV1(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GET
    @Operation(summary = "Find all teachers")
    @APIResponse(responseCode = "200", description = "Get all teachers",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Teacher.class)))
    @APIResponse(responseCode = "204", description = "The list of teachers is empty")
    public Response getAll() {
        final var teachers = teacherService.findAll();

        if (teachers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(teacherService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a teacher by its id")
    @APIResponse(responseCode = "200", description = "Get a teacher by id",
            content = @Content(schema = @Schema(implementation = Teacher.class)))
    @APIResponse(responseCode = "404", description = "Teacher with the given id dose not exists")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return teacherService.findById(id)
                .map(teacher -> Response.ok(teacher).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Create a new teacher")
    @APIResponse(responseCode = "201", description = "Teacher created",
            content = @Content(schema = @Schema(implementation = Teacher.class)))
    @APIResponse(responseCode = "400", description = "Invalid teacher payload")
    public Response create(@NotNull @Valid Teacher teacher, @Context UriInfo uriInfo) {
        final var created = teacherService.create(teacher);
        final var uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(created.id())).build();
        return Response.created(uri).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update an existing teacher")
    @APIResponse(responseCode = "200", description = "Teacher updated",
            content = @Content(schema = @Schema(implementation = Teacher.class)))
    @APIResponse(responseCode = "400", description = "Invalid teacher payload")
    @APIResponse(responseCode = "404", description = "Teacher with the given id dose not exists")
    public Response update(
            @Parameter(name = "id", required = true) @PathParam("id") final Long id,
            @Valid Teacher teacher
    ) {
        if (!Objects.equals(id, teacher.id())) {
            throw new WebApplicationException("Path variable id dose not match Teacher.id",
                    Response.Status.BAD_REQUEST);
        }
        if (!teacherService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final var updatedTeacher = teacherService.update(id, teacher);
        return Response.ok().entity(updatedTeacher).build();
    }

    @DELETE
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "Teacher deleted")
    @APIResponse(responseCode = "404", description = "Teacher with the given id dose not exists")
    public Response delete(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        if (!teacherService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teacherService.delete(id);
        return Response.noContent().build();
    }
}
