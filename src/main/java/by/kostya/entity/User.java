package by.kostya.entity;

import by.kostya.converter.BirthdayConverter;
import javax.persistence.*;
import javax.validation.Valid;

import by.kostya.dao.BaseRepository;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NamedQuery(name = "findUserByName",query = """
                select u from User u
                where u.personInfo.firstname = :firstname
                """)
@Data
//мы должны исключать из метода toString поле Company для корректной работы FetchType.LAZY
@ToString(exclude = {"company","profile"})
//переопределим методы equals и hashcode для исправления проблемы с both-side mapping
@EqualsAndHashCode(exclude = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
//@DiscriminatorColumn(name = "type")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Users")
public class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Valid
    private PersonInfo personInfo;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<UserChat> userChats = new ArrayList<>();
}

