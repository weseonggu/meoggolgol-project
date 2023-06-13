// 지도 그리기
$(function() {
  const width = 600; // 맵의 너비
  const height = 600; // 맵의 높이
  var initialScale = 5500; //확대시킬 값
  const initialX = -12000; //초기 위치값 X
  const initialY = 4050; //초기 위치값 Y
  var labels;
  // SVG 컨테이너 생성
  const svg = d3.select("#MapContainer")
    .append("svg")
    .attr("width", width)
    .attr("height", height)
    .attr('id', 'map')
    .attr('class', 'map');

  var states = svg
    .append('g')
    .attr('id', 'states')
    .attr("fill", "white"); // 배경 흰색

  states
    .append('rect')
    .attr('class', 'background')
    .attr("width", width)
    .attr("height", height);

  // 맵을 위한 투영법 정의
  const projection = d3.geoMercator()
    .center([127.985, 37.565]) // 경도 127.985, 위도 37.565로 맵 중앙 설정
    .scale(initialScale)
    .translate([width / 2, height / 2]);

  // 경로 생성기 생성
  const path = d3.geoPath().projection(projection);

  // 맵 데이터 로드
  d3.json("json/korea.json").then(function(geojson) {
    // 맵 그리기
    states.selectAll("path")
      .data(geojson.features)
      .enter()
      .append("path")
      .attr("d", path)
      .attr("fill", "lightgray")
      .attr("stroke", "gray")
      .attr('id', function(d) {
        return d.properties.code;
      })
      .on('click', function() {
        var code = $(this).attr('id');
        $("#sigunguTable").empty();
        $("#meoggolgolTable").empty();
        $.getJSON("sigungu?sidoCode=" + code, function(sigungu) {
          $.each(sigungu, function(index) {
            var button;
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
            if ((index + 1) % 3 == 0 && index != 0) {
				// 3, 6, 9 ..번의 태그에 <br>을 넣음으로써 3개 마다 줄 바꿈
              	button = $("<span id='sigunguListSpan'></span><br>").text(onlySiGu).attr("onclick", "listAjax(" + sigungu[index].code + ")");
            } else {
              	button = $("<span id='sigunguListSpan'></span>").text(onlySiGu).attr("onclick", "listAjax(" + sigungu[index].code + ")");
            }

            $("#sigunguTable").append(button);
          });
        });
      });

    labels=states.selectAll("text")
      .data(geojson.features)
      .enter()
      .append("text")
      .text(function(d) {
        return d.properties.name; // 지역 이름
      })
      .attr("x", function(d) {
    	  var arr = path.centroid(d);
    	  if (d.properties.code == 41) {
              //서울 경기도 이름 겹쳐서 경기도 내리기
              arr[1] +=
                  d3.event && d3.event.scale
                      ? d3.event.scale / height + 20
                      : initialScale / height + 20;
          } else if (d.properties.code == 44) {
              //충남은 조금 더 내리기
              arr[1] +=
                  d3.event && d3.event.scale
                      ? d3.event.scale / height + 10
                      : initialScale / height + 10;
          }
        return arr[0]; // 각 지역의 중심에 라벨 위치 설정
      })
      .attr("y", function(d) {
    	  var arr = path.centroid(d);
    	  if (d.properties.code == 41) {
              //서울 경기도 이름 겹쳐서 경기도 내리기
              arr[1] +=
                  d3.event && d3.event.scale
                      ? d3.event.scale / height + 20
                      : initialScale / height + 20;
          } else if (d.properties.code == 44) {
              //충남은 조금 더 내리기
              arr[1] +=
                  d3.event && d3.event.scale
                      ? d3.event.scale / height + 10
                      : initialScale / height + 10;
          }
        return arr[1];
      })
      .attr("text-anchor", "middle")
      .attr("fill", "black")
      .attr("font-size", "12px");

    // 줌 동작 추가
    svg.call(
      d3.zoom()
        .scaleExtent([1, 8]) // 최소 및 최대 줌 레벨 설정
        .on("zoom", zoomed)
    );

    function zoomed(event) {
      states.attr("transform", event.transform); // 줌 이벤트에 따라 맵 변환
    }
    

  });
});


// 시군도 선택시 그 지역의 먹자골목 리스트 출력
function listAjax(code){
	$("#meoggolgolTable").empty();
    $.getJSON("meoggolgol-list?sigunguCode="+code, function(data){
		$.each(data, function(i) {
			
            var ntd = $("<p id='streetName'></p>").text(data[i].FCLTY_NM);
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
	labels.attr('transform', translateTolabel);
}




