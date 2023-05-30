var page =1;
$(function() {
	// 먹자골록 위치 지도 카카오맵
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
		position : markerPosition
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
	
	// 식당 커드
	getRestaurantInfo(lo, la, page);


	//페이지가 로드될 때 카드의 요소를 확인해서 해당하는 카테고리가 있는지 확인하여 해당하는 카테고리가 있다면 버튼을 보이게 해준다.
	var restaurantCards = document.getElementsByClassName("restaurant-card");
	var categoryButtons = document.getElementById("categoryButtons").getElementsByTagName("button");

	for (var i = 0; i < categoryButtons.length; i++) {
		categoryButtons[i].style.display = "none";
	}

	for (var i = 0; i < restaurantCards.length; i++) {
		var cardCategory = restaurantCards[i].querySelector("input:nth-child(3)").textContent;

		for (var j = 0; j < categoryButtons.length; j++) {
			var category = categoryButtons[j].textContent;
			if (category === "전체" || category === "기타" || cardCategory.includes(category)) {
				categoryButtons[j].style.display = "block";
			}
		}
	}
	
	
	
	$("#next").click(function(){
		$("#restaurantListBox").empty();
		page+=1;
		getRestaurantInfo(lo, la, page);
	});
	$("#before").click(function(){
		if(page == 1){
			return
		}
		else{
			$("#restaurantListBox").empty();
			page-=1
			getRestaurantInfo(lo, la, page);
		}
	});
	
	
	
	
	
	
	
});

// 식당 카드 만들기 ajax로 호출
function getRestaurantInfo(lo, la, page){
	$.getJSON("restaurantInfo?la=" + la+"&lo="+lo+"&page="+page, function(data) {
		var id = new Array(); 
		var url = new Array(); 
		$.each(data,function(i){
			var card=$("<div></div>").attr("id",data[i].id).attr("class","restaurant-card").append(
				$("<img>").attr("src","/loading.png"),
				$("<p></p>").text(data[i].place_name),
				$("<p></p>").text(data[i].road_address_name),
				$("<p></p>").text(data[i].phone),
				$("<input>").text(data[i].x).attr("type","hidden"),
				$("<input>").text(data[i].y).attr("type","hidden"),
				$("<input>").text(data[i].place_url).attr("type","hidden"),
				$("<input>").text(data[i].category_name).attr("type","hidden")
			);
			$("#restaurantListBox").append(card);
			id[i] = data[i].id;
			url[i] = data[i].place_url;
		});
		changeImg(id, url);
	
	});
}
// 다음 식당 정보 카드
function next() {
	page+=1;
}

function filterRestaurantsByCategory(category) {
	var restaurantCards = document.getElementsByClassName("restaurant-card");


	for (var i = 0; i < restaurantCards.length; i++) {
		var cardCategory = restaurantCards[i].querySelector("input:nth-child(3)").textContent;
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

function changeImg(id, url){
	for (var i = 0; i < id.length; i++) {
		console.log(id[i]);
	}
	for (var i = 0; i < url.length; i++) {
		console.log(url[i]);
	}
}

// 이미지 요청 
//var urlList = $(".url").map(function() {
//return $(this).attr("id");
//}).get();
//// alert(urlList.length);
//for (var j = 0; j < urlList.length; j++) {
//$.getJSON("restaurantCard?url=" + urlList[j], function(data) {
//  console.log(data.url);
//  if (data.url == "error") {
//      j = j - 1;
//  } else {
//      var p = $("<img id=card_img>").attr("src", data.url);
//      $("#imgUrl").append(p);
//  }
//
//});
//}
