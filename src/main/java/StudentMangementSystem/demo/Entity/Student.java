package StudentMangementSystem.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.*;


@Entity
@Table(name = "students")

public class Student {

    @Id
//    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String firstname;
    @Column(nullable = true)
    private String lastname;
    @Column(nullable = true)
    private String gender;
    @Column(nullable = true)
    private int age;
    @Column(nullable = true, length = 10, unique = true)
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
        joinColumns = @JoinColumn(name = "Stu_id"),
        inverseJoinColumns = @JoinColumn(name = "Cour_id"))
    @JsonManagedReference
    private Set<Course> course;



    public Student(){
        super();
    }

    public Student(String firstname, String lastname, String gender, int age, String phone, Set<Course> cource){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.course = cource;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }
    //lastname
    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstName(String firstName){
        this.firstname = firstName;
    }

    public void setLastName(String lastName){
        this.lastname = lastName;
    }

    //firstname
    public String getLastname(){
        return this.lastname;
    }

    //gender
    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    //age
    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }


    //phone number
    public String getPhoneNumber(){
        return this.phone;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phone = phoneNumber;
    }

    // course set

    public Set<Course> getCourse(){
        return this.course;
    }

    public void  setCourse(Set<Course> course){
        this.course = course;
    }

}
