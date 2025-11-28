package com.kancelaria.officesystem.model.entity;

import com.kancelaria.officesystem.enums.ContactType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"Contact\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private int id_message;

    @NotNull
    @Column(name = "contact_date", nullable = false)
    private LocalDateTime contact_date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false, columnDefinition = "\"ContactType\"")
    private ContactType contact_type;

    @NotNull
    @Column(name = "result", nullable = false)
    private String result;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numbercase", nullable = false)
    private Case lawCase;
}
