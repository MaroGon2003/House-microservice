package com.powerup.house_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationEntity {

    @EmbeddedId
    private LocationId locationId;

    @ManyToOne
    @MapsId("idCity")
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @ManyToOne
    @MapsId("idState")
    @JoinColumn(name = "state_id")
    private StateEntity state;


    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationId implements Serializable{

        @Column(name = "city_id")
        private Long idCity;

        @Column(name = "state_id")
        private Long idState;

    }

}
