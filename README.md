# SmartPrayerAid: Digital Companion App

A robust **Android application** built to intelligently assist users with their daily religious practices. It includes real-time, location-based prayer time calculation, a voice-guided prayer tracker, a digital Tasbeeh counter, and custom Zikar translation. Fully developed in **Java**, the app focuses on mobile API orchestration, data persistence, and seamless user experience.

---

## ✨ Features

### 🕒 Real-Time Prayer Time
Fetches accurate prayer times (Fajr, Dhuhr, Asr, Maghrib, Isha) based on:
- GPS coordinates
- Local Timezone ID

**Key Classes:** `GPSTracker.java`, `CurrentTimeAPI.java`, `NamazAPI.java`

---

### 🎙️ Voice-Guided Prayer Tracker
- Uses **Android SpeechRecognizer** (Arabic: `ar-SA`)
- Guides users through prayer phases
- Provides audio + visual cues

---

### 📳 Haptic Feedback
- Custom vibration patterns for different prayer steps
- Managed via `PatternList.java`

---

### 🔢 Intelligent Tasbeeh Counter
- Track recitations with limit settings
- Saves and resumes sessions
- Maintains history using SQLite

**Class:** `DataBaseHelper.java`

---

### 🌙 Islamic Calendar Integration
- Customized **Caldroid** library
- Displays Hijri + Gregorian dates

---

### 🌐 Custom Zikar Translation
- Translate English Zikar to Arabic via API
- Uses external translation service

**Class:** `Translator.java`

---

## 💻 Technical Stack

| Component | Technology / Implementation |
|----------|------------------------------|
| **Platform** | Android (API Level 21+) |
| **Language** | Java |
| **Database** | SQLite (DataBaseHelper) |
| **Geolocation** | Android LocationManager, GPSTracker Service |
| **Concurrency** | AsyncTask for API calls |
| **Speech Recognition** | Android SpeechRecognizer (`ar-SA`) |
| **APIs Used** | GeoNames API, Al Adhan API, MyMemory Translation API |
| **Libraries** | Caldroid (Custom), hirondelle.date4j |

---

## 🚀 Getting Started

### **Prerequisites**
- Java JDK 8+
- Android Studio (latest version)
- Android SDK (API Level 21+)

### **Clone the Repository**
```bash
git clone https://github.com/hiraq-dev/SmartPrayerAid.git
cd SmartPrayerAid
```

### **Open in Android Studio**
1. Go to **File > Open**
2. Select the project folder

### **Run the App**
- Connect an Android device or start an emulator
- Press the **Run ▶️** button

---

## 🌐 API Endpoints Used

### **Timezone & Geolocation**
```
GET http://api.geonames.org/timezoneJSON?lat=...&lng=...&username=...
```
Used by `CurrentTimeAPI.java`

### **Prayer Times Calculation**
```
GET http://api.aladhan.com/calendar?latitude=...&longitude=...&method=...
```
Used by `NamazAPI.java`

### **Custom Zikar Translation**
```
GET http://api.mymemory.translated.net/get?q=...&langpair=en|ar
```
Used by `Translator.java`

---

## 📊 Database Schema (SQLite)

### **MasterTable**
| Column Name | Type | Description |
|-------------|------|-------------|
| ID | INTEGER (PK) | Primary Key |
| ZIKAR_NAME | TEXT | Arabic recitation text |
| SETCOUNTER | INTEGER | Target count |
| Remaining | INTEGER | Remaining count |
| Total | INTEGER | Total recited |
| Status | TEXT | Completed/Incomplete |
| Date | TEXT | Session date |

### **Other Tables**
- **MYCUSTZIKAR (TB):** Stores user-defined Zikar phrases
- **auto_tasbeeh / manual_tasbeeh:** Metadata for Tasbeeh sessions

---

## ⚡ Performance Considerations
- Indexed SQLite lookups for fast performance
- Optimized GPS usage to reduce battery drain
- API orchestration ensures accurate location + timezone-based results

---

## 🔒 Security Features
- Runtime GPS permission handling
- Validation of custom Zikar input
- All user data stored locally within private app storage

---

## 🧪 Testing
- SpeechRecognizer tested across different environments
- Haptic feedback patterns verified
- Prayer times validated with known data sources

---

## 🤝 Contributing
This was completed as a **Final Year Project**. External contributions are not currently accepted, but suggestions for improvements are welcome.

---

## 📜 License
This project is licensed under the **MIT License**.

---

### ⭐ If you found this project helpful, please give it a star! ⭐
