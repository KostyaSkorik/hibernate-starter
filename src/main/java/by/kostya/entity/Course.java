package by.kostya.entity;


import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(exclude = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<TrainerCourse> trainerCourses = new ArrayList<>();

    public void setStudent(Student student){
        students.add(student);
        student.setCourse(this);
    }
}
