
function handlePublisherSearchClick() {
	const searchInput_a = document.querySelector(".search-input")
	location.href = `http://localhost:8080/dvd/publisher/search?searchTest=${searchInput_a.value}`;
}