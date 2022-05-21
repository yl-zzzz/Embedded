# Embedded
# PIR센서 기반 밀폐 된 장소 사고 방지 시스템
 
 # 1. 프로젝트의 필요성
 밀폐된 곳에서 갇히는 사건을 기사를 통해 많이 보았다.<br>
 만약 주변에 사람이 없고, 연락할 수단이 없다면  못 빠져나올 가능성이 있다.<br>
 사람들이 갇혀있다면 특정 위치에 pir 센서를 설치 해놓고 설명서를 써놓고,<br>
 만약 갇혔다면 pir 센서가 인식하도록 해서 설정해둔 시간 이상 인식된다면 긴급 관리자에게 전화를 가게 하거나,<br> 
 근처를 지나가는 사람에게 보일 수 있도록 프로그램을 개발한다면 오랜 시간 갇혀서 위험한 상황이 나오는 것을 줄일 수 있을 것이다.<br>

 # 2. 시스템 구조
  PIR sensor :  움직임 감지, 객체 감지, Observe Option을 이용한 주기적인 움직임 감지<br>
  LCD Display :  밀폐된 장소의 온도와 갇혀있는 상황을 출력<br>
  Temperature sensor : 밀폐된 장소의 온도를 측정하는 센서<br>
  CoAP 서버 : CoAP 통신을 사용한 Resource 관리<br>
  CoAP Client/UI : CoAP 클라이언트와 UI, 센서와 실행, 상황 모니터링<br>
  ![image](https://user-images.githubusercontent.com/71144019/169632820-3d4db958-fe44-4d9e-a677-ec6993601fc7.png)

# 3. 하드웨어 구현
  -PIR: Ground, GPIO 0, 5V
  -LCD Display: Ground, 5V, I2C SDA(GPIO 8), I2C SCL(GPIO 9)
  - DHT11(temperature): Ground, 3.3V , GPIO 15
![image](https://user-images.githubusercontent.com/71144019/169632866-84726107-6baa-4abb-9e93-bd1f4babb281.png)<br>
![image](https://user-images.githubusercontent.com/71144019/169632869-6539aaf5-2470-41e4-bbb1-1665a954d516.png)<br>


# 3. 프로젝트 과정
### 1. 공공 데이터 포털에서 실시간 데이터 발급 
### 2. MongoDB에 데이터 저장
### 3. Publish서버가 토픽 발행하고 Broker 서버로 전달
### 4. Subscriber가 토픽 구독하면 Broker 서버가 데이터 전달
### 5. html에 실시간 정보 출력
 
# 3. 결과 화면
![image02](https://user-images.githubusercontent.com/71144019/122520907-2bc11500-d04f-11eb-9fb0-58c3ce8b5841.png)

# 4. 데모 영상
https://youtu.be/QFmswJG9XbM
![image](https://user-images.githubusercontent.com/71144019/169633054-83f8aa19-d9c0-407f-b2b6-7d83a0cdd5d7.png)

# 5. 프로젝트를 하면서 느낀점
 이 프로젝트를 하면서 라즈베리파이와 센서를 연결하는 법과 센서 값들을 읽어오는 방법을 배웠다.<br>
 라즈베리파이에 대해서 직접 회로도 연결해보고 배운점이 많아 성장할 수 있었던 프로젝트였다.<br>
 실제로 이러한 프로그램이 나온다면 위험한 냉동창고나 엘레베이터에 갖힌 사람들이 안전하게 나올 수 있을 것같다.<br>
 아쉬운 점은 보기 쉽게 UI를 바꾸거나 웹, 앱으로도 활용이 가능하게 했었으면 더 좋은 프로젝트 결과가 나올 수 있었을 것이다.
 앞으로 더 확장시켜 사용 할 수 있도록 개발하고싶다.<br>
 
 
