package registry.students.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import registry.students.data.entity.Student;
import registry.students.data.repository.StudentsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StudentsService {

    private StudentsRepository studentsRepository;

    public List<Student> listStudents() {
        return studentsRepository.findAll();
    }

    public Map<String, String> addStudent(Student newStudent) {
        Map<String, String> errorsMap = new HashMap<>();
        validateBeforeAdd(newStudent, errorsMap);
        if (errorsMap.isEmpty()) {
            try {
                studentsRepository.save(newStudent);
            } catch (Exception e) {
                errorsMap.put("common", e.getMessage());
            }
        }

        return errorsMap;
    }

    public Map<String, String> updateStudent(Student newStudent) {
        Map<String, String> errorsMap = new HashMap<>();
        Student oldStudent = studentsRepository.getStudentByNumber(newStudent.getNumber());
        if (oldStudent != null) {
            oldStudent = newStudent;
            try {
                studentsRepository.save(oldStudent);
            } catch (Exception e) {
                errorsMap.put("common", e.getMessage());
            }
        } else {
            errorsMap.put("common", String.format("Student with number %s not found", newStudent.getNumber()));
        }

        return errorsMap;
    }

    public Map<String, String> removeStudentByNumber(String number) {
        Map<String, String> errorsMap = new HashMap<>();
        try {
            int removedCount = studentsRepository.removeStudentByNumber(number);
            if (removedCount == 0) {
                errorsMap.put("common", String.format("Student with number %s not found", number));
            }
        } catch (Exception e) {
            errorsMap.put("common", e.getMessage());
        }

        return errorsMap;
    }

    private void validateBeforeAdd(Student student, Map<String, String> errorsMap) {
        if (StringUtils.isEmpty(student.getNumber())) {
            addRequiredFieldError("number", errorsMap);
        } else if (studentsRepository.getStudentByNumber(student.getNumber()) != null) {
            errorsMap.put("number", "Choose unique number");
        }
    }

    private void addRequiredFieldError(String field, Map<String, String> errorsMap) {
        errorsMap.put(field, "Required field");
    }

}
