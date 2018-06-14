//ML Rest
    

//GlobalAppController
var appController = (function (budgetCtrl, UICtrl){
    
	var submitRestRequest = function () {
	    var day = document.getElementsByClassName("add_day")[0].value;
	    var xhttp = new XMLHttpRequest();
	    var requestURL = "http://localhost:8080/clima?dia=" + day;
	    console.log (requestURL);
	    
	    xhttp.open("GET", requestURL, false);
	    xhttp.setRequestHeader("Content-type", "application/json");
	    
        xhttp.send();
	        
	    var response = xhttp.responseText;
        
        var obj = JSON.parse (response);
        
        updateGui (obj);    
    }
    
    document.querySelector('.submit_btn').addEventListener('click', submitRestRequest);
    
    document.addEventListener('keypress', function(event){
        if (event.keyCode === 13 || event.which === 13){
           ctrlAddItem ();
        }
    });
    
    function updateMap (obj){
        if(document.getElementsByClassName ("map")[0] != undefined)
            document.getElementsByClassName ("map")[0].remove();
        
        var startMapURL = "http://www.webmath.com/cgi-bin/grapher.cgi?param0=";
        var element1 = "%28#%2C$%29";
        var element2 = "%28#%2C$%29";
        var element3 = "%28#%2C$%29";
        var element4 = "%28#%2C$%29";
        
        element1 =element1.replace("#", "0");
        element1 = element1.replace("$", "0");
        
       element2 =element2.replace("#", obj.betasoideLocation.x );
       element2 =element2.replace("$", obj.betasoideLocation.y);
        
        element3 = element3.replace("#", obj.ferengiLocation.x );
        element3 = element3.replace("$", obj.ferengiLocation.y );
        
        element4 = element4.replace("#", obj.vulcanoLocation.x);
        element4 = element4.replace("$", obj.vulcanoLocation.y);
        
        var endMapURL = "&ymax=2000&xmin=-2000&xmax=2000&ymin=-2000&to_plot=points";
        
        var URL = startMapURL + element1 + "+" + element2 + "+" + element3 + "+" + element4 + endMapURL;
        
        
        
        var div = document.createElement('div');
        div.innerHTML = '<iframe src="'+URL+'">';
        // set style
        div.style.color = 'red';
        // better to use CSS though - just set class
        div.setAttribute('class', 'map'); // and make sure myclass has some styles in css
        div.setAttribute('style', 'width="800" height="600" frameborder="0" scrolling="no";')
       document.body.appendChild(div);
    }
        
    function updateGui (obj){
        var node = document.getElementsByClassName("result")[0];
        if(document.getElementsByClassName ("result_container")[0] != undefined)
            document.getElementsByClassName ("result_container")[0].remove();
        
                
	    console.log(obj);
	    
	    var node = document.getElementsByClassName("result")[0]; // Create a <li> node
	    
        var div = document.createElement('div');
        div.innerHTML = "Es el dia " + obj.currentDay + " y el clima es: " + obj.weatherText;
        // set style
        div.style.color = 'red';
        // better to use CSS though - just set class
        div.setAttribute('class', 'result_container'); // and make sure myclass has some styles in css

	    node.append(div);
        updateMap(obj);
    }
    
})();
                        