function handleSubmitAllClick() {
	const forms = document.querySelectorAll("form");
	const formData1 = new FormData(forms[0]);
	const formData2 = new FormData(forms[1]);
	
	let reqData = {} // formData를 모두 객체화 시킨 것 
	
	/**
		formData1 = {
			username: "admin",
			password: "1234"
		}
		entries = [ 
			["username", "admin"],
			["password", "1234"]
		]
	 */
	
	for(let entry of formData1.entries()){
		const [ key, value ] = entry;
		reqData = {
			...reqData,
			[key]: value  // 첫번째 key값과 value값 => [username] : admin
		}
	}
	
	/**
		formData2 = {
			chk: "chk1", // 2번째로 실행이 됬을 때 chk2 값이 chk1값을 덮어버리므로 배열로 만든다 
			chk: "chk2",
			rdo: "rdo1"
		}
		
		entries = [ 
			["chk", "chk1"],
			["chk", "chk2"],
			["rdo", "rdo1"]
		]
	 */
	
	let duplicatedKeys = [];
	let keyCount = {}
	
	for(let key of formData2.keys()) {
		if(Object.keys(keyCount).includes(key)) {
			keyCount = {
				...keyCount,
				[key]: keyCount[key] + 1 // keyCount의 해당 key값을 실행 후 1씩 다하는.. 
			}
			continue;				
		}
		keyCount = {
			...keyCount,
			[key]: 1
		}
	}
	
	for(let key of Object.keys(keyCount)) {
		if(keyCount[key] > 1) {
			duplicatedKeys = [ ...duplicatedKeys, key ];
		}
	}
	
	console.log(keyCount);
	console.log(duplicatedKeys);
	
	for(let entry of formData2.entries()){
		const [ key, value ] = entry;
		if(duplicatedKeys.includes(key)) {
			reqData = {
				...reqData,
				[key]: [ ...(!reqData[key] ? [] : reqData[key]), value] // 배열로 추가할 수 있게 만들어라 
			}			
			continue;
		}
		reqData = {
			...reqData,
			[key]: value
		}
	}
	
	console.log(reqData);
	
	const queryString = new URLSearchParams(reqData).toString();
	
	fetch(`http://localhost:8080/dvd/form?${queryString}`)
	.then(response => {
		response.text()
		.then(data => {
			const body = document.querySelector("body");
			body.innerHTML += `<h1>${data}</h1>`;
			console.log(data);
		})
	})
	
	
	
}