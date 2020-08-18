<template>
  <v-layout>
     <v-navigation-drawer
      app
      fixed
      v-model="showMenu"
      v-if="showMenu"
    >
      <v-list class="text-center">
        <v-list-item  :to="localePath('dash')">
          <v-list-item-content>
            <v-list-item-title>COVID-19 (dash)</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="localePath('perfil')">
          <v-list-item-content>
            <v-list-item-title>{{$t('menuBack.perfil')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item  v-if="!esSuper"  :to="localePath('clonar')">
          <v-list-item-content>
            <v-list-item-title>{{$t('menuBack.clonar')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item  v-if="esSuper"  :to="localePath('listado')">
          <v-list-item-content>
            <v-list-item-title>{{$t('menuBack.listado')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item  @click="logout">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>{{$t('menuBack.logout')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list>
        
    </v-navigation-drawer>
    <v-app-bar :clipped-right="clipped" fixed app dark :src="require('~/assets/img/CAPTURA.png')">
    <v-app-bar-nav-icon
        class="d-md-none d-lg-none d-xl-none"
       @click.stop="toggleMenu"></v-app-bar-nav-icon>
      <v-tabs class="d-none d-md-block">
        <v-tab
          style="width: 250px; font-weight: bolder; font-size: 1em;"
          :to="localePath('dash')"
        >COVID-19 (dash)</v-tab>
        <v-tab :to="localePath('perfil')">{{$t('menuBack.perfil')}}</v-tab>
        <v-tab v-if="!esSuper" :to="localePath('clonar')">{{$t('menuBack.clonar')}}</v-tab>
        <v-tab v-if="esSuper" :to="localePath('listado')">{{$t('menuBack.listado')}}</v-tab>

        <!-- <v-container class="mt-2">
          <nuxt-link hide-slider v-if="$i18n.locale !== 'en'" :to="switchLocalePath('en')">
            <v-img height="25" width="50" :src="require('~/assets/img/usa.png')"></v-img>
          </nuxt-link>
          <nuxt-link hide-slider v-if="$i18n.locale !== 'es'" :to="switchLocalePath('es')">
            <v-img height="25" width="50" :src="require('~/assets/img/spain.png')"></v-img>
          </nuxt-link>
        </v-container>-->

        <v-spacer></v-spacer>
        <v-tab @click="logout">{{$t('menuBack.logout')}}</v-tab>
      </v-tabs>
    </v-app-bar>
  </v-layout>
</template>
<script>
import { alertas } from "~/app_code/alertas";
export default {
  data() {
    return {
      esSuper: false,
      showMenu: false
    };
  },
  mounted: function () {
    if (
      sessionStorage.getItem("rolId") != undefined &&
      sessionStorage.getItem("rolId") == 3
    ) {
      this.esSuper = true;
    }
  },
  methods: {
     toggleMenu: function() {
      this.showMenu = !this.showMenu;
    },
    logout: function () {
      /**
       * Limpio las variables chungas de sesión
       */
      sessionStorage.setItem("username", "");
      sessionStorage.setItem("token", "");
      sessionStorage.setItem("rolId", "");
      alertas.exito("Desconectado", "Has cerrado la sesión correctamente", "/");
    },
  },
};
</script>