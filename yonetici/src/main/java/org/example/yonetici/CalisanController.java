package org.example.yonetici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/api/calisanlar")
public class CalisanController {

    @Autowired
    private CalisanRepository calisanRepository;

    // 1. TÜM ÇALIŞANLARI LİSTELE
    @GetMapping
    public List<Calisan> getAllCalisanlar() {
        return calisanRepository.findAll();
    }

    // 2. ID'YE GÖRE ÇALIŞANI GETİR
    @GetMapping("/{id}")
    public ResponseEntity<Calisan> getCalisanById(@PathVariable int id) {
        Optional<Calisan> calisan = calisanRepository.findById(id);
        return calisan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. YENİ ÇALIŞAN EKLE
    @PostMapping
    public Calisan createCalisan(@RequestBody Calisan calisan) {
        return calisanRepository.save(calisan);
    }

    // 4. ÇALIŞANI GÜNCELLE
    @PutMapping("/{id}")
    public ResponseEntity<Calisan> updateCalisan(@PathVariable int id, @RequestBody Calisan updatedCalisan) {
        return calisanRepository.findById(id)
                .map(calisan -> {
                    calisan.setKullanici_adi(updatedCalisan.getKullanici_adi());
                    calisan.setKullanici_sifre(updatedCalisan.getKullanici_sifre());
                    calisan.setGorev(updatedCalisan.getGorev());
                    return ResponseEntity.ok(calisanRepository.save(calisan));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ÇALIŞANI SİL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalisan(@PathVariable int id) {
        if (calisanRepository.existsById(id)) {
            calisanRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    //Kullanici giris yapmayi deneyince bu fonksiyon ile kontrol edilir
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody GirisDeneyen deneme) {
        String kullanici_adi = deneme.getKullaniciAdi();
        String sifre = deneme.getSifre();

        // Kullanıcı adı ile veritabanında arama yapıyoruz
        Calisan calisan = calisanRepository.findByKullanici_adi(kullanici_adi);

        // Yanıt olarak döneceğimiz map nesnesi
        Map<String, String> response = new HashMap<>();

        // Eğer kullanıcı adı ve şifre doğruysa giriş başarılıdır
        if (calisan != null && calisan.getKullanici_sifre().equals(sifre)) {
            response.put("message", "Giriş başarılı");
            return ResponseEntity.ok(response);  // 200 OK ile JSON yanıtı gönderiyoruz
        } else {
            response.put("message", "Geçersiz kullanıcı adı veya şifre");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // 401 Unauthorized yanıtı gönderiyoruz
        }
    }


}
