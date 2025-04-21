package org.example.yonetici;

import org.example.yonetici.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface CalisanRepository extends JpaRepository<Calisan, Integer> {

    @Query("SELECT c FROM Calisan c WHERE c.kullanici_adi = :kullanici_adi")
    Calisan findByKullanici_adi(@Param("kullanici_adi") String kullanici_adi);
}


