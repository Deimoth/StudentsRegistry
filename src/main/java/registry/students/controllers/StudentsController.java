package registry.students.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import registry.students.data.entity.Student;
import registry.students.models.Response;
import registry.students.service.StudentsService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@CrossOrigin
public class StudentsController {

    private final StudentsService studentsService;

    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response list() {
        List<Student> list = studentsService.listStudents();

        return Response.builder()
                .status(200)
                .data(list)
                .build();
    }

    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@RequestBody Student newStudent) {
        Map<String, String> errorsMap = studentsService.addStudent(newStudent);

        return Response.builder()
                .status(errorsMap.isEmpty() ? 200 : 400)
                .errorsMap(errorsMap)
                .build();
    }

    @RequestMapping(
            path = "/update",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response edit(@RequestBody Student student) {
        Map<String, String> errorsMap = studentsService.updateStudent(student);

        return Response.builder()
                .status(errorsMap.isEmpty() ? 200 : 400)
                .errorsMap(errorsMap)
                .build();
    }

    @RequestMapping(
            path = "/remove",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response remove(@PathParam("number") String number) {
        Map<String, String> errorsMap = studentsService.removeStudentByNumber(number);

        return Response.builder()
                .status(errorsMap.isEmpty() ? 200 : 400)
                .errorsMap(errorsMap)
                .build();
    }
}
