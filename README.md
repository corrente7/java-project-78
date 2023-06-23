[![Actions Status](https://github.com/corrente7/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/corrente7/java-project-78/actions)
<a href="https://codeclimate.com/github/corrente7/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/dfbaec09a8f5ee525fb1/maintainability" /></a>
<a href="https://codeclimate.com/github/corrente7/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/dfbaec09a8f5ee525fb1/test_coverage" /></a>
[![Java CI with Gradle](https://github.com/corrente7/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/corrente7/java-project-78/actions/workflows/main.yml)
# Name: # 
Data Validator

# Description and usage: # 
Student project in Hexlet school. 

Validates strings, numbers and maps.
The steps are following:
1) Create an object of validator
2) Determine a data validation schema using a set of methods (limitations) for each data type.
3) Call isValid() on the validation schema with the data that requires validation.
If the data matches all the limitations specified in the schema, isValid() returns true, otherwise - false.

# Usage: # 
### Strings Validation ###

Validator v = new Validator();

StringSchema schema = v.string();

_// before required()_
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(""); // false
schema.isValid(5); // false
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false

### Numbers Validation ###

Validator v = new Validator();

NumberSchema schema = v.number();

// before required()
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid("5"); // false
schema.isValid(10) // true

_// positive() was called ealier_
schema.isValid(-10); // false

schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false

### Maps Validation ###

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

### Validation of nested objects ###

import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();

_// setting a validation schema for "name" and "age"_
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

_// passing the schemas to shape() method_
schema.shape(schemas);

_// checking objects_
Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
