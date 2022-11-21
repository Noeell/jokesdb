package ch.bbw.m151.jokesdb.datamodel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Accessors(chain = true)
@Data
@Table(name = "jokes")
public class JokesEntity {

    @Id
    @GeneratedValue
    int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ratings_id")
    RatingsEntity ratingsId;

    @Column(length = 500)
    String joke;

    @Column
    String category;
    @Column
    String type;
    @Column
    String setup;
    @Column
    String delivery;

    @Column
    boolean safe;

    @Column
    String lang;

    @Column
    boolean nsfw;
    @Column
    boolean religious;
    @Column
    boolean political;
    @Column
    boolean racist;
    @Column
    boolean sexist;
    @Column
    boolean explicit;
    @Version
    private int version;

    @CreationTimestamp
    private OffsetDateTime createdOn;

    @UpdateTimestamp
    private OffsetDateTime updatedOn;

}
