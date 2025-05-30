
function initMap() {
	const map = new google.maps.Map(document.getElementById("map"), {
	    center: { lat: 35.681236, lng: 139.767125 }, // 東京駅
	    zoom: 13,
	    mapId: "DEMO_MAP_ID", // カスタムMap IDが必要（なくても動くけど推奨される）
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
	  
  
	
		
		storeInformation.forEach(storeInformation => {
		      console.log("Place ID:", storeInformation.placeId);
		});
		
//  	const locations = [
//		{ position: { lat: 35.6812, lng: 139.7671 }, title: "東京駅", content: "ここは東京駅です。", rank: 5, placeId: ${googleplaceId},
//	    { position: { lat: 35.6895, lng: 139.6917 }, title: "新宿駅", content: "ここは新宿です", rank: 3, placeId: "ChIJ8UNwBh-OGGAR5Q9CdbnWA7k" },
//	    { position: { lat: 35.6995, lng: 139.7737 }, title: "秋葉原駅", content: "ここ秋葉原です", rank: 2, placeId: "ChIJnUvj5jsOGGARQJJ8X4_iO0s" },
//      ];

	

//		placeIdから他の情報を引っ張る
//		const fetchPlaceDetails = (map, placeId) => {
//			return new Promise((resolve, reject) => {
//				 const service = new google.maps.places.PlacesService(map);
//				
//				       service.getDetails(
//				          {
//				            placeId: placeId,
//				            fields: ["name", "formatted_address", "rating"],
//				          },
//				          (place, status) => {
//				            if (status === google.maps.places.PlacesServiceStatus.OK) {
//				              resolve(place);
//				
//				            } else {
//				              console.error("Place の取得に失敗:", status);
//				              reject(status);
//				            }
//				          }
//				        );
//			});
//		};
		
		// place から取得した情報を location に追加
		storeInformation.forEach(storeInformation => {
//		  fetchPlaceDetails(map, location.placeId)
//		    .then(place => {
//		      
//		      location.name = place.name;
//		      location.address = place.formatted_address;
//		      location.rating = place.rating;

		      // デバッグ出力
		      console.log("拡張された location:", storeInformation.placeId);
//		    })
//		    .catch(err => {
//		      console.error("取得失敗:", err);
//		    });
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
			        position: {lat:storeInformation.storelat, lng:storeInformation.storelong},
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
		        document.getElementById("place-name").textContent = "名前:"+ place.name;
		        document.getElementById("place-address").textContent = "住所:"+place.formatted_address || "住所情報なし";
				document.getElementById("place-rating").textContent = `評価: ${storeInformation.evalation ?? "なし"}`;
		      } catch (error) {
		        console.error("詳細情報の取得に失敗しました:", error);
		      }
			  
			    document.querySelectorAll('input[name="placeId"]').forEach(el => {el.value= storeInformation.placeId});
				document.querySelector('textarea[name="memo"]').value = storeInformation.memo ?? "";
		    });
		  }); 
		
 }

			
