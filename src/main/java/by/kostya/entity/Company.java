package by.kostya.entity;


import javax.persistence.*;
import lombok.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(exclude = {"users"})
@AllArgsConstructor
//переопределим методы equals и hashcode для исправления проблемы с both-side mapping
@EqualsAndHashCode(of = "name")
@Builder
@Entity
public class Company implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    //Builder.Default для создания HashSet
    @Builder.Default
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<User> users = new ArrayList<>();


    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}

