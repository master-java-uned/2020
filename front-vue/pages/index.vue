<template>
  <v-carousel
    cycle
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item class="pa-0 ma-0 text-center"  >
      <v-img :src="require('~/assets/img/back-covid.jpg')" style="height: 500px;">
        <v-container class="mb-5 d-flex flex-column align-center" style="margin-top: 25vh;" >
          <h2>{{ $t("index.titulo1") }}</h2>
          &nbsp;
          <h3>{{ $t("index.subheading1") }}</h3>
        </v-container>
      </v-img>
    </v-carousel-item>
    <v-carousel-item class="pa-0 ma-0 text-center" style="-webkit-filter: grayscale(50%);">
      <v-img :src="require('~/assets/img/estudios.jpg')" style="height: 500px;">
      <div align-center="true" class="text-center" style="width: 100vw; position: absolute; top: 0;">
        <img
          class="w-100"
          src="https://www.uned.es/universidad/.resources/UNED/webresources/img/logo_uned.gif"
        />
      </div>
       <v-container class="mb-5 d-flex flex-column align-center" style="margin-top: 25vh;" >
          <h2>{{ $t("index.titulo2") }}</h2>
          &nbsp;
          <h3>{{ $t("index.subheading2") }}</h3>
        </v-container>
      </v-img>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";


export default {
  components: {
    Logo,
    VuetifyLogo
  },
  mounted: function(){
  }
};
</script>

<style scoped>
h2,
h3 {
  text-shadow: black 2px 2px 0;
  z-index: 1000;
}

h1 {
  font-size: 3em;
}

h4 {
  font-size: 2em;
}

.v-parallax__image-container {
  /* height: 100vh; */
  margin: 0;
  padding: 0;
}

/* .v-parallax__image {
  object-fit: contain !important;
  height: 100vh !important;
} */

</style>