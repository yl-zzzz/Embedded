
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

# 3. 하드웨어 구현
  - PIR: Ground, GPIO 0, 5V
  - LCD Display: Ground, 5V, I2C SDA(GPIO 8), I2C SCL(GPIO 9)
  - DHT11(temperature): Ground, 3.3V , GPIO 15
![image](https://user-images.githubusercontent.com/71144019/169632866-84726107-6baa-4abb-9e93-bd1f4babb281.png)<br>
![image](https://user-images.githubusercontent.com/71144019/169632869-6539aaf5-2470-41e4-bbb1-1665a954d516.png)<br>


# 4. 소프트웨어 구현
**Mini_server.java**
 - CoAP client와 연결하고, 동작을 실행시킴.
 - 서버에 PIR, LCD, Temperature 리소스를 추가함.
 - PIR 센서의 Observe 옵션을 활성화하고, 1초마다 센서 값을 전달하도록 구현함.

**Mini_client.java**
- 모니터링을 위한 UI 구현
- start 버튼을 눌렀을 땐 PIR경로에 있는 값을 읽어오고, sendmsg버튼을 누르면 적은 값이 LCD센서로 보내짐.
 - IP주소와 연결해줌.
 -  PIR센서로부터 객체가 인지되면 “trapped” 라고 LCD에 사람이 갇혀있다는 것을 표시함
 - 객체가 20초 이상 센서에 잡히면 UI에 사람이 안에 있다고 출력하고, 
 - “Emergency” 문구 출력해서 위험상황을 알림.

**LCD_display.java**
- 생성자메서드를 만들고, 생성자에서 LCD모듈 사용을 위한 객체 선언과 초기화를 해줌
- PUT메세지를 수신 시, 받은 값을 LCD 두번째 줄에 표시하고, 온도 데이터는 1번 줄에 표시해줌.  

**PIR_sensor.java**
- PIR센서 값을 읽어올 수 있도록 pi4j객체 선언, 초기화
- GET메세지 수신 시 , 수신받은 PIR을 사용해 인접한 객체를 감지하고, 결과를 Payload에 응답	
- Changed메서드를 사용해서 값이 바뀌었을때 , 현재 상태를 바꿈

**DHT11.java**
- Gpio핀 세팅 후, DHT11센서를 사용해서 데이터를 입력받는 메서드 구현
- 핀모드가 다운이 되면서 start시그널을 전송해주고,  시그널이 바뀔때마다 값을 읽어오고, 각 비트 데이터를 저장하고, 체크섬 데이터로 계산을 해서 보내주고, 체크섬을 확인해줌.

**Temp_sensor.java**
- 온도 값을 사용하기 위해 DHT11사용
- 생성자를 통해 초기화해줌
- GET수신을 받으면 데이터를 수신해준다.

# 5. 데모 영상
https://user-images.githubusercontent.com/71144019/169633159-790d5e15-7575-4bd6-af7a-95ee5a800ddc.mp4

# 6. 프로젝트를 하면서 느낀점
 이 프로젝트를 하면서 라즈베리파이와 센서를 연결하는 법과 센서 값들을 읽어오는 방법을 배웠다.<br>
 라즈베리파이에 대해서 직접 회로도 연결해보고 배운점이 많아 성장할 수 있었던 프로젝트였다.<br>
 실제로 이러한 프로그램이 나온다면 위험한 냉동창고나 엘레베이터에 갖힌 사람들이 안전하게 나올 수 있을 것같다.<br>
 아쉬운 점은 보기 쉽게 UI를 바꾸거나 웹, 앱으로도 활용이 가능하게 했었으면 더 좋은 프로젝트 결과가 나올 수 있었을 것이다.
 앞으로 더 확장시켜 사용 할 수 있도록 개발하고싶다.<br>
 
 
