## Overview

Problem statement: You need to build a scratch game, that will generate a matrix (for example 3x3)
from symbols(based on probabilities for each individual cell) and based on winning combintations
user either will win or lost.

## Assumptions

1. I didn't handle for the scenario where the configuration file doesn't have the correct order
   of probabilities. The length of standard_symbols in probabilities should be the same as size of
   matrix.
2. I focused on predefined symbols from A to F. Missed from G to Z.

## Building

Run next commands:

1. `cd ${projectDir}`
2. `mvn clean package`
3. `java -jar scratchgame-1.0-SNAPSHOT-jar-with-dependencies.jar --config /task/config.json --betting-amount 100`

the result would be (depends on configuration file):

```json
{
   "matrix": [
      ["F", "10x", "F"],
      ["F", "F", "F"],
      ["F", "F", "F"]
   ],
   "reward": 10000,
   "applied_winning_combinations": {
      "F": ["same_symbol_8_times"]
   },
   "applied_bonus_symbol": "10x"
}
```

### Decomposed task

1. Deserialization of JSON (Prepared all necessary POJOs). - DONE
2. Generate 2D matrix - DONE
3. Calculate rewards (same_symbol_X_times) - DONE
4. Pretty JSON Output format - DONE

## TODO

1. Delete WinSymbolGroup
2. Check row and column, it's a valid int number (M x N != max of int). Can't be 0 x 0
3. Check negative bet_amount and 0, infinite

## Nice to have (TODO)

1. Validation for all json structure and non-null fields.