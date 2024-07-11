
function handleProducerSearchClick() {
	const searchInput = document.querySelector(".search-input");
	location.href = `http://localhost:8080/dvd/producer/search?searchText=${searchInput.value}`;
	
}