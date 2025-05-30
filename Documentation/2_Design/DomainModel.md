# Domain Model

This document outlines the conceptual data model behind the application. It reflects how users, content, routines, and skills interact within the main app's context

---

<br>

## 👤 User

* Each user has an account, a profile, and a customizable routine calendar.
* Users can select and unlock avatars.
* Users progress by gaining XP through activity completion.

<br>

## 🎭 Avatar

* Visual representation of a user.
* Avatars are unlockable and selectable.

<br>

## 🧠 Skill

* Represents a measurable ability like Sleep, Focus, Nutrition.
* XP gained through completing activities linked to that skill.

<br>

## 🗂 Topic

* Thematic category grouping resources and routines.
* Examples: Sleep, Nutrition, Mindfulness.

<br>

## 🧰 Tool

* Passive or interactive resources under a Topic.
* Types include: Podcast, Guide, Music, etc.

<br>

## 🔁 Routine (Template)

* A predefined sequence of Activities within a Topic.
* Can be activated by the user and merged into their personal calendar.

<br>

## 🧩 Activity

* A core task the user can complete (e.g., listen to podcast, complete a journal).
* Can appear in multiple routines.
* Can be linked to tools and one or more skills.

<br>

## 📅 User Routine

* The user’s active calendar of scheduled activity instances.
* Aggregates activities from multiple routines.

<br>

## ✅ Activity Instance

* A scheduled version of an Activity.
* Tracks completion, scheduling, and user response.

<br>

## 🔗 Relationships Summary

* Topics → Tools & Routines
* Routines → Activities (many-to-many)
* Activities → Skills (many-to-many)
* Activities → Tools (optional, many-to-many)
* User → Skills (per-skill XP/level)
* User → Routine (calendar)
* Routine → Activity Instances (with schedule & responses)

<br>

For the database modeling, see: [`ERD.svg`](./ERD.svg) and [`ERD_Glossary.md`](./ERD_Glossary.md).
