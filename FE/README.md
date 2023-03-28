# 

# GIT

1. GIT LAB
   
   - 우선, git lab을 활용하여 merge request 를 하여 지속적인 코드 형상을 관리할 예정.

2. GIT FLOW

- 개발자 입장 :
  
  - [초기 설정]
  
  - ```markdown
    git clone 
    or 
    git init
    git remote add origin [주소]
    git config --global user.name "김동희"
    git config --global user.email "dongh94@naver.com"
    git pull origin master
    ```
  
  - [개발] 시작
  
  - ```markdown
    git switch -c [branch name] (branch 생성 후 이동까지 )
    ```
  
  - [개발] 기능 단위로 commit
  
  - ```markdown
    git add [filename]
    git commit -m ["commit convention"]
    ```
  
  - commit convention
    
    - ### 날짜[역할 구분][커밋 분류]_"상세 내용"
      
      - 날짜 예시) 220721
      - 역할 구분) FE,BE
      - 커밋 분류)
        - feat : 새로운 기능에 대한 커밋
        - fix : build 빌드 관련 파일 수정에 대한 커밋
        - build : 빌드 관련 파일 수정에 대한 커밋
        - chore : 그 외 자잘한 수정에 대한 커밋(rlxk qusrud)
        - ci : CI 관련 설정 수정에 대한 커밋
        - docs : 문서 수정에 대한 커밋
        - style : 코드 스타일 혹은 포맷 등에 관한 커밋
        - refactor : 코드 리팩토링에 대한 커밋
        - test : 테스트 코드 수정에 대한 커밋
      - 상세 내용) MemberRepository() 구현, Jwt 토큰 인증 구현
      - 작성 예시) **220721[FE][feat]_"LoginService() 메소드 구현"**
  
  - ```markdown
    git push origin [branch name]
    (ctrl + click) gitlab [주소] 
    ```
  
  - ```markdown
    [[ gitlab site ]]
    Merge request(set Assignee) >> 
    Approve(after review/resolved) >> 
    Merge !!(check squash commits)
    ```
  
  - ```markdown
    git log --oneline --all --graph
    git branch
    git switch master
    git branch -d [branch name]
    ```

- ```markdown
  git pull origin master
  ```



## FE : Vue 개발을 위한 VSCode 개발환경 설정 정리

<< 확장자 >>

- Auto close Tag : 자동 태그 닫기

- Auto rename Tage : 자동 태그 수정

- Color Highlight : 컬러 색상 미리보기

- Highlight Matching Tag : 일치하는 테그를 하이라이팅 해주는 확장프로그램

- EditorConfig for VS Code : 에디터에서 일정한 코드 Convention을 유지할 수 있게 여러가지 옵션과 설정을 지정할 수 있음.

- Live Server : 코딩을 실시간으로 확인-적용시켜 작업하기 편리

- ~~One dark pro : 어두운 테마로 작성하는 코딩이 눈에 잘 띔~~

- ~~Korean Language Pack for Visual Studio Code : VSCode 한국어 지원~~

- Prettier : 코드 자동 정렬 ( **추후 추가 설정 필요** )

- ESLint : 소스코드를 분석해 문법 에러, 버그를 찾기 ( **추후 추가 설정 필요** )
  
  - 중요 !
  
  - vue project와 함께 git pull & push 에서 문제가 나는 것.
  
  - LF 와 CRLF 문제 차이였음 (
    
    ```null
    Line must end with LF, not CRLF)
    아래 줄로 이동 하는 것이 Line Feed(LF)이고, 왼쪽 끝으로 밀어 주는 것이 Carrige Return(CR)이다.
    ```
  
  - 해결 : .eslintrc.js 파일 안에서
  
  - `rules: {`} : rules 속성에
  
  - `"prettier/prettier": ["error", { "endOfLine": "auto" }] ,` 추가한다.

[ Vue관련 ]

- Vetur : 문법 강조, vue.js 파일 코드의 하이라이팅 지원

- vscode-icons : VSCode 내 파일의 아이콘을 설정하여 무슨 파일인지 한번에 파악가능

- Vue VSCode Snippets : 코드 조작 지원, vue.js 파일 초기 구성 생성

- ~~vue-beautify : 코드정렬~~
