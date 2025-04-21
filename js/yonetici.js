document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Sayfanın yenilenmesini engeller
 
    const kullaniciAdi = document.getElementById("username").value;
    const sifre = document.getElementById("password").value;

    const loginData = {
        kullaniciAdi: kullaniciAdi,
        sifre: sifre
    };

    // Backend API'ye giriş isteği gönderiyoruz
    fetch("http://localhost:8080/api/calisanlar/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        console.log("Sunucu yanıtı:", response);  // Yanıtın detaylarını inceleyin

        if (!response.ok) {
            // Eğer HTTP durumu 200 değilse, bir hata mesajı döndür
            return response.text().then(errorText => {
                console.error("Sunucu hatası:", errorText);
                return Promise.reject('Sunucu hatası: ' + errorText);
            });
        }
        return response.json(); // JSON formatında yanıtı döndür
    })
    .then(data => {
        console.log("Backend’den gelen data: ", data);
        if (data.message === "Giriş başarılı") {
            window.location.href = "homePage.html"; // Başarılıysa yönlendir
        } else {
            alert("Giriş başarısız: " + data.message); // Hata mesajını göster
        }
    })
    .catch(error => {
        console.error('Hata:', error);  // Hata detaylarını konsola yazdır
        alert("Bir hata oluştu: " + error);  // Kullanıcıya göster
    });
});
