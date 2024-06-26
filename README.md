# 1. ToDo 리스트

-자신의 할일을 카드로 작성할 수 있는 기능을 가지고 있다. 

-아래는 Use Case Diagram이며 할일 카드 생성, 할일 조회(전체 목록 조회 및 선택한 할일 카드 조회), 삭제 기능이 있다.

-생성된 카드는 id,제목(title),작성자(name),내용(description),날짜(date) 형식으로 반환값을 준다.

-날짜는 작성 시간을 기준으로 기록된다.


# 2. Use Case Diagram

![제목 없는 다이어그램 - draw io - Chrome 2024-05-23 오후 7_11_14](https://github.com/gooddle/ToDo/assets/128583844/c3075a32-3ef3-4cfc-9c4d-6d03af22c073)


- Actor은 사용자를 의미하며 할일 카드를 작성

- 할일 조회는 전체 목록 조회가 가능하며 특정 카드 조회가 가능하다. -> 할일 카드에 작성된 댓글 포함 조회 

- 선택한 특정 할일카드를 삭제가 가능하다.

- 선택한 특정 할일 카드를 수정 가능하다. -> 할일 목록을 완료 했을 시 완료 기능 

- 할일 카드 생성시 반환된 id값을 통해 선택한  할일카드에 댓글 등록 가능

- 댓글 등록시 비밀번호 입력

- 선택한 댓글 수정 및 삭제 시 비밀번호 이용

- 할일 카드 삭제 시 관련 댓글 전부 삭제


# 3. ERD

![ToDoController kt – postgres 2024-05-23 오후 2_54_42](https://github.com/gooddle/ToDo/assets/128583844/91406e00-8c3a-42bb-b6c8-eb0d12aafba0)


# 4. API 명세
| Name     | URI(Resource)  | Method | Status Code |
|--------- | -------------| -------------| -------------|
| 할일 카드 작성 | /todo | POST | 201 |
| 할일 카드 목록 조회 | /todo |  GET | 200 |
| 선택한 할일 카드 조회 | /todo/{todoId} | GET | 200 |
| 선택한 할일 카드 수정 | /todo/{todoId} | PUT | 200 |
| 선택한 할일 카드 삭제 | /todo/{todoId} | DELETE | 204 |
| 댓글 작성 | /todo/{todoId}/comment | POST | 201 |
|  댓글 수정 | /todo/{todoId}/comment/{commentId} |  PUT | 200 |
| 댓글 삭제 | /todo/{todoId}/comment/{commentId} | GET | 200 |
