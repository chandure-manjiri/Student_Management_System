package StudentMangementSystem.demo.Controller;

import StudentMangementSystem.demo.Entity.Course;
import StudentMangementSystem.demo.Entity.Student;
import StudentMangementSystem.demo.Repository.CourseRepository;
import StudentMangementSystem.demo.Repository.StudentRepository;
import StudentMangementSystem.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/students_Courses")
public class CourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(){

        try{
            List<Course> courseList = this.courseRepository.findAll();
            return ResponseEntity.ok(courseList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(name = "id") long id) throws ResourceNotFoundException {

        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));

        return ResponseEntity.ok().body(course);

    }

    @GetMapping("/courses/getstudents/{id}")
    public ResponseEntity<Set<Student>> getStudentsByCourseId(@PathVariable(name = "id") long id) throws ResourceNotFoundException {

        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
       Set<Student> studentSet = course.getStudent();
        return ResponseEntity.ok().body(studentSet);

    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course course1 = this.courseRepository.save(course);
        return new ResponseEntity<>(course1, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable(name = "id") long cour_id) throws ResourceNotFoundException {
        Course course1 = this.courseRepository.findById(cour_id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cour_id));

        course1.setName(course1.getName());


        return ResponseEntity.ok(this.courseRepository.save(course1));

    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable (value = "id") long id) throws ResourceNotFoundException {


        Course course = this.courseRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Course not found by id :: "+ id));

        this.courseRepository.deleteById(id);
        Map<String, Boolean> respoce = new HashMap<>();
        respoce.put("deleted", Boolean.TRUE);
        return  respoce;
    }


}
