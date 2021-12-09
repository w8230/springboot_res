<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w1100">
	<div class="sub_hd">
		<h2>고객센터</h2>
	</div>
	
	<ul class="basic_step m-b-50">
		<li>
			<a href="notice_page">공지사항</a>
		</li>
		<li class="active">
			<a href="questions_page">자주하는 질문</a>
		</li>
		<c:if test="${userData.STATUS eq 'U' or userData.STATUS eq 'P'}">
		<li>
			<a href="inquiry_write_page">고객센터 문의</a>
		</li>
		</c:if>
		
		<c:if test="${userData.STATUS eq 'A'}">
		<li>
			<a href="inquiry_page">문의 리스트</a>
		</li>
		</c:if>
	</ul>
	
	<div class="m-t-10 m-b-10">
		<div class="posts_total">
			<h6>총 <span class="color_red">${total}</span>건의 글이 등록되었습니다.</h6>
		</div>
	</div>
	
	<c:if test="${list eq '[]'}">
		<div class="no_posts text-center p-t-180 p-b-180">
			<div class="no_list_txt_box">
				등록된 자주하는 질문이 없습니다.
			</div>
		</div>
	</c:if>
	
	<c:if test="${list ne '[]'}">
		<table class="tb">
		<colgroup>
			<col style="width:100px">
			<col>
			<col style="width:170px">
			<col style="width:150px">
			<col style="width:100px">
		</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list">
					<tr>
						<td class="text-center">${list.NO}</td>
						<td><a href="questions_details_page?NO=${list.NO}">${list.TITLE}</a></td>
						<td class="text-center">${list.NICKNAME}</td>
						<td class="text-center">${list.WRITE_DATE}</td>
						<td class="text-center">${list.VIEWS}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	
	<c:if test="${userData.STATUS eq 'A'}">
		<div class="m-t-23">
			<a href="questions_write_page" class="write_btn">글쓰기</a>
		</div>
	</c:if>
	
	<!-- 페이징 [s] -->
	<div class="text-center m-t-30 m-b-60">
		<c:if test="${paging.NOW_PAGE > 1}">
			<a href="questions_page?page_num=${paging.NOW_PAGE-1}" class="m-r-20">이전</a>
		</c:if>
		<div class="paging_box">
			<c:forEach items="${list}" var="list" begin="1" end="${paging.TOTAL_PAGE}" varStatus="status">
				<c:if test="${paging.NOW_PAGE eq status.count}">
					<a href="questions_page?page_num=${status.count}" class="sp_active">${status.count}</a>
				</c:if>
				<c:if test="${paging.NOW_PAGE ne status.count}">
					<a href="questions_page?page_num=${status.count}">${status.count}</a>
				</c:if>
		    </c:forEach>
		</div>
		<c:if test="${paging.NOW_PAGE < paging.TOTAL_PAGE}">
			<a href="questions_page?page_num=${paging.NOW_PAGE+1}" class="m-l-20">다음</a>
		</c:if>
	</div>
	<!-- 페이징 [e] -->
</div>