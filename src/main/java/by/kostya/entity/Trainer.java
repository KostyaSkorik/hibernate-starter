package by.kostya.entity;


import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Builder.Default
    @OneToMany(mappedBy = "trainer")
    private List<TrainerCourse> trainerCourses = new ArrayList<>();



}
