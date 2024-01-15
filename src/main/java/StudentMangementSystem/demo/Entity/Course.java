package StudentMangementSystem.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "courses")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, unique = true)
    private String Name;


    @ManyToMany(mappedBy = "course")
    @JsonBackReference
    private Set<Student> student;


    public Course(){
        super();
    }

    public Course(String Name, Set<Student> student){
        this.Name = Name;
        this.student = student;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public Set<Student> getStudent(){ return  this.student;}

    public void setStudent(Set<Student> student){
        this.student = student;
    }


}
