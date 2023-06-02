// Markdown 변환기 인스턴스 생성
const markDownConverter = new showdown.Converter();

// 마크다운 입력창과 미리보기 영역
const markDownInput = document.getElementById('markDownInput');
const markDownPreview = document.getElementById('markDownPreview');

// 입력 상태를 실시간으로 변환하여(마크다운 -> HTML) 미리보기 영역에 보여주기
markDownInput.addEventListener('input', function () {
    const markDownText = markDownInput.value;
    const html = markDownConverter.makeHtml(markDownText);
    markDownPreview.innerHTML = html;
});
 