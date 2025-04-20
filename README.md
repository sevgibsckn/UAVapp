# 🛰️ Android UAV Telemetry Uygulaması

Bu Android uygulaması, insansız hava aracı (UAV) telemetri verilerinin bir yer kontrol istasyonu üzerinden izlenmesini simüle eder. Gerçek cihaz bağlantısı yerine sahte (mock) bir veri akışıyla çalışır. Uygulama, gelen verileri işler ve kullanıcı arayüzünde canlı olarak gösterir.

---

## 📋 Uygulama Özellikleri

- Gerçek zamanlı telemetri verisi alma (sahte WebSocket üzerinden)
- JSON veri çözümleme (parse)
- Kullanıcı arayüzünde batarya, rakım, GPS ve uçuş süresinin gösterimi
- Başlat ve durdur butonlarıyla veri akışını kontrol etme
- Minimum Android 8.0 (API 26) desteği
- Sade ve işlevsel kullanıcı arayüzü

---
## 🔧 Kurulum ve Çalıştırma

1. **Projeyi GitHub'dan klonlayın veya indirin:**

```bash
git clone https://github.com/kullaniciadi/uav-telemetry-app.git
```

2. **Android Studio ile açın.**
   - Minimum SDK olarak **API 26 (Android 8.0)** seçili olmalıdır.

3. **Emülatörü başlatın** veya fiziksel cihaz bağlayın.

4. **WebSocket sunucusunu başlatın:**
   - Uygulama, `ws://10.0.2.2:8080` adresinden veri alır.
   - Bu adres Android emülatörü için bilgisayarınızın localhost'unu temsil eder.

