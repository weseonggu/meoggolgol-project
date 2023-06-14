
function handleStarRating(rating) {
  const reviewData = {
    rating: rating
  };

  const jsonStr = JSON.stringify(reviewData);

  const xhr = new XMLHttpRequest();
  xhr.open('POST', '/restaurant-detail/review', true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
      console.log(xhr.responseText);
    }
  };
  xhr.send(jsonStr);
}

const starRadios = document.querySelectorAll("input[name='reviewStar']");
starRadios.forEach(radio => {
  radio.addEventListener("change", () => {
    if (radio.checked) {
      handleStarRating(radio.value);
    }
  });
});
