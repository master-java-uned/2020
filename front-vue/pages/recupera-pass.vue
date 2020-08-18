<template>
  <v-row
    :style="'padding: 50px 0 50px 0; height: 100; width: 100vw; background-image: url('+require('~/assets/img/COVID.jpg')+');'"
  >
    <!-- <v-col
      class="col-3 col"
    ></v-col>-->

    <v-spacer></v-spacer>
    <v-col class="col-md-6 col">
      <v-spacer></v-spacer>
      <v-card elevation="12" class="mx-1" style="background: #344e5e; color: white;">
        <v-spacer></v-spacer>
        <v-card-title>
          <div class="display-1 my-2">{{$t('recuperapass.titulo')}}</div>
          <div class="title font-weight-regular darkgrey--text">{{$t('recuperapass.tituloDesc')}}</div>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-form ref="form" v-model="valid" :lazy-validation="lazy">
            <v-text-field
              v-model="name"
              :rules="emailRules"
              :label="$t('shared.emailInput')"
              required
              background-color="grey lighten-3"
              outlined
            ></v-text-field>

            <div class="col-lg-12 text-center">
              <br />
              <v-btn
                :disabled="!valid"
                style="width: 70%;"
                class="btn btn-outline-info d-inline"
                @click="validate"
              >{{$t('shared.enviar')}}</v-btn>
              <v-btn color="error" class @click="reset">{{$t('shared.resetear')}}</v-btn>
              <br />
            </div>
          </v-form>
          <v-container center>
            <v-row class="col-12">
              <v-col class="col-md-6">
                <v-btn large class="text-center col-12">
                  <nuxt-link
                    class="text-center boton_down"
                    :to="localePath('login')"
                  >{{$t('shared.tienesCuenta')}}</nuxt-link>
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
    <!-- <v-col
      class="col-3 col"
    ></v-col>-->

    <v-spacer></v-spacer>
  </v-row>
</template>






<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";
import { alertas } from "~/app_code/alertas";

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
    validate() {
      if (this.$refs.form.validate()) {
        alertas.error(
          "¿Me regalas una cuenta de mail?",
          "Me da pereza desarrollar este punto... además, quieres que te mande un correo desde la cuala acuenta¿? a freír los pájaros..."
        );
      }
    },
    reset() {
      this.$refs.form.reset();
      return false;
    },
    resetValidation() {
      this.$refs.form.resetValidation();
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