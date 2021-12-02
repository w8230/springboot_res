<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w1100">
	<%-- <c:choose>
		<c:when test="${userData.STATUS eq 'U'}">
		<div class="sub_hd">
			<h2>숙소 예약 내역</h2>
		</div>
		
		<ul class="basic_step">
			<li class="active">
				<a href="my_page1">진행중인 예약</a>
			</li>
			<li>
				<a href="my_page2">지난 예약</a>
			</li>
			<li>
				<a href="my_info_modify_page">정보 수정</a>
			</li>
		</ul>
		
		<div class="term_hd m-b-0i">
			<h3 class="sub_title">진행중인 예약</h3>
		</div>
		</c:when>
		
		<c:when test="${userData.STATUS eq 'P'}">
		<div class="sub_hd">
			<h2>게시글 및 예약 내역 확인</h2>
		</div>
		
		<ul class="basic_step">
			<li class="active">
				<a href="my_page1">내가 쓴 글 보기</a>
			</li>
			<li>
				<a href="my_page2">예약 내역 확인</a>
			</li>
			<li>
				<a href="my_info_modify_page">정보 수정</a>
			</li>
		</ul>
		
		<div class="term_hd m-b-0i">
			<h3 class="sub_title">내가 쓴 글 보기</h3>
		</div>
		</c:when>
	</c:choose>

	<table class="tb text-center m-b-80">
		<colgroup>
			<col style="width:435px">
			<col span="3">
			<col style="width:225px">
		</colgroup>
		<c:choose>
			<c:when test="${userData.STATUS eq 'U'}">
			<thead>
				<tr>
					<th>숙소명</th>
					<th width="176">예약번호</th>
					<th>체크인 날짜</th>
					<th width="112">숙박일수</th>
					<th>예약진행 상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-left">
						<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
						<span class="d_block">대한민국 > 제주</span>
					</td>
					<td>
						<a href="#">1612862996079</a>
					</td>
					<td>2021-03-26</td>
					<td>3박</td>
					<td class="blue">진행중</td>
				</tr>
				<tr>
					<td class="text-left">
						<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
						<span class="d_block">대한민국 > 제주</span>
					</td>
					<td>
						<a href="#">1612862996079</a>
					</td>
					<td>2021-03-26</td>
					<td>3박</td>
					<td class="red">예약취소</td>
				</tr>
				<tr>
					<td class="text-left">
						<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
						<span class="d_block">대한민국 > 제주</span>
					</td>
					<td>
						<a href="#">1612862996079</a>
					</td>
					<td>2021-03-26</td>
					<td>3박</td>
					<td class="green">예약승인</td>
				</tr>
			</tbody>
			</c:when>
			
			<c:when test="${userData.STATUS eq 'P'}">
			<thead>
				<tr>
					<th>제목1</th>
					<th width="176">제목2</th>
					<th>제목3</th>
					<th width="112">제목4</th>
					<th>제목5</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-left">
						<a href="#">내용1</a>
					</td>
					<td>
						<a href="#">내용2</a>
					</td>
					<td>내용3</td>
					<td>내용4</td>
					<td>내용5</td>
				</tr>
				<tr>
					<td class="text-left">
						<a href="#">내용1</a>
					</td>
					<td>
						<a href="#">내용2</a>
					</td>
					<td>내용3</td>
					<td>내용4</td>
					<td>내용5</td>
				</tr>
				<tr>
					<td class="text-left">
						<a href="#">내용1</a>
					</td>
					<td>
						<a href="#">내용2</a>
					</td>
					<td>내용3</td>
					<td>내용4</td>
					<td>내용5</td>
				</tr>
			</tbody>
			</c:when>
		</c:choose>
	</table> --%>
	
	<div class="sub_hd">
		<h2>숙소 예약 내역</h2>
	</div>
	
	<ul class="basic_step">
		<li class="active">
			<a href="my_page1">진행중인 예약</a>
		</li>
		<li>
			<a href="my_page2">지난 예약</a>
		</li>
		<li>
			<a href="my_info_modify_page">정보 수정</a>
		</li>
	</ul>
	
	<div class="term_hd m-b-0i">
		<h3 class="sub_title">진행중인 예약</h3>
	</div>
	
	<!-- <div class="no_posts text-center p-t-180 p-b-180">
		<div class="no_list_txt_box">
			진행중인 예약이 없습니다.
		</div>
	</div> -->
	
	<table class="tb text-center m-b-80">
		<colgroup>
			<col style="width:435px">
			<col span="3">
			<col style="width:225px">
		</colgroup>
		<thead>
			<tr>
				<th>숙소명</th>
				<th width="176">예약번호</th>
				<th>체크인 날짜</th>
				<th width="112">숙박일수</th>
				<th>예약진행 상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-left">
					<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
					<span class="d_block">대한민국 > 제주</span>
				</td>
				<td>
					<a href="#">1612862996079</a>
				</td>
				<td>2021-03-26</td>
				<td>3박</td>
				<td class="blue">진행중</td>
			</tr>
			<tr>
				<td class="text-left">
					<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
					<span class="d_block">대한민국 > 제주</span>
				</td>
				<td>
					<a href="#">1612862996079</a>
				</td>
				<td>2021-03-26</td>
				<td>3박</td>
				<td class="red">예약취소</td>
			</tr>
			<tr>
				<td class="text-left">
					<a href="#">제주 구덕게스트하우스(제주 하이킹인)</a>
					<span class="d_block">대한민국 > 제주</span>
				</td>
				<td>
					<a href="#">1612862996079</a>
				</td>
				<td>2021-03-26</td>
				<td>3박</td>
				<td class="green">예약승인</td>
			</tr>
		</tbody>
	</table>

	<div class="sub_hd">
		<h2>게시글 및 예약 내역 확인</h2>
	</div>
	
	<ul class="basic_step">
		<li class="active">
			<a href="my_page1">내가 쓴 글 보기</a>
		</li>
		<li>
			<a href="my_page2">예약 내역 확인</a>
		</li>
		<li>
			<a href="my_info_modify_page">정보 수정</a>
		</li>
	</ul>
	
	<div class="term_hd m-b-0i">
		<h3 class="sub_title">내가 쓴 글 보기</h3>
	</div>
	
	<!-- <div class="no_posts text-center p-t-180 p-b-180">
		<div class="no_list_txt_box">
			작성한 게시글이 없습니다.
		</div>
	</div> -->
	
	<table class="tb text-center m-b-80">
		<colgroup>
			<col style="width:435px">
			<col span="3">
			<col style="width:225px">
		</colgroup>
		<thead>
			<tr>
				<th>제목1</th>
				<th width="176">제목2</th>
				<th>제목3</th>
				<th width="112">제목4</th>
				<th>제목5</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="text-left">
					<a href="#">내용1</a>
				</td>
				<td>
					<a href="#">내용2</a>
				</td>
				<td>내용3</td>
				<td>내용4</td>
				<td>내용5</td>
			</tr>
			<tr>
				<td class="text-left">
					<a href="#">내용1</a>
				</td>
				<td>
					<a href="#">내용2</a>
				</td>
				<td>내용3</td>
				<td>내용4</td>
				<td>내용5</td>
			</tr>
			<tr>
				<td class="text-left">
					<a href="#">내용1</a>
				</td>
				<td>
					<a href="#">내용2</a>
				</td>
				<td>내용3</td>
				<td>내용4</td>
				<td>내용5</td>
			</tr>
		</tbody>
	</table>
	</c:if>
	
	<c:if test="${userData.STATUS eq 'A'}">
	<div class="sub_hd">
		<h2>관리자 페이지</h2>
	</div>
	
	<ul class="basic_step">
		<li class="active">
			<a href="my_page1">관리자 페이지</a>
		</li>
		<li>
			<a href="my_page2">관리자 페이지</a>
		</li>
		<li>
			<a href="my_info_modify_page">정보 수정</a>
		</li>
	</ul>
	
	<div class="term_hd m-b-0i">
		<h3 class="sub_title">관리자 페이지 내용 없음.</h3>
	</div>
	
	<div class="no_posts text-center p-t-180 p-b-180">
		<div class="no_list_txt_box">
			관리자 페이지 내용 없음.
		</div>
	</div>
	</c:if>
	
	<!-- 페이징 [s] -->
	<div class="text-center m-t-30 m-b-60">
		<a href="javascript:void(0);" class="m-r-20">이전</a>
		<div class="paging_box">
			<a href="javascript:void(0);" class="sp_active">1</a>
			<a href="javascript:void(0);">2</a>
			<a href="javascript:void(0);">3</a>
			<a href="javascript:void(0);">4</a>
			<a href="javascript:void(0);">5</a>
			<a href="javascript:void(0);">6</a>
		</div>
		<a href="javascript:void(0);" class="m-l-20">다음</a>
	</div>
	<!-- 페이징 [e] -->
</div>