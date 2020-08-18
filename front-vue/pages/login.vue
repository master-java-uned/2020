<template>
  <v-row
    :style="'padding: 50px 0 50px 0; height: 100; width: 100vw; background-image: url('+require('~/assets/img/COVID.jpg')+');'"
  >
    <!-- <v-col class="col col-md-3"></v-col> -->
    <v-spacer></v-spacer>
    <v-col class="col-12 col-md-6 col">
      <v-spacer></v-spacer>
      <v-card elevation="12" class="mx-1" style="background: #344e5e; color: white;">
        <v-spacer></v-spacer>
        <v-card-title>
          <div class="display-1 my-2">{{ $t("login.titulo") }}</div>
          <div class="title font-weight-regular darkgrey--text">&nbsp;{{ $t("login.credenciales") }}</div>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-form ref="form" v-model="valid" :lazy-validation="lazy">
            <v-text-field
              v-model="name"
              :rules="emailRules"
              :label="$t('shared.nombreUsuario')"
              required
              background-color="grey lighten-3"
              outlined
            ></v-text-field>

            <v-text-field
                  v-model="password"
                  :counter="10"
                  :rules="passRules"
                  :label="$t('shared.contrasenya')"
                  :placeholder="$t('shared.contrasenya')"
                  background-color="grey lighten-3"
                  outlined
                  required
                  id="passToCompare"
                  :append-icon="!show1? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  @click:append="show1 = !show1"
                ></v-text-field>&nbsp;

            <div class="col-lg-12 text-center">
              <br />
              <v-btn
                :disabled="!valid"
                style="width: 70%;"
                class="btn btn-outline-info d-inline"
                @click="submit"
              >{{ $t("shared.enviar") }}</v-btn>
              <v-btn color="error" class @click="reset">{{ $t("shared.resetear") }}</v-btn>
              <br />
            </div>
          </v-form>
          <v-container center>
            <v-row class="col-12">
              <v-col class="col-md-6">
                <v-btn large class="text-center col-12">
                  <nuxt-link
                    class="text-center boton_down"
                    :to="localePath('recupera-pass')"
                  >{{ $t("shared.recuperaPass") }}</nuxt-link>
                </v-btn>
              </v-col>
              <v-spacer></v-spacer>
              <v-col class="col-md-6">
                <v-btn large color class="text-center col-12">
                  <nuxt-link
                    class="text-center boton_down"
                    :to="localePath('registro')"
                  >{{ $t("shared.creaCuenta") }}</nuxt-link>
                </v-btn>
              </v-col>
            </v-row>
          </v-container>
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


function getValidacionTraduccion(id) {
  var el = document.getElementById(id);
  if (el != undefined && el != null) {
    return el.value;
  }
}

export default {
  components: {
    Logo,
    VuetifyLogo,
  },
  mounted: function () {},
  data: () => ({
    show1: false,
    show2: false,
    valid: true,
    name: "",
    password: "",
    passRules: [
      (v) => !!v || getValidacionTraduccion("obligatorio"),
      (v) => (v && v.length <= 10) || getValidacionTraduccion("passMin10"),
    ],
    email: "",
    emailRules: [
      (v) => !!v || getValidacionTraduccion("obligatorio"),
      (v) => /.+@.+\..+/.test(v) || getValidacionTraduccion("mailValido"),
    ],
    noemptyRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    select: null,
    checkbox: false,
    lazy: false,
  }),

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.checkLogin();
      }
    },
    reset() {
      this.$refs.form.reset();
      return false;
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    checkLogin() {
      //Encripto el password
      var bcrypt = require("bcryptjs");
      const salt = bcrypt.genSaltSync(10);
      var pass = bcrypt.hashSync(this.password, salt);
      //this.password = pass;

      $.ajax({
        type: "POST",
        url: "http://127.0.0.1:8090/users/login",
        data: JSON.stringify({
          username: this.name,
          password: this.password,
        }),
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        success: function (result) {
          /**
           * metemos en la sesión del navegador el tokata y el username.
           */
          sessionStorage.setItem("username", this.name);
          let tokenStr = "Bearer " + result.token;
          sessionStorage.setItem("token", tokenStr);

          let rolId = result.rolId;
          sessionStorage.setItem("rolId", rolId);

          //this.router.navigate(["/dash"]);
          alertas.exito(getValidacionTraduccion("exito"), getValidacionTraduccion("exitoAutenticado"), "/dash");
          this.invalidLogin = false;
        },
        error: function () {
          this.invalidLogin = true;
          alertas.error(
            getValidacionTraduccion("error"),
            getValidacionTraduccion("errorRevisa")
          );
        },
      });
    },
  },
};
</script>
<style scoped>
.boton_down {
  text-decoration: none;
  color: black;
}

.v-input *,
input,
::placeholder {
  color: white !important;
}

.v-label {
  margin-top: 10px;
}
</style>