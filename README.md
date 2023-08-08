# WhoAreYou 개인 프로젝트 작성

## 화면 상세 내역

### SignIn Activity

1. 빈칸이 있는 상태로 로그인 시 토스트 메세지 띄움
2. 회원가입이 되어있지 않은 상태로 값을 넣어도 토스트 메세지 띄움
3. registerForActivityResult를 이용해 회원가입 한 데이터 불러옴
4. 로그인 성공 시 토스트 메세지 띄움
5. shared preference를 사용하요 데이터 값을 list로 저장할 수 있음
6. 자동 로그인을 통해 체크 시 따로 회원가입 없이 로그인 함 (추후 예정)
   

### SignUp Activity

1. 아이디, 비밀번호에 n글자 이상 작성해야 회원가입이 됌
2. 아이디, 비밀번호에 n글자 이상 작성을 안할 시 에러 메세지가 보여짐
3. 아이디, 비밀번호, 이름 필수항목에 값이 입력 안될 시 회원가입이 안된다는 토스트 메세지 띄움

### Home Activity

1. 프로필 이미지가 랜덤으로 보여짐
2. SignUp에서 입력한 정보가 그대로 불러옴
3. 종료 버튼 클릭 시 버튼 색, 이모티콘이 변경됨
4. 종료 시 다시 SignIn으로 넘어감


## 앱 실행 화면 예시

![test](https://github.com/joye-seo/WhoAreYou/assets/104261048/45fb9c0d-4fef-4efc-8e04-7961a21bfd04)
