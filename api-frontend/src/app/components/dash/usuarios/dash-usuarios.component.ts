/**
 * Peter Fight
 *
 * Listado de users, sólo los debería ver el admin
 */

import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../../userServices/user.service";
import {alertas} from "../../../commons/Alertas";
import {User} from "../../../userServices/auth.service";
import {UserModel} from "../../../models/user.model";

@Component({
  selector: 'app-usuarios',
  templateUrl: './dash-usuarios.component.html',
  styleUrls: ['./dash-usuarios.component.css']
})
export class DashUsuariosComponent implements OnInit {
  users:Array<UserModel>;
  totalPages:number;
  totalElements:number;
  indexActual:number;
  pageSize:number;
  totalPagesArray:Array<number>;

  constructor(private userService: UserService) { }

  /**
   * Método mágico que en bse al tamaño de la página y la página actual busca nuevos resultados
   */
  private updateTabla(){
    let x = this.userService.getUsersPaged(this.pageSize, this.indexActual);
    x.then((result: any) => {
        //Ha ido guay
        this.users = [];
        for(var elemento in result.respuesta.content)
        {
          var user = new UserModel();
          user.id = result.respuesta.content[elemento].id;
          user.email = result.respuesta.content[elemento].email;
          user.username = result.respuesta.content[elemento].username;
          user.password = result.respuesta.content[elemento].password;
          user.firstName = result.respuesta.content[elemento].firstName;
          user.lastName = result.respuesta.content[elemento].lastName;
          user.imageBase64 = result.respuesta.content[elemento].imageBase64;
          this.users.push(user);
          this.totalPages = result.respuesta.totalPages;
          this.totalPagesArray = [];
          for(var i = 1; i <= this.totalPages; i++)
          {
            this.totalPagesArray.push(i);
          }
          this.totalElements = result.respuesta.totalElements;
        }
      },
      function (error) {
        alertas.error("Se ha producido un error", "Se ha producido un error al cargar el listado");
      });
  }


  ngOnInit() {
    this.pageSize = 5;
    this.indexActual = 0;
    this.updateTabla();
  }

  next(){
    if(this.indexActual < this.totalPages)
    {
      this.indexActual++;
      this.updateTabla();
    }
    return false;
  }
  previo(){
    if(this.indexActual > 0)
    {
      this.indexActual--;
      this.updateTabla();
    }
    return false;
  }
  setPage(pagina){
    this.indexActual = pagina -1;
    this.updateTabla();
    return false;
  }

  setPageSize(evento)
  {
    this.pageSize = evento.target.value;
    this.updateTabla();
    return false;
  }



  inicioPaginaActual(){
    return parseInt(this.indexActual.toString()) * parseInt(this.pageSize.toString());
  }
  finPaginaActual(){
    return this.inicioPaginaActual() + parseInt(this.pageSize.toString());
  }
}
