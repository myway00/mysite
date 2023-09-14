| 목적                           | 기존 방식                                     | EL 표현식                                   |
|--------------------------------|---------------------------------------------|---------------------------------------------|
| 컨텍스트 경로 가져오기          | `<%=request.getContextPath()%>`             | `${pageContext.request.contextPath}`         |
| 요청 파라미터 값 가져오기        | `<%=request.getParameter("no") %>`         | `${param.no}`                               |
| 문자열 치환 (replace)           | `<%pageContext.setAttribute("newline", "\n");%>` | `${fn:replace(vo.message, newline, "<br/>")}` |
| 리스트 크기 가져오기             | `[리스트 사이즈] list.size()`              | `${fn:length(list)}`                        |
| 리스트 반복하기                  | `[리스트 for문] for(Vo vo : list)`          | `<c:forEach items="${list}" var="vo">`      |
| 증감 연산 수행하기              | 증감 연산자를 지원하지 않음                    | `<c:set var="index" value="${index + 1}"/>` |
  
| 기능                             | `<c:set>` 태그 사용 방법                                     |
|----------------------------------|------------------------------------------------------------|
| 변수 설정 및 할당               | `<c:set var="변수명" value="값" />`                          |
| EL 표현식 계산                 | `<c:set var="변수명" value="${표현식}" />`                   |
| 속성 설정                       | `<c:set target="HTML요소" property="속성명" value="값" />`   |
| 변수 범위(scope) 지정           | `<c:set var="변수명" value="값" scope="범위" />`             |
