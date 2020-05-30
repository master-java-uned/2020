<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<html lang="en">
  <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/css/ol.css" type="text/css">
    <style>
      .map {
        height: 100%;
        width: 100%;
      }
    </style>
    <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/build/ol.js"></script>
    <script src="https://libs.cartocdn.com/cartodb.js/v3/3.15/cartodb.js"></script>
    <script type ="text/javascript">
     function inicio()
     {
    	 
    	 
    	 var source = new ol.source.Vector({
    	    	url: 'data/geojson/countries.geojson',
    	    	  format: new ol.format.GeoJSON()
    	    });
    	    var vector = new ol.layer.Vector({
    	        source: source,
    	        
    	      });
    	    
    	    var mapConfig = {
    	    		  'layers': [{
    	    		    'type': 'cartodb',
    	    		    'options': {
    	    		      'cartocss_version': '2.1.1',
    	    		      'cartocss': '#layer { polygon-fill: #F00; }',
    	    		      'sql': 'select * from world_borders_1 where name ilike '
    	    		    }
    	    		  }]
    	    		};
    	    var cartoDBSource = new ol.source.CartoDB({
    	    	  account: 'documentation',
    	    	  config: mapConfig
    	    	});

    	   
    	    var osm=  new ol.layer.Tile({
    	        source: new ol.source.OSM()        
    	    });
    	    var cartodb= new ol.layer.Tile({
    	    	source: cartoDBSource
    	    })
    	    
    	    
    	    var map = new ol.Map({
    	        target: 'map',
    	        layers: [osm,cartodb,vector],
    	        view: new ol.View({
    	          
    	          center: ol.proj.fromLonLat([-4, 42]),
    	          zoom: 1
    	        })
    	      });
    	    
    	    
     }
     window.onload=inicio;
    </script>
    <title>OpenLayers example</title>
  </head>
  <body>
    <h2>My Map</h2>
    <div id="map" class="map"></div>
    <div id="overlay" style="background-color: white; border-radius: 10px; border: 1px solid black; padding: 5px 10px;">
    <div id="info1"><%=request.getAttribute("country-area") %> &nbsp;</div>
    <div id="info2"><%=request.getAttribute("casos") %> enfermos &nbsp;</div>
    <div id="info3"><%=request.getAttribute("muertes") %> muertos &nbsp;</div>
    <div id="info4">&nbsp;</div>
    <script type="text/javascript">
   
    function setArea(n) {
  	  mapConfig.layers[0].options.sql =
  	      'select * from world_borders_1 where name ilike ' + "'" + n + "'";
  	  cartoDBSource.setConfig(mapConfig);
  	 
  	  element.style.margin="10px 500px";
  	}
    	
       window.addEventListener("load",function(event){
    	   alert(document.getElementById('info1').value);
    	   
       });

    	document.querySelector('info1').addEventListener('keydown', updatevalue);
    	function updatevalue(e) {
    	  
    	  setArea(e.target.value);
    	  
    	 
    	  
    	};
    	
    	
        
    </script>
   
    <% String st=request.getParameter("country-area"); System.out.println(st);%>
  </body>
</html>
            
   