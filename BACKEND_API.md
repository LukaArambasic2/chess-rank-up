# Backend API Documentation

All endpoints start with `/api`.

## Section endpoints

### `GET` /sections

#### Description

Retrieves all sections.

### `GET` /sections/{idSection}

#### Description

Retrieves a specific section if it exists.

#### Response

OK (200) - Section Found

NOT FOUND (404) - Section Not Found

### `POST` /sections

#### Description

Creates a new section.

### Data

```json
{
  "name": "Section Name"
}
```

## News endpoints

## Calendar endpoints

## Profile Endpoints

## Scoreboard Endpoints

### `GET` /sections/{idSection}/scoreboard/total

#### Description

Retrieves the points for members in a specific section for the last semester.

### `GET` /sections/{idSection}/scoreboard/semester

#### Description

Retrieves the points for members in a specific section for the last semester.

### `GET` /sections/{idSection}/scoreboard/year

#### Description

Retrieves the points for members in a specific section for the current year. <br>
If current semester is in the Winter then only the points for the Winter semester are returned. <br>
If current semester is in the Summer then the points for both the Winter and Summer semesters are returned.

### Example Response

All endpoints return the same response structure:

```json
[
  {
    "firstName": "John",
    "lastName": "Doe",
    "points": 15
  },
  {
    "firstName": "Jane",
    "lastName": "Smith",
    "points": 10
  }
]
```

## Leaderboard Endpoints

## Admin Page Endpoints
