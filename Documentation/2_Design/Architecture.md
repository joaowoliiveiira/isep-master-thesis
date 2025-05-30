# Architecture – Gamified Self-Help App

This document outlines the architectural structure of the system, including the core modules, technologies, and interaction between the main app, backoffice, and data services.

---

## High-Level Components

### 1 - Main App (MentalPotion)

* Built with Kotlin + Compose
* Focus: End-user experience
* Key Features:

  * Authentication via Firebase
  * Customizable routine/calendar
  * Tools and routines from topic modules
  * Activity tracking and skill XP system
  

<br>

### 2 - Backoffice App

* Also built with KMP (desktop-first)
* Focus: Admins and content managers
* Key Features:

  * Manage Topics, Tools, Routines, Activities
  * View user stats (future)
    

<br>

### 3 - Backend Services

* Firebase Authentication: Handles user sign-up/login in the Main App
* Firestore Cloud: Stores user data, routines, skills, activities, tool usage
* Supabase: Handles authentication in the Backoffice App, for admin role authentication and analytics  

<br>

---

<br>

## Main App Structure

### `core/`

* Shared types (models, Result wrapper, etc.)
* Shared services (auth, Firestore interactions)

### `feature-auth/`

* Login and signup flows

### `feature-home/`

* Displays active routine and progress

### `feature-topic/`

* Topic browsing and content access

### `feature-routine/`

* Routine activation, activity tracking and completion

### `feature-profile/`

* Profile editing, avatar selection, skill display

<br>

---


## Data Flow

1. **Login**: User authenticates via Firebase → session stored
2. **Topic Interaction**: Fetches routines and tools from Firestore
3. **Routine Activation**: Adds activity instances to user calendar
4. **Activity Completion**: Updates activity status, logs responses, adjusts XP
5. **Profile Display**: Aggregates skill and routine stats from user data

<br>

---

## Future Enhancements

* Therapist/Coach dashboards
* Cross-user routine sharing (e.g., friends)
* AI-based routine recommendation engine
