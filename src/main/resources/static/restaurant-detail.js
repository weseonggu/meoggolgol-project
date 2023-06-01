$(function() {
	// 먹자골록 위치 지도 카카오맵
	const urlParams = new URL(location.href).searchParams;

	const la = urlParams.get('la');
	const lo = urlParams.get('lo');
	const imgUrl = urlParams.get('imgUrl');
	const placeName = urlParams.get('placeName');
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

});