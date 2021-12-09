<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="resources/data-components/service_center/common.js"></script>

<div class="w1100">
	<div class="sub_hd">
		<h2>고객센터</h2>
	</div>
	
	<ul class="basic_step m-b-50">
		<li class="active">
			<a href="notice_page">공지사항</a>
		</li>
		<li>
			<a href="questions_page">자주하는 질문</a>
		</li>
		<c:if test="${userData.STATUS eq 'U' or userData.STATUS eq 'P'}">
		<li>
			<a href="inquiry_page">고객센터 문의</a>
		</li>
		</c:if>
		
		<c:if test="${userData.STATUS eq 'A'}">
		<li>
			<a href="inquiry_page">문의 리스트</a>
		</li>
		</c:if>
	</ul>
	
	<div class="view p-b-110">
		<input type="hidden" id="no" value="${notice_details.NO}">
		<div class="header">
			<div class="sub_title_wrap">
				<h3 class="sub_title m-b-10">${notice_details.TITLE}</h3>
			</div>
			<div class="sub_contents gary">
				<div class="sub_contents_txt">
					<i class="fas fa-user"></i>&nbsp;${notice_details.NICKNAME}&nbsp;
					<i class="far fa-calendar-alt"></i>&nbsp;${notice_details.WRITE_DATE}&nbsp;
					<i class="far fa-eye"></i>&nbsp;${notice_details.VIEWS}&nbsp;
				</div>
			</div>
			
			<div class="modify_delete_btn">
				<a class="modify_btn" href="notice_modify_page?NO=${notice_details.NO}">수정</a>
				<a class="delete_btn" onclick="notice_delete()">삭제</a>
			</div>
		</div>
		<div class="viewbody gary">
			<h6>
				${notice_details.CONTENTS}
				안녕하세요.<br>
				자유여행의 시작 민다입니다.<br><br>
				2021년 10월 4일(월)은 대체공휴일 휴무로<br>
				민다 고객센터가 운영되지 않습니다.<br>
				(*10월2일(토)~10/4일(월) : 주말 및 대체공휴일 휴무)<br><br>
				문의사항은 로그인후 우측 상단 [고객센터] > [고객센터 문의] 란을 이용하여 접수해주시면,<br>
				고객센터가 정상 운영되는 10월 5일(화)부터 순차적으로 처리될 예정입니다.<br><br>
				고맙습니다. 
			</h6>
		</div>
	</div>
</div>