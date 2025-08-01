package by.kostya.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "trainer_course")
public class TrainerCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public void setTrainer(Trainer trainer){
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }
    public void setCourse(Course course){
        this.course = course;
        course.getTrainerCourses().add(this);
    }
}
