# trello-metrics

Project implemented in Kotlin to get metrics from your boards on [Trello](https://trello.com/).

## Instructions to run

1. Clone this repository.
2. Run the buid in the project root directory path:
```
./gradlew clean build
```
3. Execute jar file:
```
java -jar build/libs/trello-metrics-0.1.0.jar -apiKey: {{TRELLO_API_KEY}}  -token: {{TRELLO_TOKEN}} -idBoard: {{BOARD_ID}} -initDate: {{dd/MM/yyy}} -finalDate: {{dd/MM/yyy}}
```

*Obs: To get your TRELLO_API_KEY and TRELLO_TOKEN, access [Trello Developers](
https://developers.trello.com/).*

