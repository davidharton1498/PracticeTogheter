package com.example.practicetogheter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.practicetogheter.entity.Ride;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    @Query(value = "SELECT * FROM ride WHERE user_id = ?1", nativeQuery = true)
    List <Ride> findAllRidesByUserId(long id);

    @Query(value = "SELECT * FROM ride WHERE placeb = ?1", nativeQuery = true)
    List <Ride> findbyDestination(String placeb);

    @Query(value = "SELECT * FROM ride WHERE placea = ?1", nativeQuery = true)
    List <Ride> findbyStart(String placea);

    @Query(value = "SELECT * FROM ride WHERE date = ?1", nativeQuery = true)
    List <Ride> findbyDate(String date);
    Ride findOne(Long id);
}




