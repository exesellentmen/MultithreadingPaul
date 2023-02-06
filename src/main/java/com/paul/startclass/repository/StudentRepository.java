package com.paul.startclass.repository;

import com.paul.startclass.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
