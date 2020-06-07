Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var fecha_inicio_ = "2020/01/01";
var fecha_inicio = "2020/01/01";

function setMyBarChart2(countries, deaths, cases) 
{
    var ctx = document.getElementById("myBarChart2");
    var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: countries,
            datasets: [{
                    label: " Deceased ",
                    backgroundColor: "#bb596b",
                    borderColor: "#bb596b",
                    data: deaths,
                },
                {
                    label: " Confirmed ",
                    backgroundColor: "#27496d",
                    borderColor: "#27496d",
                    data: cases,
                }
            ],
        },
        options: {
            title: {
                display: false,
                text: 'Cases - Death'
            },
            tooltips: {
                mode: 'index',
                intersect: false
            },
            responsive: true,
            scales: {
                xAxes: [{
                    stacked: true,
                }],
                yAxes: [{
                    stacked: true
                }]
            }
        }
    });
}



function CreateMap(data) 
{
    singlemap = null;
    singlemap = $('#world-map').vectorMap({
        map: 'world_mill',
        backgroundColor: 'transparent',
        zoomOnScroll: false,
        zoomButtons: false,
        panOnDrag: false,
        series: {
            regions: [{
                values: data,
                scale: ['#C8EEFF', '#bb596b'],

                normalizeFunction: 'polynomial'
            }]
        },
        onRegionTipShow: function(e, el, code) {
            if (casesData[code] != null) {

                el.html(el.html() + ' (Cases - ' + data[code] + ')');
            }
        }
    });
}




function setvalue1() 
{
    var f_ini = new Date(fecha_inicio);
    f_ini.setDate(f_ini.getDate() + 1);
    fecha_inicio = f_ini.yyyymmdd();

    $("#actualDay").html(f_ini.yyyymmdd());
    var data = {
        maxdate: f_ini.yyyymmdd()
    };

    $.ajax({
        type: "POST",
        url: "./covidbydate",

        data: data,
        success: function(responseJson) {
            console.log(f_ini.yyyymmdd());
            ClearMapa();
            casesData = responseJson;
            CreateMap(casesData);
        }
    });
}

function setvalue1auto() 
{
    var f_ini = new Date(fecha_inicio);
    f_ini.setDate(f_ini.getDate() + 1);
    fecha_inicio = f_ini.yyyymmdd();

    $("#actualDay").html(f_ini.yyyymmdd());
    var data = {
        maxdate: f_ini.yyyymmdd()
    };

    $.ajax({
        type: "POST",
        url: "./covidbydate",

        data: data,
        success: function(responseJson) {
            console.log(f_ini.yyyymmdd());
            ClearMapa();
            casesData = responseJson;
            CreateMap(casesData);
            autoCall();
        }
    });
}

function autoCall() 
{

    setTimeout(function() 
    {
        setvalue1auto()
    }, 1000);
}




function setvalue3() 
{
    fecha_inicio = fecha_inicio_;

    $("#actualDay").html(fecha_inicio);
    var data = {
        maxdate: "2020/01/01"
    };

    $.ajax({
        type: "POST",
        url: "./covidbydate",

        data: data,
        success: function(responseJson) {

            ClearMapa();
            casesData = responseJson;
            CreateMap(casesData);
        }
    });
}


function ClearMapa() 
{
    $('.jvectormap-container').remove();
    $('.jvectormap-tip').remove();
    $('.jvectormap-tip').html("");
    $('#world-map').html("");
}

function setvalue4() 
{
    fecha_inicio = fecha_inicio_;

    $("#actualDay").html(fecha_inicio);
    var data = {
        maxdate: "2020/01/01"
    };

    $.ajax({
        type: "POST",
        url: "./covidbydate",

        data: data,
        success: function(responseJson) {
            ClearMapa();

            casesData = responseJson;
            CreateMap(casesData);
            autoCall();
        }
    });
}


Date.prototype.yyyymmdd = function()
{
    var mm = this.getMonth() + 1; // getMonth() is zero-based
    var dd = this.getDate();

    return [this.getFullYear(),
        (mm > 9 ? '' : '0') + mm,
        (dd > 9 ? '' : '0') + dd
    ].join('/');
};

var date = new Date();