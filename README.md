# 📱 SmartPrayerAid – Islamic Utility Suite

*A Unified Android Application for Parental Monitoring, Islamic Calendar, and Tasbeeh Tracking*

---

## 🌟 Overview

SmartPrayerAid is a multi-module Android application designed to support digital wellbeing, spiritual growth, and daily Islamic practices. It integrates **Parental Monitoring**, an **Islamic Calendar**, and a **Tasbeeh Counter** into one intelligent, secure, and user-friendly app.

This project showcases strong Android development skills using **Java**, **SQLite**, **API integrations**, **background services**, and **custom UI components**.

---

# 📦 Modules Overview

## 🕵️‍♂️ 1. Parental Eye – Monitoring & Alert System

**A parental control module that detects app usage and sends automated SMS alerts.**

### Key Features

* 🔐 Secure Authentication: Password creation, login system, automatic logout.
* 📱 Real-time App Monitoring: Detects when a configured app is launched.
* 💬 SMS Alerts: Sends customizable SMS notifications.
* ⚙️ Background Monitoring: Works continuously using `AlarmManager` + Broadcast Receivers.
* 📊 Activity Logs: Complete monitoring history.

### Technical Highlights

* Background services (`CameraService`, `CameraService2`)
* SQLite database (rules + logs + authentication)
* App detection via background polling
* Secure password management

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
* SMS permission (for parental module)

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
* **Custom UI components**

## Database Structure (SQLite)

### Parental Eye Module

```sql
CREATE TABLE entry (ID INTEGER PRIMARY KEY, APP_NAME TEXT, NUMBER TEXT, MSG TEXT);
CREATE TABLE register (ID INTEGER PRIMARY KEY, password TEXT);
```

### Tasbeeh Counter Module

```sql
CREATE TABLE MasterTable (ID INTEGER PRIMARY KEY, ZIKAR_NAME TEXT, SETCOUNTER NUMBER, ...);
CREATE TABLE MYCUSTZIKAR (ID INTEGER PRIMARY KEY, NAMECUST TEXT);
```

---

# 📖 Usage Guide

## Module 1 – Parental Eye

1. Set password on first launch
2. Add app to monitor
3. Enter parent contact number
4. Customize alert message
5. Monitor activity logs

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
* Accessible color schemes

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

* Parental Eye Dashboard
* Islamic Calendar
* Tasbeeh Counter
* Settings Screens

---

# 🚨 Troubleshooting

### Prayer Times Not Updating

* Enable GPS
* Check connectivity
* Verify API responses

### SMS Not Sending

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
Email: [Your Email]

<div align="center">
### 🌟 *May this application bring ease and barakah to its users.*  
**"Indeed, in the remembrance of Allah do hearts find rest." – Quran 13:28**
</div>
