package com.microservices.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

@Entity
@Table(name="USER_TYPES")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTypeId;

    @Column(name = "USER_TYPE_NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "USER_TYPE_DESCRIPTION", nullable = false, length = 250)
    private String description;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
