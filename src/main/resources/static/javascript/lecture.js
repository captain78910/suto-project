
function initMap() {
	const map = new google.maps.Map(document.getElementById("map"), {
	    center: { lat: 35.681236, lng: 139.767125 }, // 東京駅
	    zoom: 13,
	    mapId: "DEMO_MAP_ID", // カスタムMap IDが必要（なくても動くけど推奨される）
	  });
  
	const infoWindow = new google.maps.InfoWindow();
  	const locations = [
		{ position: { lat: 35.6812, lng: 139.7671 }, title: "東京駅", content: "ここは東京駅です。", rank: 5 },
	    { position: { lat: 35.6895, lng: 139.6917 }, title: "新宿駅", content: "ここは新宿です", rank: 3 },
	    { position: { lat: 35.6995, lng: 139.7737 }, title: "秋葉原駅", content: "ここ秋葉原です", rank: 2 },
      ];

      locations.forEach((loc) => {
		const pin = new google.maps.marker.PinElement({
		        background: "#4285F4", // Google Blue
		      });

		const marker = new google.maps.marker.AdvancedMarkerElement({
			map,
			position: loc.position,
			title: loc.title,
			content: pin.element
		});
		
		marker.addEventListener("click", () => {
			const content = `
			        <div style="font-size: 16px; font-weight: bold; color: #4285F4;">
			          <h3>${loc.title}</h3>
					  <h4>評価：${loc.rank}</h4>
			          <p style="color: #555;">${loc.content}</p>
			        </div>
			      `;
			infoWindow.setContent(content);
			infoWindow.setPosition(loc.position); // クリックした位置に表示
	      　infoWindow.open(map); // マップに表示
		})
      });
}

