function init(){
    var customMapType = new google.maps.StyledMapType([
        {
            elementType: 'labels',
            stylers: [{visibility: 'off'}]
        }
    ], {
        name: 'Custom Style'
    });
    var customMapTypeId = 'custom_style';
     map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: { lat: 42.888117,	lng: 74.635481 },
         mapTypeControlOptions: {
             mapTypeIds: [google.maps.MapTypeId.ROADMAP, customMapTypeId]
         },
        mapTypeId: "terrain",
    });

    map.mapTypes.set(customMapTypeId, customMapType);
    map.setMapTypeId(customMapTypeId);
     marker = new google.maps.Marker({
         position:  { lat: 42.888117,	lng: 74.635481 },
         map: map
     });

}
let map;
let marker;

function animationPath(dept_lat, dept_lng, arr_lat, arr_lng){
    var departure = new google.maps.LatLng(dept_lat, dept_lng); //Set to whatever lat/lng you need for your departure location
    var arrival = new google.maps.LatLng(arr_lat, arr_lng); //Set to whatever lat/lng you need for your arrival location
    var line = new google.maps.Polyline({
        path: [departure, departure],
        strokeColor: "#FF0000",
        strokeOpacity: 1,
        strokeWeight: 1,
        geodesic: true, //set to false if you want straight line instead of arc
        map: map,
    });
    var step = 0;
    var numSteps = 25; //Change this to set animation resolution
    var timePerStep = 1; //Change this to alter animation speed
    var interval = setInterval(function() {
        step += 1;
        if (step > numSteps) {
            clearInterval(interval);
        } else {
            var are_we_there_yet = google.maps.geometry.spherical.interpolate(departure,arrival,step/numSteps);
            line.setPath([departure, are_we_there_yet]);
        }
    }, timePerStep);
}

// setTimeout(() => animationPath(42.888117,74.635481,42.885633, 74.678353), 5000)

window.initMap = init;