<%@ page import="model.Profile" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
    List<Profile> scrapList; String img_url;
%>
<html>
<head>
    <title>스크랩</title>
    <style>
        @font-face {
            font-family: 'SBAggroB';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroB.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        @font-face {
            font-family: 'SBAggroM';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroM.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        @font-face {
            font-family: 'SBAggroL';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroL.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        html, body {
            width: auto;
            height: auto;
            text-align: center;
        }
        #logo {
            margin-top: 10px;
            width: 70px;
        }
        h3.roomietitle {
            margin-top: 40px;
            font-family: SBAggroB;
            float: left;
            color: black;
            margin-left: 8%;
        }
        h3.scraptitle {
            font-family:SBAggroL;
            text-align: right;
            padding-top: 5px;
            padding-bottom: 10px;
            margin-right: 8%;
            margin-bottom: 4%;
        }
        h4 {
            font-family: SBAggroL;
        }
        a {
            font-family: SBAggroL;
        }
        .card-margin {
            font-family: SBAggroL;
            width: 40%;
            display: inline-block;
            padding-bottom: 2%;
            margin: 0 4.9%;
        }
        .mb-3 {
            width: 100%;
            padding: 4%;
            border-radius: 10px;
            cursor: pointer;
        }
        .col-md-4 {
            border-radius: 10px;
            margin: 0 2%;
        }
        .col-md-8 {
            margin-right: -7%;
            margin-left: 3%;
        }
        #footer {
            position: relative;
            width: 100%;
            bottom: 0;
            font-family: SBAggroL;
            text-align: center;
            font-size: x-small;
            padding-bottom: 50px;
            margin-top: 100px;
        }
        #footer p {
            padding: 0px 30px;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<%!
    int totalCount;
    int countList;
    int totalPage;
    int i;
%>
<%
    totalCount = 25;
    countList = 10;

    totalPage = totalCount / countList;

    if (totalCount % countList > 0) {
        totalPage++;
    }
%>

<span id="top"><div class="w-auto p-2" style="background-color: lightcyan; text-align: center;"><h4>당신의 루미를 찾아보세요! <button class="btn btn-outline-success" type="submit" onClick="location.href='/student/automatch'">자동매칭 하러가기</button></h4></div></span>

<a href = "${pageContext.request.contextPath}/student/main"><h3 class="roomietitle">Roomie</h3>
<img src="/images/logo-font.png" id="logo" /></a>

<h3 class="scraptitle">스크랩</h3>

<%scrapList = (List<Profile>) request.getAttribute("scrapList");%>
<c:forEach var="profile" items="${scrapList}">
    <div class="card-margin">
        <div class="card mb-3" style="border-radius: 10px; max-width: 600px;" onclick="location.href='/student/main/detail?s_id=' + ${profile.s_id}">
            <div class="row g-0">
                <div class="col-md-4">
                    <c:if test="${profile.pr_img eq 0}"><%img_url = "/images/man1.png";%></c:if>
                    <c:if test="${profile.pr_img eq 1}"><%img_url = "/images/man2.png";%></c:if>
                    <c:if test="${profile.pr_img eq 2}"><%img_url = "/images/woman1.png";%></c:if>
                    <c:if test="${profile.pr_img eq 3}"><%img_url = "/images/woman2.png";%></c:if>
                    <img style="border-radius: 10px;" src="<c:url value='<%=img_url%>' />" class="img-fluid rounded-start" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${profile.name}</h5>
                        <p class="card-text">전공: ${profile.major}&emsp;&emsp;&emsp;&emsp;&emsp; <br>나이: ${profile.age}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                        <p class="card-text"><small class="text-muted"></small></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<footer id="footer">
    <hr>
    <p>데이터베이스프로그래밍 01-06 ©야-심차게<br>권민지 김유림 김은혜 박초은</p>
</footer>
<div style="position: fixed; bottom: 20px; right: 20px; cursor: pointer;">
    <a href="#top"><img src="/images/btnTop2.png" title="맨 위로" style="width: 60px; height: 63px;"></a>
</div>
</body>
</html>