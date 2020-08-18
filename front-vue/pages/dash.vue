<template>
  <v-flex>
    <v-container class="fill-height cabecera">
      <v-row class=" text-center">
        <div class="text-center" style="width: 100%;">
          <br />
          <h1 class="">Sea usted bienvenido a su DashBoard!</h1>
          <v-spacer></v-spacer>
          <br />
          <h3 class="text-center">UNED, NEDU, EDUN, <i><u><b>DUNE IV</b></u></i>!!!</h3>
        </div>
        <br />
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <div class="row text-center row--dense" style="margin-top: 25px;">
          <v-card elevation="12"  class="col-12 col-md-3 col  mt-3 rounded-xl">
              <v-card-title class="d-inline" dark>
                <v-icon dark>mdi-login</v-icon>
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Perfil</h2>This is not a question<hr/>
              </v-card-subtitle>
              <v-card-text>Lorem ipsum dolor sit
              </v-card-text>
              <v-spacer></v-spacer>
                <v-btn to="/perfil" dark>Perfil</v-btn>
          </v-card>
          <v-spacer></v-spacer>
         <v-card v-if="!esSuper" elevation="12"  class="col-12 col-md-3 col  mt-3 rounded-xl">
              <v-card-title class="d-inline" dark>
                <v-icon dark>mdi-sheep</v-icon>
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Clóname</h2>This is not a question<hr/>
              </v-card-subtitle>
              <v-card-text>Lorem ipsum dolor sit
              </v-card-text>
              <v-spacer></v-spacer>
                <v-btn to="/clonar" dark>Dolly sheep</v-btn>
          </v-card>
          <v-card v-if="esSuper" elevation="12"  class="col-12 col-md-3 col  mt-3 rounded-xl">
              <v-card-title class="d-inline" dark>
                <v-icon dark>mdi-format-list-bulleted</v-icon>
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">Listado</h2>This is not a question<hr/>
              </v-card-subtitle>
              <v-card-text>Lorem ipsum dolor sit
              </v-card-text>
              <v-spacer></v-spacer>
                <v-btn to="/listado" dark>Listado</v-btn>
          </v-card>
          <v-spacer></v-spacer>
          <v-card elevation="12"  class="col-12 col-md-3 col  mt-3 rounded-xl">
              <v-card-title class="d-inline" dark>
                <v-icon dark>mdi-logout</v-icon>
              </v-card-title>
              <v-spacer></v-spacer>
              <v-card-subtitle>
                <h2 class="name">To quit or not Nesquik</h2>This is not the question<hr/>
              </v-card-subtitle>
              <v-card-text>Lorem ipsum dolor sit
              </v-card-text>
              <v-spacer></v-spacer>
                <v-btn @click="logout" dark>Nesquik</v-btn>
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
import { alertas } from "~/app_code/alertas";

export default {
  components: {
    Logo,
    VuetifyLogo,
  },
  methods: {
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
  data() {
    return {
      esSuper: false,
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
};
</script>
<style scoped>
.v-card {
  background: #344e5e;
  padding: 2em;
  height: 275px;
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