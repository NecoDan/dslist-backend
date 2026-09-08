package com.devsuperior.dslist.adapter.out.database.users_jpa.repository;

import com.devsuperior.dslist.adapter.out.entities.user_jpa.UserDsListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserDsListRepository extends JpaRepository<UserDsListEntity, Long> {

    //    @Query("select obj from UserDsListEntity obj where obj.salary >= :minSalary and obj.salary <= :maxSalary")
    Page<UserDsListEntity> findAllBySalary(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    //    @Query("select obj from UserDsListEntity obj where LOWER(obj.name) like LOWER(CONCAT('%', :name,'%'))")
    Page<UserDsListEntity> findAllByName(String name, Pageable pageable);

    Page<UserDsListEntity> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    Page<UserDsListEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
