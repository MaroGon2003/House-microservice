package com.powerup.house_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "real_estate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private RealEstateCategoryEntity category;

    @Column(name = "rooms_count", nullable = false)
    private int roomsCount;

    @Column(name = "bathrooms_count", nullable = false)
    private int bathroomsCount;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "active_from", nullable = false)
    private LocalDate activeFrom;

    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDate createdOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ListingStatus status;
}
