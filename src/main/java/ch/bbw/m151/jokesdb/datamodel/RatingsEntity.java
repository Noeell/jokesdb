package ch.bbw.m151.jokesdb.datamodel;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "ratings")
public class RatingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    int rating;

    @CreationTimestamp
    private OffsetDateTime createdOn;

    @UpdateTimestamp
    private OffsetDateTime updatedOn;
}
