package org.kosta.semi.controller;
//singleton pattern
//개별 컨트롤러 객체 생성을 전담하는 클래스(Factory 클래스)
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return instance;
	}
	public Controller create(String command) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Controller controller = null;
		//현재 패키지명을 구한다
		String packageName = this.getClass().getPackage().getName();
		//System.out.println("현재패키지명:"+packageName);
		String classInfo = new StringBuilder(packageName).append(".").append(command).toString();
		controller = (Controller)Class.forName(classInfo).newInstance();
		return controller;
	}
	/*
	public static void main(String[] args) {
		try {
			Controller c = HandlerMapping.getInstance().create("MockController");
			System.out.println(c.execute(null,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

}
