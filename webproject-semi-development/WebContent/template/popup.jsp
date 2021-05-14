<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript">
	function getCookie(name) {
		var cookie = document.cookie;
		if (document.cookie != "") {
			var cookie_array = cookie.split("; ");
			for ( var index in cookie_array) {
				var cookie_name = cookie_array[index].split("=");
				if (cookie_name[0] == "popupYN") {
					return cookie_name[1];
				}
			}
		}
		return;
	}
	function openPopup(url) {
		var cookieCheck = getCookie("popupYN");
		if (cookieCheck != "N")
			window.open(url, '', 'width=615,height=640,left=50,top=50')
	}
</script>
 <body id="page-top" onload="javascript:openPopup('popup.html')">
