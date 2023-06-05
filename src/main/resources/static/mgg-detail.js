var page = 1;
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
//	getRestaurantInfo(lo, la, page);
	getRestaurantInfo(lo, la, page)
	  .then(function(result) {
	    // 결과 처리
		  changeImg(result);
	  })
	  .catch(function(error) {
	    // 오류 처리
	  });	

	
	// 다음 식당 정보
	$("#next").click(function() {
		$("#restaurantListBox").empty();
		page += 1;
		getRestaurantInfo(lo, la, page)
		  .then(function(result) {
		    // 결과 처리
			  changeImg(result);
		  })
		  .catch(function(error) {
		    // 오류 처리
		  });	
	});
	// 이전 식당 정보
	$("#before").click(function() {
		if (page == 1) {
			return
		}
		else {
			$("#restaurantListBox").empty();
			page -= 1
			getRestaurantInfo(lo, la, page)
			  .then(function(result) {
			    // 결과 처리
				  changeImg(result);
			  })
			  .catch(function(error) {
			    // 오류 처리
			  });	
		}
	});

	$(document).on('click', '.restaurant-card', function() {
		var mggName = $("#mggName").text();
		var cardlo = $(this).attr("lo");
		var cardla = $(this).attr("la");
		var imgUrl = $(this).find('.restaurant-image').attr('src');
		var placeUrl = $(this).find('.restaurant-url').val();
		var placeName = $(this).find('.restaurant-name').text();
		var roadAddress = $(this).find('.restaurant-address').text();
		var url = "restaurant-detail?lo=" + cardlo + "&la=" + cardla + "&imgUrl=" + imgUrl + "&placeUrl=" + placeUrl + "&placeName=" + placeName + "&roadAddress=" + roadAddress+"&mggname="+mggName;
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
	          $("<input>").attr("type", "hidden").val(data[i].category_name).addClass("category")
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
	
	for (var y = 0; y < result.url.length; y++) {
		$.getJSON("restaurantCardImg?url=" + result.url[y]+"&id="+result.id[y], function(data)
				{
			if(data.url == "error"){
				y=y-1;
			}
			else{
				$("#"+data.id+" img").attr("src",data.url);
			}
				});
	}
}

function filterRestaurantsByCategory(kind){
	var cate = ["한식","일식","중식","양식"];
	// 카드들의 카테고리를 배열로 만들기 
	var categoryValues = $('.category').map(function() {
		var strsplit=$(this).val().split(' > ');
		  return strsplit[1];
		}).get();
	// 카드들의 id를 배열로 만들기 
	var restaurantCardID = $('.restaurant-card').map(function() {
			return $(this).attr("id");
		}).get();
		
	// 한 중 일 양 보이게 하기
	for (var i = 0; i < categoryValues.length; i++) {
		if (categoryValues[i] == kind) {
			$("#"+restaurantCardID[i]).css('display','block');
		}
		else{
			$("#"+restaurantCardID[i]).css('display','none');
		}
	}
	
	// 전체일 때
	if(kind == "전체"){
		$('.restaurant-card').css('display','block');
	}
	
	// 기타 클릭시
	if(kind == "기타"){
		for (var i = 0; i < categoryValues.length; i++) {
			if ((categoryValues[i] != cate[0]) && (categoryValues[i] != cate[1]) &&(categoryValues[i] != cate[2]) && (categoryValues[i] != cate[3]) ) {
				$("#"+restaurantCardID[i]).css('display','block');
			}
			else{
				$("#"+restaurantCardID[i]).css('display','none');
			}
		}
	}
}



