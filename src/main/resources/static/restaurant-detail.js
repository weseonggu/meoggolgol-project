$(function() {
	// 먹자골록 위치 지도 카카오맵
	const urlParams = new URL(location.href).searchParams;

	const la = urlParams.get('la');
	const lo = urlParams.get('lo');
	const imgUrl = urlParams.get('imgUrl');
	const placeName = urlParams.get('placeName');
	const placeUrl = urlParams.get('placeUrl');
	const roadAddress = urlParams.get('roadAddress');

	var mapContainer = document.getElementById('mggRestaurant'), // 지도의 중심좌표
		mapOption = {
			center: new kakao.maps.LatLng(la, lo), // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 지도에 마커를 표시합니다 
	var marker = new kakao.maps.Marker({
		map: map,
		position: new kakao.maps.LatLng(la, lo)
	});

	// 커스텀 오버레이에 표시할 컨텐츠 입니다
	// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
	// 별도의 이벤트 메소드를 제공하지 않습니다 
	var content = '<div class="wrap">' +
		'    <div class="info">' +
		'        <div class="title">' + placeName +
		'            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
		'        </div>' +
		'        <div class="body">' +
		'            <div class="img">' +
		'                <img src="https:' + imgUrl + '" width="73" height="70">' +
		'           </div>' +
		'            <div class="desc">' +
		'                <div class="ellipsis">' + roadAddress + '</div>' +
		'            </div>' +
		'        </div>' +
		'    </div>' +
		'</div>';

	// 마커 위에 커스텀오버레이를 표시합니다
	// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
	var overlay = new kakao.maps.CustomOverlay({
		content: content,
		map: map,
		position: marker.getPosition()
	});

	// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		overlay.setMap(map);
	});

	// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
	function closeOverlay() {
		overlay.setMap(null);
	}
	// getRestaurantInfo(lo, la, page);
	getRestaurantInfo(placeUrl, placeName);
	getRestaurantafdsInfo(placeUrl);
	
	
	
	  var windowWidth = $(window).width();
	  if (windowWidth<=1330) {
			$("#mggInfo").css("width","100%");
			$("#reviewList").css("width","100%");
			$(".mggInfoBox").css("width","100%");
			$("#score").css("width","100%");
		}else{
			$("#mggInfo").css("width","1330px");
			$("#reviewList").css("width","1330px");
			$(".mggInfoBox").css("width","1330px");
			$("#score").css("width","1330px");
		}
	
	  // 카드 이미지
	  $(window).on('resize', function() {
		  
		  var windowWidth = $(window).width();
		  if (windowWidth<=1330) {
			  $("#mggInfo").css("width","100%");
			  $("#reviewList").css("width","100%");
			  $(".mggInfoBox").css("width","100%");
			  $("#score").css("width","100%");
			}else{
				$("#mggInfo").css("width","1330px");
				$("#reviewList").css("width","1330px");
				$(".mggInfoBox").css("width","1330px");
				$("#score").css("width","1330px");
			}
		});
});

// 식당 세부 정보 ajax로 호출
function getRestaurantInfo(placeUrl, placeName) {
	return new Promise(function(reject) {
		placeUrl = placeUrl.replace("http:", "https:"); // "placeUrl" 변수에 대입하는 부분 수정
		$.getJSON("restaurantDetailInfo?placeUrl=" + placeUrl, function(data) {
			$("#mggInfoabc").append(
				$("<h3></h3>").text(placeName),
				$("<a></a>").attr("href", data.mapURL).append($("<img>").attr("src", "/find1.png").addClass("findMapURL")), // "val()" 대신 "attr()"을 사용하여 href 속성 설정
				$("<div></div>").text(data.operation),
				$("<div></div>").text(data.businessHours),
				$("<div></div>").text(data.locationDetail),
				$("<div></div>").text(data.facilityInfos)
			);
		}).fail(function(error) {
			reject(error);
		});
	});
}

// 식당 메뉴 ajax로 호출
function getRestaurantafdsInfo(placeUrl) {
	return new Promise(function(reject) {
		placeUrl = placeUrl.replace("http:", "https:"); // "placeUrl" 변수에 대입하는 부분 수정
		$.getJSON("restaurantMenuInfo?placeUrl=" + placeUrl, function(data) {
			$("#menuHead").append(
				$("<tr></tr>").append(
					$("<th></th>").text("메뉴명"),
					$("<th></th>").text("가격")
					)
			);
			$.each(data, function(i) {
				var menuInfo = $("<tr></tr>").append(
					$("<td></td>").text(data[i].name),
					$("<td></td>").text(data[i].price)
				);
				$("#menuBody").append(menuInfo);
			});
		}).fail(function(error) {
			reject(error);
		});
	});
}
