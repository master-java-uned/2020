import { Component } from '@angular/core';
import {DomSanitizer, SafeUrl, Title} from '@angular/platform-browser';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import {HttpClient} from "@angular/common/http";

import {ModeloDatosCovid} from "./models/ModeloDatosCovid";
import {HeadersHelpers} from "./app_code/viewsUtils/HeadersHelpers";
import {$} from 'protractor';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'api-frontend';
  url = 'http://localhost:8081/websocket'
  client: any;
  message:string;
  scrollTop() {
    /**
     * Sube el scroll arriba.
     */
    window.scroll(0,0);
  }


  constructor(private httpClient: HttpClient,private sanitizer: DomSanitizer){
    this.connection();
  }


  connection(){
    let ws = new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that = this;
    this.client.connect({}, function(frame) {
      that.client.subscribe("/topic/updateData", (message) => {
        // debugger;
        if(message.body) {
          this.message = message.body;
          ModeloDatosCovid.init(this.message);
          // $(".msg").html(this.message)

        }
      });
    });

    /**
     * Peter Fight
     *
     * No me funciona la descarga del mapa por kafka y no tengo tiempo ni energía para averiguar por qué no va.
     * Lo monto con un get a un controlador del back, entendible por cualquiera, y a funcionar.
     *
     * Por otro lado, tener un servicio que descarga el JSON entero y lo envía a todos los usuarios conectados parece
     * ineficiente no, lo siguiente. Mejor que el trabajo lo haga el navegador del cliente, nop¿? No molará tanto,
     * pero funciona mejor.
     *
     * --> Enfoque que tomo, lo cargo en un módulo estático. Una vez lo tenga cargo todos los países posibles en el
     * menú. Una vez cargados tengo el modelo y pinto el mapa en consonancia en base al modelo. No creo que se
     * actualicen los datos cada 30 segundos, eso para Telegram...
     */

    // let p = Promise;
    // return this.httpClient.get('http://127.0.0.1:8080/users/getJson',
    //   {
    //     headers: HeadersHelpers.getHeadersANON()
    //   })
    //   .toPromise().then(function(JSON){
    //     /**
    //      * ModeloDatosCovid es una clase estática compartida por toda la aplicación que contiene los datos
    //      * descargados del servidor.
    //      */
    //     console.log(JSON);
    //     ModeloDatosCovid.init(JSON);
    //   });




  }
}

