/*!
    * Start Bootstrap - SB Admin v6.0.0 (https://startbootstrap.com/templates/sb-admin)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    (function($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
            if (this.href === path) {
                $(this).addClass("active");
            }
        });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function(e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });
    
    $(".submit-button-file").on("click", function(e) 
    {
    	if( $(".file").val()!= null)
    	{
    		$('#loadingModal').modal('show');
    	}
    });
    
    $('.resendcode').click(function(event) 
    {
    	alert("click");
		$.post('resend',{}, function(responseText) 
		{
			alert(responseText);
		});
    });
    
})(jQuery);

    
    function setAreaChart(type,dates,data,div,title,backgroundColor,borderColor,pointBackgroundColor,pointBorderColor,pointHoverBackgroundColor)
	{
		// Area Chart Example
		var ctx = document.getElementById(div);
		var myLineChart = new Chart(ctx, {
		  type: type,
		  data: {
		    labels: dates,
		    datasets: [{
		      label: title,
		      lineTension: 0.3,
		      backgroundColor: backgroundColor,
		      borderColor: borderColor,
		      pointRadius: 1,
		      pointBackgroundColor: pointBackgroundColor,
		      pointBorderColor: pointBorderColor,
		      pointHoverRadius: 2,
		      pointHoverBackgroundColor: pointHoverBackgroundColor,
		      pointHitRadius: 1,
		      pointBorderWidth: 0,
		      data: data,
		    }],
		  },
		  options: {
		    scales: {
		      xAxes: [{
		        time: {
		          unit: 'date'
		        },
		        gridLines: {
		          display: false
		        },
		        ticks: {
		          maxTicksLimit: 100
		        }
		      }],
		      yAxes: [{
		        ticks: {
		          min: 0,
		          maxTicksLimit: 100
		        },
		        gridLines: {
		          color: "rgba(0, 0, 0, .125)",
		        }
		      }],
		    },
		    legend: {
		      display: false
		    }
		  }
		});
	}