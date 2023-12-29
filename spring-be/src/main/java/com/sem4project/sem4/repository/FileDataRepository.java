package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData, Long > {
}
