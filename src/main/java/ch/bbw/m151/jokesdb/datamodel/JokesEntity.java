package ch.bbw.m151.jokesdb.datamodel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.util.Date;

@Entity
@Accessors(chain = true)
@Data
@Table(name = "jokes")
public class JokesEntity {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String joke;

    @Version
    private int version;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}
