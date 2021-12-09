<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/data-components/guest_house/common.js"></script>
<script src="resources/data-components/common/common.js"></script>

<div id="container_wrap">
	<div class="container">
		<div class="">
			<div class="tit-wrap">
				<h2 class="tit">게스트하우스</h2>
				<h3 class="tit-split">정보 입력</h3>
			</div>
    		
			<form action="gh_modify" method="post" enctype="multipart/form-data">
				<input type="hidden" name="USER_ID" id="user_id" value="${userData.USER_ID}">
				 
				<div class="tit-wrap">
					<h4 class="sub-tit">기본 정보</h4>
					<div class="tit-right">
						<p class="p-txt"><b class="color_red">*</b>필수입력표시는 필수 입력 정보입니다. 반드시 입력해주세요.</p>
					</div>
				</div>
    			
				<table class="table-list">
					<tbody class="table-body">
						<tr class="table-row">
							<th class="table-col table-col-tit">숙소명&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="PARTNER_NAME" id="partner_name" value="${gh_info.PARTNER_NAME}">
								</span>
								<p class="text_alert" id="warning_partner_name"></p>
							</td>
							
							<th class="table-col table-col-tit">우편번호&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-group input-group__id">
									<span class="input-wrap">
										<input type="text" name="POST_CODE" id="post_code" value="${gh_info.POST_CODE}" readonly>
									</span>
									<button type="button" class="info-manage__btn-id" id="test" onclick="search_Postcode()">주소 찾기</button>
								</span>
								<p class="text_alert" id="warning_post_code"></p>
							</td>
						</tr>
						
						<tr class="table-row">
							<th class="table-col table-col-tit">주소&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="ADDR" id="addr" value="${gh_info.ADDR}" readonly>
								</span>
								<p class="text_alert" id="warning_addr"></p>
							</td>
							
							<th class="table-col table-col-tit">상세주소&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="DETAIL_ADDR" id="detail_addr" value="${gh_info.DETAIL_ADDR}">
								</span>
								<p class="text_alert" id="warning_detail_addr"></p>
							</td>
						</tr>
						
						<tr class="table-row">
							<th class="table-col table-col-tit">숙소 소개&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col" colspan="3">
								<textarea class="input-wrap" rows="10" name="INTRODUCE" id="introduce" placeholder="숙소를 소개해주세요."
									style="font-size:12px; padding: 5px;">${gh_info.INTRODUCE}</textarea>
								<p class="text_alert" id="warning_introduce"></p>
							</td>
						</tr>
						
						<tr class="table-row">
							<th class="table-col table-col-tit">체크인&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="CHECK_IN" id="check_in" value="${gh_info.CHECK_IN}">
								</span>
								<p class="text_alert" id="warning_check_in"></p>
							</td>
							
							<th class="table-col table-col-tit">체크아웃&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="CHECK_OUT" id="check_out" value="${gh_info.CHECK_OUT}">
								</span>
								<p class="text_alert" id="warning_check_out"></p>
							</td>
						</tr>
						
						<tr class="table-row">
							<th class="table-col table-col-tit">소등시간&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="LIGHTS_OUT_TIME" id="lights_out_time" value="${gh_info.LIGHTS_OUT_TIME}">
								</span>
								<p class="text_alert" id="warning_lights_out_time"></p>
							</td>
							
							<th class="table-col table-col-tit">썸네일 및 외부사진&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-group input-group__id">
									<span class="input-wrap">
										<input type="file" name="THUMBNAIL" id="thumbnail" style="display: none">
										<input type="text" id="thumbnail_name" value="${gh_info.THUMBNAIL}" readonly>
									</span>
									<label class="info-manage__btn-id" for="thumbnail" style="margin-bottom: 0">파일 선택</label>
								</span>
							</td>
						</tr>
					</tbody>
				</table>
				
				<div class="tit-wrap">
					<h4 class="sub-tit">객실 정보</h4>
					<a class="add_del" onclick="del_Room_info()">삭제</a>
					<a class="add_del" onclick="add_Room_info()">추가</a>
				</div>
    			
				<table class="table-list">
					<tbody class="table-body" id="room_info">
						<c:forEach items="${gh_room_info}" var="gh_room_info" varStatus="status">
						<tr class="table-row" id="room_info1">
							<th class="table-col table-col-tit">객실&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="ROOMS" id="rooms${status.count}" onkeydown="keyevent(this.id);" value="${gh_room_info.ROOMS}">
								</span>
								<p class="text_alert" id="warning_rooms${status.count}"></p>
							</td>
							<th class="table-col table-col-tit">1박 단가&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="PRICE" id="price${status.count}" onkeydown="keyevent(this.id);" value="${gh_room_info.PRICE}">
								</span>
								<p class="text_alert" id="warning_price${status.count}"></p>
							</td>
						</tr>
						
						<tr class="table-row" id="room_info2">
							<th class="table-col table-col-tit">남&nbsp;&nbsp;/&nbsp;&nbsp;여&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="ROOM_GENDER" id="room_gender${status.count}" onkeydown="keyevent(this.id);" value="${gh_room_info.ROOM_GENDER}">
								</span>
								<p class="text_alert" id="warning_room_gender${status.count}"></p>
							</td>
							<th class="table-col table-col-tit">정원&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="PERSONNEL" id="personnel${status.count}" onkeydown="keyevent(this.id);" value="${gh_room_info.PERSONNEL}">
								</span>
								<p class="text_alert" id="warning_personnel${status.count}"></p>
							</td>
						</tr>
						
						<tr class="table-row" id="room_info3">
							<th class="table-col table-col-tit">객실 사진&nbsp;&nbsp;<b class="color_red">*</b></th>
							<td class="table-col">
								<span class="input-group input-group__id">
									<span class="input-wrap">
										<input type="file" name="ROOMS_IMG" id="room_img${status.count}" onclick="btn_click(this)" style="display: none">
										<input type="text" id="room_img_name${status.count}" value="${gh_room_info.ROOMS_IMG}" readonly>
									</span>
									<label class="info-manage__btn-id" for="room_img${status.count}" style="margin-bottom: 0">파일 선택</label>
								</span>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="tit-wrap">
					<h4 class="sub-tit">부가시설 및 서비스</h4>
				</div>
				<div class="table-list introduce1">
					<div class="room_service">
						<ul class="room_service_list">
							<li>
								<h5>공용시설</h5>
								<ul class="chk_list">
									<c:set var="public_list" value="${fn:split(gh_info.PUBLIC_FACILITY, ',')}"></c:set>
									<c:set var="public_facility" value="Wifi,헤어드라이기,전자렌지,커피포트,주차가능,게스트 부엌,토스트기,TV,엘리베이터,다리미,휴게실,공용PC,프린터 사용,정원,바베큐시설,수영장,레스토랑,카페,헬스시설,팩스 사용,스파시설"></c:set>
									<c:set var="public_change" value="${fn:split(public_facility, ',')}"></c:set>
									
									<c:forEach items="${public_list}" var="public_list" begin="0" end="13" varStatus="status">
										<c:if test="${public_list eq 'Y'}">
										<li>
											<label id="test" for="chk_box${status.count}">
												<input type="checkbox" id="chk_box${status.count}" checked>
												<input type="hidden" name="PUBLIC_FACILITY" id="chk${status.count}" value="${public_list}">
												${public_change[status.index]}
											</label>
										</li>
										</c:if>
										<c:if test="${public_list eq 'N'}">
										<li>
											<label id="test" for="chk_box${status.count}">
												<input type="checkbox" id="chk_box${status.count}">
												<input type="hidden" name="PUBLIC_FACILITY" id="chk${status.count}" value="${public_list}">
												${public_change[status.index]}
											</label>
										</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
							<li>
								<ul class="chk_list m-t-45">
									<c:set var="public_list" value="${fn:split(gh_info.PUBLIC_FACILITY, ',')}"></c:set>
									<c:set var="public_facility" value="Wifi,헤어드라이기,전자렌지,커피포트,주차가능,게스트 부엌,토스트기,TV,엘리베이터,다리미,휴게실,공용PC,프린터 사용,정원,바베큐시설,수영장,레스토랑,카페,헬스시설,팩스 사용,스파시설"></c:set>
									<c:set var="public_change" value="${fn:split(public_facility, ',')}"></c:set>
									
									<c:forEach items="${public_list}" var="public_list" begin="14" varStatus="status">
										<c:if test="${public_list eq 'Y'}">
										<li>
											<label id="test" for="chk_box${status.count + 14}">
												<input type="checkbox" id="chk_box${status.count + 14}" checked>
												<input type="hidden" name="PUBLIC_FACILITY" id="chk${status.count + 14}" value="${public_list}">
												${public_change[status.index]}
											</label>
										</li>
										</c:if>
										<c:if test="${public_list eq 'N'}">
										<li>
											<label id="test" for="chk_box${status.count + 14}">
												<input type="checkbox" id="chk_box${status.count + 14}">
												<input type="hidden" name="PUBLIC_FACILITY" id="chk${status.count + 14}" value="${public_list}">
												${public_change[status.index]}
											</label>
										</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
							
							<li>
								<h5>욕실</h5>
								<ul class="chk_list">
									<c:set var="batroom_list" value="${fn:split(gh_info.BATHROOM, ',')}"></c:set>
									<c:set var="batroom" value="화장실,샤워실,수건,비누,치약,샴푸,린스,바디샴푸,1회용 칫솔"></c:set>
									<c:set var="batroom_change" value="${fn:split(batroom, ',')}"></c:set>
									
									<c:forEach items="${batroom_list}" var="batroom_list" varStatus="status">
										<c:if test="${batroom_list eq 'Y'}">
										<li>
											<label id="test" for="chk_box${status.count + 21}">
												<input type="checkbox" id="chk_box${status.count + 21}" checked>
												<input type="hidden" name="BATHROOM" id="chk${status.count + 21}" value="${batroom_list}">
												${batroom_change[status.index]}
											</label>
										</li>
										</c:if>
										<c:if test="${batroom_list eq 'N'}">
										<li>
											<label id="test" for="chk_box${status.count + 21}">
												<input type="checkbox" id="chk_box${status.count + 21}">
												<input type="hidden" name="BATHROOM" id="chk${status.count + 21}" value="${batroom_list}">
												${batroom_change[status.index]}
											</label>
										</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
							<li>
								<h5>기타서비스</h5>
								<ul class="chk_list">
									<c:set var="etc_list" value="${fn:split(gh_info.ETC, ',')}"></c:set>
									<c:set var="etc" value="공항픽업,짐 보관 서비스,자전거 대여,24시간 출입자유,여행투어 제공,신용카드 결제가능,세탁,국제전화 무료(한국),키보증금,흡연가능,외국인 숙박불가,유아 숙박불가,어린이 숙박불가,애완동물 동반가능"></c:set>
									<c:set var="etc_change" value="${fn:split(etc, ',')}"></c:set>
									
									<c:forEach items="${etc_list}" var="etc_list" varStatus="status">
										<c:if test="${etc_list eq 'Y'}">
										<li>
											<label id="test" for="chk_box${status.count + 30}">
												<input type="checkbox" id="chk_box${status.count + 30}" checked>
												<input type="hidden" name="ETC" id="chk${status.count + 30}" value="${etc_list}">
												${etc_change[status.index]}
											</label>
										</li>
										</c:if>
										<c:if test="${etc_list eq 'N'}">
										<li>
											<label id="test" for="chk_box${status.count + 30}">
												<input type="checkbox" id="chk_box${status.count + 30}">
												<input type="hidden" name="ETC" id="chk${status.count + 30}" value="${etc_list}">
												${etc_change[status.index]}
											</label>
										</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</ul>
					</div>
				</div>
    			
    			<div class="text-center m-t-40">
					<button class="btn bg_blu btn-lg nsdow" onclick="return form_validation();">등록</button>
					<button class="btn bg_dark_grey btn-lg m-l-5 nsdow" type="button" onclick="cancel();">취소</button>
				</div>
	    	</form>
    	</div>
	</div>
</div>