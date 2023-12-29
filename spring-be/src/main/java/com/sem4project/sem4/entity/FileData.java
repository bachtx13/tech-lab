package com.sem4project.sem4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Table(name = "FILE_DATA")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class FileData extends BaseEntity{

    private String name;
    private String type;
    private String filePath;
}
