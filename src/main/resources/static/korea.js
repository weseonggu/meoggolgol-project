//지도 그리기
window.onload = function() {
    var width = 300; //지도의 넓이
    var height = 300; //지도의 높이
    var initialScale = 5500; //확대시킬 값
    var initialX = -12000; //초기 위치값 X
    var initialY = 4050; //초기 위치값 Y
    var labels;

    var projection = d3.geo
        .mercator()
        .scale(initialScale)
        .translate([initialX, initialY]);
    var path = d3.geo.path().projection(projection);
    var zoom = d3.behavior
        .zoom()
        .translate(projection.translate())
        .scale(projection.scale())
        .scaleExtent([height, 800 * height])
        .on('zoom', zoom);

    var svg = d3
        .select('#MapContainer')// id container 선택
        .append('svg')
        .attr('width', width + 'px')
        .attr('height', 'auto')
        .attr('id', 'map')
        .attr('class', 'map');

    var states = svg
        .append('g')
        .attr('id', 'states')
        .call(zoom)
        .attr("fill", "white");// 배경 흰색

    states
        .append('rect')
        .attr('class', 'background')
        .attr('width', width + 'px')
        .attr('height', 'auto');

    //geoJson데이터를 파싱하여 지도그리기
    d3.json('json/korea.json', function(json) {
        states
            .selectAll('path') //지역 설정
            .data(json.features)
            .enter()
            .append('path')
            .attr('d', path)
            .attr('id', function(d) {
                return 'path-' + d.properties.name_eng;
            })
            // 지역 코드 가져 오기(이벤트 처리)
            .on("click", function(d) {

            	$("#sigunguTable").empty();

            	$("#meoggolgolTable").empty();
            	$.getJSON("sigungu?sidoCode="+d.properties.code, function(sigungu){

            		$.each(sigungu, function(index) {
						// 버튼
                        var button;
                        
                        // 해당 지역 눌렀을 때 도+시+군+구 뜨는 거 지저분해보여서 " "를 기준으로 잘라서 가져왔음
						var sigungu_name = sigungu[index].name
						var sigungu_name2 = []
						sigungu_name2 = sigungu_name.split(" ");
						
						// 시, 군, 구로 분할 한 것 중 구 값이 undefined일 수 있음 -> 조건문으로 undefined 제거
						if (sigungu_name2[2] == undefined) {
							// UI에 띄울 변수
							var onlySiGu = sigungu_name2[1];
						} else {
							// UI에 띄울 변수
							var onlySiGu = sigungu_name2[1] + " " + sigungu_name2[2];
						}
						
						// index가 0번 부터 시작이어서 (index+1)로 넣어야 알맞게 3개씩 분할됨
						if ((index+1)%3==0 && index != 0) {
							// 3, 6, 9 ..번의 태그에 <br>을 넣음으로써 3개 마다 줄 바꿈
							button = $("<span id='sigunguListSpan'></span><br>").text(onlySiGu).attr("onclick","listAjax("+sigungu[index].code+")")
							
						} else {
							button = $("<span id='sigunguListSpan'></span>").text(onlySiGu).attr("onclick","listAjax("+sigungu[index].code+")")
						}
						
						$("#sigunguTable").append(button);
        			});
         		});
        	});

        labels = states
            .selectAll('text')
            .data(json.features) //라벨표시
            .enter()
            .append('text')
            .attr('transform', translateTolabel)
            .attr('id', function(d) {
                return 'label-' + d.properties.name_eng;
            })
            .attr('text-anchor', 'middle')
            .attr('dy', '.35em')
            .text(function(d) {
                return d.properties.name;
            });
    });

    //텍스트 위치 조절 - 하드코딩으로 위치 조절을 했습니다.
    function translateTolabel(d) {
        var arr = path.centroid(d);
        if (d.properties.code == 31) {
            //서울 경기도 이름 겹쳐서 경기도 내리기
            arr[1] +=
                d3.event && d3.event.scale
                    ? d3.event.scale / height + 20
                    : initialScale / height + 20;
        } else if (d.properties.code == 34) {
            //충남은 조금 더 내리기
            arr[1] +=
                d3.event && d3.event.scale
                    ? d3.event.scale / height + 10
                    : initialScale / height + 10;
        }
        return 'translate(' + arr + ')';
    }

    function zoom() {
        projection.translate(d3.event.translate).scale(d3.event.scale);
        states.selectAll('path').attr('d', path);
        labels.attr('transform', translateTolabel);
    }

	const urlParams = new URL(location.href).searchParams;

	const la = urlParams.get('la');
	const lo = urlParams.get('lo');


	var container = document.getElementById('mggMap');
		var options = {
			center: new kakao.maps.LatLng(la, lo),
			level: 3
		};

	var map = new kakao.maps.Map(container, options);

	//마커가 표시될 위치
	var markerPosition  = new kakao.maps.LatLng(la, lo); 
	
	//마커를 생성
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	
	//마커가 지도 위에 표시되도록 설정
	marker.setMap(map);
};
// 시군도 선택시 그 지역의 먹자골목 리스트 출력
function listAjax(code){
	$("#meoggolgolTable").empty();
    $.getJSON("meoggolgol-list?sigunguCode="+code, function(data){
		$.each(data, function(i) {
			
            var ntd = $("<h5 id='streetName'></h5>").text(data[i].FCLTY_NM);
            var ltd = $("<p id='streetAddress'></p>").text(data[i].RDNMADR_NM);
            var lotd = $("<input>").attr("value", data[i].FCLTY_LO);
            var latd = $("<input>").attr("value", data[i].FCLTY_LA);
            
            var tr = $("<div></div>").attr("onclick","mggDetail("+data[i].FCLTY_LO+","+data[i].FCLTY_LA+")").append(ntd,ltd, lotd, latd);
			$("#meoggolgolTable").append(tr);
		});
		});
}
function mggDetail(lo,la){
	location.href="mgg-detail?lo="+lo+"&la="+la;
}






