package com.example.backend.repositories;

import com.example.backend.models.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM students s WHERE (:id IS NULL OR s.id = :id) " +
            "AND (:firstName IS NULL OR BINARY s.first_name LIKE %:firstName%) " +
            "AND (:lastName IS NULL OR BINARY s.last_name LIKE %:lastName%) " +
            "AND (:email IS NULL OR BINARY s.email LIKE %:email%) " +
            "AND (:gender IS NULL OR BINARY s.gender LIKE %:gender%) " +
            "AND (:score IS NULL OR s.score = :score) " +
            "AND (:classId IS NULL OR s.class_id = :classId)", nativeQuery = true)
    public List<Student> filterByMultipleFieldsCaseSensitive(@Param("id") Long id, @Param("firstName") String firstName,
                                                @Param("lastName") String lastName, @Param("email") String email,
                                                @Param("gender") String gender, @Param("score") Integer score,
                                                @Param("classId") String classId
    );

//    @Query(value = "SELECT * FROM student s WHERE BINARY s.first_name LIKE %:firstName%", nativeQuery = true)
//    public List<Student> filterByMultipleFieldsCaseSensitive(@Param("firstName") String firstName);

    @Query("SELECT s FROM Student s WHERE (:id IS NULL OR s.id = :id) " +
            "AND (:firstName IS NULL OR s.firstName LIKE %:firstName%) " +
            "AND (:lastName IS NULL OR s.lastName LIKE %:lastName%) " +
            "AND (:email IS NULL OR s.email LIKE %:email%) " +
            "AND (:gender IS NULL OR s.gender LIKE %:gender%) " +
            "AND (:score IS NULL OR s.score = :score) " +
            "AND (:classId IS NULL OR s.classId = :classId)")
    public List<Student> filterByMultipleFieldsInCaseSensitive(@Param("id") Long id, @Param("firstName") String firstName,
                                       @Param("lastName") String lastName, @Param("email") String email,
                                       @Param("gender") String gender, @Param("score") Integer score,
                                       @Param("classId") String classId
    );
}
