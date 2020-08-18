<template>
  <v-form ref="form" v-model="valid" :lazy-validation="lazy">

    <v-container>
      <v-row>
        <v-col class="d-none col-md-6 ma-0 pa-5 mt-0 d-md-inline-flex flex-row">
          <v-row class="col-12">
            <div class="card-shadow text-center pa-0 ma-0">
              <img
                class="col-12 img-fluid ma-0 pa-0"
                style="width: 100%; box-shadow: 1px 1px 20px black; filter: grayscale(1);"
                :src="require('~/assets/img/telefonista.jpg')"
              />
            </div>
            <v-row class="col-12 text-center d-block ma-0 mt-5 pa-0">
              <v-card elevation="12" row class="rounded row col-12 d-block ma-0 pa-0">
                <div class="row col-12 d-block">
                  <div class="card-body d-flex align-items-center c-detail col-12">
                    <div class="align-self-center col-6">
                      <!-- <img height="50" width="50" :src="require('~/assets/img/sitio.png')" /> -->
                      <v-icon style="font-size: 50px; color: white;">mdi-domain</v-icon>
                    </div>
                    <div class="col-6">
                      <h6 class="font-weight-medium">{{ $t("contacto.direccion") }}</h6>
                      <p class>
                        1600 Amphitheatre Parkway
                        <br />Santa Clara
                      </p>
                    </div>
                  </div>
                </div>
              </v-card>
              <v-card elevation="12" row class="row col-12 d-block ma-0 mt-5 pa-0">
                <div class="row col-12 d-block">
                  <div class="card-body d-flex align-items-center c-detail col-12">
                    <div class="align-self-center col-6">
                      <!-- <img height="50" width="50" :src="require('~/assets/img/telefono.svg')" /> -->
                      <v-icon style="font-size: 50px; color: white;">mdi-phone</v-icon>
                    </div>
                    <div class="col-6">
                      <h6 class="font-weight-medium">{{ $t("contacto.telf") }}</h6>
                      <p class>
                        Att. Larry Page
                        <br />917 486 400
                      </p>
                    </div>
                  </div>
                </div>
              </v-card>
              <v-card elevation="12" row class="row col-12 d-block ma-0 mt-5 pa-0">
                <div class="row col-12">
                  <div class="card-body d-flex align-items-center c-detail col-12">
                    <div class="align-self-center col-6">
                      <!-- <img height="50" width="50" :src="require('assets/img/correo.svg')" /> -->
                      <v-icon style="font-size: 50px; color: white;">mdi-email</v-icon>
                    </div>
                    <div class="col-6">
                      <h6 class="font-weight-medium">{{ $t("contacto.correo") }}</h6>
                      <p class>
                        admin@google.biz
                        <br />
                        {{ $t("contacto.insista") }}
                      </p>
                    </div>
                  </div>
                </div>
              </v-card>
            </v-row>
          </v-row>
        </v-col>
        <v-col class="align-center col-md-6 ma-0 pa-5 mt-0 d-md-inline-flex flex-row">
          <v-card elevation="12" class="pa-5 d-block">
            <div class="contact-box">
              <h2 class="font-weight-light mt-2 text-center">{{ $t("contacto.contacto") }}</h2>

              <v-row class="row mt-5">
                <div class="col-lg-12">
                  <div class="form-group mt-0">
                    <v-text-field
                      formControlName="Nombre"
                      class="col-12 float-left form-control form-control-user rounded-lg"
                      type="text"
                      id="Nombre"
                      aria-describedby="Nombre"
                      :placeholder="$t('shared.nombreInput')"
                      name="Nombre"
                      background-color="grey lighten-3"
                      :rules="noemptyRules"
                      required
                      outlined
                    />
                  </div>
                </div>
                <div class="col-lg-12">
                  <div class="form-group mt-0">
                    <v-text-field
                      formControlName="Email"
                      name="email"
                      class="col-12 float-left form-control form-control-user rounded-lg"
                      type="email"
                      id="Email"
                      :rules="emailRules"
                      aria-describedby="Email"
                      background-color="grey lighten-3"
                      :placeholder="$t('shared.emailInput')"
                      required
                      outlined
                    />
                  </div>
                </div>
                <div class="col-lg-12">
                  <div class="form-group mt-0">
                    <v-text-field
                      formControlName="Telefono"
                      name="Telefono"
                      class="col-12 float-left form-control form-control-user rounded-lg"
                      type="text"
                      id="Telefono"
                      :rules="noemptyRules"
                      aria-describedby="Telefono"
                      :placeholder="$t('shared.telefonoInput')"
                      background-color="grey lighten-3"
                      required
                      outlined
                    />
                  </div>
                </div>
                <div class="col-lg-12">
                  <div class="form-group mt-0">
                    <v-textarea
                      outlined
                      :rules="noemptyRules"
                      class="rounded-lg"
                      solo
                      name="input-7-4"
                      :label="$t('shared.mensajeInput')"
                      required
                      background-color="grey lighten-3"
                    ></v-textarea>
                  </div>
                </div>
                <div class="col-lg-12 text-center">
                  <v-btn
                    :disabled="!valid"
                    style="width: 70%;"
                    class="btn btn-outline-info d-inline"
                    @click="validate"
                  >{{$t('shared.enviar')}}</v-btn>
                  <v-btn color="error" class @click="reset">{{$t('shared.resetear')}}</v-btn>
                </div>
              </v-row>
            </div>
          </v-card>
        </v-col>
      </v-row>
      <input type="hidden" id="nombreObligatorio" :value="$t('validaciones.nombreObligatorio')" />
      <input
        type="hidden"
        id="nombreMin10Caracteres"
        :value="$t('validaciones.nombreMin10Caracteres')"
      />
      
    </v-container>
  </v-form>
</template>


<script>
import Logo from "~/components/Logo.vue";
import VuetifyLogo from "~/components/VuetifyLogo.vue";
import ValMessage from "~/components/nyapaMensajesValidacion.vue";

function getValidacionTraduccion(id) {
  var el = document.getElementById(id);
  if(el != undefined && el != null)
  {
    return el.value;
  }
}

export default {
  components: {
    Logo,
    VuetifyLogo,
    ValMessage
  },
  data: () => ({
    valid: true,
    name: "",
    nameRules: [
      (v) => !!v || getValidacionTraduccion("nombreObligatorio"),
      (v) =>
        (v && v.length <= 10) ||
        getValidacionTraduccion("nombreMin10Caracteres"),
    ],
    email: "",
    emailRules: [
      (v) => 
        !!v || getValidacionTraduccion("mailObligatorio"),
      (v) => /.+@.+\..+/.test(v) || getValidacionTraduccion("mailValido"),
    ],
    noemptyRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    select: null,
    items: ["Item 1", "Item 2", "Item 3", "Item 4"],
    checkbox: false,
    lazy: false,
  }),

  methods: {
    validate() {
      this.$refs.form.validate();
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
.v-card,
.bloquecontacto {
  background: #344e5e;
  color: white;
}

.v-input *,
input,
::placeholder {
  color: white !important;
}
</style>