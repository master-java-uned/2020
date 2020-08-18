<template>
  <v-row
    :style="'padding: 50px 0 50px 0; height: 100; width: 100vw; background-image: url('+require('~/assets/img/COVID.jpg')+');'"
  >
    <!-- <v-col class="col-3 col"></v-col> -->
    <v-spacer></v-spacer>
    <v-col class="col-12 col-md-6 col">
      <v-spacer></v-spacer>
      <v-card elevation="12" class="mx-1" style="background: #344e5e; color: white;">
        <v-spacer></v-spacer>
        <v-card-title>
          <div class="display-1 my-2">
            Clóname
            <b>n</b> veces!
          </div>
          <div
            class="title font-weight-regular darkgrey--text"
          >&nbsp;En esta pantalla puede clonarte tantas veces como lo desees. Tal vez sea el inicio de un ejército, o tal vez aparezcan más elementos en el listado del backend para los superadmins.</div>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-form ref="form">
            <v-text-field
              v-model="miniyos"
              :label="MiniYós"
              required
              background-color="grey lighten-3"
              outlined
              type="number"
              min="1"
              max="100"
            ></v-text-field>

            <div class="col-lg-12 text-center">
              <br />
              <v-btn
                style="width: 70%;"
                class="btn btn-outline-info d-inline"
                @click="send"
              >{{ $t("shared.enviar") }}</v-btn>
              <v-btn color="error" class @click="reset">{{ $t("shared.resetear") }}</v-btn>
              <br />
            </div>
          </v-form>
        </v-card-text>
      </v-card>
    </v-col>
    <!-- <v-col class="col-3 col"></v-col> -->
    <v-spacer></v-spacer>
  </v-row>
</template>






<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";
import { alertas } from "~/app_code/alertas";

const $ = require("jquery");


export default {
  components: {
    Logo,
    VuetifyLogo,
  },
  mounted: function () {},
  data: () => ({
    miniyos: 0
  }),

  methods: {
    clonarOvejitas(cantidad){
      $.ajax({
        type: "POST",
        url: "http://127.0.0.1:8090/users/ovejitasDolly",
        data: JSON.stringify(cantidad),
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
            Authorization: sessionStorage.getItem("token")
        },
        success: function (result) {
          alertas.exito("Clonación exitosa", "Se han creado " + cantidad +" clones a partir de tu perfil.")
        },
        error: function () {
         alertas.error("Se ha producido un error", "Se ha producido un error al tratar de clonar tu perfil");
        },
      });
    },
    send() {  
      this.clonarOvejitas(this.miniyos);
    },
  },
};
</script>