<%@page import="org.kosta.semi.model.FileVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="uploadTest.jsp">upload</a>
<%-- <c:forEach begin="0" end="${requestScope.list.length}" var="loop" >
</c:forEach>
 --%>
 
<%--  <%	ArrayList<FileVO> list = (ArrayList<FileVO>)request.getAttribute("list"); %>
 <% long fileSize = (long)request.getAttribute("fileSize"); %>
 		<table>
 		<%for(int i=0; i<list.size(); i++){ %>
 			<tr>
 				<td><%=list.get(i).getFilePath() %>\<%=list.get(i).getFileName() %></td>
 				<td><a href="TestDownloadController.do?name=<%=list.get(i).getFileName() %>">
 				<%=list.get(i).getFileName() %></a></td>
 			</tr>
 			 <% } %>
 		</table> --%>
 		
<%-- ${pageContext.request.contextPath} --%>
 <table>
 	<tr>
 		<td><a href="../TestDownloadController.do?name=${param.name }">${param.name }</a></td>
 	</tr>
 </table>
</body>
</html>