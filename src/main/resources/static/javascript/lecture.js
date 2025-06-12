
function initMap() {
	const map = new google.maps.Map(document.getElementById("map"), {
		center: { lat: 35.681236, lng: 139.767125 }, // 東京駅
		zoom: 13,
		mapId: "DEMO_MAP_ID", // カスタムMap IDが必要（なくても動くけど推奨される）
	});

	storeInformation.forEach(storeInformation => {
		console.log("Place ID:", storeInformation.placeId);
	});


	const fetchPlaceDetails = (map, placeId) => {
		return new Promise((resolve, reject) => {
			const service = new google.maps.places.PlacesService(map);

			service.getDetails(
				{
					placeId: placeId,
					fields: ["name", "formatted_address"],
				},
				(place, status) => {
					if (status === google.maps.places.PlacesServiceStatus.OK) {
						resolve(place);

					} else {
						console.error("Place の取得に失敗:", status);
						reject(status);
					}
				}
			);
		});
	};


	storeInformation.forEach((storeInformation) => {
		const pin = new google.maps.marker.PinElement({
			background: "#4285F4", // Google Blue
		});

		const marker = new google.maps.marker.AdvancedMarkerElement({
			map,
			position: { lat: storeInformation.storelat, lng: storeInformation.storelong },
			content: pin.element
		});

		marker.addListener("gmp-click", async () => {

			if (!storeInformation.placeId) {
				alert("この場所には placeId が設定されていません。");
				return;
			}

			try {
				const place = await fetchPlaceDetails(map, storeInformation.placeId);

				document.getElementById("side-panel").classList.remove("hidden");
				document.getElementById("place-name").textContent = `名前: ${storeInformation.storeName ?? "なし"}`;
				document.getElementById("place-address").textContent = "住所:" + place.formatted_address || "住所情報なし";
				document.getElementById("place-rating").textContent = `評価: ${storeInformation.evalation ?? "なし"}`;
			} catch (error) {
				console.error("詳細情報の取得に失敗しました:", error);
			}

			document.querySelectorAll('input[name="placeId"]').forEach(el => { el.value = storeInformation.placeId });
			document.querySelector('textarea[name="memo"]').value = storeInformation.memo ?? "";
		});
	});

	// ←検索窓  
	const input = document.getElementById("pac-input");
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	const searchBox = document.getElementById("pac-input");
	const suggestions = document.getElementById("suggestions");

	searchBox.addEventListener('input', async () => {
		const query = searchBox.value;
		if (query.length < 1) {
			suggestions.classList.add('hidden');
			return;
		}
		const res = await fetch(`/lecture/search?keyword=${encodeURIComponent(query)}`);
		const results = await res.json();
		console.log("候補:", results);

		suggestions.innerHTML = '';
		results.forEach(item => {
			const li = document.createElement('li');
			li.textContent = item.storeName;
			li.className = 'p-2 hover:bg-gray-200 cursor-pointer';
			
			li.addEventListener('click', async () => {
				searchBox.value = item.storeName;
				suggestions.classList.add('hidden');

				//検索結果の詳細をサイドパネルに表示
				console.log("情報:", storeInformation);
				const storeInfo = storeInformation.find(store => store.storeName === item.storeName);
				if (!storeInfo) {
					alert("該当の店舗情報が見つかりませんでした");
					return;
				}
				try {
					const place = await fetchPlaceDetails(map, storeInfo.placeId);

					document.getElementById("side-panel").classList.remove("hidden");
					document.getElementById("place-name").textContent = `名前: ${storeInfo.storeName ?? "なし"}`;
					document.getElementById("place-address").textContent = "住所:" + place.formatted_address || "住所情報なし";
					document.getElementById("place-rating").textContent = `評価: ${storeInfo.evalation ?? "なし"}`;

					document.querySelectorAll('input[name="placeId"]').forEach(el => { el.value = storeInfo.placeId });
					document.querySelector('textarea[name="memo"]').value = storeInfo.memo ?? "";
				} catch (error) {
					console.error("詳細情報の取得に失敗:", error);
					alert("詳細情報の取得に失敗しました");
				}
			});
		suggestions.appendChild(li);
	});
	suggestions.classList.remove('hidden');
});

}


