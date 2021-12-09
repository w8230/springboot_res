
<script src="resources/data-components/guest_house/gh_details.js"></script>

<div id="container" class="container">
	<input type="hidden" id="no" value="${gh_info.NO}">
	<input type="hidden" id="user_id" value="${userData.USER_ID}">
	<div class="top_info">
		<div class="top_info_box">
			<div class="top_info_box_left">
				<!-- 큰 이미지 [s] -->
				<div class="slider_bx">
					<div style="margin: 0 auto;">
						<div style="width: 100%; overflow: hidden; position: relative; height: 500px;">
							<img style="width: 100%; height: 100%;" src="resources/assets/main/gh_img/${gh_info.THUMBNAIL}">
						</div>
					</div>
				</div>
				<!-- 큰 이미지 [e] -->
				
				<!-- 썸네일 [s] -->
				<!-- <div class="slider_bx img_wrap_list" style="padding: 20px 0;">
					<ul class="img_slider_control">
						<li class="bxprev">
							<a href="#" class="bx-prev">이전</a>
						</li>
						<li class="bxnext">
							<a href="#" class="bx-next">다음</a>
						</li>
					</ul>
					<div style="max-width: 497px; margin: 0 auto;">
						<div style="width: 100%; overflow: hidden; position: relative; height: 80px;">
							<ul style="width: 1000%; position: relative;">
								<li style="float: left; list-style: none; position: relative; width: 113px; margin-right: 15px;">
									<img src="resources/assets/main/img/details-img/1.png" style="width: 104px; height: 80px;">
								</li>
								<li style="float: left; list-style: none; position: relative; width: 113px; margin-right: 15px;">
									<img src="resources/assets/main/img/details-img/1.png" style="width: 104px; height: 80px;">
								</li>
								<li style="float: left; list-style: none; position: relative; width: 113px; margin-right: 15px;">
									<img src="resources/assets/main/img/details-img/1.png" style="width: 104px; height: 80px;">
								</li>
								<li style="float: left; list-style: none; position: relative; width: 113px; margin-right: 15px;">
									<img src="resources/assets/main/img/details-img/1.png" style="width: 104px; height: 80px;">
								</li>
							</ul>
						</div>
					</div>
				</div> -->
				<!-- 썸네일 [e	] -->
			</div>
			<div class="top_info_box_right">
				<span class="top_info_icon">
					<c:if test="${result eq '1'}">
						<a class="top_info_icon_heart" style="cursor: pointer;" onclick="gh_like_up_btn()"><i id="heart" class="fas fa-heart fa-2x" style="color: #ff4a52"></i></a>
					</c:if>
					<c:if test="${result eq '0'}">
						<a class="top_info_icon_heart" style="cursor: pointer;" onclick="gh_like_up_btn()"><i id="heart" class="far fa-heart fa-2x"></i></a>
					</c:if>
				</span>
				<h2 class="top_info_title">${gh_info.PARTNER_NAME}</h2>
				
				<c:if test="${userData.STATUS eq 'P'}">
					<div class="m-b-10">
						<a class="modify_delete_button" href="gh_modify_page?NO=${gh_info.NO}">수정</a>
						<a class="modify_delete_button" onclick="gh_delete_btn()">삭제</a>
					</div>
				</c:if>
				
				<div class="top_info_price_box">
					<div class="top_info_price_top">
						<div class="price_top_title">
							<h6>도미토리</h6>
						</div>
					</div>
					<div class="top_info_price_mid">
						<c:forEach items="${gh_room_info}" var="gh_room_info" end="0">
							<div class="mid_price">${gh_room_info.PRICE}원</div>
						</c:forEach>
						<div class="mid-price_r">&nbsp;(￦ 최소 가격) ~ </div>
					</div>
				</div>
				<div class="top_info_rating_box">
					<span class="top_info_rating_review">
						<i class="fas fa-star"></i>
						<span class="top_info_rating">4.00</span>
						<a href="#review_move" class="top_info_review">${gh_info.CNT}명의 리뷰보기</a>
					</span>
					<span class="top_info_like">
						이 숙소를 좋아하는 <strong>${gh_info.LIKES}</strong>명
					</span>
				</div>
				<div class="top_info_additional_infomation_box">
					<h4 class="additional_title">위치</h4>
					<h6 class="additional_contents">${gh_info.ADDR}</h6>
					<h6></h6>
				</div>
				<div class="m-t-30">
					<h4 class="additional_title">공용시설</h4>
					<div class="public_list">
						<h6 class="additional_contents">
							<input type="hidden" id="public_facility" value="${gh_info.PUBLIC_FACILITY}">
							<input type="hidden" id="etc" value="${gh_info.ETC}">
							<input type="hidden" id="bathroom" value="${gh_info.BATHROOM}">
							${gh_info.PUBLIC_FACILITY}
						</h6>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="partner_info_box_wrap">
		<div class="partner_info_box p-t-20 p-b-20 m-b-50">
			<div class="partner_info_img">
				<img src="resources/assets/main/img/import/img/unnamed.png" alt="프로필 없믐">
			</div>
			<div class="partner_info_txt p-l-10">
				<div class="partner_info_title m-b-5">게스트하우스</div>
				<div class="partner_info_contents">안녕하세요? 게스트하우스 입니다.</div>
			</div>
		</div>
	</div>
	
	<div class="room_check">
		<h3 class="sub_title m-b-0">객실 예약</h3>
		<div class="room_reservation_wrap m-b-60">
			<div class="calendar_wrap m-r-8">
				<div class="calendar_box">
					<div class="calendar_input_box">
						<label class="calendar_input_label">체크인</label>
					</div>
					<label><i class="fas fa-arrow-right"></i></label>
					<div class="calendar_input_box">
						<label class="calendar_input_label">체크아웃</label>
					</div>
				</div>
			</div>
			<div class="select_wrap">
				<select class="select_box">
					<option>1명</option>
					<option>2명</option>
					<option>3명</option>
				</select>
			</div>
		</div>
		
		<div class="room_list_wrap">
			<div class="room_list_sub_info_box m-b-10">
			<c:forEach items="${gh_room_info}" var="gh_room_info" end="0">
				<p class="count_room">총 <b>${gh_room_info.CNT}</b>개의 객실이 있습니다.</p>
			</c:forEach>
			</div>
			<div class="room_list_box">
				<c:forEach items="${gh_room_info}" var="gh_room_info">
				<div class="room_list">
					<div class="list_contents-left">
						<div class="thumbnail_box">
							<img src="resources/assets/main/room_img/${gh_room_info.ROOMS_IMG}">
						</div>
					</div>
					<div class="list_contents-right m-l-20">
						<div class="list_txt_top m-b-10">
							<div class="title_box">
								<h2 class="title m-t-10 m-b-15">${gh_room_info.ROOMS}</h2>
							</div>
							<div class="list_txt_bottom">
								<div class="list_txt_bottom-left">
									<div class="room_info_txt_box">
										<span>${gh_room_info.ROOM_GENDER} 도미토리</span>
										<span class="line"></span>
										<span>객실정원 1~${gh_room_info.PERSONNEL}명</span>									
									</div>
									<div class="room_info_txt_box">
										<span>최소예약 1박이상</span>
									</div>
								</div>
								<div class="list_txt_bottom-right">
									<div class="price_wrap">
										<div class="price_basis_box">1인 1박</div>
										<div class="price__box">
											<div class="current_price_box">
												<div class="current_price">${gh_room_info.PRICE}</div>
												원(￦ ${gh_room_info.PRICE})
											</div>
										</div>
										<div class="meta_price_box m-t-20"></div>
									</div>
									<div class="button_wrap">
										<div class="button_box">
											<button class="reservation_button">예약문의</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="room_introduce">
		<h3 class="sub_title">숙소 소개</h3>
		<div class="txt_wrap p-t-30">
			<h6 class="txt_box">
				${gh_info.INTRODUCE} <br>
				※ 거실에서 바다가 보이는 복층 게스트하우스! <br>
				※  멍때리며 하늘과 바다를 하염없이 바라볼 수 있는 곳!!<br><br>
				반갑습니다.<br>
				와락 게스트하우스 주인 존입니다.<br>
				와락 게스트하우스는 제주의 동쪽인 구좌읍과 세화리 경계에 자리잡고 있습니다. <br>
				바로 앞에는 세화항이 자리잡고 있어 거실에서 아름다운 제주 바다를 바라볼 수 있습니다.<br><br>
				1층에 6개, 2층에 3개의 객실이 자리 잡고 있으며, 4인 도미토리부터 2인실, 커플실, 가족실 등 다양한 방들로 구성되어 있습니다. <br>
				바다가 한눈에 보이는 거실과 복층구조로 이어진 다락이 있는 조금은 특이한 구조를 가지고 있는 게스트하우스이지요.<br><br>
				가끔 시끌시끌할 때도 있습니다만 전반적으로 조용하고 차분한 분위기입니다. <br>
				바베큐도 안하고, 맥주 파티도 없는 곳이며, 23시가 되면 소등하는 조금은 엄격한 규칙을 가지고 있는 게스트하우스입니다. <br>
				하지만 푹 잘 수 있고, 멍 때리고, 하늘과 바다를 하염없이 보고, 다락에서 맘껏 책 읽고, 그렇게 집처럼 편하게 쉴 수 있는 그런 공간입니다. <br><br>
				세화 오일장, 해녀박물관, 세화 해수욕장, 벨롱장(벼룩시장) 등이 도보로 이동 가능한 위치에 있으며, 비자림, 월정리, 다랑쉬오름, 용눈이오름, 레일바이크, 메이즈랜드, 만장굴 등이 차량 20분 이내 거리에 있어서 제주 여행을 하는데 무척 편리할 겁니다.
			</h6>
		</div>
	</div>
	<div class="gh_details_introduce">
		<div class="sub_title_wrap m-t-60">
			<h3 class="sub_title m-b-0">숙소 상세 정보</h3>
		</div>
		<div class="introduce1">
			<h4 class="m-b-0 p-t-0i">부가시설 및 서비스</h4>
			<div class="room_service">
				<ul class="room_service_list">
					<li>
						<h5>공용시설</h5>
						<ul class="chk_list">
						<c:set var="public_facility" value="Wifi,헤어드라이기,전자렌지,커피포트,주차가능,게스트 부엌,토스트기,TV,엘리베이터,다리미,휴게실,공용PC,프린터 사용,정원,바베큐시설,수영장,레스토랑,카페,헬스시설,팩스 사용,스파시설"></c:set>
						<c:set var="public_change" value="${fn:split(public_facility, ',')}"></c:set>
						<c:set var="public_list" value="${fn:split(gh_info.PUBLIC_FACILITY, ',')}"></c:set>
						
						<c:forEach items="${public_list}" var="public_list" begin="0" end="13" varStatus="status">
							<c:if test="${public_list eq 'Y'}">
								<li><i class="fas fa-check"></i>&nbsp;${public_change[status.index]}</li>
							</c:if>
							<c:if test="${public_list eq 'N'}">
								<li class="disabled"><i class="fas fa-check"></i>&nbsp;${public_change[status.index]}</li>
							</c:if>
						</c:forEach>
						</ul>
					</li>
					<li>
						<ul class="chk_list m-t-45">
							<c:set var="public_facility" value="Wifi,헤어드라이기,전자렌지,커피포트,주차가능,게스트 부엌,토스트기,TV,엘리베이터,다리미,휴게실,공용PC,프린터 사용,정원,바베큐시설,수영장,레스토랑,카페,헬스시설,팩스 사용,스파시설"></c:set>
							<c:set var="public_change" value="${fn:split(public_facility, ',')}"></c:set>
							<c:set var="public_list" value="${fn:split(gh_info.PUBLIC_FACILITY, ',')}"></c:set>
							
							<c:forEach items="${public_list}" var="public_list" begin="14" varStatus="status">
								<c:if test="${public_list eq 'Y'}">
									<li><i class="fas fa-check"></i>&nbsp;${public_change[status.index]}</li>
								</c:if>
								<c:if test="${public_list eq 'N'}">
									<li class="disabled"><i class="fas fa-check"></i>&nbsp;${public_change[status.index]}</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
					<li>
						<h5>욕실</h5>
						<ul class="chk_list">
							<c:set var="batroom" value="화장실,샤워실,수건,비누,치약,샴푸,린스,바디샴푸,1회용 칫솔"></c:set>
							<c:set var="batroom_change" value="${fn:split(batroom, ',')}"></c:set>
							<c:set var="batroom_list" value="${fn:split(gh_info.BATHROOM, ',')}"></c:set>
							
							<c:forEach items="${batroom_list}" var="batroom_list" varStatus="status">
								<c:if test="${batroom_list eq 'Y'}">
									<li><i class="fas fa-check"></i>&nbsp;${batroom_change[status.index]}</li>
								</c:if>
								<c:if test="${batroom_list eq 'N'}">
									<li class="disabled"><i class="fas fa-check"></i>&nbsp;${batroom_change[status.index]}</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
					<li>
						<h5>기타서비스</h5>
						<ul class="chk_list">
							<c:set var="etc" value="공항픽업,짐 보관 서비스,자전거 대여,24시간 출입자유,여행투어 제공,신용카드 결제가능,세탁,국제전화 무료(한국),키보증금,흡연가능,외국인 숙박불가,유아 숙박불가,어린이 숙박불가,애완동물 동반가능"></c:set>
							<c:set var="etc_change" value="${fn:split(etc, ',')}"></c:set>
							<c:set var="etc_list" value="${fn:split(gh_info.ETC, ',')}"></c:set>
							
							<c:forEach items="${etc_list}" var="etc_list" varStatus="status">
								<c:if test="${etc_list eq 'Y'}">
									<li><i class="fas fa-check"></i>&nbsp;${etc_change[status.index]}</li>
								</c:if>
								<c:if test="${etc_list eq 'N'}">
									<li class="disabled"><i class="fas fa-check"></i>&nbsp;${etc_change[status.index]}</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="introduce2">
			<h4 class="m-b-0">식사 및 간식</h4>
			<h4 class="m-b-0" style="margin-left: -4px;">숙소 이용규칙</h4>
			<div class="border_box">
				<div class="meal_info">
					<dl>
						<dt>조식</dt>
						<dd>아침</dd>
					</dl>
					<dl>
						<dt>중식</dt>
						<dd>점심</dd>
					</dl>
					<dl>
						<dt>석식</dt>
						<dd>저녁</dd>
					</dl>
				</div>
				<div class="house_rules">
					<dl>
						<dt>체크인</dt>
						<dd>${gh_info.CHECK_IN}</dd>
					</dl>
					<dl>
						<dt>체크아웃</dt>
						<dd>${gh_info.CHECK_OUT}</dd>
					</dl>
					<dl>
						<dt>소등시간</dt>
						<dd>${gh_info.LIGHTS_OUT_TIME}</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<div class="room_review_wrap p-b-60" id="review_move">
		<div class="sub_title_wrap m-t-60">
			<h3 class="sub_title m-b-0">리뷰</h3>
		</div>
		<div class="room_review">
			<div class="sub_contents">
				<div class="sub_contents_txt">리뷰의 신뢰도를 위해 실제로 숙박하신 분들만 작성 가능합니다.</div>
			</div>
			
			<c:choose>
				<c:when test="${empty gh_reiview_info}">
					<div class="no_review p-t-180">
						<div class="no_review_txt_box p-b-70">
							이 숙소를 예약하시고 첫 리뷰 작성 해주세요.
						</div>
					</div>
				</c:when>
				<c:otherwise>
				<div class="column_left">
					<c:forEach items="${gh_reiview_info}" var="gh_reiview_info">
					<div class="review_list">
						<div class="review_ditails">
							<div class="user_review_wrap">
								<div class="meta">
									<div class="user">${gh_reiview_info.NICKNAME}</div>
									<div class="date">${gh_reiview_info.REVIEW_WRITE_DATE}</div>
								</div>
								<p class="txt">${gh_reiview_info.CONTENTS}</p>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
				</c:otherwise>
			</c:choose>
			
			<div class="comment_box">
				<div class="left-area">	
					<h3 id="test">리뷰를 <br>작성해주세요!</h3>
				</div>
				<div class="right-area">
					<div class="cmt_edit">
						<textarea class="form-control" placeholder="리뷰를 입력해주세요."></textarea>
						<button type="submit" onclick="">리뷰작성</button>
					</div>
				</div>
			</div>

			<!-- 페이징 [s] -->
			<div class="text-center column_left m-t-30 m-b-60" id="test">
				<c:if test="${paging.NOW_PAGE > 1}">
					<a href="gh_details_page?NO=${gh_info.NO}&page_num=${paging.NOW_PAGE-1}" class="m-r-20">이전</a>
				</c:if>
				<div class="paging_box">
					<c:forEach begin="1" end="${paging.TOTAL_PAGE}" varStatus="status">
						<c:if test="${paging.NOW_PAGE eq status.count}">
							<a href="gh_details_page?NO=${gh_info.NO}&page_num=${status.count}" class="sp_active">${status.count}</a>
						</c:if>
						<c:if test="${paging.NOW_PAGE ne status.count}">
							<a href="gh_details_page?NO=${gh_info.NO}&page_num=${status.count}">${status.count}</a>
						</c:if>
				    </c:forEach>
				</div>
				<c:if test="${paging.NOW_PAGE < paging.TOTAL_PAGE}">
					<a href="gh_details_page?NO=${gh_info.NO}&page_num=${paging.NOW_PAGE+1}" class="m-l-20">다음</a>
				</c:if>
			</div>
			<!-- 페이징 [e] -->
		</div>
	</div>
</div>