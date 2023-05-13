# Spring Unit Test
스프링부트에서 docker-compose를 어떻게 사용할수 있는지에 대한 연습용 프로젝트.

### Reference

spring-boot 3.1.0 RC1 버전부터 docker-compose를 지원.
- build.gradle
```groovy
implementation 'org.springframework.boot:spring-boot-docker-compose'
```
- application.yml
```yaml
spring:
  docker:
    compose:
      lifecycle-management:  start_and_stop # container life-cycle에 대한 옵션.
      file: docker-compose-test-redis.yml # 작성 파일이 애플리케이션과 동일한 디렉터리에 없거나 이름이 다른 경우 경로 지정
      start:
        command: start # 서비스 컨테이너 구동 옵션. run | start 선택가능
        log-level: info
      stop:
        command: down # 서비스 컨테이너 구동 옵션. down | stop 선택가능
```
