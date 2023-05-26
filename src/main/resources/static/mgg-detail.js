$(function() {

	//  먹자골록 위치 지도 카카오맵
	const urlParams = new URL(location.href).searchParams;

	const la = urlParams.get('la');
	const lo = urlParams.get('lo');


	var container = document.getElementById('mggMap');
	var options = {
		center: new kakao.maps.LatLng(la, lo),
		level: 3
	};

	var map = new kakao.maps.Map(container, options);

	// 마커가 표시될 위치
	var markerPosition = new kakao.maps.LatLng(la, lo);

	// 마커를 생성
	var marker = new kakao.maps.Marker({
		position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정
	marker.setMap(map);

	// 지도에 표시할 원을 생성합니다
	var circle = new kakao.maps.Circle({
		center: new kakao.maps.LatLng(la, lo), // 원의 중심좌표입니다
		radius: 200, // 미터 단위의 원의 반지름입니다
		strokeWeight: 5, // 선의 두께입니다
		strokeColor: '#75B8FA', // 선의 색깔입니다
		strokeOpacity: 1, // 선의 불투명도입니다
		strokeStyle: 'dashed', // 선의 스타일입니다
		fillColor: '#CFE7FF', // 채우기 색깔입니다
		fillOpacity: 0.4 // 채우기 불투명도입니다
	});

	// 지도에 원을 표시합니다
	circle.setMap(map);

	//	alert($(".url").text());
});

/*function filterRestaurantsByCategory(category) {
	var restaurantCards = document.getElementsByClassName("restaurant-card");

	for (var i = 0; i < restaurantCards.length; i++) {
		var cardCategory = restaurantCards[i].querySelector("p:nth-child(4)").textContent;
		if (category === "전체" || (category === "기타" && !isIncludedInCategories(cardCategory))) {
			restaurantCards[i].style.display = "block";
		} else if (cardCategory.includes(category)) {
			restaurantCards[i].style.display = "block";
		} else {
			restaurantCards[i].style.display = "none";
		}
	}
}

function isIncludedInCategories(category) {
	var categories = ["한식", "중식", "양식", "일식"];
	for (var i = 0; i < categories.length; i++) {
		if (category.includes(categories[i])) {
			return true;
		}
	}
	return false;
}*/
function filterRestaurantsByCategory(category) {
	var restaurantCards = document.getElementsByClassName("restaurant-card");
	
	for (var i = 0; i < restaurantCards.length; i++) {
		var cardCategory = restaurantCards[i].querySelector("p:nth-child(4)").textContent;
		if (category === "전체" || (category === "기타" && !isIncludedInCategories(cardCategory))) {
			restaurantCards[i].style.display = "block";
		} else if (cardCategory.includes(category)) {
			restaurantCards[i].style.display = "block";
			
		} else {
			restaurantCards[i].style.display = "none";
		}
	}
}

function isIncludedInCategories(category) {
	var categories = ["한식", "중식", "양식", "일식"];
	for (var i = 0; i < categories.length; i++) {
		if (category.includes(categories[i])) {
			return true;
		}
	}
	return false;
}


