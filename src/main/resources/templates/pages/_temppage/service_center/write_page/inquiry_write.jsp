<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w1100">
	<div class="sub_hd">
		<h2>고객센터</h2>
	</div>
	
	<ul class="basic_step m-b-50">
		<c:if test="${userData.STATUS eq 'U' or userData.STATUS eq 'P'}">
		<li class="active">
			<a href="inquiry_write_page">고객센터 문의</a>
		</li>
		<li>
			<a href="inquiry_page">문의 내역</a>
		</li>
		</c:if>
	</ul>
	
	<div class="form_wrap">
		<c:if test="${userData.STATUS eq 'U' or userData.STATUS eq 'P'}">
		<h3 class="sub_title">고객센터 문의하기</h3>
		
		<form action="inquiry_write" method="post">
		<input type="hidden" name="USER_ID" id="user_id" value="${userData.USER_ID}">
			<table class="tb tb_frm">
				<tbody>
					<tr>
						<th>서비스</th>
						<td>
							<input type="text" class="no_style" name="SERVICE" id="service">
						</td>
					</tr>
					<tr>
						<th>분류</th>
						<td>
							<input type="text" class="no_style" name="CATEGORY" id="category">
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input class="no_style" name="NAME" id="name" value="${userData.NAME}" readonly>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" class="no_style" name="TITLE" id="title" placeholder="제목을 입력해주세요.">
						</td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td>
							<textarea class="no_style" rows="10" name="CONTENTS" id="contents" placeholder="내용을 입력해주세요."></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="text-center m-t-25 m-b-80">
				<button class="btn bg_blu btn-lg nsdow">등록</button>
				<button class="btn bg_dark_grey btn-lg m-l-5 nsdow">취소</button>
			</div>
		</form>
		</c:if>
	</div>
</div>