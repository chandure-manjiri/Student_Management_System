package StudentMangementSystem.demo.Repository;

import StudentMangementSystem.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Long> {
}
