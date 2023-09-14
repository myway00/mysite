<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn:replace(vo.content ,newline ,"<br/>")}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<c:choose>
						<c:when test="${authUser.name eq vo.userName}">
							<a
								href="${pageContext.request.contextPath }/board?a=updateform&no=${vo.no}">글수정</a>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${authUser.name != null }">
							<a
								href="${pageContext.request.contextPath }/board?a=commentform&no=${vo.no}">답글
							</a>
						</c:when>
					</c:choose>

					<form name="modify" method="post"
						action="${pageContext.request.contextPath }/board?a=comment">
						<input type="hidden" name="group_no" value="${vo.groupNo}" /> <input
							type="hidden" name="order_no" value="${vo.orderNo}" /> <input
							type="hidden" name="depth" value="${vo.depth}" />
					</form>
				</div>

			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>