
function initMap() {
	const map = new google.maps.Map(document.getElementById("map"), {
		center: { lat: 35.681236, lng: 139.767125 }, // 東京駅
		zoom: 16,
		mapId: "DEMO_MAP_ID", // カスタムMap IDが必要（なくても動くけど推奨される）
	});


	//初期マップ表示  
	const defaultmap = new google.maps.places.PlacesService(map);
	const request = {
		location: map.getCenter(),
		radius: 1000,
		type: "restaurant"
	};
	defaultmap.nearbySearch(request, (results, status) => {
		if (status === google.maps.places.PlacesServiceStatus.OK && results) {
			results.forEach((place) => {
				console.log("名前:", place.name);
				console.log("プレイスid:", place.place_id);
				console.log("場所:", place.geometry.location.toString());

				//				マーカーを表示
				const marker = new google.maps.marker.AdvancedMarkerElement({
					map,
					position: place.geometry.location,
					title: place.name,
				});
				//クリックして詳細情報を表示
				marker.addListener("gmp-click", async () => {
					console.log("マーカーがクリックされました！");
					//placeIdから他の情報を引っ張る
					const fetchPlaceDetails = (map, placeId) => {
						return new Promise((resolve, reject) => {
							const service = new google.maps.places.PlacesService(map);

							service.getDetails(
								{
									placeId: placeId,
									fields: ["name", "formatted_address", "geometry","photos"],
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
					const originalPlaceId = place.place_id;
					fetchPlaceDetails(map, originalPlaceId)
						.then(placeDetails => {
							//登録済店舗かどうかをチェック
							console.log("check",storeInformation);
							const isAlreadyRegistered = storeInformation.some(store => store.placeId === originalPlaceId);
							if(isAlreadyRegistered) {
								alert("この店舗は既に登録されています");
								document.getElementById("visit-button").classList.add("hidden");
							}

							location.name = placeDetails.name;
							location.address = placeDetails.formatted_address;
							location.placeId = originalPlaceId;
							location.lat = placeDetails.geometry.location.lat();
							location.lng = placeDetails.geometry.location.lng();
							//写真を表示する機能
							if(placeDetails.photos && placeDetails.photos.length> 0) {
								const photoContainer = document.getElementById("google-photo");
								photoContainer.innerHTML = "";//既存写真をクリア
								
								const attributionSet = new Set();
								
								placeDetails.photos.forEach(photo => {
									const img = document.createElement("img")
									img.src= photo.getUrl({maxWidth:400});
									img.className = "inline-block w-32 h-auto rounded object-cover";
									photoContainer.appendChild(img);
									
									//著作権表記
									(photo.html_attributions || []).forEach(attr => attributionSet.add(attr));
									});

									// attribution 一括で表示
									if (attributionSet.size > 0) {
										const attributionDiv = document.createElement("div");
										attributionDiv.className = "text-xs mt-2 text-gray-500";
										attributionDiv.innerHTML = Array.from(attributionSet).join("<br>");
										photoContainer.appendChild(attributionDiv);
									}
								
							}

							document.getElementById("side-panel").classList.remove("hidden");
							document.getElementById("place-name").textContent = "名前:" + placeDetails.name;
							document.getElementById("place-address").textContent = "住所:" + placeDetails.formatted_address || "住所情報なし";
						

							console.log("コントローラーに渡すplaceId:", location.placeId);
							console.log("コントローラーに渡すstoreLat:", location.lat);
							console.log("コントローラーに渡すstoreLng:", location.lng);
							document.querySelectorAll('input[name="placeId"]').forEach(el => { el.value = location.placeId });
							document.querySelectorAll('input[name="lat"]').forEach(el => { el.value = location.lat });
							document.querySelectorAll('input[name="lng"]').forEach(el => { el.value = location.lng });
							document.querySelectorAll('input[name="name"]').forEach(el => { el.value = location.name });

						})
						.catch(err => {
							console.error("取得失敗:", err);
						});


				});
			});
		} else {
			console.warn("結果なし", status);
		}
	});


	// ←検索窓  
	const input = document.getElementById("pac-input");
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	//検索機能
	const autocomplete = new google.maps.places.Autocomplete(input, {
		fields: ["place_id", "geometry", "name"],
		types: ["establishment"],
	});
	autocomplete.bindTo("bounds", map); // ビューポートに応じた候補に絞る

	autocomplete.addListener("place_changed", () => {
		const place = autocomplete.getPlace();

		if (!place.geometry || !place.geometry.location) {
			alert("場所の詳細が見つかりませんでした");
			return;
		}

		// 地図を検索された場所へ移動
		map.panTo(place.geometry.location);
		map.setZoom(13);
	});




}