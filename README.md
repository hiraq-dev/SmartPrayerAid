# 📱 SmartPrayerAid – Islamic Utility Suite

*A Unified Android Application for Namaz Assistance, Islamic Calendar, and Tasbeeh Counter*

---

## 🌟 Overview

SmartPrayerAid is a multi-module Android application designed to support digital wellbeing, spiritual growth, and daily Islamic practices. It integrates a **Namaz Assistance App**, an **Islamic Calendar**, and a **Tasbeeh Counter** into one intelligent, secure, and user-friendly app.

This project showcases strong Android development skills using **Java**, **SQLite**, **API integrations**, **background services**, **speech recognition**, and **custom UI components**.

---

# 📦 Modules Overview

## 🕌 1. Namaz Application – Prayer Assistance & Accessibility

**A digital companion app for tracking daily prayers, guiding users through prayer routines, and assisting disabled persons using speech recognition.**

### Key Features

* 🔐 Secure Authentication (optional for user personalization)
* 📱 Real-time Prayer Time Calculation based on location
* 🎙️ **Voice-Guided Prayer Assistance** for Rakat, Sajda, and other prayer steps
* ⚙️ Background Services for notifications and tasbeeh counting
* 📊 Prayer History Tracking
* ♿ Accessibility support for disabled users during prayers

### Technical Highlights

* Speech recognition algorithm to guide users through prayer steps
* Background services for prayer reminders and tasbeeh tracking
* SQLite database for prayer logs and user preferences
* API integration for location-based prayer times (Aladhan API)
* Custom UI components for dual-date calendars and tasbeeh tracking

---

## 📅 2. Islamic Calendar – Smart Dual-Date System

**Dual calendar integrating Hijri & Gregorian dates with location-based prayer times.**

### Key Features

* 🗓️ Dual Calendar View: Displays Hijri and Gregorian dates.
* 🕌 Prayer Time Calculation: Based on GPS coordinates and timezone.
* 🌍 GPS Integration: Auto-detects user location.
* 🎨 Custom Calendar UI: Enhanced Caldroid library.

### Technical Highlights

* Uses **Aladhan API** + **Geonames API**
* Umm al-Qura Hijri calendar algorithm
* Custom adapters for dual-date display
* Location-based timezone adjustments

---

## 📿 3. Tasbeeh Counter – Digital Prayer Bead Tracker

**Flexible tasbeeh counting tool with history, analytics, and custom zikar support.**

### Key Features

* 🎯 Manual & Auto counting
* 📊 Live progress tracking
* 🔄 Resume previous sessions
* 📝 Custom Zikar creation
* 📈 Complete history with session breakdown

### Technical Highlights

* Multi-table SQLite schema
* Session persistence and recovery
* Dynamic UI for history/logs
* Custom Zikar management

---

# 🚀 Getting Started

## Prerequisites

* Android Studio (latest)
* JDK 8+
* Minimum SDK: **API 16**
* Active Internet for API calls
* SMS permission (for tasbeeh or notification modules)

## Installation

```bash
git clone https://github.com/your-username/SmartPrayerAid.git
cd SmartPrayerAid
```

Open in **Android Studio → Build → Run**

---

# 🔐 Required Permissions

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.VIBRATE" />
```

---

# 🛠 Technical Architecture

## Core Technologies

* **Java (Android)**
* **SQLite** (local offline storage)
* **REST APIs** (Geonames, Aladhan)
* **Broadcast Receivers & Background Services**
* **Speech Recognition Algorithm for Prayer Guidance**
* **Custom UI components**

## Database Structure (SQLite)

### Namaz Module

```sql
-- Prayer logs and tasbeeh tracking
CREATE TABLE MasterTable (ID INTEGER PRIMARY KEY, ZIKAR_NAME TEXT, SETCOUNTER NUMBER, ...);
CREATE TABLE MYCUSTZIKAR (ID INTEGER PRIMARY KEY, NAMECUST TEXT);
```

---

# 📖 Usage Guide

## Module 1 – Namaz Application

1. Enable location for accurate prayer times
2. Follow voice-guided instructions for Rakat, Sajda, and other prayer steps
3. Track prayer completion and progress
4. Access history logs

## Module 2 – Islamic Calendar

1. Enable GPS
2. View dual-date calendar
3. Select date for details
4. Access daily prayer times

## Module 3 – Tasbeeh Counter

1. Choose or create a Zikar
2. Set target count
3. Start counting
4. View detailed history

---

# 🎨 UI/UX Highlights

* Material Design
* Arabic RTL support
* Dynamic tables and history logs
* Dual calendar templates
* Accessibility for visually impaired or disabled users

---

# 🔒 Security & Privacy

* Local-only data storage
* Password protection
* Explicit permission-based access
* Secure API communication

---

# 📊 Performance Optimizations

* Optimized background tasks
* Efficient database queries
* Lazy loading for history
* Battery-efficient monitoring

---

# 🤝 Contributing

```bash
git checkout -b feature/NewFeature
git commit -m "Add new feature"
git push origin feature/NewFeature
```

Submit a Pull Request ✔️

---

# 📱 Screenshots

*(Add images here in your repo)*

* Namaz Application Dashboard
* Islamic Calendar
* Tasbeeh Counter
* Settings Screens

---

# 🚨 Troubleshooting

### Prayer Guidance Not Working

* Enable microphone permissions
* Ensure speech recognition is supported on device
* Check connectivity for API-based prayer times

### Tasbeeh or Notifications Not Working

* Grant SMS permissions
* Validate phone number
* Check SIM/network

### Calendar Issues

* Check timezone settings
* Verify Internet connection

---

# 📄 License

This project is licensed under the **MIT License**.

---

# 🙏 Acknowledgments

* Geonames API – Timezone data
* Aladhan API – Prayer times
* Caldroid Library – Custom calendar UI
* Date4j – Date calculations
* Open-source community contributions

---

# 📞 Contact

**Maintainer:** Hira Qaiser
GitHub: [https://github.com/hiraq-dev](https://github.com/hiraq-dev)
Email: [hira.qaiser.study@gmail.com]

<div align="center">
### 🌟 *May this application bring ease and barakah to its users.*  
**"Indeed, in the remembrance of Allah do hearts find rest." – Quran 13:28**
</div>
