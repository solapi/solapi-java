# SOLAPI SDK for Java

[![Java 9.0](https://img.shields.io/badge/Java-v9.0-red.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

## 설정
`app/config.ini` 파일의 설정이 맞는지 확인하고 적절히 수정해 주세요.

## 예제 파일 실행
`app/src/main/java/solapi/app/` 아래 예제 파일이 위치해 있습니다. gradle을 이용해서 실행 하려면 app/build.gradle 파일 내용 중 mainClass를 수정해 주세요.

```
application {
    // Define the main class for the application.
    mainClass = 'solapi.app.GetMessageList'
}
```

아래 명령으로 실행합니다.

```
./gradlew run
```
