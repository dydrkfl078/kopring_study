<%@ page import="com.example.prac3_servlet.domain.member.Member" %>
<%@ page import="com.example.prac3_servlet.domain.member.MemberRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    System.out.println("username = " + username);
    System.out.println("age = " + age);

    Member member = new Member(username,age);
    MemberRepo memberRepo = new MemberRepo();
    memberRepo.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>성공</p>
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getName()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="../../index.html">메인</a>
</body>
</html>
