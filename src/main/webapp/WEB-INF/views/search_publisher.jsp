<%@page import="com.study.dvd.dao.PublisherDao"%>
<%@page import="java.util.List"%>
<%@page import="com.study.dvd.entity.Publisher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String st = request.getParameter("searchText");
	List<Publisher> publishers = PublisherDao.searchPublisherByPublisherName(st);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, th, td {
		border: 1px solid #dbdbdb;
		border-collapse: collapse;
	}
	
	th,td {
		padding: 5px 10px;
	}
	
</style>
</head>
<body>
	
	<div>
		<label>발행사 검색</label>
		<input type="text"
			class="search-input"
			placeholder="발행사명을 입력해주세요">
		<button onclick="handlePublisherSearchClick()">검색</button>
	</div>
	
	<table>
		<thead>
			<th>발행사ID</th>
			<th>발행사</th>
		</thead>
		<tbody>
			<%
				for(Publisher publisher : publishers) {
			%>
				<tr>
					<td><%=publisher.getPublisherId() %></td>
					<td><%=publisher.getPublisherName() %></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
	
	<script src="/dvd/static/search_publisher.js"></script>
	
</body>
</html>