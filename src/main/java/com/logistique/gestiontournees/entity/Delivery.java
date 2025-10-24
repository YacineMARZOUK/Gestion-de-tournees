package com.logistique.gestiontournees.entity;

import com.logistique.gestiontournees.entity.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Delivery")
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double weight; // en kg

    @Column(nullable = false)
    private double volume; // en m³

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = true) // Optionnel
    private String timeSlot;

    @ManyToOne
    @JoinColumn(name = "tour_id" )
    private Tour tour;


}
