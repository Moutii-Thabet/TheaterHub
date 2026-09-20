package com.moutii.TheaterHub.moviemanagement.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    boolean existsByName(String name);
}
