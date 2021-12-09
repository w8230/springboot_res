<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="resources/data-components/common/common.js"></script>

<div id="container" class="container">
	<div class="m-l-100 m-r-100">
		<div class="sub_hd">
			<h2>아이디 · 비밀번호 찾기</h2>
		</div>

		<form class="join_frm p-b-45" action="find_info" method="post">
			<p class="m-t-35 m-b-15">아이디 · 비밀번호 찾기</p>
			<table class="tb_frm ">
				<colgroup>
					<col style="width:155px"><col>
				</colgroup>
				
				<tbody>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="NAME" class="form-control-input wid-lg" placeholder="이름을 입력해주세요.">
						</td>
					</tr>
					
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="EMAIL" class="form-control-input wid-lg" placeholder="등록하신 이메일을 입력해주세요.">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="text-center m-t-25">
				<button class="btn bg_blu btn-lg nsdow">찾기</button>
				<button class="btn bg_dark_grey btn-lg m-l-5 nsdow" type="button" onclick="cancel();">취소</button>
			</div>
		</form>

		<!-- <form class="join_frm p-t-40">
			<p class="m-t-35 m-b-15">비밀번호 찾기</p>
			<table class="tb_frm">
				<colgroup>
					<col style="width:155px"><col>
				</colgroup>
				
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" class="form-control-input wid-lg" placeholder="이름을 입력해주세요.">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" class="form-control-input wid-mid" placeholder="등록하신 이메일을 입력해주세요.">
							<span>@</span>
							<input type="text" class="form-control-input wid-mid">
							<button class="info-manage__btn-id">인증요청</button>
						</td>
					</tr>
					<tr>
						<th>인증번호</th>
						<td>
							<input type="text" class="form-control-input wid-lg" placeholder="인증번호를 입력해주세요.">
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="text-center m-t-25">
				<button class="btn bg_blu btn-lg nsdow">비밀번호 찾기</button>
				<button class="btn bg_dark_grey btn-lg m-l-5 nsdow">취소</button>
			</div>
		</form> -->
	</div>
</div>