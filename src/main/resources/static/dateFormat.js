
function displayEnglishDate() {
    var today = new Date();
    var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    var dayOfWeek = days[today.getDay()];
    var month = months[today.getMonth()];
    var date = today.getDate();
    var year = today.getFullYear();

    var englishDate = 'Ubiquitous Food Street Platform\n' + dayOfWeek + ', ' + month + ' ' + date + ', ' + year;
    document.getElementById('englishDate').innerText = englishDate;

}
