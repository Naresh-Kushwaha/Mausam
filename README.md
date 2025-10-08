# 🌦️ Mausam — Real-Time Weather Dashboard

**Mausam** is a smart weather dashboard that fetches **real-time weather data** based on the user's location.  
It automatically detects your IP address, retrieves your city, and displays the current temperature, humidity, and wind speed with a clean, responsive UI.

---

## 🚀 Features

- 🌍 **Auto-location detection** using [IPAPI](https://ipapi.co/) for personalized weather updates  
- ☁️ **Real-time weather data** via [Weather API](https://www.weatherapi.com/)  
- 🔄 **Seamless frontend-backend integration** using React and REST APIs  
- 📱 **Responsive UI** — works perfectly on mobile and desktop  
- 🌡️ Displays dynamic details:
  - Temperature  
  - Humidity  
  - Wind speed  
  - Location & weather condition  

---

## 🛠️ Tech Stack

**Frontend:** ReactJS, Tailwind CSS  
**Backend:** Spring Boot   
**APIs Used:**  
- [IPAPI](https://ipapi.co/) — for IP-based geolocation  
- [WeatherAPI](https://www.weatherapi.com/) — for live weather data  

---

## ⚙️ How It Works

1. The app fetches the user's **public IP** using IPAPI.  
2. It determines the **city & coordinates** from the IP.  
3. The **Weather API** fetches real-time weather details for that location.  
4. The data is displayed dynamically on a modern, responsive weather dashboard.

---

## 🧠 Key Learnings

- Handling **asynchronous API calls** between React and backend  
- Integrating **external APIs** securely with environment variables  
- Designing **responsive UI** using Tailwind CSS  
- Implementing **geolocation-based personalization**

---

## 🚀 Future Enhancements

- 🌤️ 5-day weather forecast  
- 📍 Manual location search  
- 🔔 Weather alerts and notifications  
- 🎨 Improved animations for weather conditions  

---

> *“Weather changes every day, but Mausam always keeps you informed.”* ☁️
