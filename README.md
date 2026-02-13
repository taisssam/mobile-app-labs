# Pikmi Trade

Pikmi Trade is a mobile application that simulates basic exchange functionality: viewing assets, analyzing price charts, simulating trades, and tracking a portfolio.

This project was developed as part of the Mobile Development course at ITMO University and represents a set of labs assignments.

# Functional Requirements (FT)

## 1. Market Functionality

The application shall:

* Display a list of market assets
* Show current price and price change
* Allow the user to select an asset
* Retrieve asset data from an external (foreign) server
* Provide an option to select the market type (e.g., cryptocurrency, currency exchange)

## 2. Asset Details

The application shall:

* Display detailed information about a selected asset
* Show a price chart
* Support switching between different time periods (if applicable)

## 3. Trading Simulation

The application shall:

* Simulate buying an asset
* Display confirmation of the simulated trade

## 4. Portfolio Management

The application shall:

* Display a list of purchased assets
* Show the total portfolio balance
* Reflect simulated trades in the portfolio

## 5. Profile Management

The application shall:

* Display user profile information
* Allow editing of user data
* Preserve user data during screen rotation

## 6. Settings

The application shall:

* Support switching between Light and Dark themes
* Allow enabling or disabling notifications

## 7. Background Services and Notifications

The application shall:

* Include a background service
* Periodically simulate market updates
* Display notifications for price updates or completed trades

## 8. Localization and Adaptation

The application shall:

* Support multiple languages (English and Arabic)
* Correctly mirror the user interface in RTL mode
* Preserve screen state during device rotation

# Quick Start

Clone the repository and build the project:

```bash
./gradlew assembleDebug
```

To run tests:

```bash
./gradlew test
```
