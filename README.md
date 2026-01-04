# Chivalry 2 Weapon Stats API

A Spring Boot REST API serving weapon statistics from Chivalry 2 with my future intentions to eventually support a dueling card game between opponents.

## API Endpoints

### Weapons

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/weapons` | Get all weapons (summary view) |
| GET | `/api/weapons?category={category}` | Filter by category |
| GET | `/api/weapons?damageType={type}` | Filter by damage type |
| GET | `/api/weapons/{id}` | Get weapon by ID (full details) |
| GET | `/api/weapons/name/{name}` | Get weapon by name |
| GET | `/api/weapons/random` | Get a random weapon |
| GET | `/api/weapons/top?limit={n}` | Get top N weapons by total damage |
| GET | `/api/weapons/categories` | List all weapon categories |
| GET | `/api/weapons/damage-types` | List all damage types |

### Example Responses

**GET /api/weapons** (Summary)
```json
[
  {
    "id": 1,
    "name": "Greatsword",
    "category": "2-Handed Swords",
    "damageType": "Cut",
    "averageDamage": 67.5,
    "maxDamage": 100
  }
]
```

**GET /api/weapons/1** (Full Details)
```json
{
  "id": 1,
  "name": "Greatsword",
  "category": "2-Handed Swords",
  "damageType": "Cut",
  "damage": {
    "slash": 50,
    "stab": 50,
    "overhead": 70,
    "special": 100,
    "average": 67.5,
    "max": 100
  },
  "timing": {
    "slash": {
      "windup": 0.325,
      "windupHeavy": 0.675,
      "release": 0.55,
      "recovery": 1.1,
      "totalTime": 1.975,
      "totalTimeHeavy": 2.325
    },
    "stab": { ... },
    "overhead": { ... },
    "special": { ... },
    "fastestWindup": 0.325
  }
}
```

## Database

The application uses H2 with file persistence:
- **Location**: `./data/chivalry.mv.db`
- **H2 Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:file:./data/chivalry`
