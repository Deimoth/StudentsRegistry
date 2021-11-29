package registry.students.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import registry.students.data.entity.Student;


public interface StudentsRepository extends JpaRepository<Student, Integer> {

    Student getStudentByNumber(String number);

    @Transactional
    Integer removeStudentByNumber(String number);

}
