package by.kostya.entity;

import javax.persistence.*;
import lombok.*;

@Data
@ToString(exclude = "course")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
}
