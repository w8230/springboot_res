<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 게스트하우스 리스트 [s] -->
<section class="m-t-20 m-b-20">
	<div class="container">
		<div class="title_box m-b-20">
			<h5>원하시는 숙소를 찾아보세요.</h5>
		</div>
		<div class="row">
			<!-- 왼쪽 사이드바 [s] -->
			<div class="tit_and_tab_module col-lg-3 col-md-3">
				<div class="tab_btn_box_stay_list">
					<div class="tab_btn is_active">
						<span class="stay_kind_img"><i class="fas fa-home"></i></span>
						<span class="stay_kind">게하모 숙소</span>
					</div>
					<div class="tab_btn">
						<span class="stay_kind_img"><i class="fas fa-home"></i></span>
						<span class="stay_kind">제주</span>
					</div>
					<div class="tab_btn">
						<span class="stay_kind_img"><i class="fas fa-home"></i></span>
						<span class="stay_kind">서귀포</span>
					</div>
					<c:if test="${userData.STATUS eq 'P'}">
					<a class="tab_btn" href="gh_write_page">
						<span class="stay_kind_img"><i class="fas fa-pen"></i></span>
						<span class="stay_kind">글작성</span>
					</a>
					</c:if>
				</div>
			</div>
			<!-- 왼쪽 사이드바 [e] -->
			
			<!-- 오른쪽 게스트하우스 리스트 [s] -->
			<div class="col-lg-9 col-md-9">
				<div class="serch_total">
					<h6 class="m-b-0">검색된 숙소 <strong>${total}</strong>개</h6>
				</div>
				<div class="tit_and_info_area">
					<h3 class="m-b-0">숙소</h3>
				</div>
				
				<div class="post_list">
					<c:if test="${list eq '[]'}">
						<div class="no_list_box p-t-180 p-b-180">
							<div class="no_list_txt_box">
								등록된 숙소가 없습니다.
							</div>
						</div>
					</c:if>
					<c:forEach items="${list}" var="list">
					<div class="list_box">
						<div class="list_item_link" onclick="location.href='gh_details_page?NO=${list.NO}'" style="cursor:pointer;">
							<div class="list_contents-left">
								<div class="thumbnail_box">
									<img src="resources/assets/main/gh_img/${list.THUMBNAIL}">
								</div>
							</div>
							<div class="list_contents-right">
								<div class="list_txt_top">
									<div class="translated_name_and_name">${list.PARTNER_NAME}</div>
									<div class="added_info">
										<div class="location">${list.ADDR}</div>
									</div>
								</div>
								<div class="list_txt_bottom">
									<div class="added_info-mid">
										<div class="dot_list">즉시예약 · 전액결제</div>
									</div>
									<div class="added_info-last">
										<div class="facilities">${list.PUBLIC_FACILITY}</div>
									</div>
									<div class="essential_info">
										<div class="no_result__review">리뷰 ${list.CNT}</div>
										<div class="wish_total">좋아요 ${list.LIKES}</div>
									</div>
								</div>
								
								<div class="price_box">
									<div class="month_stay_price">
										<div>
											<div class="number_of_nights">1박</div>
										</div>
										<div class="current_price">
										<b style="color: #000000">
											${list.PRICE}원
										</b>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
				
				<!-- 페이징 [s] -->
				<div class="text-center m-t-30 m-b-60">
					<c:if test="${paging.NOW_PAGE > 1}">
						<a href="gh_list_page?page_num=${paging.NOW_PAGE-1}" class="m-r-20">이전</a>
					</c:if>
					<div class="paging_box">
						<c:forEach items="${list}" var="list" begin="1" end="${paging.TOTAL_PAGE}" varStatus="status">
							<c:if test="${paging.NOW_PAGE eq status.count}">
								<a href="gh_list_page?page_num=${status.count}" class="sp_active">${status.count}</a>
							</c:if>
							<c:if test="${paging.NOW_PAGE ne status.count}">
								<a href="gh_list_page?page_num=${status.count}">${status.count}</a>
							</c:if>
					    </c:forEach>
					</div>
					<c:if test="${paging.NOW_PAGE < paging.TOTAL_PAGE}">
						<a href="gh_list_page?page_num=${paging.NOW_PAGE+1}" class="m-l-20">다음</a>
					</c:if>
				</div>
				<!-- 페이징 [e] -->
			</div>
			<!-- 오른쪽 게스트하우스 리스트 [e] -->
		</div>
	</div>
</section>
<!-- 게스트하우스 리스트 [e] -->