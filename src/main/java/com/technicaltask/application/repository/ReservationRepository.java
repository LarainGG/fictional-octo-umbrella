package com.technicaltask.application.repository;

import com.technicaltask.application.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository extends JpaRepository<Reservation, Integer>
{
    @Query("select e from #{#entityName} e where e.isDeleted=false and e.id=?1")
    Optional<Reservation> findById(int id);

    @Override
    @Query("select e from #{#entityName} e where e.isDeleted=false")
    List<Reservation> findAll();

    @Query("select e from #{#entityName} e where e.isDeleted=true")
    List<Reservation> recycleBin();
}