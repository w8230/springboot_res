<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="resources/data-components/service_center/common.js"></script>
<script src="resources/data-components/common/common.js"></script>
<script src="resources/ckeditor/ckeditor.js"></script>

<div id="container_wrap">
	<div id="contents" class="container">
		<div class="">
			<div class="tit-wrap m-b-55">
				<h2 class="tit">공지사항</h2>
				<h3 class="tit-split">공지사항 수정</h3>
			</div>
    		
			<form action="notice_modify" method="post">
				<input type="hidden" name="USER_ID" id="user_id" value="${userData.USER_ID}">
				<input type="hidden" name="NICKNAME" id="nickname" value="${userData.NICKNAME}">
				<input type="hidden" name="NO" value="${notice_details.NO}">
				<table class="table-list">
					<tbody class="table-body">
						<tr class="table-row">
							<td class="table-col">
								<span class="input-wrap">
									<input type="text" name="TITLE" id="title" placeholder="제목을 입력해주세요." value="${notice_details.TITLE}">
								</span>
							</td>
						</tr>
						<tr class="table-row">
							<td class="table-col">
								<textarea class="input-wrap" name="CONTENTS" id="content">${notice_details.CONTENTS}</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				
    			<div class="text-center m-t-40">
					<button class="btn bg_blu btn-lg nsdow" onclick="return form_validation();">등록</button>
					<button class="btn bg_dark_grey btn-lg m-l-5 nsdow" type="button" onclick="cancel();">취소</button>
				</div>
	    	</form>
    	</div>
	</div>
</div>