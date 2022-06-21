function gettMap(){
    return  new google.maps.Map(document.getElementById("map"), {
        zoom: 5,
        center: { lat: 24, lng: 54 },
        mapTypeId: "terrain",
    });
}

const point = [];

function getPoints(){

    point.push(
        { lat: 37.772, lng: -122.214 },
        { lat: 21.291, lng: -157.821 },
        { lat: -18.142, lng: 178.431 },
        { lat: -27.467, lng: 153.027 },)
    return point
}

function initMap() {
    const map = gettMap()

    const points = getPoints()

    const path = new google.maps.Polyline({
        path: points,
        geodesic: true,
        strokeColor: "#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2,
    });

    path.setMap(map);
}

function addPoint(loc){

    const map =gettMap()

    const flightPlanCoordinates = loc

    const flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: "#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2,
    });

    flightPath.setMap(map);
}

setTimeout(function (){
    addPoint(getPoints())
}, 2000)

window.initMap = initMap;