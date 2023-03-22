# Notifier

Notifier about new appointments for city registration

## Usage

```bash
git clone git@github.com:e2e4b6b7/Notifier.git
cd Notifier
./gradlew build
java -jar build/libs/Notifier.jar
```

Or just download jar file from CI, unzip it and execute it
```
java -jar Notifier.jar
```

You can add `--help` option to show available options

## Expected behaviour

The program prints the new best appointment to the console after each change and notifies you with a strange sound.
