var page = 1;
var xhr;
var ongoingImageRequests = [];
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


	// 식당 커드
	// getRestaurantInfo(lo, la, page);
	getRestaurantInfo(lo, la, page)
		.then(function(result) {
			// 결과 처리
			changeImg(result);
			categoryNameValues();
		})
		.catch(function(error) {
			// 오류 처리
		});

	
	// 카테고리 버튼 클릭 이벤트 핸들러
	$("#btnKorean, #btnChinese, #btnWestern, #btnJapanese").click(function() {
		var category = $(this).text(); // 클릭한 버튼의 텍스트(카테고리 이름) 가져오기

		// 모든 식당 카드 숨기기
		$(".restaurant-card").hide();

		// 해당 카테고리에 해당하는 식당 카드만 보여주기
		$(".restaurant-card").each(function() {
			var cardCategory = $(this).find('input[type=hidden]').last().val(); // 식당
																				// 카드의
																				// 카테고리
																				// 이름
																				// 가져오기
			if (cardCategory.includes(category)) {
				$(this).show();
			}
		});
	});

	// "전체" 버튼 클릭 이벤트 핸들러
	$("#btnAll").click(function() {
		// 모든 식당 카드 보여주기
		$(".restaurant-card").show();
	});

	// 기타 버튼 클릭 이벤트 핸들러
	$("#btnOther").click(function() {
		// 모든 식당 카드 숨기기
		$(".restaurant-card").hide();

		// 기타 카테고리에 해당하지 않는 식당 카드만 보여주기
		$(".restaurant-card").each(function() {
			var cardCategory = $(this).find('input[type=hidden]').last().val(); // 식당
																				// 카드의
																				// 카테고리
																				// 이름
																				// 가져오기
			if (!cardCategory.includes("한식") && !cardCategory.includes("중식") && !cardCategory.includes("양식") && !cardCategory.includes("일식")) {
				$(this).show();
			}
		});
	});

// 다음 식당 정보
	$("#next").click(function() {
		// Cancel ongoing image requests
		for (var i = 0; i < ongoingImageRequests.length; i++) {
			ongoingImageRequests[i].abort();
		}
		
		ongoingImageRequests = [];
		
		$("#restaurantListBox").empty();
		page += 1;
		
		getRestaurantInfo(lo, la, page)
		.then(function(result) {
			changeImg(result);
			categoryNameValues();
		})
		.catch(function(error) {
			// Handle error
		});
	});
	
// 이전 식당 정보
		$("#before").click(function() {
		  // Cancel ongoing image requests
		  for (var i = 0; i < ongoingImageRequests.length; i++) {
		    ongoingImageRequests[i].abort();
		  }

		  ongoingImageRequests = [];

		  if (page == 1) {
		    return;
		  } else {
		    $("#restaurantListBox").empty();
		    page -= 1;

		    getRestaurantInfo(lo, la, page)
		      .then(function(result) {
		        changeImg(result);
		        categoryNameValues();
		      })
		      .catch(function(error) {
		        // Handle error
		      });
		  }
		});

	$(document).on('click', '.restaurant-card', function() {
		  for (var i = 0; i < ongoingImageRequests.length; i++) {
			    ongoingImageRequests[i].abort();
			  }
		var mggName = $("#mggName").text();
		var cardlo = $(this).attr("lo");
		var cardla = $(this).attr("la");
		var imgUrl = $(this).find('.restaurant-image').attr('src');
		var placeUrl = $(this).find('.restaurant-url').val();
		var placeName = $(this).find('.restaurant-name').text();
		var roadAddress = $(this).find('.restaurant-address').text();
		var url = "restaurant-detail?lo=" + cardlo + "&la=" + cardla + "&imgUrl=" + imgUrl + "&placeUrl=" + placeUrl + "&placeName=" + placeName + "&roadAddress=" + roadAddress + "&mggname=" + mggName;
		location.href = url;
	});

});

// 식당 카드 만들기 ajax로 호출
function getRestaurantInfo(lo, la, page) {
	return new Promise(function(resolve, reject) {
		$.getJSON("restaurantInfo?la=" + la + "&lo=" + lo + "&page=" + page, function(data) {
			var id = [];
			var url = [];
			$.each(data, function(i) {
				var card = $("<div></div>").attr("id", data[i].id).attr("class", "restaurant-card").attr("lo", data[i].x).attr("la", data[i].y).append(
					$("<img>").attr("src", "/loading.png").addClass("restaurant-image"),
					$("<p></p>").text(data[i].place_name).addClass("restaurant-name"),
					$("<p></p>").text(data[i].road_address_name).addClass("restaurant-address"),
					$("<p></p>").text(data[i].phone),
					$("<input>").attr("type", "hidden").val(data[i].place_url).addClass("restaurant-url"),
					$("<input>").attr("type", "hidden").val(data[i].category_name).addClass("category_name")
				);
				$("#restaurantListBox").append(card);
				id[i] = data[i].id;
				url[i] = data[i].place_url;
			});
			resolve({
				"id": id,
				"url": url
			});
		}).fail(function(error) {
			reject(error);
		});
	});
}

// 이미지 바꾸기
function changeImg(result) {
	  // Cancel ongoing image requests
	  for (var i = 0; i < ongoingImageRequests.length; i++) {
	    ongoingImageRequests[i].abort();
	  }

	  ongoingImageRequests = [];

	  // Create new image requests
	  for (var y = 0; y < result.url.length; y++) {
	    var request = $.getJSON("restaurantCardImg?url=" + result.url[y] + "&id=" + result.id[y], function(data) {
	      if (data.url == "error") {
	        y = y - 1;
	      } else {
	        $("#" + data.id + " img").attr("src", data.url);
	      }
	    });

	    ongoingImageRequests.push(request);
	  }
	}

function categoryNameValues() {
	var categoryValues = $('.category_name').map(function() {
		return $(this).val().split(' > ')[1];
	}).get();
	toggleCategoryButtonVisibility(categoryValues);
}

function toggleCategoryButtonVisibility(categoryValues) {
	toggleButtonVisibility("#btnKorean", categoryValues.includes('한식'));
	toggleButtonVisibility("#btnChinese", categoryValues.includes('중식'));
	toggleButtonVisibility("#btnWestern", categoryValues.includes('양식'));
	toggleButtonVisibility("#btnJapanese", categoryValues.includes('일식'));
	var hasOtherCategory = categoryValues.some(function(category) {
		return !['한식', '중식', '양식', '일식'].includes(category);
	});
	toggleButtonVisibility("#btnOther", hasOtherCategory);
}

function toggleButtonVisibility(buttonId, condition) {
	$(buttonId).toggle(condition);
}
