<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- /layput/header.jsp 하면 페이지 못찾음 -->
<%@ include file="../layout/header.jsp"%>

<!-- 글 상세보기 페이지 -->
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br>
	<br>
	<div>
		<div class="row justify-content-start">
			<div class="col-md-auto">글 번호 : <span id="id"><i>${board.id} </i></span></div>
			<div class="col-md-auto">작성자 : <span><i>${board.user.username} </i></span></div>
			<div class="col col-lg-4">작성날짜 : <span style="color: #666666; opacity: .5; font-size: 13px" class="">${board.createDate} </span></div>
		</div>
		
		

		<br>
		<div class="form-group">
			<h3>${board.title}</h3>
		</div>
		<hr>
		<div>
			<div>${board.content}</div>
		</div>
		<hr>

		<!-- 게시글 댓글  -->
		<div class="card">
			<form>
				<input type="hidden" id="userId" value="${principal.user.id}"> <input type="hidden" id="boardId" value="${board.id}">
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-rely-save" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>

		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
							<c:if test="${reply.user.id == principal.user.id }">
								<button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<script src="/js/board.js"></script>
	<%@ include file="../layout/footer.jsp"%>