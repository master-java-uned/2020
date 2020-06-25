import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BasicAuthService } from './userServices/basic-auth.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import { ContactoComponent } from './components/front/contacto/contacto.component';
import { DefaultComponent } from './components/front/default/default.component';
import { LayoutComponent } from './components/front/layout/layout.component';
import { MapaComponent } from './components/front/mapa/mapa.component';
import { RecuperaPassComponent } from './components/front/recupera_pass/recupera-pass.component';
import { RegistroComponent } from './components/front/registro/registro.component';
import { SobreNosotrosComponent } from './components/front/sobre_nosotros/sobre-nosotros.component';
import { DashPerfilComponent } from './components/dash/perfil/dash-perfil.component';
import { DashUsuariosComponent } from './components/dash/usuarios/dash-usuarios.component';
import {RouterModule} from "@angular/router";
import {LoginComponent} from "./components/front/login/login.component";
import {LogoutComponent} from "./components/front/logout/logout.component";
import {DashLayoutComponent} from "./components/dash/layout/dash-layout.component";
import {DashDefaultComponent} from "./components/dash/default/dash-default.component";
import {NoencontradoComponent} from "./components/shared/404/noencontrado/noencontrado.component";
import { ClonarComponent } from './components/dash/clonar/clonar.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../../src/environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    ContactoComponent,
    DefaultComponent,
    LayoutComponent,
    MapaComponent,
    RecuperaPassComponent,
    RegistroComponent,
    SobreNosotrosComponent,
    DashPerfilComponent,
    DashUsuariosComponent,
    DashLayoutComponent,
    LoginComponent,
    LogoutComponent,
    NoencontradoComponent,
    DashDefaultComponent,
    ClonarComponent
  ],
    imports: [
        // BrowserModule,
      BrowserModule.withServerTransition({ appId: 'app-root' }),//Para angularUniversal, yeah
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
    ],
  providers: [ {
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthService, multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
