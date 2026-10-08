# LightsOut-F1-App
# Lights Out

An Android app for Formula 1 fans. It shows the current season's driver standings, team standings and race calendar, and lets you pick a favourite driver, team and Grand Prix that appear on your home screen.

## Features

- **Home** – a card for the next race plus your favourite driver, team and Grand Prix, each with a matching helmet, car or track image.
- **Drivers** – live driver championship standings with position, team and points.
- **Teams** – live constructor standings for Red Bull, Mercedes, Ferrari, Aston Martin and Williams.
- **Schedule** – the full race calendar for the current season, with round, circuit, country and date.
- **Favourites** – add or edit your favourite driver, team and Grand Prix. Choices are saved on the device and stay after you close the app.
- **Login** – a simple sign-in screen in front of the app.

## Tech stack

| | |
|---|---|
| Language | Java 11 |
| UI | XML layouts, Fragments, RecyclerView, Material Components |
| Networking | Volley |
| Storage | SharedPreferences |
| Data | [Jolpica F1 API](https://github.com/jolpica/jolpica-f1) (Ergast-compatible) |
| Min / target SDK | 25 / 36 |
