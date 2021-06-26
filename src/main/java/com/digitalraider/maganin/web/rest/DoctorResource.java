package com.digitalraider.maganin.web.rest;

import com.digitalraider.maganin.domain.Doctor;
import com.digitalraider.maganin.domain.PersonalBlog;
import com.digitalraider.maganin.service.DoctorService;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.service.dto.DoctorDTO;
import com.digitalraider.maganin.web.rest.errors.BadRequestAlertException;
import com.digitalraider.maganin.web.rest.vm.PageRequestVM;
import com.digitalraider.maganin.web.rest.vm.SortRequestVM;
import com.digitalraider.maganin.web.util.HeaderUtil;
import com.digitalraider.maganin.web.util.PaginationUtil;
import com.digitalraider.maganin.web.util.ResponseUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.ws.rs.core.UriBuilder.fromPath;

@Path("/api/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DoctorResource {
    private final Logger log = LoggerFactory.getLogger(DoctorResource.class);

    private static final String ENTITY_NAME = "doctor";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    DoctorService doctorService;
    /**
     * {@code POST  /doctors} : Create a new doctor.
     *
     * @param doctor the doctor to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new doctor, or with status {@code 400 (Bad Request)} if the doctor has already an ID.
     */
    @POST
    public Response createDoctor(Doctor doctor, @Context UriInfo uriInfo) {
        log.debug("REST request to save Doctor : {}", doctor);
        if (doctor.name != null) {
            throw new BadRequestAlertException("A new doctor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = doctorService.persistOrUpdate(doctor);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.name).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.name).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /doctors} : Updates an existing doctor.
     *
     * @param doctor the doctor to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated doctor,
     * or with status {@code 400 (Bad Request)} if the doctor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctor couldn't be updated.
     */
    @PUT
    public Response updateDoctor(Doctor doctor) {
        log.debug("REST request to update Doctor : {}", doctor);
        if (doctor.name == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = doctorService.persistOrUpdate(doctor);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doctor.name).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /doctors/:id} : delete the "name" doctor.
     *
     * @param id the name of the doctor to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") Integer id) {
        log.debug("REST request to delete Doctor : {}", id);
        doctorService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /doctors} : get all the doctors.
     *
     * @param pageRequest the pagination information.
     * @return the {@link Response} with status {@code 200 (OK)} and the list of doctors in body.
     */
    @GET
    public Response getAllDoctors(@BeanParam PageRequestVM pageRequest, @BeanParam SortRequestVM sortRequest, @Context UriInfo uriInfo) {
        log.debug("REST request to get a page of Doctors");
        var page = pageRequest.toPage();
        var sort = sortRequest.toSort();
        Paged<Doctor> result = doctorService.findAll(page,sort);

        var response = Response.ok().entity(result.content);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);
        return response.build();
    }
    @GET
    @Path("/summery")
    public Response getAllDoctorsSummery() {
        log.debug("REST request to get all of Doctors");
        List<DoctorDTO> result = Doctor.findAll().stream().map(d -> new DoctorDTO((Doctor) d)).collect(Collectors.toList());
        var response = Response.ok().entity(result);
        return response.build();
    }

    /**
     * {@code GET  /doctors/:id} : get the "name" doctor.
     *
     * @param id the name of the doctor to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the doctor, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getDoctor(@PathParam("id") Integer id) {
        log.debug("REST request to get Doctor : {}", id);
        Optional<Doctor> doctor = doctorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doctor);
    }

    @GET
    @Path("/{id}/blogs")
    public Response getDoctorBlogs(@PathParam("id") Integer id){
        log.debug("REST request to get Doctor blogs : {} ", id);
        Optional< List<PersonalBlog> > doctorBlogs = doctorService.findOne(id).map(d -> d.blogs);
        return  ResponseUtil.wrapOrNotFound(doctorBlogs);
    }
}
