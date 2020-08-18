<template>
  <v-layout>
     <v-navigation-drawer
      app
      fixed
      v-model="showMenu"
      v-if="showMenu"
    >
      <v-list class="text-center">
        <v-list-item :to="localePath('index')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>COVID-19</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="localePath('sobre-nosotros')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>{{$t('sobrenosotros.sobre')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="localePath('contacto')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>{{$t('contacto.contacto')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="localePath('mapa')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>{{$t('mapa.mapa')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-container class="mt-2 text-center">
          <nuxt-link hide-slider v-if="$i18n.locale !== 'en'" :to="switchLocalePath('en')">
            <v-img class="d-inline-block" height="25" width="50" :src="require('~/assets/img/usa.png')"></v-img>
          </nuxt-link>
          <nuxt-link hide-slider v-if="$i18n.locale !== 'es'" :to="switchLocalePath('es')">
            <v-img class="d-inline-block" height="25" width="50" :src="require('~/assets/img/spain.png')"></v-img>
          </nuxt-link>
        </v-container>
        <v-list-item :to="localePath('login')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item :to="localePath('registro')">
          <!-- <v-list-item-action>
            <v-icon>settings</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>{{$t('shared.creaCuenta')}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list>
        
    </v-navigation-drawer>
    
    <v-app-bar :clipped-right="clipped" fixed app dark :src="require('~/assets/img/CAPTURA.png')">
      <!--  -->
      <v-app-bar-nav-icon
        class="d-md-none d-lg-none d-xl-none ml-5"
       @click.stop="toggleMenu"></v-app-bar-nav-icon>
      <!-- <router-link
        :to="localePath('index')"
        style="text-decoration: none; color: white; font-weight: bolder;"
      >
        <v-toolbar-title class="col-12" href="/" v-text="title" />
      </router-link>-->

      <v-tabs 
      show-arrows
      class="d-none d-md-block">
        <v-tab
          style="width: 250px; font-weight: bolder; font-size: 1em;"
          :to="localePath('index')"
        >COVID-19</v-tab>
        <v-tab :to="localePath('sobre-nosotros')">{{$t('sobrenosotros.sobre')}}</v-tab>
        <v-tab :to="localePath('contacto')">{{$t('contacto.contacto')}}</v-tab>
        <v-tab :to="localePath('mapa')">{{$t('mapa.mapa')}}</v-tab>

        <!-- <v-tab hide-slider>
          <nuxt-link hide-slider v-if="$i18n.locale !== 'en'" :to="switchLocalePath('en')"><v-img height="25" width="50" :src="require('~/assets/img/usa.png')"></v-img> 
          </nuxt-link>
          <nuxt-link hide-slider v-if="$i18n.locale !== 'es'" :to="switchLocalePath('es')"><v-img  height="25" width="50"  :src="require('~/assets/img/spain.png')"></v-img>
          </nuxt-link>
        </v-tab>-->
        <v-container class="mt-2">
          <nuxt-link hide-slider v-if="$i18n.locale !== 'en'" :to="switchLocalePath('en')">
            <v-img height="25" width="50" :src="require('~/assets/img/usa.png')"></v-img>
          </nuxt-link>
          <nuxt-link hide-slider v-if="$i18n.locale !== 'es'" :to="switchLocalePath('es')">
            <v-img height="25" width="50" :src="require('~/assets/img/spain.png')"></v-img>
          </nuxt-link>
        </v-container>
        <!-- </v-tabs>
        <v-tabs right slider-color="transparent"> -->
        <v-spacer></v-spacer>
        <v-tab :to="localePath('login')">Login</v-tab>
        <v-tab :to="localePath('registro')">{{$t('shared.creaCuenta')}}</v-tab>
      </v-tabs>
    </v-app-bar>
  </v-layout>
</template>
<script>
export default {
  data: () => ({
    showMenu: false
  }),
  methods: {
    toggleMenu: function() {
      this.showMenu = !this.showMenu;
    }
  },
};
</script>