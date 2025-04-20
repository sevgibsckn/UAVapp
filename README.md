
#  Android UAV Telemetry Uygulaması

Bu Android uygulaması, insansız hava aracı (UAV) telemetri verilerinin bir yer kontrol istasyonu üzerinden izlenmesini simüle eder. Gerçek cihaz bağlantısı yerine sahte (mock) bir veri akışıyla çalışır. Uygulama, gelen verileri işler ve kullanıcı arayüzünde canlı olarak gösterir.

---

##  Uygulama Özellikleri

- Gerçek zamanlı telemetri verisi alma (sahte WebSocket üzerinden)
- JSON veri çözümleme (parse)
- Kullanıcı arayüzünde batarya, rakım, GPS ve uçuş süresinin gösterimi
- Başlat ve durdur butonlarıyla veri akışını kontrol etme
- Minimum Android 8.0 (API 26) desteği
- Sade ve işlevsel kullanıcı arayüzü

---

##  Kurulum ve Çalıştırma

1. **Projeyi GitHub'dan klonlayın veya indirin:**

2. **Android Studio ile açın.**
   - Minimum SDK olarak **API 26 (Android 8.0)** seçili olmalıdır.

3. **Emülatörü başlatın** veya fiziksel cihaz bağlayın.

4. **WebSocket sunucusunu başlatın:**
   - Uygulama, `ws://10.0.2.2:8080` adresinden veri alır.
   - Bu adres Android emülatörü için bilgisayarınızın localhost'unu temsil eder.

---

##  Sahte Veri Sunucusu (Node.js ile)

Telemetri verileri, WebSocket bağlantısıyla sahte olarak gönderilir. Bunun için basit bir Node.js sunucusu oluşturacağız.

### Node.js Sunucusu Kurulumu

1. Aşağıdaki gibi bir dosya oluşturun: `server.js`

```javascript
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', ws => {
  console.log('Yeni bir istemci bağlandı.');

  setInterval(() => {
    const telemetryData = {
      batarya: (Math.random() * 100).toFixed(2),
      rakim: Math.floor(Math.random() * 5000), 
      gpsX: (Math.random() * 180 - 90).toFixed(6), 
      gpsY: (Math.random() * 360 - 180).toFixed(6), 
      sure: Math.floor(Math.random() * 100).toString() 
    };

    ws.send(JSON.stringify(telemetryData));
    console.log('WebSocket veri gönderildi (örn batarya): ',telemetryData.batarya);

  }, 3000); //3000 ms -> 3 second
});

console.log('WebSocket server çalışıyor, 8080 portu üzerinde ayakta...');

```

2. Terminalde şu komutları çalıştırarak sunucuyu başlatın:

```bash
npm install ws
node server.js
```

---

##  Kullanılan Teknolojiler

- **Android (Kotlin)**: Uygulama dili ve platformu kullanıldı.
- **WebSocket (OkHttp)**: Sahte veri akışını almak için kullanıldı.
- **Gson**: Gelen JSON verilerini Kotlin veri sınıfına dönüştürmek için kullanıldı.
- **ViewBinding**: UI bileşenlerine güvenli erişim için kullanıldı.
- **ConstraintLayout & CardView**: Modern ve okunabilir kullanıcı arayüzü tasarımı için kullanıldı.

---










