# Static Bug Finder - PMD

This project utilizes PMD, a static code analysis tool, to detect potential bugs, code style issues, and performance problems in Java code. PMD helps to enforce best practices and improve overall code quality by scanning the source code for predefined patterns and rule violations.

## Running the Bug Finder

### Maven
To run PMD execute the following command on terminal (MacOS):

```bash
pmd check -d ./4156-Miniproject-2024-Students-Java/IndividualProject -R rulesets/java/quickstart.xml -f text
```
