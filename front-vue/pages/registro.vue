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
          <div class="display-1 my-2">{{$t('registro.titulo')}}</div>
          <div class="title font-weight-regular darkgrey--text">&nbsp;{{$t('registro.tituloDesc')}}</div>
        </v-card-title>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-form ref="form" v-model="valid" :lazy-validation="lazy">
            <v-row class="d-block text-center">
              <v-img
                style="border-radius: 50%; max-height: 200px; max-width: 200px; display: inline-block;"
                :src="filePreview"
              />
            </v-row>
            <v-row class="mt-0 pd-5">
              <v-file-input
                type="file"
                filled
                cleareable="true"
                prepend-icon="mdi-camera"
                class="btn btn-outline-info"
                name="image"
                dark
                required
                outlined
                :placeholder="$t('registro.imagen')"
                @change="onFileChanged($event)"
                style="padding: 1em;"
                v-model="imagen"
                :rules="imagenRules"
              />
            </v-row>
            <div class="form-group row text-center">
              <div class="col-12 d-inline">
                <v-text-field
                  v-model="email"
                  :rules="emailRules"
                  :label="$t('shared.emailInput')"
                  required
                  background-color="grey lighten-3"
                  outlined
                ></v-text-field>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <v-text-field
                  v-model="nombre"
                  :rules="nombreRules"
                  :label="$t('shared.nombreInput')"
                  required
                  background-color="grey lighten-3"
                  outlined
                ></v-text-field>&nbsp;
              </div>
              <div class="col-sm-6">
                <v-text-field
                  v-model="apellidos"
                  :rules="apellidosRules"
                  :label="$t('shared.apellidosInput')"
                  required
                  background-color="grey lighten-3"
                  outlined
                ></v-text-field>&nbsp;
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <v-text-field
                  v-model="pass"
                  :counter="10"
                  :rules="passRules"
                  :placeholder="$t('shared.contrasenya')"
                  background-color="grey lighten-3"
                  outlined
                  required
                  id="passToCompare"
                  :append-icon="!show1? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  @click:append="show1 = !show1"
                ></v-text-field>&nbsp;
              </div>
              <div class="col-sm-6">
                <v-text-field
                  v-model="passRep"
                  :counter="10"
                  :rules="passRepRules"
                  :placeholder="$t('shared.contrasenya2')"
                  background-color="grey lighten-3"
                  outlined
                  required
                  :append-icon="!show2 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show2 ? 'text' : 'password'"
                  @click:append="show2 = !show2"
                ></v-text-field>&nbsp;
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
          </v-form>
          <v-container center>
            <v-row class="col-12">
              <v-col class="col-md-6">
                <v-btn large class="text-center col-12">
                  <nuxt-link
                    class="text-center boton_down"
                    :to="localePath('recupera-pass')"
                  >{{$t('shared.recuperaPass')}}</nuxt-link>
                </v-btn>
              </v-col>
              <v-spacer></v-spacer>
              <v-col class="col-md-6">
                <v-btn large color class="text-center col-12">
                  <nuxt-link
                    class="text-center boton_down"
                    :to="localePath('login')"
                  >{{$t('shared.tienesCuenta')}}</nuxt-link>
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
import { UserModel } from "~/app_code/user.model";

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
    // para la gestión de la imagen
    filePreview: "",
    fileName: "",
    cargandoFichero: "",
    name: "",
    passRep: "",
    passRules: [
      (v) => !!v || getValidacionTraduccion("obligatorio"),
      (v) => (v && v.length <= 10) || getValidacionTraduccion("passMin10"),
      (v) => (v && v.length > 5) || getValidacionTraduccion("passMin5"),
    ],
    imagenRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    passRepRules: [
      (v) => !!v || getValidacionTraduccion("obligatorio"),
      (v) =>
        (document.getElementById("passToCompare") != null &&
          v == document.getElementById("passToCompare").value) ||
        "Los passwords no coinciden",
    ],
    email: "",
    imagen: "",
    nombre: "",
    apellidos: "",
    pass: "",
    emailRules: [
      (v) => !!v || getValidacionTraduccion("obligatorio"),
      (v) => /.+@.+\..+/.test(v) || getValidacionTraduccion("mailValido"),
    ],
    apellidosRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    nombreRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    noemptyRules: [(v) => !!v || getValidacionTraduccion("obligatorio")],
    select: null,
    checkbox: false,
    lazy: false,
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.checkRegistro();
      }
    },
    reset() {
      this.$refs.form.reset();
      return false;
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    onFileChanged(foto) {
      this.cargandoFichero = true;
      let reader = new FileReader();
      if (foto != undefined && foto != null) {
        let file = foto;
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.fileName = file.name + " " + file.type;
          this.filePreview =
            "data:image/png" + ";base64," + reader.result.split(",")[1];
        };
      }
    },
    checkRegistro() {
      /**
       * Mapeo al user
       */
      var form = new UserModel(this.email, this.pass, this.nombre, this.apellidos, this.filePreview);
     
      $.ajax({
        type: "POST",
        url: "http://127.0.0.1:8090/users/register",
        data: JSON.stringify(form),
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        success: function (result) {
          if (
            result.listadoErrores != undefined &&
            result.listadoErrores != null &&
            result.listadoErrores.length > 0
          ) {
            this.errores = [];
            for (var i = 0; i < result.listadoErrores.length; i++) {
              var err = result.listadoErrores[i];
              //TODO: De momento sólo muestro errores en español, aunque tengo el resto
              this.errores.push(err.listadoErrores[0].mensajeError); //[0] primer idioma, español
            }
            alertas.error(
              "Se ha producido un error",
              "Por favor revisa el listado de mensajes de error"
            );
          } else {
            //Registro ok, redirijo y muestro mensaje
            // this.router.navigate(["/login"]);
            
            alertas.exito(
              "Registrado",
              "El registro se ha procesado correctamente",
              "/login"
            );
          }
        },
        error: function () {
          alertas.error("Error", "Se ha producido un error inesperado");
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