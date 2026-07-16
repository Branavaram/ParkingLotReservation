# ParkingLotReservationApp

A Java console application for managing a parking lot: zones, slot reservations, and owner registration.

## Overview

The app tracks parking zones (each with a set number of slots), lets you reserve a slot, register the vehicle owner for a reserved slot, add new zones dynamically, and save the current state to a file.

- **Owner** — name, vehicle number
- **App** — holds the parking structure (slots per zone) and owner details per slot, and drives the console menu

## Menu options

1. Reserve a parking slot
2. Show parking area
3. Add new parking zone
4. Register vehicle owner
5. Save information to file
0. Quit

## Project structure

```
src/
├── App.java     # Entry point, menu, and parking/reservation logic
└── Owner.java   # Vehicle owner details
```

## Running it

Requires Java 17+.

```bash
javac -d out src/*.java
java -cp out App
```
