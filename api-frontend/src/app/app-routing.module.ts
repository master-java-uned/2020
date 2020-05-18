/**
 * Peter Fight
 */

import { NgModule } from '@angular/core';
import {PreloadAllModules, Routes, RouterModule, NavigationEnd} from '@angular/router';

/**
 * IMPORTS SHARED
 */
import {NoencontradoComponent} from './components/shared/404/noencontrado/noencontrado.component';

/**
 * IMPORTS FRONT
 */
import {ContactoComponent} from './components/front/contacto/contacto.component';
import {LayoutComponent} from './components/front/layout/layout.component';
import {LoginComponent} from './components/front/login/login.component';
import {LogoutComponent} from './components/front/logout/logout.component';
import {MapaComponent} from './components/front/mapa/mapa.component';
import {RecuperaPassComponent} from './components/front/recupera_pass/recupera-pass.component';
import {RegistroComponent} from './components/front/registro/registro.component';

/**
 * IMPORTS DASH
 */
import {DashLayoutComponent} from './components/dash/layout/dash-layout.component';
import {DashPerfilComponent} from './components/dash/perfil/dash-perfil.component';
import {DashUsuariosComponent} from './components/dash/usuarios/dash-usuarios.component';

import { AuthGuardService } from './userServices/auth-guard.service' ;
import {SobreNosotrosComponent} from "./components/front/sobre_nosotros/sobre-nosotros.component";
import {DefaultComponent} from "./components/front/default/default.component";
import {DashDefaultComponent} from "./components/dash/default/dash-default.component";
import {ClonarComponent} from "./components/dash/clonar/clonar.component";

const routes: Routes = [
  /**
   * FRONT
   */
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: DefaultComponent},
      { path: 'contacto', component: ContactoComponent },
      { path: 'login', component: LoginComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'mapa', component: MapaComponent },
      { path: 'mapa/:continent', component: MapaComponent },
      { path: 'recupera-pass', component: RecuperaPassComponent },
      { path: 'registro', component: RegistroComponent },
      { path: 'sobre-nosotros', component: SobreNosotrosComponent }
    ]
  },
  /**
   * DASH
   */
  {
    path: 'dash',
    component: DashLayoutComponent,
    children: [
      { path: '', component: DashDefaultComponent},
      { path: 'perfil', component: DashPerfilComponent },
      { path: 'usuarios', component: DashUsuariosComponent },
      { path: 'clonar', component: ClonarComponent }
    ]
  },
  /**
   * Redirecciono los 404!!!
   */
  {path: '**', component: NoencontradoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


