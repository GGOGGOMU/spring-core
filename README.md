# Spring core 꼬꼬무

## 본질
사용자가 만든 POJO 클래스를 프레임워크에서 인스턴스화한다

## 요구 사항
1. POJO 클래스로 구현해야 한다
2. 사용자가 만든 클래스를 인식할 수 있어야 한다
3. 클래스를 인스턴스화 할 때 특별한 언급이 없으면 인스턴스는 싱글턴으로 생성해야 한다
4. 프레임워크에서 클래스를 인스턴스를 생성함에 있어 제어할 수 있어야 한다 

## 구현 사항
1. 사용자 클래스 인식기
2. 인스턴스 저장소
3. 설정 주입기

## 얻는 이점
1. POJO 클래스를 사용함에 따라 다른 프레임워크의 의존성 제거
2. 프레임워크에서 클래스를 인스턴스화 함으로써 프레임워크의 설정으로 객체지향의 이점을 살림
