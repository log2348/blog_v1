<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body>
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
      <!-- Brand -->
      <a class="navbar-brand" href="/blog">블로그</a>

      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Navbar links -->
      <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <c:choose>
            <c:when test="${empty sessionScope.principal}">
              <li class="nav-item">
                <a class="nav-link" href="/blog/user/login_form">로그인</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/blog/user/join_form">회원가입</a>
              </li>
            </c:when>
            <c:otherwise>
              <!-- 로그인 성공하면(회원이면) 글 작성 가능 -->
              <li class="nav-item">
                <a class="nav-link" href="/blog/user/login_form">글쓰기</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/blog/user/join_form">로그아웃</a>
              </li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </nav>
    <br />
    <!-- 자바 스크립트 추가할 때는 그 페이지의 밑에 작성 -->
  </body>
</html>
