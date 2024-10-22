package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			System.out.println(" C : BoardFrontController_doProcess() 호출 ");
			System.out.println(" C : GET/POST방식 상관없이 모두 처리 ");
			
			
			System.out.println(" C : -------------- 1. 가상주소 계산 - 시작----------------------------");
			
			// 실행중인 가상주소를 가져오기
			String requestURI = request.getRequestURI();
			System.out.println(" C : requestURI : "+requestURI);
			// 실행중인 프로젝트명 가져오기
			String CTXPath= request.getContextPath();
			System.out.println(" C : CTXPath : "+CTXPath);
			// 최종 계산(프로젝트명을 제거한 주소) 주소
			String command = requestURI.substring(CTXPath.length());
			System.out.println(" C : command : "+command);
			
			System.out.println(" C : -------------- 1. 가상주소 계산 - 끝----------------------------");
			
			System.out.println(" C : -------------- 2. 가상주소 매핑 - 시작----------------------------");
			Action action = null;
			ActionForward forward = null;
			
			if(command.equals("/BoardWrite.bo")){
				System.out.println(" C : /BoardWrite.bo 매핑 ");
				System.out.println(" C : 패턴 1- 데이터처리(DB사용) X, 페이지이동 O");
				
				// ActionForward객체 생성
				forward = new ActionForward();
				forward.setPath("./board/insertBoard.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/BoardWriteAction.bo")){
				System.out.println(" C : /BoardWriteAction.bo 매핑 ");
				System.out.println(" C : 패턴 2-데이터처리O, 페이지이동O");
				
				// BoardWriteAction() 객체 생성
				action = new BoardWriteAction();
				// execute() 호출				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if(command.equals("/BoardList.bo")){
				System.out.println(" C : /BoardList.bo 매핑 ");
				System.out.println(" C : 패턴 3 - 데이터처리O, 페이지출력 O");
				
				// BoardListAction() 객체 생성
				action = new BoardListAction();
				try {
					// execute() 메서드 호출
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/BoardContents.bo")){
				System.out.println(" C : /BoardContents.bo 매핑 ");
				System.out.println(" C : 패턴 3 - 데이터처리(DB사용) O,페이지 출력O ");
				
				//BoardContentsAction() 객체 생성
				action = new BoardContentsAction();
				//execute() 메서드 호출
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/BoardUpdate.bo")){
				System.out.println(" C : /BoardUpdate.bo 매핑 ");
				System.out.println(" C : 패턴 3 - 데이터처리(DB처리)O, 페이지출력O");
				
				// BoardUpdateAction() 객체 생성
				action = new BoardUpdateAction();
				// execute() 메서드 호출
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			else if(command.equals("/BoardUpdatePro.bo")){
				System.out.println(" C : /BoardUpatePro.bo 매핑 ");
				System.out.println(" C : 패턴 2 - 데이터처리(DB사용)O, 페이지이동 O");
				
				// BoardUpdateProAction() 객체 생성
				action = new BoardUpdateProAction();
				// execute() 메서드 호출
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/BoardDelete.bo")){
				System.out.println(" C : /BoardDelete.bo 매핑 ");
				System.out.println(" C : 패턴 1 - 데이터처리X, 페이지이동O ");
				
				forward = new ActionForward();
				forward.setPath("./board/deleteBoard.jsp");
				forward.setRedirect(false);				
			}
			else if(command.equals("/BoardDeleteAction.bo")){
				System.out.println(" C : /BoardDeleteAction.bo 매핑 ");
				System.out.println(" C : 패턴2 - 데이터처리(DB사용)O, 페이지이동O ");
				
				// BoardDeleteAction()객체 생성
				action = new BoardDeleteAction();
				// execute() 메서드 호출
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			else if(command.equals("/BoardReWrite.bo")){
				System.out.println(" C : /BoardReWrite.bo 매핑 ");
				System.out.println(" C : 패턴 1 - 데이터처리X, 페이지이동O ");
				
				forward = new ActionForward();
				forward.setPath("./board/reInsertBoard.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/BoardReWriteAction.bo")){
				System.out.println(" C : /BoardReWriteAction.bo 매핑 ");
				System.out.println(" C : 패턴2 - 데이터처리O, 페이지 이동O");
				
				// BoardReWriteAction() 객체 생성
				action = new BoardReWriteAction();
				// execute() 호출
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/BoardFileWrite.bo")){
				System.out.println(" C : /BoardFileWrite.bo 매핑");
				System.out.println(" C : 패턴 1 - 데이터처리X, 페이지이동O");
				
				forward = new ActionForward();
				forward.setPath("./board/insertFileBoard.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/BoardFileWriteAction.bo")){
				System.out.println(" C : /BoardFileWriteAction.bo 매핑 ");
				System.out.println(" C : 패턴 2 - 데이터처리O,페이지이동O");
				
				// BoardFileWriteAction() 객체 생성
				action = new BoardFileWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			
			
			System.out.println(" C : -------------- 2. 가상주소 매핑 - 끝----------------------------");
			
			System.out.println(" C : -------------- 3. 가상주소 이동 - 시작----------------------------");
			if(forward != null){ // 페이지 이동정보가 있다
				if(forward.isRedirect()){ //true
					System.out.println(" C : 방식 : "+forward.isRedirect()+",주소 : "+forward.getPath());
					System.out.println(" C : 페이지 이동 완료! ");
					
					response.sendRedirect(forward.getPath());
				}else{// false
					System.out.println(" C : 방식 : "+forward.isRedirect()+",주소 : "+forward.getPath());
					System.out.println(" C : 페이지 이동 완료! ");
					
					RequestDispatcher dis 
					  =request.getRequestDispatcher(forward.getPath());
					dis.forward(request, response);
				}
			}
			System.out.println(" C : -------------- 3. 가상주소 이동 - 끝----------------------------");

			
	}// doProcess
	
	// alr shift s + v
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			System.out.println("\n\n  C : BoardFrontController_doGet() 호출 ");
			doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			System.out.println("\n\n  C : BoardFrontController_doPost() 호출 ");
			doProcess(request, response);
	}
}
