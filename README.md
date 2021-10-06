# SOLAPI SDK for Java

[![Java 9.0](https://img.shields.io/badge/Java-v9.0-red.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

## 주의사항
* 예제 실행 시 반드시 JDK 9 버전 이상으로 실행해야 정상적으로 작동합니다.
* 윈도우에서 `error: unmappable character (0xEC) for encoding x-windows-949` 에러가 발생하는 경우 환경변수 내 시스템 변수에 `GRADLEGRADLE_OPTS=-Dfile.encoding=UTF-8`을 추가 해주세요. 

## 설정
`app/config.ini` 파일의 설정이 맞는지 확인하고 적절히 수정해 주세요.

## 예제 파일 실행
`app/src/main/java/solapi/app/` 아래 예제 파일들이 위치해 있으며 gradle을 이용해서 실행 하려면 app/build.gradle 파일 내용 중 mainClass를 수정해 주세요.

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
