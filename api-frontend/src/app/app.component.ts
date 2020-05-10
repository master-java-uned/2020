import {Component, OnInit} from '@angular/core';
import {Title} from '@angular/platform-browser';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  url = 'http://localhost:8081/websocket'
  client: any;
  message:string;
  ngOnInit() {
    this.title.setTitle ('api-frontend');
  }
  constructor(private title: Title){
    this.connection();
  }

  connection(){
    let ws = new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that = this;

    this.client.connect({}, function(frame) {
      that.client.subscribe("/topic/updateData", (message) => {
        if(message.body) {
          this.message = message.body;

          $(".msg").html(this.message)

        }
      });
    });
  }
}
