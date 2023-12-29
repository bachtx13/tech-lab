package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private boolean disable;
    @Column(nullable = false)
    private Instant createdAt;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User createdBy;
    private Instant updatedAt;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User updatedBy;
    @PrePersist
    protected void prePersist(){
        if(this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        if(this.createdBy == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && !authentication.getPrincipal().equals("anonymousUser")){
                this.createdBy = User.builder()
                        .id(((UserPrincipal) authentication.getPrincipal()).getId())
                        .build();
            }
        }
        this.updatedAt = Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !authentication.getPrincipal().equals("anonymousUser")){
            this.createdBy = User.builder()
                    .id(((UserPrincipal) authentication.getPrincipal()).getId())
                    .build();
        }
    }
    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null &&!authentication.getPrincipal().equals("anonymousUser")){
            this.createdBy = User.builder()
                    .id(((UserPrincipal) authentication.getPrincipal()).getId())
                    .build();
        }
    }
}
