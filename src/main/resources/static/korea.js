//지도 그리기
window.onload = function() {
    var width = 500; //지도의 넓이
    var height = 500; //지도의 높이
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
        .select('#container')// id container 선택
        .append('svg')
        .attr('width', width + 'px')
        .attr('height', height + 'px')
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
        .attr('height', height + 'px')
        ;

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
            // 지역 코드 가져 오기
            .on("click", function(d) {
            	$("#sigunguTable").empty();
            	$("#meoggolgolTable").empty();
            	$.getJSON("sigungu?sidoCode="+d.properties.code, function(sigungu){
            		$.each(sigungu, function(i) {
                        var button = $("<buuton></button>").text(sigungu[i].name).attr("onclick","listAjax("+sigungu[i].code+")")
                        var ntd = $("<th></th>").append(button);
                        var tr = $("<tr></tr>").append(ntd);
            			$("#sigunguTable").append(tr);
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

};

function listAjax(code){
	$("#meoggolgolTable").empty();
    $.getJSON("meoggolgol-list?sigunguCode="+code, function(data){
		$.each(data, function(i) {
            var ntd = $("<th></th>").text(data[i].FCLTY_NM);
            var ltd = $("<th></th>").text(data[i].RDNMADR_NM);
            
            var tr = $("<tr></tr>").append(ntd,ltd);
			$("#meoggolgolTable").append(tr);
		});
		
			
		});
}
