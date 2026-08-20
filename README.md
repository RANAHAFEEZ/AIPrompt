# 🚀 AI Prompt Snippet Vault | Android App

> **AI Prompt Snippet Vault** is a high-performance native Android application built with **Kotlin, Jetpack Compose, and Room Database**. It is designed for developers, writers, and power users to organize, manage, and quickly access their favorite AI prompts on the go.

---

## 📱 App Screenshots

*(Yahan apne app ke 2 ya 3 acche screenshots lagane hain)*

| Home Screen & Search | Add/Edit Prompt | Clipboard & Details |
| :---: | :---: | :---: |
| ![Home](url_to_home_screenshot.png) | ![Add](url_to_add_screenshot.png) | ![Details](url_to_details_screenshot.png) |

---

## ✨ Key Features & Architecture

* **Local Database Integration (Room):** Built with SQLite wrapper (Room Database) to ensure zero data loss. Users can persist custom prompts, categories, and titles locally.
* **Real-time Search & Category Filtering:** Instant search logic connected with a responsive UI (`LazyColumn`), allowing users to filter prompts dynamically by keywords or categories (*All, Coding, Writing, Productivity*).
* **Interactive Add/Edit Flow:** Seamless bottom-sheet/screen workflow enabling users to create, modify, and delete custom prompt snippets effortlessly.
* **One-Tap Clipboard Copy:** Designed for speed—users can copy any prompt instantly with a single tap, backed by interactive Toast feedback (*"Prompt copied to clipboard!"*).
* **Modern Android Tech Stack:** Developed following clean architecture principles, utilizing Kotlin Coroutines for asynchronous background tasks and Jetpack Compose for declarative UI.

---

## 🛠️ Tech Stack & Libraries

* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Local Database:** [Room Database (SQLite)](https://developer.android.com/training/data-storage/room)
* **Concurrency:** Kotlin Coroutines & Flow
* **Architecture:** MVVM (Model-View-ViewModel)

---

## 📥 Download & Installation

You can clone this repository and open it directly in **Android Studio** to run the project:

```bash
git clone [https://github.com/RANAHAFEEZ/AIprompt.git](https://github.com/RANAHAFEEZ/AIprompt.git)
