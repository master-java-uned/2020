<template>
  <v-flex>
    <v-container class="fill-height cabecera">
      <v-row class=" text-center">
        <div class="text-center" style="width: 100%;">
          <br />
          <h1 class="">{{ $t("sobrenosotros.sobre") }}</h1>
          <v-spacer></v-spacer>
          <br />
        </div>
        <h3>{{ $t("sobrenosotros.desc") }}</h3>
        <br />
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <div class="row text-center row--dense" style="margin-top: 25px;">
          <v-card elevation="12"  class="col-12 col-md-3 col  mt-3 rounded-xl">
              <v-card-title class="d-inline">
                <img height="100" class="rounded-circle"  style="background: cyan;" :src="require('~/assets/img/victor.webp')" />
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Víctor</h2>{{ $t("sobrenosotros.cabezaPensante") }}<hr/>
              </v-card-subtitle>
              <v-card-text>{{ $t("sobrenosotros.guru") }}</v-card-text>
          </v-card>
          <v-spacer></v-spacer>
          <v-card elevation="12" class="col-12 col-md-3 col mt-3 rounded-xl">
              <v-card-title class="d-inline">
                <img
                  height="100"
                  class="rounded-circle"
                  :src="require('~/assets/img/PeterFight.png')"
                />
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Peter Fight</h2>{{ $t("sobrenosotros.tonto") }}<hr/>
              </v-card-subtitle>
              <v-card-text>{{ $t("sobrenosotros.proletario") }}</v-card-text>
          </v-card>
          <v-spacer></v-spacer>
          <v-card elevation="12" class="col-12 col-md-3  mt-3 rounded-xl">
              <v-card-title class="d-inline">
                <img
                  height="100"
                  class="rounded-circle"   style="background: lightgreen;"
                  :src="require('~/assets/img/redeskako.png')"
                />
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Redeskako</h2>{{ $t("sobrenosotros.lider") }}<hr/>
              </v-card-subtitle>
              <v-card-text>{{ $t("sobrenosotros.liderDesc") }}</v-card-text>
          </v-card>
        </div>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-container>
          <v-spacer></v-spacer>
        </v-container>
      </v-row>
    </v-container>
  </v-flex>
</template>


<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";

export default {
  components: {
    Logo,
    VuetifyLogo,
  },
};
</script>
<style scoped>
.v-card {
  background: #344e5e;
  padding: 2em;
  height: 325px;
}

.v-card > * {
  color: white !important;
}

.rounded-circle {
  border-radius: 50% !important;
  border: 1px solid black;
  box-shadow: 3px 3px 30px black;
}
</style>